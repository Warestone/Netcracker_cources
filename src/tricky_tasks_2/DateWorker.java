package tricky_tasks_2;

import java.util.InputMismatchException;

public class DateWorker {
    public static enum day_enum{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;
    }
    public static enum month_enum{
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER;
    }
    boolean isLeapYear(int year) {
        if (year%4 == 0)
        {
            if (year%100==0){
                return year % 400 == 0;
            }
            else return true;
        }
        return false;
    }

    boolean isValidDate(int year, int month, int day, boolean cnt21){  //Осуществляет проверку даты на корректность.
        if (cnt21)
        {
            if (year < 2001 || year > 2100) return false;
        }
        else
        {
            if (year < 1) return false;
        }

        if (month < 1 || month > 12) return false;
        double daysInMonth = 28 + (month + Math.floor(month/8)) % 2 + 2 % month + 2 * Math.floor(1/month);
        if (isLeapYear(year) && month == 2) daysInMonth++;
        if (day <=0 | day>daysInMonth)return false;
        return true;
    }

    int getDayOfWeek(int year, int month, int day) {    //Возвращает номер дня недели, где 0 – MON, 6- SUN (Можно сделать enum – ом )
        if (!isValidDate(year,month,day,true)) throw new InputMismatchException();
        int monthCode = 0;
        switch (month)
        {
            case 1:
                monthCode =0;
                break;
            case 2:
                monthCode =3;
                break;
            case 3:
                monthCode =3;
                break;
            case 4:
                monthCode =6;
                break;
            case 5:
                monthCode =1;
                break;
            case 6:
                monthCode =4;
                break;
            case 7:
                monthCode =6;
                break;
            case 8:
                monthCode =2;
                break;
            case 9:
                monthCode =5;
                break;
            case 10:
                monthCode =0;
                break;
            case 11:
                monthCode =3;
                break;
            case 12:
                monthCode =5;
                break;
        }
        int yearCode = 0;
        if (isLeapYear(year)&& month==1|| month==2) yearCode = ((6 + (year%100)+(year%100)/4)%7)-1;
        else yearCode = (6 + (year%100)+(year%100)/4)%7;
        return (day+monthCode+yearCode)%7;
    }

    int countDays(int year, int month, int day)
    {
        if (!isValidDate(year,month,day,false)) throw new InputMismatchException();
        Long currentDate = System.currentTimeMillis()/1000/60/60/24;
        int inputDateDays = day;
        if (year > 1970)
        {
            for (int i = year; i > 1970; i--)
            {
                if (isLeapYear(i))inputDateDays+=366;
                else inputDateDays+=365;
            }
        }
        else
        {
            for (int i = year; i <=1970; i++)
            {
                if (isLeapYear(i))inputDateDays+=366;
                else inputDateDays+=365;
            }
        }
        for (int i = 1; i < month; i++) inputDateDays+= (int) (28 + (i + Math.floor(i/8)) % 2 + 2 % i + 2 * Math.floor(1/i));
        if (year > 1970)return (int) (currentDate-inputDateDays)+1;
        else return (int) (currentDate+inputDateDays)+1;
    }

    String toString(int year, int month, int day){
        return day_enum.values()[getDayOfWeek(year,month,day)-1]+", "+day+" "+month_enum.values()[month-1]+" "+year;
    }
    
}
