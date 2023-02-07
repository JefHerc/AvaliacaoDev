<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../mensagem.jsp" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary" onload="exibirMensagem()">
	<jsp:include page="../navbar.html" />

		<div class="container">
		
			<s:form id="form-funcionario" action="#">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosFuncionarios" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Funcion�rios</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title"></h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								C�digo:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="funcionarioVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-1 col-form-label text-center">
								Nome:
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="funcionarioVo.nome"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button id="btn-limpar" type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formul�rio</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="javaScript/jquery.js"></script>
		<script src="javaScript/main.js"></script>
		<script type="text/javascript">
		$(function() {
			var funcionarioRowid = "<s:property value ='funcionarioVo.rowid' />";
			if (funcionarioRowid === "") {
				$("#form-funcionario").attr("action", "/avaliacao/novoFuncionarios.action");
				$("title").text("<s:text name='label.titulo.pagina.cadastro' />");
				$(".card-title").text("Novo Funcionario");
			} else {
				$("#form-funcionario").attr('action', '/avaliacao/alterarFuncionarios.action');
				$("title").text("<s:text name='label.titulo.pagina.alteracao' />");
				$(".card-title").text("Editar Funcionario");
			}
		})
	</script>
	</body>
</html>