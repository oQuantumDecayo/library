package adaptorlib;
import java.util.Calendar;
import java.util.Date;
/**
 * ScheduleOncePerDay provides a sequence of Dates 1 day
 * apart at the same hour/minute/second each day.
 * <p>
 * Gratitude to
 * http://www.ibm.com/developerworks/java/library/j-schedule/index.html
 */
public class ScheduleOncePerDay implements ScheduleIterator {
  private final int hourOfDay, minute, second;
  private final Calendar calendar = Calendar.getInstance();

  public ScheduleOncePerDay(int hourOfDay, int minute, int second) {
    this(hourOfDay, minute, second, new Date());
  }

  public ScheduleOncePerDay(int hourOfDay, int minute, int second, Date date) {
    this.hourOfDay = hourOfDay;
    this.minute = minute;
    this.second = second;
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
    calendar.set(Calendar.MILLISECOND, 0);
    if (!calendar.getTime().before(date)) {
      calendar.add(Calendar.DATE, -1);
    }
  }

  public Date next() {
    calendar.add(Calendar.DATE, 1);
    return calendar.getTime();
  }

  public static void main(String a[]) {
    ScheduleIterator it = new ScheduleOncePerDay(7, 0, 0);
    for (int i = 0; i < 5; i++) {
      Date d = it.next();
      System.out.println("" + d);
    }
  }
}
