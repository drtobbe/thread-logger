package se.util.eif.logging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CSVWriter {

    public static String produceCsvData(Object data) throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, SecurityException {

        Class<?> classType = data.getClass();
        StringBuilder builder = new StringBuilder();
        
        Method method = classType.getMethod("getTimestamp");
        Object time = method.invoke(data);
        if (time != null) {
            builder.append(time.toString());
        }
        builder.append(": ");

        Method[] methods = classType.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    Object invoke = m.invoke(data);
                    if (invoke != null) {
                        builder.append(invoke.toString());
                    }
                    builder.append(' ');
                }
            }
        }
        return builder.toString();
    }

}
