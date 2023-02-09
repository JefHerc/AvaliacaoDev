package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	public String salvarAgendamento(AgendamentoVo agendamentoVo) {
		validarAgendamento(agendamentoVo);
		try {
			dao.insertAgendamento(agendamentoVo);
		} catch (Exception e) {
			throw new BusinessException("Falha ao salvar o agendamento");
		}

		return "Agendamento salvo com sucesso!";
	}

	public AgendamentoVo buscarAgendamentoPorId(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}

	public List<AgendamentoVo> buscarAgendamentoPorFuncionario(String nome) {
		List<AgendamentoVo> agendamentos = new ArrayList<>();
		try {
			agendamentos = dao.findAllByFuncionario(nome);
		} catch (NumberFormatException e) {
			throw new BusinessException("Falha ao localizar o funcionário");
		}

		if (agendamentos.isEmpty())
			throw new BusinessException("Nenhum funcionário localizado com este nome");

		return agendamentos;

	}

	public List<AgendamentoVo> buscarAgendamentoPorExame(String nome) {
		List<AgendamentoVo> agendamentos = new ArrayList<>();
		try {
			agendamentos = dao.findAllByExame(nome);
		} catch (NumberFormatException e) {
			throw new BusinessException("Falha ao localizar o exame");
		}

		if (agendamentos.isEmpty())
			throw new BusinessException("Nenhum exame localizado com este nome");

		return agendamentos;

	}

	public List<AgendamentoVo> buscarAgendamentoPorData(String data) {
		List<AgendamentoVo> agendamentos = new ArrayList<>();
		LocalDate dataConvertida = convertStringToLocalDate(data);
		agendamentos.addAll(dao.findAllByData(dataConvertida));
		return agendamentos;
	}

	public String deletarAgendamento(int codigo) {
		dao.deleteAgendamento(codigo);
		return "Agendamento deletado com sucesso!";
	}

	public String alterarAgendamento(AgendamentoVo agendamentoVo) {
		validarAgendamento(agendamentoVo);
		dao.editarAgendamento(agendamentoVo);
		return "Agendamento alterado com sucesso!";
	}

	public List<AgendamentoVo> filtrarAgendamentos(AgendamentoFilter filter) {
		List<AgendamentoVo> agendamentos = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			agendamentos.add(buscarAgendamentoPorId(filter.getValorBusca()));
			break;

		case FUNCIONARIO:
			agendamentos.addAll(buscarAgendamentoPorFuncionario(filter.getValorBusca()));
			break;

		case EXAME:
			agendamentos.addAll(buscarAgendamentoPorExame(filter.getValorBusca()));
			break;

		case DATA:
			agendamentos.addAll(buscarAgendamentoPorData(filter.getValorBusca()));
			break;
		}

		return agendamentos;
	}

	private void validarAgendamento(AgendamentoVo vo) {
		if (vo.getDataAgendamento() == null || vo.getExame().getRowid().isEmpty() || vo.getFuncionario().getRowid().isEmpty())
			throw new BusinessException("Preencha todos os campos.");

		boolean isAgendado = dao.isAgendado(vo);
		if (isAgendado)
			throw new BusinessException("Já existe um agendamento registrado para este Funcionário com este exame nesta data.");
	}

	private LocalDate convertStringToLocalDate(String data) {
		DateTimeFormatter formatter;
		if (data.contains("-")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		} else {
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		}
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(data, formatter);
		} catch (Exception e) {
			throw new BusinessException("Data inválida.");
		}
		return localDate;
	}
}