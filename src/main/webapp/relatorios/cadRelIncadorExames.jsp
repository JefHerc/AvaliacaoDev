<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.indicadores" /></title>
		<link rel='stylesheet'href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	
	<body>
		<div class="container">
			<h4 class="text-center mt-3"><s:text name="label.relatorio.titulo.corpo"></s:text></h4>
			<h5 class="text-center"><s:text name="label.relatorio.subtitulo.corpo"></s:text></h5>
			<div class="d-flex flex-row">
				<s:text name="label.corpo.de"></s:text>
				<label class="mr-2 fw-bold"><s:property value="dataInicio" /> </label> 
			</div>
			<div class="d-flex flex-row">
				<s:text name="label.corpo.ate"></s:text>
				<label class="mr-2 fw-bold"><s:property value="dataFim" /> </label>
			</div>
			<table class="table table-light table-striped align-middle">
				<thead>
					<tr>
						<th><s:text name="label.id" /></th>
						<th><s:text name="label.exame" /></th>
						<th><s:text name="label.indicadores.total" /></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="indicadores">
						<tr>
							<td>${rowid}</td>
							<td>${nomeExame}</td>
							<td>${qtdExame}</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	
	</body>

</html>