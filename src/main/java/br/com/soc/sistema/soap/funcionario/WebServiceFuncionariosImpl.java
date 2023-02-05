package br.com.soc.sistema.soap.funcionario;

import java.util.List;

import javax.jws.WebService;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.lista.ListaFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.funcionario.WebServiceFuncionarios" )
public class WebServiceFuncionariosImpl implements WebServiceFuncionarios {

	private FuncionarioBusiness business;
	private String resultado = null;
	
	public WebServiceFuncionariosImpl() {
		this.business = new FuncionarioBusiness();
	}

	@Override
	public ListaFuncionarios getAllFuncionarios() throws BusinessException {
		List<FuncionarioVo> Funcionarios = business.trazerTodosOsFuncionarios();
		return new ListaFuncionarios(Funcionarios);
	}

	@Override
	public FuncionarioVo getFuncionarioPorId(String codigo) throws BusinessException {
		FuncionarioVo FuncionarioVo = business.buscarFuncionarioPorId(codigo);
		return FuncionarioVo;
	}

	@Override
	public ListaFuncionarios getFuncionariosPorNome(String nomeFuncionario) throws BusinessException {
		List<FuncionarioVo> Funcionarios = business.buscarFuncionarioPorNome(nomeFuncionario);
		return new ListaFuncionarios(Funcionarios);
	}

	@Override
	public String setFuncionario(String nomeFuncionario) throws BusinessException {
		resultado = business.salvarFuncionario(nomeFuncionario);
		return resultado;
	}

	@Override
	public String deletarFuncionario(String codigo) throws BusinessException {
		resultado = business.deletarFuncionario(codigo);
		return resultado;
	}

	@Override
	public String modificarFuncionario(FuncionarioVo FuncionarioVo) throws BusinessException {
		resultado = business.alterarFuncionario(FuncionarioVo);
		return resultado;
	}
}
