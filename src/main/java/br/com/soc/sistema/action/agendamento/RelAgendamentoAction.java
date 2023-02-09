package br.com.soc.sistema.action.agendamento;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

import br.com.soc.sistema.business.RelAgendamentoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.AgendamentoVo;
import br.com.soc.sistema.vo.IndicadorExameVo;

@Conversion
public class RelAgendamentoAction extends Action {

	private RelAgendamentoBusiness business = new RelAgendamentoBusiness();
	private List<AgendamentoVo> agendamentos = new ArrayList<>();
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private InputStream fileInputStream;
	private List<IndicadorExameVo> indicadores = new ArrayList<>();

	public String direcionar() {
		if (!isUserAutenticado())
			return "loginError";

		return SUCCESS;
	}

	public String xls() {
		if (!isUserAutenticado())
			return "loginError";

		try {
			HSSFWorkbook workbook = business.gerarXls(dataInicio, dataFim);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			fileInputStream = new ByteArrayInputStream(outputStream.toByteArray());
			workbook.close();
			
			return "downloadXls";
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
		}

		return INPUT;
	}

	public String indicador() {
		try {
			indicadores.addAll(business.gerarRelatoriEmTela(dataInicio, dataFim));
		} catch (Exception e) {
			exibirMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		return "indicador";
	}

	public List<AgendamentoVo> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVo> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@TypeConversion(converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	@TypeConversion(converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	@TypeConversion(converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public LocalDate getDataFim() {
		return dataFim;
	}

	@TypeConversion(converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public List<IndicadorExameVo> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<IndicadorExameVo> indicadores) {
		this.indicadores = indicadores;
	}

}
