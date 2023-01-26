package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarAgendamentos;

public class AgendamentoFilter {
	private OpcoesComboBuscarAgendamentos opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public AgendamentoFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarAgendamentos getOpcoesCombo() {
		return opcoesCombo;
	}

	public AgendamentoFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarAgendamentos.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static AgendamentoFilter builder() {
		return new AgendamentoFilter();
	}
}
