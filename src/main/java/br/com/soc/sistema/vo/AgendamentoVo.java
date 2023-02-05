package br.com.soc.sistema.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

import br.com.soc.sistema.util.DateAdpter;

@Conversion
@XmlAccessorType(XmlAccessType.FIELD)
public class AgendamentoVo {
	
	private Integer rowid;
	private FuncionarioVo funcionario;
	private ExameVo exame;
	
	@XmlJavaTypeAdapter(DateAdpter.class)
	private LocalDate dataAgendamento;
	
	public AgendamentoVo() {}

	public AgendamentoVo(Integer rowid, FuncionarioVo funcionario, ExameVo exame, LocalDate dataAgendamento) {
		super();
		this.rowid = rowid;
		this.funcionario = funcionario;
		this.exame = exame;
		this.dataAgendamento = dataAgendamento;
	}
	
	public AgendamentoVo(FuncionarioVo funcionario, ExameVo exame, LocalDate dataAgendamento) {
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

	@TypeConversion (converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}
	
	@TypeConversion (converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getDataFormatada() {
    	DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatador.format(getDataAgendamento());
    }
	
	@Override
	public String toString() {
		return "AgendamentoVo [rowid=" + rowid + ", funcionario=" + funcionario + ", exame=" + exame
				+ ", dataAgendamento=" + dataAgendamento + "]";
	}
}
