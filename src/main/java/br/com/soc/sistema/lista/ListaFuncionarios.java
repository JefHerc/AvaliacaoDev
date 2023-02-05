package br.com.soc.sistema.lista;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.soc.sistema.vo.FuncionarioVo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaFuncionarios {

	@XmlElement(name = "funcionario")
	private List<FuncionarioVo> funcionarios;

	public ListaFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public ListaFuncionarios() {
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}
}
