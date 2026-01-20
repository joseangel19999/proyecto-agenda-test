package prueba.com.pruebaAgenda.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

	 // Formato específico
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static final DateTimeFormatter FORMATTER_12H = 
            DateTimeFormatter.ofPattern("hh:mm a");
    
    public static LocalDate stringToLocalDate(String dateString) {
        return LocalDate.parse(dateString, FORMATTER);
    }
    
    public static String localDateTimeToString(LocalDate fecha) {
    	return FORMATTER.format(fecha);
    }
    
    public LocalDateTime parseWithMultipleFormats(String dateString) {
        String[] patterns = {
            "yyyy-MM-dd HH:mm:ss",
            "dd/MM/yyyy HH:mm",
            "yyyy-MM-dd'T'HH:mm:ss"
        };
        
        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(dateString, formatter);
            } catch (Exception e) {
                // Continuar con el siguiente patrón
            }
        }
        throw new IllegalArgumentException("Formato de fecha no reconocido: " + dateString);
    }
      
}
