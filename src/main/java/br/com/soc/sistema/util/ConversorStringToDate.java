package br.com.soc.sistema.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class ConversorStringToDate extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map arg0, String[] strings, Class arg2) {
		if (strings == null || strings.length == 0 || strings[0].trim().length() == 0) {
			return null;
		}
		return LocalDate.parse(strings[0]);
	}

	@Override
	public String convertToString(Map arg0, Object date) {
		LocalDate data = (LocalDate) date;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = data.format(dtf);
		return dataFormatada;
	}
}
