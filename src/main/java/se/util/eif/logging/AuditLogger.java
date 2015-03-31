package se.util.eif.logging;

public interface AuditLogger {

    public void useFormat(Format format);

    /**
    * AuditLog a message at the DEBUG level.
    *
    * @param msg the message string to be logged
    */
    public void debug(String msg);

    /**
     * AuditLog a message at the INFO level and inticate the beginning of a request
     *
     * @param msg the message string to be logged
     * @param metaData the metaData to be cloned
     */
    public void infoBegin(String msg, EifMetaData metaData);

    /**
     * AuditLog a message at the INFO level and inticate the end of a request
     *
     * @param msg the message string to be logged
     */
    public void infoEnd(String msg);

    /**
     * AuditLog a message at the INFO level and inticate the end of a request with error
     *
     * @param msg the message string to be logged
     */
    public void infoEndError(String msg);

    /**
     * AuditLog a message at the INFO level and inticate start of a outbound request
     *
     * @param msg the message string to be logged
     */
    public void infoSending(String msg);

    /**
     * AuditLog a message at the INFO level and inticate end of a outbound request
     *
     * @param msg the message string to be logged
     */
    public void infoReceived(String msg);

    /**
     * AuditLog a message at the INFO level.
     *
     * @param msg the message string to be logged
     */
    public void info(String msg);

    /**
     * AuditLog a message at the WARN level.
     *
     * @param msg the message string to be logged
     */
    public void warn(String msg);

    /**
     * AuditLog a message at the ERROR level.
     *
     * @param msg the message string to be logged
     */
    public void error(String msg);

    /**
     * AuditLog an exception (throwable) at the ERROR level with an
     * accompanying message. 
     * 
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    public void error(String msg, Throwable t);

    /**
     * AuditLog a message at the FATAL level.
     *
     * @param msg the message string to be logged
     */
    public void fatal(String msg);

}