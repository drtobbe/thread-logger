package se.util.eif.logging;

import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author drtobbe
 */
abstract class EifLoggerUtil {
    private static final ThreadLocal<EifMetaData> threadLocal = new ThreadLocal<EifMetaData>();

    protected static XMLGregorianCalendar getXMLGregorianCalendarNow() {
        try {
            GregorianCalendar gregCal = (GregorianCalendar) GregorianCalendar.getInstance();
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void initialize(EifMetaData eifMetaData) {
        // clone a copy for this Thread
        EifMetaData metaData = eifMetaData.clone();

        // always generate a new fragmentId on initiliaze
        metaData.setBusinessFragmentInstanceId(UUID.randomUUID().toString());

        // generate BusinessPartInstanceId if not defined
        String partId = metaData.getBusinessPartInstanceId();
        if (partId == null || partId.length() == 0) {
            metaData.setBusinessPartInstanceId(UUID.randomUUID().toString());
        } else {
            metaData.setBusinessPartInstanceId(partId);
        }

        // generate BusinessPartInstanceId if not defined
        String processId = metaData.getBusinessProcessInstanceId();
        if (processId == null || processId.length() == 0) {
            metaData.setBusinessProcessInstanceId(UUID.randomUUID().toString());
        } else {
            metaData.setBusinessProcessInstanceId(processId);
        }

        // set global state if not defined
        String globalState = metaData.getGlobalState();
        if (globalState == null || globalState.length() == 0) {
            metaData.setGlobalState(LogConstants.GlobalState.UNDEFINED.toString());
        } else {
            metaData.setGlobalState(globalState);
        }
        threadLocal.set(metaData);
    }

    protected static EifMetaData getEifMetaData() {
        EifMetaData eifMetaData = threadLocal.get();
        if (eifMetaData == null) {
            eifMetaData = new EifMetaData();
            Exception exception = new Exception("EifLoggerUtil not initialized on Thread");
            exception.printStackTrace();
        }
        return eifMetaData;
    }

    protected static String getSource() {
        try {
            return (new Throwable()).getStackTrace()[3].toString();
        } catch (Exception e) {
            return null;
        }
    }

}
