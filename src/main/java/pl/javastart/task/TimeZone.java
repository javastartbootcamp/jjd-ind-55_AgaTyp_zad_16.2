package pl.javastart.task;

import java.time.ZoneId;

public enum TimeZone {
    SYSTEM_DEFAULT("Czas lokalny", ZoneId.systemDefault()),
    UTC("UTC", ZoneId.of("UTC")),
    LONDON("Londyn", ZoneId.of("Europe/London")),
    LOS_ANGELES("Los Angeles", ZoneId.of("America/Los_Angeles")),
    SYDNEY("Sydney", ZoneId.of("Australia/Sydney"));

    private final String description;
    private final ZoneId timeZoneId;

    TimeZone(String description, ZoneId timeZoneId) {
        this.description = description;
        this.timeZoneId = timeZoneId;
    }

    public String getDescription() {
        return description;
    }

    public ZoneId getTimeZoneId() {
        return timeZoneId;
    }

    @Override
    public String toString() {
        return "TimeZones{" +
                "description='" + description + '\'' +
                ", timeZoneId=" + timeZoneId +
                '}';
    }
}
