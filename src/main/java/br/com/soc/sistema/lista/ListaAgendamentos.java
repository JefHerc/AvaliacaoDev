package br.com.soc.sistema.lista;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.soc.sistema.vo.AgendamentoVo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaAgendamentos {

	@XmlElement(name = "agendamento")
	private List<AgendamentoVo> agendamentos;

	public ListaAgendamentos(List<AgendamentoVo> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public ListaAgendamentos() {
	}

	public List<AgendamentoVo> getAgendamentos() {
		return agendamentos;
	}
}
