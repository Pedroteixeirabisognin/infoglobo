package br.com.globo.Infoglobo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

	public static String retornaDataFormatadaLocal() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY  HH:mm:ss");
        Date date = new Date();

		String dataFormatadaBrasil = dateFormat.format(date);

		return dataFormatadaBrasil;
	}
}
