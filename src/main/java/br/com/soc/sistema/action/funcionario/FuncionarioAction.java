package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action{

	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	public String mensagemErro;
	private HttpSession session = ServletActionContext.getRequest().getSession();

	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());	
		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		try {
			funcionarios = business.filtrarFuncionarios(filtrar);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return REDIRECT;
		}
		
		return SUCCESS;
	}
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		try {
			business.salvarFuncionario(funcionarioVo.getNome());
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		
		return REDIRECT;
	}
	
	public String editar() {
		if(funcionarioVo.getRowid() == null)
			return REDIRECT;

		funcionarioVo = business.buscarFuncionarioPorId(funcionarioVo.getRowid());
		
		return INPUT;
	}
	
	public String alterar() {
		try {
			business.alterarFuncionario(funcionarioVo);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		return REDIRECT;
	}
	
	public String deletar() {
		try {
			business.deletarFuncionario(funcionarioVo.getRowid());
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		return REDIRECT;
	}

	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> Funcionarios) {
		this.funcionarios = Funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo FuncionarioVo) {
		this.funcionarioVo = FuncionarioVo;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	private void exibirMensagemErro(String mensagemErro) {
		setMensagemErro(mensagemErro);
		session.setAttribute("mensagem", getMensagemErro());
	}

}
