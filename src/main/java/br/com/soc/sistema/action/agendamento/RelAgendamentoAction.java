package br.com.soc.sistema.action.agendamento;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

@Conversion
public class RelAgendamentoAction extends Action {

	private RelAgendamentoBusiness business = new RelAgendamentoBusiness();
	private List<AgendamentoVo> agendamentos = new ArrayList<>();
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private InputStream fileInputStream;

	public String direcionar() {
		return SUCCESS;
	}

	public String gerar() {
		agendamentos = business.gerarRelatoriEmTela(dataInicio, dataFim);
		try {
			HSSFWorkbook workbook = business.gerarXls(dataInicio, dataFim);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        try {
	            workbook.write(outputStream);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        fileInputStream = new ByteArrayInputStream(outputStream.toByteArray());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "gerar";
	}

	public List<AgendamentoVo> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVo> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@TypeConversion (converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	@TypeConversion (converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	@TypeConversion (converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public LocalDate getDataFim() {
		return dataFim;
	}
	
	@TypeConversion (converter = "br.com.soc.sistema.util.ConversorStringToDate")
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

}
