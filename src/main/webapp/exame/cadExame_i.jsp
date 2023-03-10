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
			<s:form id="form-exame" action="#">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosExames" var="todos"/>
								<a href="${todos}" class="btn" >
									<img width="40px" alt="voltar" src="imagens/voltar.png">
								</a>
							</div>
							
							<div class="d-flex align-items-center col-sm">
								<h5 class="card-title"></h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								<s:text name="label.id"></s:text>
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="exameVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-1 col-form-label text-center">
								<s:text name="label.nome"></s:text>
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="exameVo.nome"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn col-sm-1"><img width="40px" alt="salvar" src="imagens/salvar.png"></button>
							<button id="btn-limpar" type="button" onclick="limpar()" class="btn col-sm-1"><img width="40px" alt="limpar" src="imagens/limpar.png"></button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="javaScript/jquery.js"></script>
		<script type="text/javascript" src="javaScript/main.js"></script>
		<script type="text/javascript">
		$(function() {
			var exameRowid = "<s:property value ='exameVo.rowid' />";

			if (exameRowid == "") {
				$("#form-exame").attr("action", "/avaliacao/novoExames.action");
				$("title").text("<s:text name='label.titulo.pagina.exame.cadastro' />");
				$(".card-title").text("Novo Exame");
			} else {
				$("#form-exame").attr('action', '/avaliacao/alterarExames.action');
				$("title").text("<s:text name='label.titulo.pagina.exame.alteracao' />");
				$(".card-title").text("Editar Exame");
			}
		})
		</script>
		
	</body>
</html>