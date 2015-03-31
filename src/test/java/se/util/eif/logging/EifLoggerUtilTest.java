package se.util.eif.logging;

import org.junit.Before;
import org.junit.Test;

/**
 * @author drtobbe
 */
public class EifLoggerUtilTest {
    private static AuditLogger alog = LoggerFactory.getAuditLogger(EifLoggerUtilTest.class);
    private static SystemLogger slog = LoggerFactory.getSystemLogger(EifLoggerUtilTest.class);
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
        slog.application("This is a info", metaData);
        slog.error("This is a error", new Exception("This is a Exception message"));
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
            slog.info("This is a System info: " + i);
        }
        System.out.println("###");
        System.out.println("elapsed: " + (System.currentTimeMillis() - start + 0f) / num + " millis/log");
    }

}