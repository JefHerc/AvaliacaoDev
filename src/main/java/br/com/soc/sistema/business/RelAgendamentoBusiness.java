package br.com.soc.sistema.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendamentoVo;
import br.com.soc.sistema.vo.IndicadorExameVo;

public class RelAgendamentoBusiness {
	
	private static final String SEM_DADOS_PARA_O_PERIODO = "Sem dados para o período selecionado";
	private AgendamentoDao dao;

	public RelAgendamentoBusiness() {
		this.dao = new AgendamentoDao();
	}

	public List<IndicadorExameVo> gerarRelatoriEmTela(LocalDate dataInicio, LocalDate dataFim) {
		List<IndicadorExameVo> indicadores = dao.indicadorByRangeData(dataInicio, dataFim);
		if(indicadores.isEmpty())
			throw new BusinessException(SEM_DADOS_PARA_O_PERIODO);

		return indicadores;
	}

	public HSSFWorkbook gerarXls(LocalDate dataInicio, LocalDate dataFim) throws IOException {
		List<AgendamentoVo> agendamentos = dao.findAllByRangeData(dataInicio, dataFim);
		if(agendamentos.isEmpty())
			throw new BusinessException(SEM_DADOS_PARA_O_PERIODO);

		HSSFWorkbook workbook = new RelAgendamentoXls().gerarXls(agendamentos);
		return workbook;
	}
}