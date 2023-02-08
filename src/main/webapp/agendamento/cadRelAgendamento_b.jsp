<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../mensagem.jsp" />
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title><s:text name="label.titulo.pagina.login"></s:text></title>
	<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	<script src='https://cdn.plot.ly/plotly-2.18.0.min.js'></script>
</head>

<body class="bg-secondary" onload="exibirMensagem()">
	<jsp:include page="../navbar.html" />
	<div class="mt-3">
		<s:form id="data-form" action="#">
			<s:text name="">Data Inicio</s:text>
			<s:textfield id="dataInicio" type="text" name="dataInicio" data-mask="00/00/0000" placeholder="__/__/____" />
			<s:text name="">Data Fim</s:text>
			<s:textfield id="dataFim" type="text" name="dataFim" data-mask="00/00/0000" placeholder="__/__/____" />
			<button class="btn btn-primary" onclick="sendXls('xlsRelatorios.action')">XLS</button>
			<button class="btn btn-primary"	onclick="sendIndicador('indicadorRelatorios.action')">Indicador</button>
		</s:form>
	</div>

	<div hidden="true" id='indicador' class="d-flex align-items-center justify-content-center"></div>
	
	<script src="javaScript/jquery.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script src="javaScript/main.js"></script>
	<script type="text/javascript">
		function sendXls(acao) {
			$("#data-form").attr("action", acao);
			$("#data-form").submit();
		}

		function sendIndicador(acao) {
			$("#data-form").attr("action", acao);
			$("#data-form").submit();
			$("#indicador").attr("hidden", false);
			var data = [ {
				values : [ 19, 26, 55 ],
				labels : [ 'Residential', 'Non-Residential', 'Utility' ],
				type : 'pie'
			} ];

			var layout = {
				height : 400,
				width : 500
			};

			Plotly.newPlot('indicador', data, layout);
		}
	</script>
</body>

</html>