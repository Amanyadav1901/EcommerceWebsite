package com.myProject.ecommerce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.IsoEra;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.DateFormatter;

import static java.time.temporal.ChronoField.YEAR;
import static java.time.temporal.ChronoField.YEAR_OF_ERA;

public class DateManipulation {

    public static final String DATE_PATTERN = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

    public static void main(String[] args) {

        String attributeValue = "12/12/44";
//        Locale locale = Locale.US;
        Locale locale = new Locale("en", "US");

        System.out.println("Printing Input Value of the Attribute: " + attributeValue);

//        DateTimeFormatter TWO_YEAR_FORMATTER = new DateTimeFormatterBuilder()
//                .appendValueReduced(ChronoField.YEAR, 2, 2, LocalDate.now().minusYears(80))
//                .toFormatter();
//        int year = Year.parse("99", TWO_YEAR_FORMATTER).getValue();
//        System.out.println("Two Digit Year Converted:" + year);

        String shortDatePattern = DateTimeFormatterBuilder.getLocalizedDateTimePattern(
                FormatStyle.SHORT,
                null,
                IsoChronology.INSTANCE,
                locale);

        DateTimeFormatter shortDateFormatter = new DateTimeFormatterBuilder()
                .appendPattern(shortDatePattern)
                .parseDefaulting(ChronoField.ERA, IsoEra.CE.getValue())
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);

        DateTimeFormatter fullDateFormatter = new DateTimeFormatterBuilder()
                .appendPattern("M/dd/yyyy")
                .parseDefaulting(ChronoField.ERA, IsoEra.CE.getValue())
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);

        LocalDate attributeDateValue = null;

        try {
            attributeDateValue = LocalDate.parse(attributeValue, shortDateFormatter);
            // Handle two-digit year by checking if the parsed year is greater than the current year.
            int parsedYear = attributeDateValue.getYear();
//            int currentYear = LocalDate.now().getYear();
//            if (parsedYear >= 2070 && parsedYear > currentYear) {
//                attributeDateValue = attributeDateValue.minusYears(100);
//            }
            if(locale.getCountry() == "US") {
                attributeDateValue = attributeDateValue.minusYears(parsedYear/100 * 100);
            }
        } catch (DateTimeParseException e) {
            attributeDateValue = LocalDate.parse(attributeValue, fullDateFormatter);
        }

        String attributeDateValueStr = attributeDateValue.toString();
        Matcher matcher = Pattern.compile(DATE_PATTERN).matcher(attributeDateValueStr);
        if (matcher.matches()) {
            System.out.println("Setting Final Attribute Value for the attribute in MATCHER: " + attributeDateValueStr);
        } else {
            //Formatting date to UTC format when we get any format in "yy-MM-dd" after user locale date parsing, so we are trying to format it to "yyyy-MM-dd"
            //Need to persist attribute date values in "yyyy-MM-dd" format in DB.
            String attributeDate = attributeDateValue.getYear() + "-" + attributeDateValue.getMonthValue() + "-" + attributeDateValue.getDayOfMonth();
            SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String attributeDateFormattedValue = null;
            try {
                attributeDateFormattedValue = formatter.format(parser.parse(attributeDate));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Setting Final attributeDateFormattedValue: " + attributeDateFormattedValue);
        }

    }


}
