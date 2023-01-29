package br.com.soc.sistema.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.vo.AgendamentoVo;

public class RelAgendamentoBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private AgendamentoDao dao;

	public RelAgendamentoBusiness() {
		this.dao = new AgendamentoDao();
	}

	public List<AgendamentoVo> gerarRelatoriEmTela(LocalDate dataInicio, LocalDate dataFim) {
		List<AgendamentoVo> agendamentos = dao.findAllByRangeData(dataInicio, dataFim);
		return agendamentos;
	}

	public void gerarXls(LocalDate dataInicio, LocalDate dataFim) throws IOException {
		List<AgendamentoVo> agendamentos = dao.findAllByRangeData(dataInicio, dataFim);
		new RelAgendamentoXls().gerarXls(agendamentos);
	}
		

}