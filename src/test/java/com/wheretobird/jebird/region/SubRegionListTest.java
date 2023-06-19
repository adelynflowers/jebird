package com.wheretobird.jebird.region;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.wheretobird.jebird.Jebird;
import com.wheretobird.jebird.JebirdTest;
import com.wheretobird.jebird.exceptions.EbirdApiException;
import com.wheretobird.jebird.models.region.SubRegionListItem;

public class SubRegionListTest extends JebirdTest {

    @Test
    public void test_subRegions_validRegion_validType() {
        try {
            SubRegionListItem[] regions = Jebird.getSubRegions("subnational2", "US-NC", this.apiToken);
            assertTrue("Testing for result length > 0", regions.length > 0);
            for (SubRegionListItem region : regions) {
                assertNotNull("Testing for non-null region names", region.getName());
                assertNotNull("Testing for non-null region codes", region.getCode());
            }
        } catch (IOException | InterruptedException | EbirdApiException e) {
            // Catch unexpected errors and fail the test
            e.printStackTrace();
            assertTrue("Failing subRegions_validRegion_validType due to exception: " + e.getMessage(), false);
        }

    }

    @Test
    public void test_subRegions_invalidRegion_validType() {
        SubRegionListItem[] regions;
        try {
            regions = Jebird.getSubRegions("subnational2", "US-NZ", this.apiToken);
            assertEquals("Testing for empty region list", 0, regions.length);
        } catch (IOException | InterruptedException | EbirdApiException e) {
            // Catch unexpected errors and fail test
            e.printStackTrace();
            assertTrue("Failing subRegions_invalidRegion_validType due to exception: " + e.getMessage(), false);
        }

    }

    @Test
    public void test_subRegions_invalidRegion_invalidType() {
        try {
            Jebird.getSubRegions("subnational3", "US-NZ", this.apiToken);
            assertTrue("Failing test because exception was not thrown", false);
        } catch (EbirdApiException e) {
            assertTrue("Successfully received API Exception: " + e.getMessage(), true);
        } catch (IOException | InterruptedException e) {
            // Catch unexpected errors and fail
            e.printStackTrace();
            assertTrue("Failing subRegions_invalidRegion_invalidType due to exception: " + e.getMessage(), false);
        }

    }

    @Test
    public void test_subRegions_validRegion_invalidType() {
        try {
            Jebird.getSubRegions("subnational3", "US-NC", this.apiToken);
            assertTrue("Failing test because exception was not thrown", false);
        } catch (EbirdApiException e) {
            assertTrue("Successfully received API Exception: " + e.getMessage(), true);
        } catch (IOException | InterruptedException e) {
            // Catch unexpected errors and fail test
            e.printStackTrace();
            assertTrue("Failing subRegions_invalidRegion_validType due to exception: " + e.getMessage(), false);
        }

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
