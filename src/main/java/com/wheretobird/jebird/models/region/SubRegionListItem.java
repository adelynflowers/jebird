package com.wheretobird.jebird.models.region;

public class SubRegionListItem {
    /*
     * This class is used in conjuction with
     * Gson.fromJson to deserialize JSON reponses
     * to the /ref/region/list endpoint.
     * 
     * @see com.wheretobird.jebird#getSubRegions
     */

    private String name = null;
    private String code = null;

    /**
     * Gets display name of listed subregion.
     * 
     * @return String display name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets region code of listed subregion.
     * 
     * @return String region code
     */
    public String getCode() {
        return this.code;
    }
}
