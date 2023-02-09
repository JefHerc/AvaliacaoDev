function sendRelatorio(acao) {
    let emitir = validarEmissaoRelatorio();
    if (!emitir) {
		alert('Verifique as datas inseridas.');
    } else {
	    let form = $("#data-form");
        form.attr("action", acao);
        form.attr("target", "_blank");
        form.submit();
    }
}

$('.to-check').on('input', function(){
    $(this).removeClass("is-invalid");    
});

function validarEmissaoRelatorio() {
    var inicio = $('#dataInicio').val();
    var fim = $('#dataFim').val();

    var dataInicio = Date.parse(inicio);
    var dataFim = Date.parse(fim);

    validarData(dataInicio, dataFim)
    compararDatas(dataInicio, dataFim)

    var validar = $('.to-check').hasClass('is-invalid');
    if (validar) {
        return false;
    } else {
        return true;
    }
}

function compararDatas(data1, data2) {
    if (data1 > data2) {
        $('#dataInicio').addClass("is-invalid");
    }
}

function validarData(data1, data2) {
    if (isNaN(data1))
        $('#dataInicio').addClass("is-invalid");

    if (isNaN(data2))
        $('#dataFim').addClass("is-invalid");
}