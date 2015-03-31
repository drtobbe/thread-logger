package se.util.eif.logging;

public class LoggerFactory {

    public static AuditLogger getAuditLogger(Class<?> clazz) {
        return new AuditLoggerUtil(clazz);
    }

    public static SystemLogger getSystemLogger(Class<?> clazz) {
        return new SystemLoggerUtil(clazz);
    }

}
