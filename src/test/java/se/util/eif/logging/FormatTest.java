package se.util.eif.logging;

import org.junit.Before;
import org.junit.Test;

/**
 * @author drtobbe
 */
public class FormatTest {
    private static AuditLogger alog = LoggerFactory.getAuditLogger(FormatTest.class);
    private static SystemLogger slog = LoggerFactory.getSystemLogger(FormatTest.class);
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
        alog.infoBegin("Begin testing auditlog", metaData);
        alog.infoEnd("End testing auditlog");
    }

    @Test
    public void testExceptionJSON() {
        slog.useFormat(Format.JSON);
        slog.application("This is a info", metaData);
        slog.error("This is a error", new Exception("This is a Exception message"));
    }

    @Test
    public void testErrorCSV() {
        alog.useFormat(Format.CSV);
        alog.infoBegin("Begin testing", metaData);
        alog.error("This is a error", new Exception("This is a Exception message"));
    }

}