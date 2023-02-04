package br.com.soc.sistema.soap;

import java.util.List;

import javax.jws.WebService;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.lista.ListaExames;
import br.com.soc.sistema.vo.ExameVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceExames" )
public class WebServiceExamesImpl implements WebServiceExames {

	private ExameBusiness business;
	private String resultado = null;
	
	public WebServiceExamesImpl() {
		this.business = new ExameBusiness();
	}

	@Override
	public ListaExames getAllExames() throws BusinessException {
		List<ExameVo> exames = business.trazerTodosOsExames();
		return new ListaExames(exames);
	}

	@Override
	public ExameVo getExamePorId(String codigo) throws BusinessException {
		ExameVo exameVo = business.buscarExamePorId(codigo);
		return exameVo;
	}

	@Override
	public ListaExames getExamesPorNome(String nomeExame) throws BusinessException {
		List<ExameVo> exames = business.buscarExamePorNome(nomeExame);
		return new ListaExames(exames);
	}

	@Override
	public String setExame(String nomeExame) throws BusinessException {
		resultado = business.salvarExame(nomeExame);
		return resultado;
	}

	@Override
	public String deletarExame(String codigo) throws BusinessException {
		resultado = business.deletarExame(codigo);
		return resultado;
	}

	@Override
	public String modificarExame(ExameVo exameVo) throws BusinessException {
		resultado = business.alterarExame(exameVo);
		return resultado;
	}
}
