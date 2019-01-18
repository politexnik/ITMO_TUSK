


import java.util.Arrays;

import java.util.Comparator;



    public class FilterClass{

        public static void FilterClass(String ars[]){
            String[] array = new String[]{"1", "23", "string", "test", "Hello, Java!"};
            sortAndPrint(array);
            ArraysUtil.filter(array, new ArraysUtil.Filter() {
                @Override
                public boolean apply(Object o) {
                    return o.toString().length() > 2;
                }
            });
        }

    /*
        Дан​ ​ массив​ ​ строк​. Выведете
        их​ ​ все​ ​ разными​ ​ способами:
        По​ ​ алфавиту
        По​ ​ возрастанию​ ​ длины
        По​ ​ количеству​ ​ вхождений​ ​ цифр​ ​ в ​ ​ строку
     */

        private static void sortAndPrint(String[] strings){
            Arrays.sort(strings);
            System.out.println(Arrays.toString(strings));
            Arrays.sort(strings, (o1, o2) -> {
                    return Integer.compare(o1.toString().length(), o2.toString().length());
            });

            System.out.println(Arrays.toString(strings));
            Arrays.sort(strings, new Comparator() {

                @Override
                public int compare(Object o1, Object o2) {
                    return Integer.compare(digitsCount(o1.toString()), digitsCount(o2.toString()));
                }

                private int digitsCount(String s){
                    char[] chars = s.toCharArray();
                    int count = 0;
                    for(char ch : chars){
                        if(Character.isDigit(ch)) // or ch >= '0' && ch <='9'
                            count++;
                    }
                    return count;
                }
            });

            System.out.println(Arrays.toString(strings));
        }
    }

    class ArraysUtil {
        public interface Filter{
            boolean apply(Object o);
        }

        public static Object[] filter(Object[] array, Filter filter){
            int removed = 0;
            for(int i = 0; i < array.length; i++){
                if(!filter.apply(array[i])){
                    for(int j = i; j < array.length - 1 - removed; j++){
                        array[j] = array[j + 1];
                    }
                    removed++;
                }
            }

            return java.util.Arrays.copyOf(array, array.length - removed);
        }
}
