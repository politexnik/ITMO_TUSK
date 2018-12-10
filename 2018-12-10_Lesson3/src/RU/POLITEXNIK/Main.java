package RU.POLITEXNIK;

public class Main {

    public static void main(String[] args) {
        // write your code here
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            getStepenIter(13, 200);
        }
        System.out.println((System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            getStepenRecurs(13, 200);
        }

        System.out.println((System.currentTimeMillis() - time));

        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        time = System.currentTimeMillis();
        System.out.println(containsInArray(arr, 99999));
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        System.out.println(containsInArrayBooleanSearch(arr, 99999));
        System.out.println(System.currentTimeMillis() - time);

        System.out.println(findRoot(0, 10));

    }

    public static long getStepenIter(int i, int n) {
        long res = i;
        for (int j = 2; j <= n; j++) {
            res = i * res;
        }
        return res;
    }

    public static long getStepenRecurs(long i, int n) {
        if (n == 1) return i;
        int m = n / 2;
        n -= m;
        i = getStepenRecurs(i, m) * getStepenRecurs(i, n);
        return i;
    }


    public static void sort(int[] arr) {
        for (int min = 0; min < arr.length - 1; min++) {
            int least = min;
            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j] < arr[least]) {
                    least = j;
                }
            }
            int tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
        }
    }

    public static boolean containsInArray(int[] arr, int n) {
        for (int i : arr) {
            if (i == n) return true;
        }
        return false;
    }

    public static boolean containsInArrayBooleanSearch(int[] arr, int n) {
        int i = -1;
        if (arr != null) {
            int low = 0, high = arr.length, mid;
            while (low < high) {
                mid = (low + high) / 2; // Можно заменить на: (low + high) >>> 1, чтоб не возникло переполнение целого
                if (n == arr[mid]) {
                    i = mid;
                    return true;
                } else {
                    if (n <= arr[mid]) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return false;
    }

    public static double findRoot(double x1, double x2){
        if (x2 - x1 < 0.001) return (x1 + x2) / 2;
        double midX = (x1 + x2) / 2;

        double y1 = fy(x1);
        double y2 = fy(x2);
        double yMid = fy(midX);
        if (yMid > 0 && y2 > 0) return findRoot(x1, midX);
        if (yMid < 0 && y1 < 0) return findRoot(midX, x2);

        return 0;
    }

    public static double fy(double x) {
        return Math.cos(Math.pow(x, 5)) + Math.pow(x, 4) - 345.3 * x - 23;
    }


}
