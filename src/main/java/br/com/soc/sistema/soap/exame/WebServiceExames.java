package br.com.soc.sistema.soap.exame;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.lista.ListaExames;
import br.com.soc.sistema.vo.ExameVo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceExames {
	
	@WebMethod (operationName = "TodosExames")
	@WebResult (name = "Exames")
	ListaExames getAllExames() throws BusinessException;

	@WebMethod(operationName = "BuscarExamePorCodigo")
	@WebResult (name = "Exame")
	ExameVo getExamePorId(@WebParam (name = "CodigoExame")String codigo) throws BusinessException;
	
	@WebMethod(operationName = "BuscarExamesPorNome")
	@WebResult (name = "Exames")
	ListaExames getExamesPorNome(@WebParam (name = "NomeExame")String nomeExame) throws BusinessException;	

	@WebMethod(operationName = "SalvarNovoExame")
	String setExame(@WebParam (name = "NomeExame") String nome) throws BusinessException;
	
	@WebMethod(operationName = "DeletarExame")
	String deletarExame(@WebParam (name = "CodigoExame") String codigoExame) throws BusinessException;
	
	@WebMethod(operationName = "AlterarExame")
	String modificarExame(@WebParam (name = "Exame") ExameVo exameVo) throws BusinessException;
}
