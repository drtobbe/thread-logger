package se.util.eif.logging;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.util.namespaces.eif.logging.auditlog._0001.AuditLog;
import se.util.namespaces.eif.logging.auditlog._0001.EnLogLevel;

/**
 * @author drtobbe
 */
public abstract class AuditLoggerUtil extends EifLoggerUtil {
    private static final String AUDITLOG = "auditlog.";
    private static JAXBContext auditLogContext;

    static {
        try {
            auditLogContext = JAXBContext.newInstance(AuditLog.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AuditLog a message at the DEBUG level.
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void debug(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isDebugEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.DEBUG);
            auditLog.setDescription(msg);
            log.debug(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the INFO level and inticate the beginning of a request
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     * @param metaData the metaData to be cloned
     */
    public static void infoBegin(Logger clog, String msg, EifMetaData metaData) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        initialize(metaData);
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.BEGIN.toString());
            log.info(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the INFO level and inticate the end of a request
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void infoEnd(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.END.toString());
            log.info(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the INFO level and inticate the end of a request with error
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void infoEndError(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.EXIT_WITH_ERROR.toString());
            log.info(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the INFO level and inticate start of a outbound request
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void infoSending(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.SENDING.toString());
            log.info(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the INFO level and inticate end of a outbound request
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void infoReceived(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.RECEIVED.toString());
            log.info(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the INFO level.
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void info(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setDescription(msg);
            log.info(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the WARN level.
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void warn(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isWarnEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.WARN);
            auditLog.setDescription(msg);
            log.warn(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the ERROR level.
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void error(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isErrorEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.ERROR);
            auditLog.setDescription(msg);
            log.error(marshall(auditLog));
        }
    }

    /**
     * AuditLog an exception (throwable) at the ERROR level with an
     * accompanying message. 
     * 
     * @param log the logger to be used
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    public static void error(Logger clog, String msg, Throwable t) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isErrorEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.ERROR);
            auditLog.setDescription(msg);
            //bidirectional id references
            String sid = SystemLoggerUtil.error(log, msg, t, auditLog.getLogItemId());
            auditLog.setSystemLogItemId(sid);
            log.error(marshall(auditLog));
        }
    }

    /**
     * AuditLog a message at the FATAL level.
     *
     * @param log the logger to be used
     * @param msg the message string to be logged
     */
    public static void fatal(Logger clog, String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isErrorEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.FATAL);
            auditLog.setDescription(msg);
            log.error(marshall(auditLog));
        }
    }

    private static AuditLog populateAuditLog(String msg) {
        AuditLog auditLog = new AuditLog();
        EifMetaData metaData = getEifMetaData();
        auditLog.setBusinessFragmentInstanceId(metaData.getBusinessFragmentInstanceId());
        auditLog.setBusinessFragmentName(metaData.getBusinessFragmentName());
        auditLog.setBusinessObjectType(metaData.getBusinessObjectType());
        auditLog.setBusinessPartInstanceId(metaData.getBusinessPartInstanceId());
        auditLog.setBusinessPartName(metaData.getBusinessPartName());
        auditLog.setBusinessProcessInstanceId(metaData.getBusinessProcessInstanceId());
        auditLog.setBusinessProcessName(metaData.getBusinessProcessName());
        auditLog.setFunctionalModuleInstanceId(metaData.getFunctionalModuleInstanceId());
        auditLog.setFunctionalModuleName(metaData.getFunctionalModuleName());
        auditLog.setGlobalState(metaData.getGlobalState());
        auditLog.setHostname(metaData.getHostname());
        auditLog.setReceiverInformation(metaData.getReceiverInformation());
        auditLog.setRelatedDocumentId(metaData.getRelatedDocumentId());
        auditLog.setRelatedDocumentLocation(metaData.getRelatedDocumentLocation());
        auditLog.setRelatedDocumentType(metaData.getRelatedDocumentType());
        auditLog.setSystemName(metaData.getSystemName());
        auditLog.setTransactionIdentity(metaData.getTransactionIdentity());
        // get audit sequence and increment
        auditLog.setAuditSequenceNo(BigInteger.valueOf(metaData.getAuditSequenceNoAndIncrement()));
        auditLog.setLogItemId(UUID.randomUUID().toString());
        auditLog.setTimestamp(getXMLGregorianCalendarNow());
        auditLog.setDescription(msg);
        auditLog.setSourceName(getSource());
        auditLog.setKeyFields(metaData.getKeyFields());
        return auditLog;
    }

    private static String marshall(AuditLog auditLog) {
        if (auditLogContext != null) {
            try {
                Marshaller auditMarshaller = auditLogContext.createMarshaller();
                auditMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter sw = new StringWriter();
                auditMarshaller.marshal(auditLog, sw);
                return sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            return "Error: auditLogContext is null!";
        }
    }

}
