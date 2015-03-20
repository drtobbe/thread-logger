package se.util.eif.logging;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.util.eif.logging.AuditLoggerUtil;
import se.util.eif.logging.EifMetaData;
import se.util.eif.logging.SystemLoggerUtil;

/**
 * @author drtobbe
 */
public class EifLoggerUtilTest {
    private static Logger log = LoggerFactory.getLogger(EifLoggerUtilTest.class);
    private static Logger logse = LoggerFactory.getLogger("silence");
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
    public void test() {
        AuditLoggerUtil.infoBegin(log, "Begin testing auditlog", metaData);
        AuditLoggerUtil.infoEnd(log, "End testing auditlog");
    }

    @Test
    public void testException() {
        SystemLoggerUtil.application(log, "This is a info", metaData);
        SystemLoggerUtil.error(log, "This is a error", new Exception("This is a Exception message"));
    }

    @Test
    public void testError() {
        AuditLoggerUtil.infoBegin(log, "Begin testing", metaData);
        AuditLoggerUtil.error(log, "This is a error", new Exception("This is a Exception message"));
    }

    @Test
    public void testLoading() throws Exception {
        AuditLoggerUtil.infoBegin(logse, "Begin testing auditlog", metaData);
        //
        long start = System.currentTimeMillis();
        int num = 10;
        for (int i = 0; i < num; i++) {
            AuditLoggerUtil.info(logse, "This is a Audit info: " + i);
            SystemLoggerUtil.info(logse, "This is a System info: " + i);
        }
        System.out.println("###");
        System.out.println("elapsed: " + (System.currentTimeMillis() - start + 0f) / num + " millis/log");
    }

}