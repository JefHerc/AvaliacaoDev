package br.com.soc.sistema.soap.funcionario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.lista.ListaFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface WebServiceFuncionarios {
	
	@WebMethod (operationName = "TodosFuncionarios")
	@WebResult (name = "Funcionarios")
	ListaFuncionarios getAllFuncionarios() throws BusinessException;

	@WebMethod(operationName = "BuscarFuncionarioPorCodigo")
	@WebResult (name = "Funcionario")
	FuncionarioVo getFuncionarioPorId(@WebParam (name = "CodigoFuncionario")String codigo) throws BusinessException;
	
	@WebMethod(operationName = "BuscarFuncionariosPorNome")
	@WebResult (name = "Funcionarios")
	ListaFuncionarios getFuncionariosPorNome(@WebParam (name = "NomeFuncionario")String nomeFuncionario) throws BusinessException;	

	@WebMethod(operationName = "SalvarNovoFuncionario")
	String setFuncionario(@WebParam (name = "NomeFuncionario") String nome) throws BusinessException;
	
	@WebMethod(operationName = "DeletarFuncionario")
	String deletarFuncionario(@WebParam (name = "CodigoFuncionario") String codigoFuncionario) throws BusinessException;
	
	@WebMethod(operationName = "AlterarFuncionario")
	String modificarFuncionario(@WebParam (name = "Funcionario") FuncionarioVo FuncionarioVo) throws BusinessException;
}
