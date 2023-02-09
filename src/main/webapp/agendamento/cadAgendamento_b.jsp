<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../mensagem.jsp" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.agendamento.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary" onload="exibirMensagem()">
	<jsp:include page="../navbar.html" />
	
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					<s:form action="/filtrarAgendamentos.action">
						<div class="input-group">
							<span class="input-group-text">
								<strong><s:text name="label.buscar.por"/></strong>
							</span>	
								<s:select  
									cssClass="form-select" 
									name="filtrar.opcoesCombo" 
									list="listaOpcoesCombo"  
									headerKey=""  
									headerValue="Escolha..." 
									listKey="%{codigo}" 
									listValueKey="%{descricao}"
									value="filtrar.opcoesCombo.codigo"									
 								/> 
								
								<s:textfield type="text" cssClass="form-control" id="valor-busca" name="filtrar.valorBusca"/>
								<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button>
						</div>
					</s:form>			
				</div>				
			</div>

			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id"/></th>
							<th><s:text name="label.funcionario"/></th>
							<th><s:text name="label.exame"/></th>
							<th><s:text name="label.data"/></th>
							<th class="text-end mt-5"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="agendamentos" >
							<tr>
								<td>${rowid}</td>
								<td>${funcionario.nome}</td>
								<td>${exame.nome}</td>
								<td>${dataFormatada}</td>
								<td class="text-end">
									<s:url action="editarAgendamentos" var="editar">
										<s:param name="agendamentoVo.rowid" value="rowid"></s:param>
									</s:url>

									<a href="${editar}" class="btn">
										<img width="40px" alt="editar" src="imagens/editar.png">
									</a>
									<s:url action="deletarAgendamentos" var="deletar">
										<s:param name="agendamentoVo.rowid" value="rowid"></s:param>
									</s:url>				
																			
									<a href="#" data-id="${deletar}" class="btn" data-bs-toggle="modal" data-bs-target="#confirmarExclusao">
										<img width="40px" alt="deletar" src="imagens/deletar.png">
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						<tr>
							<td colspan="5">
								<s:url action="novoAgendamentos" var="novo"/>
								
								<a href="${novo}" class="btn">
									<img width="40px" alt="mais" src="imagens/mais.png">
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
					<s:text name="label.nao"/>
				</a>
	        	
				<s:a href="#" id="excluir" class="btn btn-primary" style="width: 75px;">
					<s:text name="label.sim"/>
				</s:a>						
		      </div>
		    </div>		    
		  </div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="javaScript/jquery.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
		<script src="javaScript/main.js"></script>
		<script type="text/javascript">
		$('#filtrarAgendamentos_filtrar_opcoesCombo').on('change', function(){
			if(this.value == '4') {
				$('#valor-busca').mask("00/00/0000", {placeholder: "__/__/____"});
			} else {
				$('#valor-busca').unmask();
			}
		})
		</script>
	</body>
</html>