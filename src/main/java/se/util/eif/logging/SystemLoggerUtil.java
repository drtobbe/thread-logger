package se.util.eif.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.util.namespaces.eif.logging.systemlog._0001.EnLogLevel;
import se.util.namespaces.eif.logging.systemlog._0001.SystemLog;

/**
 * @author drtobbe
 */
public class SystemLoggerUtil extends EifLoggerUtil implements SystemLogger {
    private static final ThreadLocal<Format> formatLocal = new ThreadLocal<Format>();
    private static final String SYSTEMLOG = "systemlog.";
    private static JAXBContext syslogContext;
    private static ObjectMapper mapper;
    private Logger clog;

    SystemLoggerUtil(String name) {
        this.clog = LoggerFactory.getLogger(SYSTEMLOG + name);
    }

    static {
        try {
            syslogContext = JAXBContext.newInstance(SystemLog.class);
            mapper = new ObjectMapper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#application(java.lang.String, se.util.eif.logging.EifMetaData)
     */
    @Override
    public void application(String msg, EifMetaData metaData) {
        initialize(metaData);
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isInfoEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.INFO);
            sysLog.setDebugInformation(msg);
            log.info(marshall(sysLog));
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isDebugEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.DEBUG);
            sysLog.setDebugInformation(msg);
            log.debug(marshall(sysLog));
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#info(java.lang.String)
     */
    @Override
    public void info(String msg) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isInfoEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.INFO);
            sysLog.setDebugInformation(msg);
            log.info(marshall(sysLog));
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isWarnEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.WARN);
            sysLog.setErrorMessage(msg);
            log.warn(marshall(sysLog));
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#error(java.lang.String)
     */
    @Override
    public void error(String msg) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isErrorEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.ERROR);
            sysLog.setErrorMessage(msg);
            log.error(marshall(sysLog));
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(String msg, Throwable t) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isErrorEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.ERROR);
            sysLog.setDebugInformation(msg);
            sysLog.setErrorMessage(msg);
            sysLog.setStackTrace(getStackTrace(t));
            log.error(marshall(sysLog));
        }
    }

    private static String getStackTrace(Throwable t) {
        if (t != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            t.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } else {
            return null;
        }
    }

    static String error(Logger clog, String msg, Throwable t, String linkedAuditId) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isErrorEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.ERROR);
            sysLog.setErrorMessage(msg);
            sysLog.setStackTrace(getStackTrace(t));
            sysLog.setLinkedLogItemId(linkedAuditId);
            log.error(marshall(sysLog));
            return sysLog.getLogItemId();
        } else {
            return null;
        }
    }

    /**
     * SystemLog an exception (throwable) at the ERROR level with an
     * accompanying message. 
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     * @param probableCause the probableCause
     * @param proposedAction the proposedAction
     * @param t the exception (throwable) to log
     */
    public static void error(Logger clog, String msg, String probableCause, String proposedAction, Throwable t) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isErrorEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.ERROR);
            sysLog.setErrorMessage(msg);
            sysLog.setProbableCause(probableCause);
            sysLog.setProposedAction(proposedAction);
            sysLog.setStackTrace(getStackTrace(t));
            log.error(marshall(sysLog));
        }
    }

    /**
     * SystemLog a message at the FATAL level.
     *
     * @param msg the message string to be logged
     */
    public static void fatal(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(SYSTEMLOG + clog.getName());
        if (log.isErrorEnabled()) {
            SystemLog sysLog = populateSystemLog(msg);
            sysLog.setLogLevel(EnLogLevel.FATAL);
            sysLog.setErrorMessage(msg);
            log.error(marshall(sysLog));
        }
    }

    private static SystemLog populateSystemLog(String msg) {
        SystemLog sysLog = new SystemLog();
        EifMetaData metaData = getEifMetaData();
        sysLog.setBusinessFragmentInstanceId(metaData.getBusinessFragmentInstanceId());
        sysLog.setBusinessFragmentName(metaData.getBusinessFragmentName());
        sysLog.setBusinessPartInstanceId(metaData.getBusinessPartInstanceId());
        sysLog.setBusinessPartName(metaData.getBusinessPartName());
        sysLog.setBusinessProcessInstanceId(metaData.getBusinessProcessInstanceId());
        sysLog.setBusinessProcessName(metaData.getBusinessProcessName());
        sysLog.setFunctionalModuleInstanceId(metaData.getFunctionalModuleInstanceId());
        sysLog.setFunctionalModuleName(metaData.getFunctionalModuleName());
        sysLog.setSystemName(metaData.getSystemName());
        sysLog.setHostname(metaData.getHostname());
        // set LogId and DateTime
        sysLog.setSourceName(getSource());
        sysLog.setLogItemId(UUID.randomUUID().toString());
        sysLog.setTimestamp(getXMLGregorianCalendarNow());
        return sysLog;
    }

    private static String marshall(SystemLog systemLog) {
        if (syslogContext != null) {
            switch (getFormat()) {
            case XML:
                try {
                    Marshaller syslogMarshaller = syslogContext.createMarshaller();
                    syslogMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    StringWriter sw = new StringWriter();
                    syslogMarshaller.marshal(systemLog, sw);
                    return sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            case CSV:
                try {
                    return CSVWriter.produceCsvData(systemLog);
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            case JSON:
                try {
                    StringWriter sw = new StringWriter();
                    mapper.writeValue(sw, systemLog);
                    return sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            default:
                return "Error: unknown format: " + getFormat();
            }
        } else {
            return "Error: syslogContext is null!";
        }
    }

    /* (non-Javadoc)
     * @see se.util.eif.logging.SystemLogger#useFormat(se.util.eif.logging.Format)
     */
    @Override
    public void useFormat(Format format) {
        formatLocal.set(format);
    }

    public static Format getFormat() {
        Format format = formatLocal.get();
        if (format == null) {
            format = Format.XML;
            formatLocal.set(format);
        }
        return format;
    }

}
