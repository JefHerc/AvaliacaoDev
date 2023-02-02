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

		exames.addAll(business.trazerTodosOsExames());
		System.out.println(mensagemErro);
		return SUCCESS;
	}

	public String filtrar() {
		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		exames = business.filtrarExames(filtrar);

		return SUCCESS;
	}

	public String novo() {
		if (exameVo.getNome() == null)
			return INPUT;

		business.salvarExame(exameVo);

		return REDIRECT;
	}

	public String editar() {
		if (exameVo.getRowid() == null)
			return REDIRECT;

		exameVo = business.buscarExamePor(exameVo.getRowid());

		return INPUT;
	}

	public String alterar() {
		try {
			business.alterarExame(exameVo);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
			e.printStackTrace();
			return INPUT;
		}
		return REDIRECT;
	}

	public String deletar() {
		try {
			business.deletarExame(exameVo.getRowid());
		} catch (Exception e) {
			mensagemErro = e.getMessage();
			session.setAttribute("mensagem", mensagemErro);
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
	
}
