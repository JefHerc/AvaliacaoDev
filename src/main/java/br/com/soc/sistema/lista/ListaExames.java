package br.com.soc.sistema.lista;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.soc.sistema.vo.ExameVo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaExames {

	@XmlElement(name = "Exame")
	private List<ExameVo> exames;

	public ListaExames() {
	}

	public ListaExames(List<ExameVo> exames) {
		super();
		this.exames = exames;
	}

	public List<ExameVo> getExames() {
		return exames;
	}
	
}
