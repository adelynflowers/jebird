package com.wheretobird.jebird.taxonomy;

public class TaxonomicGroup {
    private String groupName = null;
    private int groupOrder = -1;
    private int[] taxonOrderBounds;

    public TaxonomicGroup() {
        // Empty constructor for bean
    }

    /**
     * @return String
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }

    public int[] getTaxonOrderBounds() {
        return taxonOrderBounds;
    }

    public void setTaxonOrderBounds(int[] taxonOrderBounds) {
        this.taxonOrderBounds = taxonOrderBounds;
    }

}
