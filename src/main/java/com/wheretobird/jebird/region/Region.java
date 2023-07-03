package com.wheretobird.jebird.region;

import java.io.Serializable;

public class Region implements Serializable {

    // Region display name
    private String result = null;
    private RegionBounds bounds = null;

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
     * Get bounds object
     * 
     * @return
     */
    public RegionBounds getBounds() {
        return this.bounds;
    }

    /**
     * Set bounds object
     * 
     * @param bounds
     */
    public void setBounds(RegionBounds bounds) {
        this.bounds = bounds;
    }

    /**
     * Empty constructor
     */
    public Region() {
        // Empty constructor for bean
        this.bounds = new RegionBounds();
    }

    public static class RegionBounds implements Serializable {
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

        /**
         * Get min Y coordinate
         * 
         * @return
         */
        public float getMinY() {
            return this.minY;
        }

        /**
         * Set min X coordinate
         * 
         * @param minX
         */
        public void setMinX(float minX) {
            this.minX = minX;
        }

        /**
         * Set max X coordinate
         * 
         * @param maxX
         */
        public void setMaxX(float maxX) {
            this.maxX = maxX;
        }

        /**
         * Set min Y coordinate
         * 
         * @param minY
         */
        public void setMinY(float minY) {
            this.minY = minY;
        }

        /**
         * Get max Y coordinate
         * 
         * @return
         */
        public float getMaxY() {
            return maxY;
        }

        /**
         * Set max Y coordinate
         * 
         * @param maxY
         */
        public void setMaxY(float maxY) {
            this.maxY = maxY;
        }

    }

}
