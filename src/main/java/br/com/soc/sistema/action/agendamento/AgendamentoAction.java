package br.com.soc.sistema.action.agendamento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import br.com.soc.sistema.business.AgendamentoBusiness;
import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.AgendamentoFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarAgendamentos;
import br.com.soc.sistema.vo.AgendamentoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class AgendamentoAction extends Action {

	private AgendamentoBusiness business = new AgendamentoBusiness();
	private ExameBusiness exameBusiness = new ExameBusiness();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	private List<AgendamentoVo> agendamentos = new ArrayList<>();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private List<ExameVo> exames = new ArrayList<>();
	private AgendamentoVo agendamentoVo = new AgendamentoVo();
	private AgendamentoFilter filtrar = new AgendamentoFilter();
	private HttpSession session = ServletActionContext.getRequest().getSession();

	public String todos() {
		if(!isUserAutenticado())
			return "loginError";
			
		agendamentos.addAll(business.trazerTodosOsAgendamentos());
		return SUCCESS;
	}

	public String novo() {
		if(!isUserAutenticado())
			return "loginError";
		
		if (agendamentoVo.getFuncionario() == null) {
			carregarListas();
			return INPUT;
		}

		try {
			business.salvarAgendamento(agendamentoVo);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			carregarListas();
			return INPUT;
		}

		return REDIRECT;
	}

	public String deletar() {
		if(!isUserAutenticado())
			return "loginError";
		
		business.deletarAgendamento(agendamentoVo.getRowid());
		return REDIRECT;
	}

	public String editar() {
		if(!isUserAutenticado())
			return "loginError";
		
		if (agendamentoVo.getRowid() == null)
			return REDIRECT;

		carregarListas();

		agendamentoVo = business.buscarAgendamentoPorId(agendamentoVo.getRowid().toString());

		return INPUT;
	}

	public String alterar() {
		if(!isUserAutenticado())
			return "loginError";
		
		try {
			business.alterarAgendamento(agendamentoVo);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			carregarListas();
			return INPUT;
		}
		return REDIRECT;
	}

	public String filtrar() {
		if(!isUserAutenticado())
			return "loginError";

		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		try {
			agendamentos = business.filtrarAgendamentos(filtrar);
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
			return REDIRECT;
		}

		return SUCCESS;
	}

	private void carregarListas() {
		funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
		exames.addAll(exameBusiness.trazerTodosOsExames());
	}

	public List<OpcoesComboBuscarAgendamentos> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarAgendamentos.values());
	}

	public AgendamentoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(AgendamentoFilter filtrar) {
		this.filtrar = filtrar;
	}

	public AgendamentoBusiness getBusiness() {
		return business;
	}

	public void setBusiness(AgendamentoBusiness business) {
		this.business = business;
	}

	public ExameBusiness getExameBusiness() {
		return exameBusiness;
	}

	public void setExameBusiness(ExameBusiness exameBusiness) {
		this.exameBusiness = exameBusiness;
	}

	public FuncionarioBusiness getFuncionarioBusiness() {
		return funcionarioBusiness;
	}

	public void setFuncionarioBusiness(FuncionarioBusiness funcionarioBusiness) {
		this.funcionarioBusiness = funcionarioBusiness;
	}

	public List<AgendamentoVo> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVo> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public AgendamentoVo getAgendamentoVo() {
		return agendamentoVo;
	}

	public void setAgendamentoVo(AgendamentoVo agendamentoVo) {
		this.agendamentoVo = agendamentoVo;
	}

}
