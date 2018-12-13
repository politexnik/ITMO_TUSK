package RU.POLITEXNIK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    List<PointNode> pointList;
    double length;

    public Route(PointNode point){
        pointList = new ArrayList<>();
        pointList.add(point);
        length = 0;
    }

    public Route(Route previuosWay){
        pointList = new ArrayList<>(previuosWay.pointList.size() * 2);
        //Collections.copy(pointList, previuosWay.pointList);
        for (PointNode point: previuosWay.pointList) {
            pointList.add(point);
        }
        length = previuosWay.length;
    }

    public void add(PointNode point){
        length += Math.sqrt(Math.pow(point.x - pointList.get(pointList.size() - 1).x, 2) +
                        Math.pow(point.y - pointList.get(pointList.size() - 1).y, 2));
        pointList.add(point);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (PointNode point: pointList) {
            stringBuffer = stringBuffer.append(point.name + " " + point.x + " " + point.y + "\n");
        }
        return stringBuffer.toString();
    }
}
