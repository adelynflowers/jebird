package com.wheretobird.jebird;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class JebirdTest {
    /*
     * Parent class to centralize api token
     * retrieval. A valid api token under the
     * key EBIRD_API_KEY must be available in
     * a .env file in the project root.
     */

    protected String apiToken = null;

    public JebirdTest() {
        Dotenv dotenv = Dotenv.load();
        this.apiToken = dotenv.get("EBIRD_API_KEY");
    }
}
