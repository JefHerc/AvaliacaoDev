package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendamentoVo;

public class AgendamentoBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private AgendamentoDao dao;

	public AgendamentoBusiness() {
		this.dao = new AgendamentoDao();
	}

	public List<AgendamentoVo> trazerTodosOsAgendamentos() {
		return dao.findAllAgendamentos();
	}

	public void salvarAgendamento(AgendamentoVo agendamentoVo) {
		try {
//			if (exameVo.getNome().isEmpty())
//				throw new IllegalArgumentException("Nome nao pode ser em branco");

			dao.insertAgendamento(agendamentoVo);
		} catch (Exception e) {
//			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}

	}
//
//	public List<ExameVo> filtrarExames(ExameFilter filter) {
//		List<ExameVo> exames = new ArrayList<>();
//
//		switch (filter.getOpcoesCombo()) {
//		case ID:
//			try {
//				Integer codigo = Integer.parseInt(filter.getValorBusca());
//				exames.add(dao.findByCodigo(codigo));
//			} catch (NumberFormatException e) {
//				throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
//			}
//			break;
//
//		case NOME:
//			exames.addAll(dao.findAllByNome(filter.getValorBusca()));
//			break;
//		}
//
//		return exames;
//	}
//
	public AgendamentoVo buscarAgendamentoPor(int codigo) {
		try {
			return dao.findByCodigo(codigo);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
//
	public void deletarAgendamento(int codigo) {
		dao.deleteAgendamento(codigo);
	}
//
//	public void alterarExame(ExameVo exameVo) {
//		dao.editarExame(exameVo);		
//	}
}