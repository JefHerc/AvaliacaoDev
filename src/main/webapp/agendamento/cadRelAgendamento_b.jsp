<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.consulta" /></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
		<jsp:include page="../navbar.html" />
	
	<s:form action="/gerarRelatorios.action">
		<s:text name="">Data Inicio</s:text>
		<s:textfield type="date" name="dataInicio"></s:textfield>
		<s:text name="">Data Fim</s:text>
		<s:textfield type="date" name="dataFim"></s:textfield>
		<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button> 
	</s:form>
		<h1>RELATÓRIO</h1>
	<div class="container">
		<div class="row">
			<table class="table table-light table-striped align-middle">
				<thead>
					<tr>
						<th><s:text name="label.id" /></th>
						<th><s:text name="label.funcionario" /></th>
						<th><s:text name="label.id" /></th>
						<th><s:text name="label.exame" /></th>
						<th><s:text name="label.data" /></th>
					</tr>
				</thead>
	
				<tbody>
					<s:iterator value="agendamentos">
						<tr>
							<td>${funcionario.rowid}</td>
							<td>${funcionario.nome}</td>
							<td>${exame.rowid}</td>
							<td>${exame.nome}</td>
							<td>${dataFormatada}</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		</div>
	
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="javaScript/jquery.js"></script>
	</body>
</html>