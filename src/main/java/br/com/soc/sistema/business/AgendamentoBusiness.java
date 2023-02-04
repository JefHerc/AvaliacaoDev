package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.AgendamentoFilter;
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
//		if (exameVo.getNome().isEmpty())
//		throw new IllegalArgumentException("Nome nao pode ser em branco");
		validarAgendamento(agendamentoVo);
		try {
			dao.insertAgendamento(agendamentoVo);
		} catch (Exception e) {
			throw new BusinessException("Falha ao salvar o agendamento");
		}

	}

	public AgendamentoVo buscarAgendamentoPor(int codigo) {
		try {
			return dao.findByCodigo(codigo);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}

	public void deletarAgendamento(int codigo) {
		dao.deleteAgendamento(codigo);
	}

	public void alterarAgendamento(AgendamentoVo agendamentoVo) {
		dao.editarAgendamento(agendamentoVo);
	}

	public List<AgendamentoVo> filtrarAgendamentos(AgendamentoFilter filter) {
		List<AgendamentoVo> agendamentos = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				agendamentos.add(dao.findByCodigo(codigo));
			} catch (NumberFormatException e) {
				throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
			}
			break;

		case FUNCIONARIO:
			agendamentos.addAll(dao.findAllByFuncionario(filter.getValorBusca()));
			break;

		case EXAME:
			agendamentos.addAll(dao.findAllByExame(filter.getValorBusca()));
			break;

		case DATA:
			String data = filter.getValorBusca();
			LocalDate dataConvertida = LocalDate.parse(data);
			agendamentos.addAll(dao.findAllByData(dataConvertida));
			break;
		}

		return agendamentos;
	}

	private void validarAgendamento(AgendamentoVo agendamentoVo) {
		boolean isAgendado = dao.isAgendado(agendamentoVo);
		if (isAgendado)
			throw new BusinessException(
					"Já existe um agendamento registrado para este Funcionário com este exame nesta data.");
	}

}