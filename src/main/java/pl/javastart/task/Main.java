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
                
        ZonedDateTime localDateTime = getDateTime(scanner);
        printZoneDateTime(localDateTime);

    }

    private void printZoneDateTime(ZonedDateTime localDateTime) {
        DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (TimeZone timeZone : TimeZone.values()) {
            ZonedDateTime zoneDateTime = localDateTime.withZoneSameInstant(timeZone.getTimeZoneId());
            String zoneDateTimeFormatted = zoneDateTime.format(dateTimePattern);
            System.out.println(timeZone.getDescription() + ": " + zoneDateTimeFormatted);
        }

    }

    private ZonedDateTime getDateTime(Scanner scanner) {
        ZoneId localZone = ZoneId.systemDefault();
        boolean error = true;
        String timeInput = "00:00:00";
        LocalDate createdDate;
        LocalDateTime dateTime = null;

        DateTimeFormatter datePatternDayFirst = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter datePatternDayLast = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("HH:mm:ss");

        do {
            System.out.println("Podaj datę:");
            String inputDateTime = scanner.nextLine();
            String[] dateAndTime = inputDateTime.split(" ");
            String dateInput = dateAndTime[0].replace(".", "-");
            if (dateInput.split("-").length != 3) {
                System.out.println("Zły format daty.");
            } else {
                error = false;
                if (dateAndTime.length == 2) {
                    timeInput = dateAndTime[1];
                }
                try {
                    createdDate = LocalDate.parse(dateInput, datePatternDayFirst);
                } catch (DateTimeParseException e) {
                    createdDate = LocalDate.parse(dateInput, datePatternDayLast);
                }

                LocalTime createTime = LocalTime.parse(timeInput, timePattern);
                dateTime = LocalDateTime.of(createdDate, createTime);
            }
            
        } while (error);

        return ZonedDateTime.of(dateTime, localZone);

    }

}
