package com.wheretobird.jebird.models.region;

import java.io.Serializable;

public class SubRegionListItem implements Serializable {
    /**
     * This class is used in conjuction with
     * Gson.fromJson to deserialize JSON reponses
     * to the /ref/region/list endpoint.
     * 
     * @see com.wheretobird.jebird#getSubRegions
     */

    private String name = null;
    private String code = null;

    public SubRegionListItem() {
        // No initialization needed

    }

    /**
     * Gets display name of listed subregion.
     * 
     * @return String display name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets display name of listed subregion.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets region code of listed subregion.
     * 
     * @return String region code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets region code of listed subregion.
     * 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
