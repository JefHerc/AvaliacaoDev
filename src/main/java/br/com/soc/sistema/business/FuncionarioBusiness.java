package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {

	private static final String INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID = "Informe um número para buscar por ID";
	private static final String NOME_NAO_PODE_SER_EM_BRANCO = "Nome não pode ser em branco";
	private FuncionarioDao dao;
	private AgendamentoDao daoAgendamento;

	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
		this.daoAgendamento = new AgendamentoDao();
	}

	public List<FuncionarioVo> trazerTodosOsFuncionarios() {
		return dao.findAllFuncionarios();
	}

	public String salvarFuncionario(String nomeFuncionario) {
		if (nomeFuncionario.isEmpty())
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		try {
			dao.insertFuncionario(nomeFuncionario);
		} catch (Exception e) {
			throw new BusinessException("Não foi possível salvar o funcionário");
		}

		return "Funcionário salvo com sucesso!";
	}

	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter) {
		List<FuncionarioVo> Funcionarios = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			buscarFuncionarioPorId(filter.getValorBusca());
			break;

		case NOME:
			Funcionarios.addAll(buscarFuncionarioPorNome(filter.getValorBusca()));
			break;
		}
		return Funcionarios;
	}

	public FuncionarioVo buscarFuncionarioPorId(String codigo) {
		FuncionarioVo funcionario = null;
		try {
			Integer cod = Integer.parseInt(codigo);
			funcionario = dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID);
		}

		if (funcionario == null)
			throw new BusinessException("Funcionário não localizado");

		return funcionario;
	}

	public List<FuncionarioVo> buscarFuncionarioPorNome(String nome) {
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		try {
			funcionarios = dao.findAllByNome(nome);
		} catch (NumberFormatException e) {
			throw new BusinessException("Falha ao localizar o funcionário");
		}

		if (funcionarios.isEmpty())
			throw new BusinessException("Nenhum funcionário localizado com este nome");

		return funcionarios;
	}

	public String deletarFuncionario(String codigo) {
		Integer cod = Integer.parseInt(codigo);
		boolean isDeletado = false;
		boolean isAgendado = daoAgendamento.isFuncionairoAgendado(cod);
		try {
			if (isAgendado) {
				isDeletado = dao.deleteFuncionarioComAgendamento(cod);
			} else {
				isDeletado = dao.deleteFuncionario(cod);
				dao.deleteFuncionario(cod);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao deletar Funcionário");
		}

		if (!isDeletado)
			throw new BusinessException("Código do funcionário inválido");

		return "Funcionário deletado com sucesso!";

	}

	public String alterarFuncionario(FuncionarioVo funcionarioVo) {
		if (funcionarioVo.getNome().isEmpty() || funcionarioVo.getNome() == "")
			throw new IllegalArgumentException(NOME_NAO_PODE_SER_EM_BRANCO);

		boolean isAlterado = false;

		try {
			isAlterado = dao.editarFuncionario(funcionarioVo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao alterar funcionário");
		}

		if (!isAlterado)
			throw new BusinessException("Código do funcionário inválido");

		return "Funcionário alterado com sucesso!";
	}
}
