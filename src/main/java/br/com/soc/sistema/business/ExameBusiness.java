package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {

	private static final String INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID = "Informe um n�mero para buscar por ID";
	private static final String NOME_NAO_PODE_SER_EM_BRANCO = "Nome n�o pode ser em branco";
	private ExameDao dao;
	private AgendamentoDao daoAgendamento;

	public ExameBusiness() {
		this.dao = new ExameDao();
		this.daoAgendamento = new AgendamentoDao();
	}

	public List<ExameVo> trazerTodosOsExames() {
		return dao.findAllExames();
	}

	public String salvarExame(String nomeExame) {
		if (nomeExame.isEmpty())
			throw new IllegalArgumentException(NOME_NAO_PODE_SER_EM_BRANCO);

		try {
			dao.insertExame(nomeExame);
		} catch (Exception e) {
			throw new BusinessException("N�o foi poss�vel salvar o exame");
		}

		return "Exame salvo com sucesso!";
	}

	public List<ExameVo> filtrarExames(ExameFilter filter) {
		List<ExameVo> exames = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			exames.add(buscarExamePorId(filter.getValorBusca()));
			break;

		case NOME:
			exames.addAll(buscarExamePorNome(filter.getValorBusca()));
			break;
		}

		return exames;
	}

	public ExameVo buscarExamePorId(String codigo) {
		ExameVo exame = null;
		try {
			Integer cod = Integer.parseInt(codigo);
			exame = dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID);
		}

		if (exame == null)
			throw new BusinessException("Exame n�o localizado");

		return exame;
	}

	public List<ExameVo> buscarExamePorNome(String nome) {
		List<ExameVo> exames = new ArrayList<>();
		try {
			exames = dao.findAllByNome(nome);
		} catch (NumberFormatException e) {
			throw new BusinessException("Falha ao localizar o exame");
		}
		
		if (exames.isEmpty())
			throw new BusinessException("Nenhum exame localizado com este nome");

		return exames;
	}

	public String deletarExame(String codigo) {
		Integer cod = Integer.parseInt(codigo);
		isExameAgendado(cod);

		boolean isDeletado = false;

		try {
			isDeletado = dao.deleteExame(cod);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao deletar exame");
		}

		if (!isDeletado)
			throw new BusinessException("C�digo do exame inv�lido");

		return "Exame deletado com sucesso!";
	}

	public String alterarExame(ExameVo exameVo) {
		if (exameVo.getNome().isEmpty() || exameVo.getNome() == "")
			throw new IllegalArgumentException(NOME_NAO_PODE_SER_EM_BRANCO);

		boolean isAlterado = false;

		try {
			isAlterado = dao.editarExame(exameVo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao alterar exame");
		}

		if (!isAlterado)
			throw new BusinessException("C�digo do exame inv�lido");

		return "Exame alterado com sucesso!";
	}

	private void isExameAgendado(int cod) {
		boolean exameAgendado = daoAgendamento.isExamePossuiAgendamentos(cod);
		if (exameAgendado)
			throw new BusinessException(
					"Este exame n�o pode ser exclu�do pois est� vinculado a um ou mais agendamentos");
	}
}