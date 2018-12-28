import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyRoadChecker implements RoadChecker {


    @Override
    public int getRestrictedRoads(String fileName, Date date) throws IOException, ParseException {
        String[] lines = Files.readAllLines(Paths.get(fileName)).toArray(new String[]{});
        int count = 0;

        for (int i = 1; i < lines.length; i++) {
            String[] partsString = lines[i].split(",");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date startRestrDate = simpleDateFormat.parse(partsString[10]);
            Date endRestrDate;
            if (partsString[12].equals("-")) {
                endRestrDate = simpleDateFormat.parse(partsString[11]);
            } else {
                endRestrDate = simpleDateFormat.parse(partsString[12]);
            }

            if (date.after(startRestrDate) && date.before(endRestrDate)) {
                count++;
            }
        }
        return count;
    }
}
