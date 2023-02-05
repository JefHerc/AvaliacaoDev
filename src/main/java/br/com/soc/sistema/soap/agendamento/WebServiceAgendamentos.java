package br.com.soc.sistema.soap.agendamento;

import java.time.LocalDate;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.lista.ListaAgendamentos;
import br.com.soc.sistema.vo.AgendamentoVo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceAgendamentos {

	@WebMethod(operationName = "TodosAgendamentos")
	@WebResult(name = "Agendamentos")
	ListaAgendamentos getAllAgendamentos() throws BusinessException;

	@WebMethod(operationName = "BuscarAgendamentoPorCodigo")
	@WebResult(name = "Agendamento")
	AgendamentoVo getAgendamentoPorId(@WebParam(name = "CodigoAgendamento") String codigo) throws BusinessException;

	@WebMethod(operationName = "BuscarAgendamentosPorFuncionario")
	@WebResult(name = "Agendamento")
	ListaAgendamentos getAgendamentosPorFuncionario(@WebParam(name = "NomeFuncionario") String nome) throws BusinessException;

	@WebMethod(operationName = "BuscarAgendamentoPorExame")
	@WebResult(name = "Agendamento")
	ListaAgendamentos getAgendamentosPorExame(@WebParam(name = "NomeExame") String nome) throws BusinessException;

	@WebMethod(operationName = "BuscarAgendamentosPorData")
	@WebResult(name = "Agendamentos")
	ListaAgendamentos getAgendamentosPorData(@WebParam(name = "DatadoAgendamento") String dataAgendamento)
			throws BusinessException;

	@WebMethod(operationName = "SalvarNovoAgendamento")
	String setAgendamento(@WebParam(name = "CodigoFuncionario") String cdFuncionario,
						@WebParam(name = "CodigoExame") String cdExame,
						@WebParam(name = "DataAgendamento") String dataAgendamento) throws BusinessException;

	@WebMethod(operationName = "DeletarAgendamento")
	String deletarAgendamento(@WebParam(name = "CodigoAgendamento") int codigoAgendamento) throws BusinessException;

	@WebMethod(operationName = "AlterarAgendamento")
	String modificarAgendamento(@WebParam(name = "CodigoAgendamento") int codigoAgendamento,
								@WebParam(name = "CodigoFuncionario") String cdFuncionario, 
								@WebParam(name = "CodigoExame") String cdExame,
								@WebParam(name = "DataAgendamento") String dataAgendamento) throws BusinessException;
}
