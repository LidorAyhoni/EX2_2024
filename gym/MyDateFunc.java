package gym;
import gym.Exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyDateFunc{
    public static double Age(String birthday){
        double Age=0;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birth = LocalDate.parse(birthday, formatter);
            Period birthPeriod = Period.between(birth, LocalDate.now());
            int years = birthPeriod.getYears();
            int months = birthPeriod.getMonths();
            int days = birthPeriod.getDays();
            Age= years+ (months/12.0) +(days/365.0);
            return Age;
        }
        catch (InvalidDateFormat e) {
            throw new InvalidDateFormat("Invalid Date Format! Please try the following format: dd-MM-yyyy");
        }
    }
    public static LocalDate parseSessionStringToDate(String sessionDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            return LocalDate.parse(sessionDate, formatter);
        }
        catch (InvalidDateFormat e) {
            throw new InvalidDateFormat("Invalid Date Format! Please try the following format: dd-MM-yyyy HH:mm");
        }
    }
    public static boolean theSessionIsInTheFuture(String sessionDate){
        LocalDate now = LocalDate.now();
        return now.compareTo(MyDateFunc.parseSessionStringToDate(sessionDate)) > 0;
    }
    public static String parseDateFormat (String sessionDate){
        String newFormat =sessionDate.substring(6,10);
        newFormat+="-"+sessionDate.substring(3,5);
        newFormat+="-"+sessionDate.substring(0,2)+"T";
        newFormat+=sessionDate.substring(11);
        return newFormat;
    }
    public static String parseDateFormat1 (String sessionDate){
        String newFormat= sessionDate.substring(0,2);
        newFormat+="-"+sessionDate.substring(3,5);
        newFormat+="-"+sessionDate.substring(6,10);
        return newFormat;
    }
    public static String opDate(String sessionDate) {
        String newFormat =sessionDate.substring(6,10);
        newFormat+="-"+sessionDate.substring(3,5);
        newFormat+="-"+sessionDate.substring(0,2);
        return newFormat;
    }
}
