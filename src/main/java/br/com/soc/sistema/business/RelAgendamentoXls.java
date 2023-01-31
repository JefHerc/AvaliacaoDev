package br.com.soc.sistema.business;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import br.com.soc.sistema.vo.AgendamentoVo;

public class RelAgendamentoXls {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	public RelAgendamentoXls() {
		workbook = new HSSFWorkbook();
	}
		
	public HSSFWorkbook gerarXls(List<AgendamentoVo> agendamentos) throws IOException {
		sheet = workbook.createSheet("Agendamentos");

		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Código Funcionário");
		sheet.getRow(0).createCell(1).setCellValue("Nome Funcionário");
		sheet.getRow(0).createCell(2).setCellValue("Código Exame");
		sheet.getRow(0).createCell(3).setCellValue("Nome Exame");
		sheet.getRow(0).createCell(4).setCellValue("Data Exame");

		aplicarStyleRow(sheet.getRow(0), styleCelulaCabecalho());

		int linha = 1;
		for (AgendamentoVo agendamento : agendamentos) {
			sheet.createRow(linha);
			sheet.getRow(linha).createCell(0).setCellValue(agendamento.getFuncionario().getRowid());
			sheet.getRow(linha).createCell(1).setCellValue(agendamento.getFuncionario().getNome());
			sheet.getRow(linha).createCell(2).setCellValue(agendamento.getExame().getRowid());
			sheet.getRow(linha).createCell(3).setCellValue(agendamento.getExame().getNome());
			sheet.getRow(linha).createCell(4).setCellValue(agendamento.getDataFormatada());

			if (linha % 2 == 0) {
				aplicarStyleRow(sheet.getRow(linha), styleCelulaComCor());

			} else {
				aplicarStyleRow(sheet.getRow(linha), styleCelulaSemCor());
			}

			linha++;
		}

		setAutoSizeColumn();

    
		return workbook;
	}

	private void setAutoSizeColumn() {
	    for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
	        sheet.autoSizeColumn(i);
	    }
	}
	
	private HSSFCellStyle styleCelulaCabecalho() {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 12);
		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);

		return style;
	}

	private HSSFCellStyle styleCelulaComCor() {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 10);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);

		return style;
	}

	private HSSFCellStyle styleCelulaSemCor() {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 10);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);

		return style;
	}

	private void aplicarStyleRow(Row row, CellStyle style) {
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			cell.setCellStyle(style);
		}
	}
}
