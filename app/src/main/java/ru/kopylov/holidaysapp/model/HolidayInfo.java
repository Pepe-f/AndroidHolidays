package ru.kopylov.holidaysapp.model;

public class HolidayInfo {
    private int id;
    private String holidayName;
    private String holidayDate;

    public HolidayInfo(String holidayName, String holidayDate) {
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public String getHolidayDate() {
        return holidayDate;
    }
}
