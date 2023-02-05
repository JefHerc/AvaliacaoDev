package br.com.soc.sistema.soap.agendamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.jws.WebService;

import br.com.soc.sistema.business.AgendamentoBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.lista.ListaAgendamentos;
import br.com.soc.sistema.vo.AgendamentoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.agendamento.WebServiceAgendamentos")
public class WebServiceAgendamentosImpl implements WebServiceAgendamentos {

	private AgendamentoBusiness business = new AgendamentoBusiness();

	@Override
	public ListaAgendamentos getAllAgendamentos() throws BusinessException {
		List<AgendamentoVo> agendamentos = business.trazerTodosOsAgendamentos();
		return new ListaAgendamentos(agendamentos);
	}

	@Override
	public AgendamentoVo getAgendamentoPorId(String codigo) throws BusinessException {
		return business.buscarAgendamentoPorId(codigo);
	}

	@Override
	public ListaAgendamentos getAgendamentosPorData(String dataAgendamento) throws BusinessException {
		List<AgendamentoVo> agendamentos = business.buscarAgendamentoPorData(dataAgendamento);
		return new ListaAgendamentos(agendamentos);
	}

	@Override
	public ListaAgendamentos getAgendamentosPorFuncionario(String nome) throws BusinessException {
		List<AgendamentoVo> agendamentos = business.buscarAgendamentoPorFuncionario(nome);
		return new ListaAgendamentos(agendamentos);
	}

	@Override
	public ListaAgendamentos getAgendamentosPorExame(String nome) throws BusinessException {
		List<AgendamentoVo> agendamentos = business.buscarAgendamentoPorExame(nome);
		return new ListaAgendamentos(agendamentos);
	}
	
	@Override
	public String setAgendamento(String cdFuncionario, String cdExame, String dataAgendamento)
			throws BusinessException {
		FuncionarioVo funcionario = new FuncionarioVo();
		funcionario.setRowid(cdFuncionario);

		ExameVo exame = new ExameVo();
		exame.setRowid(cdExame);

		AgendamentoVo agendamento = new AgendamentoVo(funcionario, exame, convertStringToLocalDate(dataAgendamento));
		return business.salvarAgendamento(agendamento);
	}

	@Override
	public String deletarAgendamento(int codigoAgendamento) throws BusinessException {
		return business.deletarAgendamento(codigoAgendamento);
	}

	@Override
	public String modificarAgendamento(int codigoAgendamento, String cdFuncionario, String cdExame,
			String dataAgendamento) throws BusinessException {
		FuncionarioVo funcionario = new FuncionarioVo();
		funcionario.setRowid(cdFuncionario);

		ExameVo exame = new ExameVo();
		exame.setRowid(cdExame);

		AgendamentoVo agendamento = new AgendamentoVo(codigoAgendamento, funcionario, exame, convertStringToLocalDate(dataAgendamento));
		return business.alterarAgendamento(agendamento);
	}

	private LocalDate convertStringToLocalDate(String data) {
		DateTimeFormatter formatter;
		if (data.contains("-")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		} else {
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		}
		LocalDate localDate = LocalDate.parse(data, formatter);
		return localDate;
	}


}
