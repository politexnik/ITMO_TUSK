import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class MyClass {


//1. Напишите метод, который будет по имени класса возвращать созданный экземпляр класса. Его поля (примитивных типов)
// должны быть заполнены случайными значениями.
    public static <T> T createClassInstance(String className) throws  ReflectiveOperationException {
       T object =  (T)Class.forName(className).getConstructor().newInstance();
        Field[] fields = object.getClass().getDeclaredFields();
        Random random = new Random();
        for (Field field: fields) {
            if (!field.canAccess(object))
                field.setAccessible(true);
            field.set(object, random.nextInt(120));
        }
       return object;
    }

//3. Напишите метод, осуществляющий поиск несортированных массивов. Не забудьте про лямбды


}
