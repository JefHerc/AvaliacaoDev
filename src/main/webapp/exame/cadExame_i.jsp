<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
	<jsp:include page="../navbar.html" />
		<div class="container">
			<s:form id="form-exame" action="#">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosExames" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Exames</a>
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
								<s:textfield cssClass="form-control" id="id" name="exameVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-1 col-form-label text-center">
								Nome:
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="exameVo.nome"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button id="btn-limpar" type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulário</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		 <label id="msg"><s:property value='mensagemErro'/></label>>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="javaScript/jquery.js"></script>
		<script type="text/javascript" charset="UTF-8">
			$(function(){
				var exameRowid = "<s:property value ='exameVo.rowid' />";
				if(exameRowid === "") {
					console.log('exameRowid vazio')
					$("#form-exame").attr("action", "/avaliacao/novoExames.action");
					$("title").text("<s:text name='label.titulo.pagina.cadastro' />");
					$(".card-title").text("Novo Exame");
				} else {
					$("#form-exame").attr('action', '/avaliacao/alterarExames.action');
					$("title").text("<s:text name='label.titulo.pagina.alteracao' />");
					$(".card-title").text("Editar Exame");
				}
				
				exibirMensagem();
			})
			
			function exibirMensagem() {
				
				var msg = $("#msg").text();
				if(msg != "")
				alert(msg);
			}
		</script>
		
	</body>
</html>