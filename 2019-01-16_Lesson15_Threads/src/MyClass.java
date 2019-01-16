public class MyClass {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }

    public static class MyThread extends Thread {
        static Object obj = new Object();

        @Override
        public void run() {
            while (true)
                synchronized (obj){
                    try {
                        obj.notify();
                        obj.wait();
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }

        public synchronized void printName() {

        }
    }

}
