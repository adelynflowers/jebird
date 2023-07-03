package com.wheretobird.jebird.region;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.wheretobird.jebird.Jebird;
import com.wheretobird.jebird.JebirdTest;
import com.wheretobird.jebird.exceptions.EbirdApiException;
import com.wheretobird.jebird.region.Region;

public class RegionTest extends JebirdTest {

    @Test
    public void test_regionInfo_allValid() throws IOException, InterruptedException, EbirdApiException {
        Region canada = Jebird.getRegionInfo("CA", "full", ',', apiToken);
        assertEquals("Testing for correct name", "Canada", canada.getResult());
        assertEquals("Testing coordinate bounds", 41.708293, canada.getBounds().getMinY(), 0.001);
    }

    @Test
    public void test_regionInfo_validRegionDelim_invalidFormAt()
            throws IOException, InterruptedException, EbirdApiException {
        Region nc = Jebird.getRegionInfo("US-NC", "zyfsef", ',', apiToken);
        assertEquals("Testing name equivalence", "North Carolina, United States", nc.getResult());
    }

    @Test(expected = EbirdApiException.class)
    public void test_regionInfo_invalidRegion() throws IOException, InterruptedException, EbirdApiException {
        Jebird.getRegionInfo("US-XC", "full", ',', apiToken);
    }

    @Test
    public void test_regionInfo_serializable() throws ClassNotFoundException, IOException {
        Region testRegion = new Region();
        testRegion.setResult("US");
        testRegion.getBounds().setMaxX(-1);
        Region deserialized = serialize_deserialize(testRegion);
        assertEquals("Testing serialized name equivalence", testRegion.getResult(), deserialized.getResult());
        assertEquals("Test serialized coord equivalence", testRegion.getBounds().getMaxX(),
                deserialized.getBounds().getMaxX(), 0.001);
    }

}
