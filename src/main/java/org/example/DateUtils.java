package org.example;

// Class: DateUtils
public class DateUtils {
    public DateUtils() {
        // Default constructor
    }

    /*
     * This method takes a year as input and returns a boolean indicating
     * whether the year is a leap year or not. It follows the standard
     * rules for determining leap years: a year is a leap year if it
     * is divisible by 4, except for years divisible by 100, which
     * are not leap years unless they are also divisible by 400
     */
    public boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public int getDaysInMonth(int year, int month) {

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month value: " + month);
        }

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month == 2 && isLeapYear(year)) {
            return 29;
        }

        return daysInMonth[month - 1];
    }
}