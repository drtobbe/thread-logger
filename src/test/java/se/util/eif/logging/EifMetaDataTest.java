package se.util.eif.logging;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author drtobbe
 */
public class EifMetaDataTest {
    private static SystemLogger slog = LoggerFactory.getSystemLogger(EifMetaDataTest.class);

    @Test
    public void test() {
        EifMetaData data = new EifMetaData();
        String props = "businessProcessName=BusProc, businessPartName=BusPart, businessFragmentName=FraName, "
                + "businessFragmentInstanceId=FraIns, systemName=SysNam, functionalModuleName=FunModNam, functionalModuleInstanceId=FunModInsId";
        data.setupPropertiesFromString(props);
        Assert.assertEquals("BusProc", data.getBusinessProcessName());
        Assert.assertEquals("BusPart", data.getBusinessPartName());
        Assert.assertEquals("FraName", data.getBusinessFragmentName());
        Assert.assertEquals("FraIns", data.getBusinessFragmentInstanceId());
        Assert.assertEquals("SysNam", data.getSystemName());
        Assert.assertEquals("FunModNam", data.getFunctionalModuleName());
        Assert.assertEquals("FunModInsId", data.getFunctionalModuleInstanceId());
        slog.application("setupPropertiesFromString", data);
    }

}
