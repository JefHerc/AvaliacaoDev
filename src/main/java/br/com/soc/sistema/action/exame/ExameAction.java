package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameVo;

public class ExameAction extends Action {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();
	public String mensagemErro;
	private HttpSession session = ServletActionContext.getRequest().getSession();

	public String todos() {
		if(!isUserAutenticado())
			return "loginError";

		exames.addAll(business.trazerTodosOsExames());
		return SUCCESS;
	}

	public String filtrar() {
		if(!isUserAutenticado())
			return "loginError";

		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		try {
			exames = business.filtrarExames(filtrar);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return REDIRECT;
		}

		return SUCCESS;
	}

	public String novo() {
		if(!isUserAutenticado())
			return "loginError";

		if (exameVo.getNome() == null)
			return INPUT;

		try {
			business.salvarExame(exameVo.getNome());
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return INPUT;
		}

		return REDIRECT;
	}

	public String editar() {
		if(!isUserAutenticado())
			return "loginError";

		if (exameVo.getRowid() == null)
			return REDIRECT;

		try {
			exameVo = business.buscarExamePorId(exameVo.getRowid());
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return REDIRECT;
		}

		return INPUT;
	}

	public String alterar() {
		if(!isUserAutenticado())
			return "loginError";

		try {
			business.alterarExame(exameVo);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		return REDIRECT;
	}

	public String deletar() {
		if(!isUserAutenticado())
			return "loginError";

		try {
			business.deletarExame(exameVo.getRowid());
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
		}	
		return REDIRECT;

	}

	public List<OpcoesComboBuscarExames> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}

	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
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
