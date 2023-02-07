<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../mensagem.jsp" />

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>
		<s:text name="label.titulo.pagina.consulta" />
	</title>
	<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>

<body class="bg-secondary" onload="exibirMensagem()">
	<jsp:include page="../navbar.html" />

	<s:form action="/xlsRelatorios.action">
		<s:text name="">Data Inicio</s:text>
		<s:textfield type="text" name="dataInicio" data-mask="00/00/0000" placeholder="__/__/____" />
		<s:text name="">Data Fim</s:text>
		<s:textfield type="text" name="dataFim" data-mask="00/00/0000" placeholder="__/__/____" />
		<button class="btn btn-primary" type="submit">
			<s:text name="label.pesquisar" />
		</button>
	</s:form>

	<s:form action="/indicadorRelatorios.action">
		<s:text name="">Data Inicio</s:text>
		<s:textfield type="date" name="dataInicio"></s:textfield>
		<s:text name="">Data Fim</s:text>
		<s:textfield type="date" name="dataFim"></s:textfield>
		<button class="btn btn-primary" type="submit">
			<s:text name="label.pesquisar" />
		</button>
	</s:form>

	<div>
		<img alt="" src="imagens/chart.png" >
	</div>
	<script src="javaScript/jquery.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script src="javaScript/main.js"></script>
	
	<script type="text/javascript">
		function exibirMensagem() {
			var msg = mensagem;
			if(msg != "")
			alert(msg); }

	</script>
</body>

</html>