<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../mensagem.jsp" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.agendamento.consulta"></s:text></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
    </head>
	<body class="bg-secondary" onload="exibirMensagem()">
	<jsp:include page="../navbar.html" />

		<div class="container">
		
			<s:form id="form-agendamento" action="#">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosAgendamentos" var="todos"/>
								<a href="${todos}" class="btn" ><img width="40px" alt="voltar" src="imagens/voltar.png"></a>
							</div>
							
							<div class="d-flex align-items-center col-sm">
								<h5 class="card-title"></h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-2 col-form-label text-center">
								<s:text name="label.id"></s:text>
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="agendamentoVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="funcionario" class="col-sm-2 col-form-label text-center">
								<s:text name="label.funcionario"></s:text>
							</label>	

							<div class="col-sm-4">
								
								<s:select id="select-funcionarios"
 									cssClass="form-select"  
									name="agendamentoVo.funcionario.rowid"  
									list="funcionarios"   
									headerKey="%{agendamentoVo.funcionario.rowid}"   
									headerValue="%{agendamentoVo.funcionario.nome}"  
									listKey="%{rowid}" 
 									listValueKey="%{nome}"
								/>	
							</div>	
						</div>
						<div class="row align-items-center mt-3">
							<label for="exame" class="col-sm-2 col-form-label text-center">
								<s:text name="label.exame"></s:text>
							</label>	

							<div class="col-sm-4">
								<s:select  id="select-exames"
 									cssClass="form-select"  
									name="agendamentoVo.exame.rowid"  
									list="exames"   
									headerKey="%{agendamentoVo.exame.rowid}"   
									headerValue="%{agendamentoVo.exame.nome}"  
									listKey="%{rowid}" 
 									listValueKey="%{nome}"
								/>								
						</div>
						<div class="row align-items-center mt-3">
							<label for="data-agendamento" class="col-sm-2 col-form-label text-center">
								<s:text name="label.data"></s:text>
							</label>	

							<div class="col-sm-2">
								<s:textfield type="text" data-mask="00/00/0000" cssClass="form-control" id="data-agendada" name="agendamentoVo.dataAgendamento" placeholder="__/__/____"/>
							</div>	
						</div>
					</div>

					<div class="card-footer mt-3">
						<div class="form-row">
							<button class="btn col-sm-1"><img width="40px" alt="salvar" src="imagens/salvar.png"></button>
							<button id="btn-limpar" onclick="limpar()" type="button" class="btn col-sm-1"><img width="40px" alt="limpar" src="imagens/limpar.png"></button>
						</div>
					</div>
				</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="javaScript/jquery.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
		
		<script src="javaScript/main.js"></script>
		<script type="text/javascript">
		$(function() {
			var agendamentoRowid = "<s:property value ='agendamentoVo.rowid' />";
			if (agendamentoRowid == "") {
				$("#form-agendamento").attr("action", "/avaliacao/novoAgendamentos.action");
				$("title").text("<s:text name='label.titulo.pagina.agendamento.cadastro' />");
				$(".card-title").text("Novo Agendamento");
			} else {
				$("#form-agendamento").attr('action', '/avaliacao/alterarAgendamentos.action');
				$("title").text("<s:text name='label.titulo.pagina.agendamento.alteracao' />");
				$(".card-title").text("Editar Agendamento");
			}
		})
		function limpar(){
			$('#data-agendada').val('');
			let exame = document.querySelector("#select-funcionarios");
			exame.selectedIndex = -1;
			
			let func = 	document.querySelector("#select-exames");
			func.selectedIndex = -1;
		}
		</script>
	</body>
</html>