package com.wheretobird.jebird.models.region;

import java.io.Serializable;

public class Region implements Serializable {

    // Region display name
    private String result = null;

    /**
     * Get region name
     * 
     * @return String
     */
    public String getResult() {
        return this.result;
    }

    /**
     * Set region name
     * 
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Empty constructor
     */
    public Region() {
        // Empty constructor for bean
    }

    public class RegionBounds implements Serializable {
        // members representing coordinate bounds
        private float minX;
        private float maxX;
        private float minY;
        private float maxY;

        /**
         * Empty constructor
         */
        public RegionBounds() {
            // Empty constructor to make bean
        }

        /**
         * Get min X coordinate
         * 
         * @return
         */
        public float getMinX() {
            return this.minX;
        }

        /**
         * Get max X coordinate
         * 
         * @return
         */
        public float getMaxX() {
            return this.maxX;
        }

        public float getMinY() {
            return this.minY;
        }

        public void setMinX(float minX) {
            this.minX = minX;
        }

        public void setMaxX(float maxX) {
            this.maxX = maxX;
        }

        public void setMinY(float minY) {
            this.minY = minY;
        }

        public float getMaxY() {
            return maxY;
        }

        public void setMaxY(float maxY) {
            this.maxY = maxY;
        }

    }

}
