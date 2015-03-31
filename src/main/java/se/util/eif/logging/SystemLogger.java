package se.util.eif.logging;

public interface SystemLogger {

    /**
     * SystemLog a message at the INFO level and initialize the EifMetaData on the current thread
     *
     * @param msg the message string to be logged
     * @param metaData the metaData to be cloned
     */
    public void application(String msg, EifMetaData metaData);

    /**
     * SystemLog a message at the DEBUG level.
     *
     * @param msg the message string to be logged
     */
    public void debug(String msg);

    /**
     * SystemLog a message at the INFO level.
     *
     * @param msg the message string to be logged
     */
    public void info(String msg);

    /**
     * SystemLog a message at the WARN level.
     *
     * @param msg the message string to be logged
     */
    public void warn(String msg);

    /**
     * SystemLog a message at the ERROR level.
     *
     * @param msg the message string to be logged
     */
    public void error(String msg);

    /**
     * SystemLog an exception (throwable) at the ERROR level with an
     * accompanying message. 
     * 
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    public void error(String msg, Throwable t);

    public void useFormat(Format format);

}