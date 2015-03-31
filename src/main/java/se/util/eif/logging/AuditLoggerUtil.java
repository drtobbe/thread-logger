package se.util.eif.logging;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.util.namespaces.eif.logging.auditlog._0001.AuditLog;
import se.util.namespaces.eif.logging.auditlog._0001.EnLogLevel;

/**
 * @author drtobbe
 */
public class AuditLoggerUtil extends EifLoggerUtil implements AuditLogger {
    private static final ThreadLocal<Format> formatLocal = new ThreadLocal<Format>();
    private static final String AUDITLOG = "auditlog.";
    private static JAXBContext auditLogContext;
    private static ObjectMapper mapper;
    private Logger clog;

    AuditLoggerUtil(String name) {
        this.clog = LoggerFactory.getLogger(AUDITLOG + name);
    }

    static {
        try {
            auditLogContext = JAXBContext.newInstance(AuditLog.class);
            mapper = new ObjectMapper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isDebugEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.DEBUG);
            auditLog.setDescription(msg);
            log.debug(marshall(auditLog));
        }
    }

    @Override
    public void infoBegin(String msg, EifMetaData metaData) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        initialize(metaData);
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.BEGIN.toString());
            log.info(marshall(auditLog));
        }
    }

    @Override
    public void infoEnd(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.END.toString());
            log.info(marshall(auditLog));
        }
    }

    @Override
    public void infoEndError(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.EXIT_WITH_ERROR.toString());
            log.info(marshall(auditLog));
        }
    }

    @Override
    public void infoSending(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.SENDING.toString());
            log.info(marshall(auditLog));
        }
    }

    @Override
    public void infoReceived(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setLocalState(LogConstants.LocalState.RECEIVED.toString());
            log.info(marshall(auditLog));
        }
    }

    @Override
    public void info(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isInfoEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.INFO);
            auditLog.setDescription(msg);
            log.info(marshall(auditLog));
        }
    }

    @Override
    public void info(Map<String, Object> map) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isWarnEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.WARN);
            auditLog.setDescription(msg);
            log.warn(marshall(auditLog));
        }
    }

    @Override
    public void error(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isErrorEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.ERROR);
            auditLog.setDescription(msg);
            log.error(marshall(auditLog));
        }
    }

    @Override
    public void error(String msg, Throwable t) {
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

    @Override
    public void fatal(String msg) {
        Logger log = LoggerFactory.getLogger(AUDITLOG + clog.getName());
        if (log.isErrorEnabled()) {
            AuditLog auditLog = populateAuditLog(msg);
            auditLog.setLogLevel(EnLogLevel.FATAL);
            auditLog.setDescription(msg);
            log.error(marshall(auditLog));
        }
    }

    private AuditLog populateAuditLog(String msg) {
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
            switch (getFormat()) {
            case XML:
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
            case CSV:
                try {
                    return CSVWriter.produceCsvData(auditLog);
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            case JSON:
                try {
                    StringWriter sw = new StringWriter();
                    mapper.writeValue(sw, auditLog);
                    return sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            default:
                return "Error: unknown format: " + getFormat();
            }
        } else {
            return "Error: auditLogContext is null!";
        }
    }

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
