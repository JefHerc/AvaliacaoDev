package br.com.soc.sistema.vo;

import java.time.LocalDate;

public class AgendamentoVo {
	
	private Integer rowid;
	private FuncionarioVo funcionario;
	private ExameVo exame;
	private LocalDate dataAgendamento;
	
	public AgendamentoVo() {}

	public AgendamentoVo(Integer rowid, FuncionarioVo funcionario, ExameVo exame, LocalDate dataAgendamento) {
		super();
		this.rowid = rowid;
		this.funcionario = funcionario;
		this.exame = exame;
		this.dataAgendamento = dataAgendamento;
	}

	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	public FuncionarioVo getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioVo funcionario) {
		this.funcionario = funcionario;
	}

	public ExameVo getExame() {
		return exame;
	}

	public void setExame(ExameVo exame) {
		this.exame = exame;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	@Override
	public String toString() {
		return "AgendamentoVo [rowid=" + rowid + ", funcionario=" + funcionario + ", exame=" + exame
				+ ", dataAgendamento=" + dataAgendamento + "]";
	}
}
