<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.login"></s:text></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
		<div class="mt-5 d-flex text-center">
			<div class="card mx-auto">
				<div class="card-header">
					<h4 class="card-title">Login</h4>
				</div>
				<div class="card-body">
					<s:form action="loginAplicacao">
						<div class="row mb-3 mx-auto">
							<s:textfield cssClass="form-control" id="user" name="login.user" placeholder="Usuário" />
						</div>
						<div class="row mb-3 mx-auto">
							<s:password cssClass="form-control" id="password" name="login.password" placeholder="Senha"/>
						</div>
						<button class="btn btn-primary" type="submit"><s:text name="label.corpo.pagina.entrar"></s:text></button>
					</s:form>
				</div>
			</div>
		</div>
	</body>
</html>