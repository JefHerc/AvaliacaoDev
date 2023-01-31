<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.consulta" /></title>
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
						<div class="row mb-3">
							<label for="user" class="col-sm-4 col-form-label">Usuário:</label>
							<div class="col-sm-8">
								<s:textfield cssClass="form-control" id="user" name="login.user" />
							</div>
						</div>
						<div class="row mb-3">
							<label for="password" class="col-sm-4 col-form-label">Senha:</label>
							<div class="col-sm-8">
								<s:password cssClass="form-control" id="password" name="login.password" />
							</div>
						</div>
						<button class="btn btn-primary" type="submit">Entrar</button>
					</s:form>
				</div>
			</div>
		</div>
	</body>
</html>