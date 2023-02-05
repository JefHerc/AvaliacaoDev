package br.com.soc.sistema.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdpter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String dateString) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateString, dtf);
		return date;
	}

	@Override
	public String marshal(LocalDate date) throws Exception {
		LocalDate data = (LocalDate) date;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = data.format(dtf);
		return dataFormatada;
	}

}
