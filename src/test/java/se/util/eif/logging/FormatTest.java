package se.util.eif.logging;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author drtobbe
 */
public class FormatTest {
    private static Logger log = LoggerFactory.getLogger(FormatTest.class);
    private EifMetaData metaData;

    @Before
    public void setup() {
        metaData = new EifMetaData();
        metaData.setBusinessProcessName("CintEcs.Security.SubscriberAdmin.BProc");
        metaData.setBusinessPartName("Cint.Security.SubscriberAuth.Auth.BPart");
        metaData.setBusinessFragmentName("SgNessSoaComposite.AdminServiceC.BFrag");
        //
        metaData.setSystemName("SystemName");
        metaData.setFunctionalModuleName("FunctionalModuleName");
        metaData.setFunctionalModuleInstanceId("1");
    }

    @Test
    public void testXML() {
        AuditLoggerUtil.infoBegin(log, "Begin testing auditlog", metaData);
        AuditLoggerUtil.infoEnd(log, "End testing auditlog");
    }

    @Test
    public void testExceptionJSON() {
        SystemLoggerUtil.useFormat(Format.JSON);
        SystemLoggerUtil.application(log, "This is a info", metaData);
        SystemLoggerUtil.error(log, "This is a error", new Exception("This is a Exception message"));
    }

    @Test
    public void testErrorCSV() {
        AuditLoggerUtil.useFormat(Format.CSV);
        AuditLoggerUtil.infoBegin(log, "Begin testing", metaData);
        AuditLoggerUtil.error(log, "This is a error", new Exception("This is a Exception message"));
    }

}