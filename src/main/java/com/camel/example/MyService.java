package com.camel.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.camel.CamelContext;
import org.apache.camel.Header;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    CamelContext context;
    public int helloWithName(@Header("input") String input) throws ParseException {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        String dateInString =input;
        simpleDateFormat.setLenient(false);
        simpleDateFormat.parse(dateInString);   
        Date input_date = simpleDateFormat.parse(dateInString); 
        Instant instantj = input_date.toInstant();
        ZonedDateTime zdtk = instantj.atZone(ZoneId.systemDefault());
        LocalDate start = zdtk.toLocalDate();
        LocalDate end = LocalDate.now();  
        DayOfWeek startW = start.getDayOfWeek();
        DayOfWeek endW = end.getDayOfWeek();
        long days =Math.abs(ChronoUnit.DAYS.between(start, end)/7);
        if(start.isBefore(end))
            days += (endW.getValue()<=startW.getValue())?1:0;
        else
            days += (startW.getValue()<=endW.getValue())?1:0;
        return (int) days;
        }
}