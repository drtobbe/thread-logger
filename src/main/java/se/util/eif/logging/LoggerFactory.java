package se.util.eif.logging;

public class LoggerFactory {

    public static AuditLogger getAuditLogger(String name) {
        return new AuditLoggerUtil(name);
    }

    public static AuditLogger getAuditLogger(Class<?> clazz) {
        return new AuditLoggerUtil(clazz.getName());
    }

    public static SystemLogger getSystemLogger(String name) {
        return new SystemLoggerUtil(name);
    }

    public static SystemLogger getSystemLogger(Class<?> clazz) {
        return new SystemLoggerUtil(clazz.getName());
    }

}
