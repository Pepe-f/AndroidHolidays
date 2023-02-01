package ru.kopylov.holidaysapp.model;

public class HolidaysListInfo {
    public HolidayInfo[] holidaysListInfo;

    public HolidaysListInfo(int size) {
        holidaysListInfo = new HolidayInfo[size];
    }

    public void addHoliday(String holidayName, String holidayDate, int id) {
        HolidayInfo holiday = new HolidayInfo(holidayName, holidayDate);
        holidaysListInfo[id] = holiday;
    }
}
