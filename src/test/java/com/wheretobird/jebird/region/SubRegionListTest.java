package com.wheretobird.jebird.region;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.junit.Test;

import com.wheretobird.jebird.Jebird;
import com.wheretobird.jebird.JebirdTest;
import com.wheretobird.jebird.exceptions.EbirdApiException;

public class SubRegionListTest extends JebirdTest {

    @Test
    public void test_subRegions_validRegion_validType() throws IOException, InterruptedException, EbirdApiException {
        LinkedHashSet<SubRegionListItem> regions =  new LinkedHashSet<>(Jebird.getSubRegions("subnational2", "US-NC", this.apiToken));
        assertTrue("Testing for result length > 0", regions.size() > 0);
        for (SubRegionListItem region : regions) {
            assertNotNull("Testing for non-null region names", region.getName());
            assertNotNull("Testing for non-null region codes", region.getCode());
        }
    }

    @Test
    public void test_subRegions_invalidRegion_validType() throws IOException, InterruptedException, EbirdApiException {
        HashSet<SubRegionListItem> regions =  new HashSet<>(Jebird.getSubRegions("subnational2", "US-NZ", this.apiToken));
        assertEquals("Testing for empty region list", 0, regions.size());
    

    }

    @Test(expected = EbirdApiException.class)
    public void test_subRegions_invalidRegion_invalidType() throws IOException, InterruptedException, EbirdApiException {
        Jebird.getSubRegions("subnational3", "US-NZ", this.apiToken);
        assertTrue("Failing test because exception was not thrown", false);
    }

    @Test(expected = EbirdApiException.class)
    public void test_subRegions_validRegion_invalidType() throws IOException, InterruptedException, EbirdApiException {
        Jebird.getSubRegions("subnational3", "US-NC", this.apiToken);
        assertTrue("Failing test because exception was not thrown", false);
    }

    /**
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @Test
    public void test_subRegions_serializable() throws ClassNotFoundException, IOException {
        SubRegionListItem item = new SubRegionListItem();
        item.setCode("abc");
        item.setName("123");
        SubRegionListItem itemFromStream = serialize_deserialize(item);
        assertEquals("Testing name equivalence", item.getName(), itemFromStream.getName());
        assertEquals("Testing region code equivalence", item.getCode(), itemFromStream.getCode());
    }

}
