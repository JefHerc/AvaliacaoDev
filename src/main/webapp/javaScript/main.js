$('#confirmarExclusao').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget)
	var agendamentoId = button.data('id')
	$('#excluir').attr("href", agendamentoId);
});

function exibirMensagem() {
	var msg = mensagem;
	if (msg != "")
		alert(msg);
}