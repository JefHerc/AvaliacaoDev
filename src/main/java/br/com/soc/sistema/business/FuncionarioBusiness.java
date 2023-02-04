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

	public void salvarFuncionario(FuncionarioVo FuncionarioVo) {
		if (FuncionarioVo.getNome().isEmpty())
			throw new IllegalArgumentException("Nome nao pode ser em branco");

		try {
			dao.insertFuncionario(FuncionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Não foi possível salvar o funcionário");
		}

	}

	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter) {
		List<FuncionarioVo> Funcionarios = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				Funcionarios.add(dao.findByCodigo(codigo));
			} catch (NumberFormatException e) {
				throw new BusinessException(INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID);
			}
			break;

		case NOME:
			Funcionarios.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}

		return Funcionarios;
	}

	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(INFORME_UM_NUMERO_PARA_BUSCAR_POR_ID);
		}
	}

	public void deletarFuncionario(String codigo) {
		Integer cod = Integer.parseInt(codigo);
		dao.deleteFuncionario(cod);
		daoAgendamento.deleteAgendamentosPorFuncionario(cod);
	}

	public void alterarFuncionario(FuncionarioVo funcionarioVo) {
		if(funcionarioVo.getNome().isEmpty() || funcionarioVo.getNome() == "")
			throw new IllegalArgumentException(NOME_NAO_PODE_SER_EM_BRANCO);

		try {
			dao.editarFuncionario(funcionarioVo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Falha ao alterar exame");
		}		
	}

}
