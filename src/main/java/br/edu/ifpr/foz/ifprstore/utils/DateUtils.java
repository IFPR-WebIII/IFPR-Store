package br.edu.ifpr.foz.ifprstore.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String localDateToBrazilianFormat(LocalDate date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return formatter.format(date);

    }

}
