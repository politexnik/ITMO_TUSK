package RU.POLITEXNIK;

import java.util.ArrayList;
import java.util.List;

public class PointNode {
    String name;
    double x;
    double y;
    List<PointNode> connectionPointList;

    public PointNode(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
        connectionPointList = new ArrayList<>();
    }

    public void connectWithPoint(PointNode B){
        connectionPointList.add(B);
        B.connectionPointList.add(this);
    }
}
