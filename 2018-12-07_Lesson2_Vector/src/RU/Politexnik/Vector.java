package RU.Politexnik;

import java.util.Random;

public class Vector {
    private double x;
    private double y;
    private double z;

    public double length;

    public static void main(String[] args) {
	// write your code here
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    public double getLength() {
        return length;
    }

    public double scalarProduct(Vector v){
        return x * v.x + y * v.y + z * v.z;
    }

    public Vector scalarProductWith(Vector v) {
        return new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getCosAngleWithVector(Vector v) {
        return scalarProduct(v) / (length * v.length);
    }

    public double getAngleWithVector(Vector v) {
        return Math.acos(getCosAngleWithVector(v));
    }

    public Vector summ(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }
    public Vector difference(Vector v) {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }

    static Vector[] getRandomVectorArray(int size){
        Random random = new Random();
        Vector[] arrayVector = new Vector[size];
        for (int i = 0; i < size; i++) {
            arrayVector[i] = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
        }
        return arrayVector;
    }
}
