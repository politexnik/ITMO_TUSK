package RU.POLITEXNIK;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        PointNode A = new PointNode("A",0,0);
        PointNode B = new PointNode("B",20,10);
        PointNode C = new PointNode("C",40,20);
        PointNode D = new PointNode("D",30,30);
        PointNode E = new PointNode("E",20,50);
        PointNode F = new PointNode("F",-10,40);
        PointNode G = new PointNode("G",10,20);
        PointNode H = new PointNode("H",10,40);
        PointNode I = new PointNode("I",30,40);
        PointNode K = new PointNode("K",30,20);
        PointNode L = new PointNode("L",40,40);

        A.connectWithPoint(B);
        A.connectWithPoint(G);
        G.connectWithPoint(F);
        G.connectWithPoint(H);
        G.connectWithPoint(D);
        K.connectWithPoint(L);
        B.connectWithPoint(C);
        D.connectWithPoint(I);
        D.connectWithPoint(E);
        L.connectWithPoint(E);
        F.connectWithPoint(E);
        H.connectWithPoint(E);
        I.connectWithPoint(E);

        Route route = searchWay(A, E);
        System.out.println(route);

    }

    public static Route searchWay(PointNode A, PointNode B){
        PointNode currentPoint = A;
        Route currentWay = new Route(A);

        PriorityQueue<Route> queue = new PriorityQueue<>((o1, o2) -> {  //приоритет для очереди маршрутов считается по
            //сумме пройденного расстояния и оставшегося пути по прямой
            PointNode o1LastPoint = o1.pointList.get(o1.pointList.size() - 1);
            PointNode o2LastPoint = o2.pointList.get(o2.pointList.size() - 1);
            double fx1 = o1.length + Math.sqrt(Math.pow(o1LastPoint.x - B.x, 2) + Math.pow(o1LastPoint.x - B.y, 2));
            double fx2 = o2.length + Math.sqrt(Math.pow(o2LastPoint.x - B.x, 2) + Math.pow(o2LastPoint.x - B.y, 2));
            if (fx1 - fx2 == 0) return 0;
            return fx1 - fx2 > 0? 1: -1;
            });
        queue.add(currentWay);
        //создаем пути по всем возможным направлениям
        while (!queue.isEmpty()) {
            currentWay = queue.poll();
            currentPoint = currentWay.pointList.get(currentWay.pointList.size() - 1);   //берем последнюю точку в пути
            for (PointNode point: currentPoint.connectionPointList) {
                if (point == currentWay.pointList.get(currentWay.pointList.size() - 1)) continue;   // если путь обратно - следующая итерация
                Route way = new Route(currentWay);
                way.add(point);
                queue.add(way);
                if (point == B) {
                    return way;
                }
            }
        }

        return currentWay;
    }
}
