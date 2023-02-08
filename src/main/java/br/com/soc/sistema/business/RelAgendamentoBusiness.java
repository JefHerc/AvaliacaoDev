package br.com.soc.sistema.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.vo.AgendamentoVo;

public class RelAgendamentoBusiness {

	private AgendamentoDao dao;

	public RelAgendamentoBusiness() {
		this.dao = new AgendamentoDao();
	}

	public List<AgendamentoVo> gerarRelatoriEmTela(LocalDate dataInicio, LocalDate dataFim) {
		List<AgendamentoVo> agendamentos = dao.findAllByRangeData(dataInicio, dataFim);
		return agendamentos;
	}

	public HSSFWorkbook gerarXls(LocalDate dataInicio, LocalDate dataFim) throws IOException {
		List<AgendamentoVo> agendamentos = dao.findAllByRangeData(dataInicio, dataFim);
		HSSFWorkbook workbook = new RelAgendamentoXls().gerarXls(agendamentos);
		return workbook;
	}
		

}