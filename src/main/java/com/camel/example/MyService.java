package com.camel.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

import org.apache.camel.CamelContext;
import org.apache.camel.Header;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    CamelContext context;
    public int helloWithName(@Header("input") String input) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH);
    String dateInString ="2021-07-16";
    Date input_date = simpleDateFormat.parse(dateInString); 
    Instant instantj = input_date.toInstant();
    ZonedDateTime zdtk = instantj.atZone(ZoneId.systemDefault());
    long count_friday = 0;  
    LocalDate start = zdtk.toLocalDate();
    LocalDate end = LocalDate.now(); 
    if(start.isAfter(end))
    {
        LocalDate temp=start;
        start=end;
        end=temp;
    }
    LocalDate firstFriday = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    LocalDate lastFriday = end.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
    count_friday = ChronoUnit.WEEKS.between(firstFriday, lastFriday);
    return (int) (count_friday + 1);

     }
}