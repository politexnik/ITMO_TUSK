import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class Main {
    public static void main(String s[]) throws IOException, ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        System.out.println(calendar.getTime());

        RoadChecker roadChecker = new MyRoadChecker();
        int count = roadChecker.getRestrictedRoads("data-20181029T075308-structure-20160117T173719.csv",
                calendar.getTime());

        System.out.println(count);
    }


}
