package se.util.eif.logging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import se.util.namespaces.eif.logging.auditlog._0001.AuditLog;
import se.util.namespaces.eif.logging.systemlog._0001.SystemLog;

/**
 * @author drtobbe
 */
public class CSVWriter {

    public static String produceCsvData(AuditLog auditLog) throws IllegalAccessException, InvocationTargetException {
        StringBuilder builder = new StringBuilder();
        builder.append(auditLog.getTimestamp()).append(" ");
        builder.append(auditLog.getLogLevel()).append(" ");
        builder.append(auditLog.getDescription()).append(" ");
        scan(auditLog, builder);
        return builder.toString();
    }

    public static String produceCsvData(SystemLog systemLog) throws IllegalAccessException, InvocationTargetException {
        StringBuilder builder = new StringBuilder();
        builder.append(systemLog.getTimestamp()).append(" ");
        builder.append(systemLog.getLogLevel()).append(" ");
        builder.append(systemLog.getDebugInformation()).append(" ");
        scan(systemLog, builder);
        return builder.toString();
    }

    private static void scan(Object data, StringBuilder builder) throws IllegalAccessException,
            InvocationTargetException {
        Class<?> classType = data.getClass();
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
    }

}
