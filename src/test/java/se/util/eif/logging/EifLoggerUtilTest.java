package se.util.eif.logging;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author drtobbe
 */
public class EifLoggerUtilTest {
    private static AuditLogger alog = AuditLoggerUtil.getLogger(EifLoggerUtilTest.class);
    private static Logger logse = LoggerFactory.getLogger(EifLoggerUtilTest.class);
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
        alog.infoBegin("Begin testing auditlog", metaData);
        alog.infoEnd("End testing auditlog");
    }

    @Test
    public void testException() {
        SystemLoggerUtil.application(logse, "This is a info", metaData);
        SystemLoggerUtil.error(logse, "This is a error", new Exception("This is a Exception message"));
    }

    @Test
    public void testError() {
        alog.infoBegin("Begin testing", metaData);
        alog.error("This is a error", new Exception("This is a Exception message"));
    }

    @Test
    public void testLoading() throws Exception {
        alog.infoBegin("Begin testing auditlog", metaData);
        //
        long start = System.currentTimeMillis();
        int num = 10;
        for (int i = 0; i < num; i++) {
            alog.info("This is a Audit info: " + i);
            SystemLoggerUtil.info(logse, "This is a System info: " + i);
        }
        System.out.println("###");
        System.out.println("elapsed: " + (System.currentTimeMillis() - start + 0f) / num + " millis/log");
    }

}