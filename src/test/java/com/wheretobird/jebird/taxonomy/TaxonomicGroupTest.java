package com.wheretobird.jebird.taxonomy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

import com.wheretobird.jebird.Jebird;
import com.wheretobird.jebird.JebirdTest;
import com.wheretobird.jebird.exceptions.EbirdApiException;

public class TaxonomicGroupTest extends JebirdTest{
    
    @Test 
    public void test_taxonomicGroup_allValid() throws IOException, InterruptedException, EbirdApiException {
        HashSet<TaxonomicGroup> taxonomicGroups = new HashSet<>(Jebird.getTaxonomicGroups("merlin", "en", this.apiToken));
        assertTrue("Checking for valid listing", taxonomicGroups.size() > 0);
    }

    @Test
    public void text_taxonomicGroup_validGrouping_invalidLocale() throws IOException, InterruptedException, EbirdApiException {
        HashSet<TaxonomicGroup> taxonomicGroups = new HashSet<>(Jebird.getTaxonomicGroups("merlin", "xyz", this.apiToken));
        assertTrue("Checking for valid listing despite invalid locale", taxonomicGroups.size() > 0);
    } 

    @Test(expected = EbirdApiException.class)
    public void test_taxonomicGroup_invalidGrouping_validLocale() throws IOException, InterruptedException, EbirdApiException {
        Jebird.getTaxonomicGroups("xyz", "en", this.apiToken);
    }

    @Test 
    public void test_taxonomicGroup_serializable() throws ClassNotFoundException, IOException {
        TaxonomicGroup taxa = new TaxonomicGroup();
        taxa.setGroupName("abc");
        taxa.setGroupOrder(5);
        TaxonomicGroup taxaFromStream = serialize_deserialize(taxa);
        assertEquals("Testing name equivalence", taxa.getGroupName(), taxaFromStream.getGroupName());
        assertEquals("Testing region code equivalence", taxa.getGroupOrder(), taxaFromStream.getGroupOrder()); 
    }
    

}
