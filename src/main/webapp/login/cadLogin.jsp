<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../mensagem.jsp" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.login"></s:text></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary" onload="exibirMensagem()">
		<div class="d-flex justify-content-center mt-3">
			<div class="card w-25">
				<div class="card-header">
					<h4 class="card-title text-center"><s:text name="label.titulo.pagina.login"></s:text> </h4>
				</div>
				<div class="card-body">
					<s:form action="loginAplicacao" onsubmit="return validarLogin()">
						<div class="row mb-3">
							<s:textfield maxlength="100" cssClass="form-control to-check" id="user" name="login.user" placeholder="Usuário" />
							<div class="invalid-feedback">
								<s:text name="mensagem.preencher.usuario"></s:text>
							</div>
						</div>
						
						<div class="row mb-3">
							<s:password maxlength="100" cssClass="form-control to-check" id="password" name="login.password" placeholder="Senha"/>
							<div class="invalid-feedback">
								<s:text name="mensagem.preencher.senha"></s:text>
							</div>
						</div>
						<div class="d-flex justify-content-center mb-3">
							<button class="btn btn-primary" type="submit"><s:text name="label.corpo.pagina.entrar"></s:text></button>
						</div>
					</s:form>
				</div>
			</div>
		</div>
		<script src="javaScript/jquery.js"></script>
		<script src="javaScript/cadLogin.js"></script>
		<script src="javaScript/main.js"></script>
	</body>
</html>