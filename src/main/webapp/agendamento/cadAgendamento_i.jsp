<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
	<jsp:include page="../navbar.html" />

		<div class="container">
		
			<s:form id="form-agendamento" action="#">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosAgendamentos" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Agendamentos</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title"></h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								Código:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="agendamentoVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="funcionario" class="col-sm-1 col-form-label text-center">
								Funcionário:
							</label>	

							<div class="col-sm-5">
								
								<s:select  
 									cssClass="form-select"  
									name="filtrar.opcoesCombo"  
									list="funcionarios"   
									headerKey="%{agendamentoVo.funcionario.rowid}"   
									headerValue="%{agendamentoVo.funcionario.nome}"  
									listKey="%{rowid}" 
 									listValueKey="%{nome}"
								/>	
							</div>	
						</div>
						<div class="row align-items-center mt-3">
							<label for="exame" class="col-sm-1 col-form-label text-center">
								Exame:
							</label>	

							<div class="col-sm-5">
								<s:select  
 									cssClass="form-select"  
									name="filtrar.opcoesCombo"  
									list="exames"   
									headerKey="%{agendamentoVo.exame.rowid}"   
									headerValue="%{agendamentoVo.exame.nome}"  
									listKey="%{rowid}" 
 									listValueKey="%{nome}"
								/>								
						</div>
						<div class="row align-items-center mt-3">
							<label for="data-agendamento" class="col-sm-1 col-form-label text-center">
								Data:
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="data-agendamento" name="agendamentoVo.dataAgendamento"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button id="btn-limpar" type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="javaScript/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				var agendamentoRowid = "<s:property value ='agendamentoVo.rowid' />";
				if(exameRowid === "") {
					console.log('agendamentoRowid vazio')
					$("#form-agendamento").attr("action", "/avaliacao/novoAgendamentos.action");
					$("title").text("<s:text name='label.titulo.pagina.cadastro' />");
					$(".card-title").text("Novo Agendamento");
				} else {
					$("#form-agendamento").attr('action', '/avaliacao/alterarAgendamentos.action');
					$("title").text("<s:text name='label.titulo.pagina.alteracao' />");
					$(".card-title").text("Editar Agendamento");
				}
			})
		</script>
		
	</body>
</html>