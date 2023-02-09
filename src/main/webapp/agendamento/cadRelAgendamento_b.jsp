<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../mensagem.jsp" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.relatorio"></s:text></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	
	<body class="bg-secondary" onload="exibirMensagem()">
		<jsp:include page="../navbar.html" />
		<div class="d-flex justify-content-center mt-3">
			<div class="card w-25">
				<div class="card-header text-center">
					<h4 class="card-title"><s:text name="label.titulo.pagina.relatorio"></s:text></h4>
				</div>
				<div class="card-body">
					<s:form id="data-form" action="#" >
						<div class="row mb-3 mx-auto">
							<s:text name="label.corpo.data.inicio"></s:text>
							<s:textfield id="dataInicio" type="text" cssClass="form-control to-check" name="dataInicio" data-mask="00/00/0000" placeholder="__/__/____" />
							<div class="invalid-feedback">
								<s:text name="label.problema.data"></s:text>
							</div>
						</div>
						<div class="row mb-3 mx-auto">
							<s:text name="label.corpo.data.fim"></s:text>
							<s:textfield id="dataFim" type="text" cssClass="form-control to-check" name="dataFim" data-mask="00/00/0000" placeholder="__/__/____" />
							<div class="invalid-feedback">
								<s:text name="label.problema.data"></s:text>
							</div>
						</div>
						<div class="d-flex flex-row justify-content-center">
							<button type="button" class="btn p-3" onclick="sendRelatorio('xlsRelatorios.action')">
								<img width="45px" alt="xls" src="imagens/xls.png">
							</button>
							<button type="button" class="btn p-3" onclick="sendRelatorio('indicadorRelatorios.action')">
								<img width="45px" alt="indicador" src="imagens/grafico.png">
							</button>
						</div>
					</s:form>
				</div>
			</div>
		</div>
	
	
		<script src="javaScript/jquery.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
		<script src="javaScript/main.js"></script>
		<script src="javaScript/cadRelAgendamento_b.js">
			
		</script>
	</body>
</html>