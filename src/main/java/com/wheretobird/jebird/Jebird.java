package com.wheretobird.jebird;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.BodySubscribers;
import java.net.http.HttpResponse.ResponseInfo;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.wheretobird.jebird.exceptions.EbirdApiException;
import com.wheretobird.jebird.models.region.SubRegionListItem;
import com.wheretobird.jebird.models.taxonomy.TaxonomicGroup;
import com.wheretobird.jebird.models.region.Region;

public final class Jebird {
    /**
     * This class is the source of all calls to the
     * eBird API, providing static methods that
     * correspond to the API paths documented at
     * https://documenter.getpostman.com/view/664302/S1ENwy59.
     * EbirdApiException is the class used for exceptions that
     * come about from bad parameters.
     */

    private Jebird() {

    }

    record Response<R, T>(R response, T error) {
    }

    static class ErrorBodyHandler<R, T> implements BodyHandler<Response<R, T>> {
        /*
         * Class used to assist throwing HTTP status code exceptions. Used
         * unchanged from https://stackoverflow.com/a/67532905.
         */
        final BodyHandler<R> responseHandler;
        final BodyHandler<T> errorHandler;

        public ErrorBodyHandler(BodyHandler<R> responseHandler, BodyHandler<T> errorHandler) {
            this.responseHandler = responseHandler;
            this.errorHandler = errorHandler;
        }

        @Override
        public BodySubscriber<Response<R, T>> apply(ResponseInfo responseInfo) {
            if (responseInfo.statusCode() == 200) {
                return BodySubscribers.mapping(responseHandler.apply(responseInfo),
                        r -> new Response<>(r, null));
            } else {
                return BodySubscribers.mapping(errorHandler.apply(responseInfo),
                        t -> new Response<>(null, t));
            }
        }
    }

    public static final String BASEURL = "https://api.ebird.org/v2";

    /**
     * Calls the eBird API client for the given path and params, returning
     * an object T that is a class-based representation of the JSON response.
     * 
     * @param <T>   the object that gson will use to deserialize the response
     * @param path  the url path including params to append to the base url
     * @param token the ebird api token used to authenticate
     * @param c     the class that the JSON response will be deserialized to
     * @return T a class representing the deserialized response
     * @throws IOException          if there are problems outside of http error
     *                              codes
     * @throws InterruptedException if there are problems outside of http error
     *                              codes
     * @throws EbirdApiException    if the api returns a non-200 status code
     */
    private static <T extends Object> T getApiResponse(String path, String token, java.lang.Class<T> c)
            throws IOException, InterruptedException, EbirdApiException {
        String uri = BASEURL + path;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-eBirdApiToken", token)
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            Gson gson = new Gson();
            return gson.fromJson(responseBody, c);
        } else {
            throw new EbirdApiException(response.body());
        }

    }

    private static <T extends Object> T getApiResponse(String path, Map<String, String> queryParams, String token,
            java.lang.Class<T> c) throws IOException, InterruptedException, EbirdApiException {
        if (queryParams.size() > 0) {
            StringBuilder str = new StringBuilder(path);
            str.append("?");
            for (Map.Entry<String, String> paramEntry : queryParams.entrySet()) {
                str.append(String.format("%s=%s", paramEntry.getKey(), paramEntry.getValue()));
            }
            return getApiResponse(str.toString(), token, c);
        }
        return getApiResponse(path, token, c);
    }

    /**
     * Calls the /ref/region/list endpoint, giving information on the
     * subregions within a given region. Returns a list of subregions with the
     * following properties:
     * code: representing the regions code to use elsewhere in the API
     * name: The display name for the region
     * 
     * @param regionType       the region type to list, must be smaller than parent
     *                         type
     * @param parentRegionCode the region code of the parent region
     * @param apiToken         the ebird api token used to authenticate
     * @return SubRegionListItem[] a list of subregions
     * @throws IOException          if there are problems outside of http error
     *                              codes
     * @throws InterruptedException if there are problems outside of http error
     *                              codes
     * @throws EbirdApiException    if the api returns a non-200 status code
     */
    public static SubRegionListItem[] getSubRegions(String regionType, String parentRegionCode, String apiToken)
            throws IOException, InterruptedException, EbirdApiException {
        String uri = String.format("/ref/region/list/%s/%s", regionType, parentRegionCode);
        return getApiResponse(uri, apiToken, SubRegionListItem[].class);
    }

    public static Region getRegionInfo(String regionCode, String regionNameFormAt, Character delim, String apiToken)
            throws IOException, InterruptedException, EbirdApiException {

        String uri = String.format("/ref/region/info/%s", regionCode);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("regionNameFormAt", regionNameFormAt);
        queryParams.put("delim", delim.toString());

        Region response = getApiResponse(uri, queryParams, apiToken, Region.class);

        if (response.getResult() == null) {
            throw new EbirdApiException("No results returned");
        } else {
            return response;
        }
    }

    private TaxonomicGroup[] getTaxonomicGroups(String speciesGrouping, String groupNameLocale, String apiToken)
            throws IOException, InterruptedException, EbirdApiException {
        String path = "/ref/sppgroup/";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("groupNameLocale", groupNameLocale);
        return getApiResponse(path, queryParams, apiToken, TaxonomicGroup[].class);

    }
}
