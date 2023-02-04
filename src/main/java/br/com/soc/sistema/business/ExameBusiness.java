package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {

	private static final String INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID = "Informe um número para buscar por ID";
	private static final String NOME_NAO_PODE_SER_EM_BRANCO = "Nome não pode ser em branco";
	private ExameDao dao;
	private AgendamentoDao daoAgendamento;

	public ExameBusiness() {
		this.dao = new ExameDao();
		this.daoAgendamento = new AgendamentoDao();
	}

	public List<ExameVo> trazerTodosOsExames() {
		return dao.findAllExames();
	}

	public String salvarExame(ExameVo exameVo) {
		if (exameVo.getNome().isEmpty())
			throw new IllegalArgumentException(NOME_NAO_PODE_SER_EM_BRANCO);

		try {
			dao.insertExame(exameVo);
			return "Exame cadastrado";
		} catch (Exception e) {
			throw new BusinessException("Não foi possível salvar o exame");
		}

	}

	public List<ExameVo> filtrarExames(ExameFilter filter) {
		List<ExameVo> exames = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				exames.add(dao.findByCodigo(codigo));
			} catch (NumberFormatException e) {
				throw new BusinessException(INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID);
			}
			break;

		case NOME:
			exames.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}

		return exames;
	}

	public ExameVo buscarExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID);
		}
	}

	public String deletarExame(String codigo) {
		Integer cod = Integer.parseInt(codigo);
		isExameAgendado(cod);

		try {
			dao.deleteExame(cod);
			return "Exame deletado";
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao deletar exame");
		}
	}

	public String alterarExame(ExameVo exameVo) {
		if(exameVo.getNome().isEmpty() || exameVo.getNome() == "")
			throw new IllegalArgumentException(NOME_NAO_PODE_SER_EM_BRANCO);
		
		try {
			dao.editarExame(exameVo);
			return "Exame alterado";
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao alterar exame");
		}
	}
	
	private void isExameAgendado(int cod) {
		boolean exameAgendado = daoAgendamento.isExamePossuiAgendamentos(cod);
		if(exameAgendado)
			throw new BusinessException("Este exame não pode ser excluído pois está vinculado a um ou mais agendamentos");
	}
}