package com.wheretobird.jebird.region;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.wheretobird.jebird.Jebird;
import com.wheretobird.jebird.JebirdTest;
import com.wheretobird.jebird.exceptions.EbirdApiException;
import com.wheretobird.jebird.models.region.Region;

public class RegionTest extends JebirdTest {

    @Test
    public void test_regionInfo_allValid() throws IOException, InterruptedException, EbirdApiException {
        String regionCode = "CA";
        Region canada = Jebird.getRegionInfo(regionCode, apiToken);
        assertEquals("Testing for correct name", "Canada", canada.getResult());
    }

}
