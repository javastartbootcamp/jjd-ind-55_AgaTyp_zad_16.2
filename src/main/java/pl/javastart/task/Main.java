package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        // uzupełnij rozwiązanie. Korzystaj z przekazanego w parametrze scannera

        ZoneId localZone = ZoneId.of(java.util.TimeZone.getDefault().getID());

        DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime localDateTime = getDateTime(scanner, localZone);
        String localDateTimeFormatted = localDateTime.format(dateTimePattern);
        System.out.println("Czas lokalny: " + localDateTimeFormatted);

        printZoneDateTime(localDateTime, dateTimePattern);

    }

    private void printZoneDateTime(ZonedDateTime localDateTime, DateTimeFormatter dateTimePattern) {
        for (TimeZone timeZone : TimeZone.values()) {
            ZonedDateTime zoneDateTime = localDateTime.withZoneSameInstant(timeZone.getTimeZoneId());
            String zoneDateTimeFormatted = zoneDateTime.format(dateTimePattern);
            System.out.println(timeZone.getDescription() + ": " + zoneDateTimeFormatted);
        }

    }

    private ZonedDateTime getDateTime(Scanner scanner, ZoneId localZone) {
        String timeInput = "00:00:00";
        LocalDate createdDate;
        System.out.println("Podaj datę:");

        String[] dateAndTime = scanner.nextLine().split(" ");
        String dateInput = dateAndTime[0].replace(".", "-");
        if (dateAndTime.length == 2) {
            timeInput = dateAndTime[1];
        }

        DateTimeFormatter datePatternDayFirst = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter datePatternDayLast = DateTimeFormatter.ofPattern("yyy-MM-dd");
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            createdDate = LocalDate.parse(dateInput, datePatternDayFirst);
        } catch (DateTimeParseException e) {
            createdDate = LocalDate.parse(dateInput, datePatternDayLast);
        }

        LocalTime createTime = LocalTime.parse(timeInput, timePattern);
        LocalDateTime dateTime = LocalDateTime.of(createdDate, createTime);

        return ZonedDateTime.of(dateTime, localZone);

    }

}
