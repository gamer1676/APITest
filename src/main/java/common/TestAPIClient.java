package common;

import org.testng.Assert;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/*
 A very simple API Client that implements the GET and POST methods
 */
public class TestAPIClient {
    protected HttpRequest request;
    protected HttpResponse response;

    /*
     Performs a GET call.  Returns the response as a string.
     */
    public String GET(String uri) {
        String retVal = "";
        try {
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(uri))
                    .build();

            response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            retVal = response.body().toString();
        }
        catch (Exception e) {
            // correctly handles the exception according to framework
            // for this case, just returns an empty string
        }
        return retVal;
    }

    /*
     Performs a POST call with specified parameters.  If no parameters are specified, send a noBody.
     Return the result as a string
     */
    public String POST(String uri, Map<Object, Object> parameters) {
        String retVal = "";

        try {
            StringBuilder builder = new StringBuilder();

            // only encode if we have parameters
            if (parameters != null) {
                // encode all the parameters
                for (Map.Entry<Object, Object> entry : parameters.entrySet()) {
                    if (builder.length() > 0) {
                        builder.append("&");
                    }
                    builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
                    builder.append("=");
                    builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
                }
            }

            request = HttpRequest.newBuilder()
                    .uri(new URI("https://deckofcardsapi.com/api/deck/new/"))
                    .POST(
                            // if we have no parameters, send a noBody
                            parameters == null
                                    ? HttpRequest.BodyPublishers.noBody()
                                    : HttpRequest.BodyPublishers.ofString(builder.toString())
                    )
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            retVal = response.body().toString();
        } catch (Exception e) {
            // correctly handles the exception according to framework
            // for this case, just returns an empty string
        }
        return retVal;

    }
}
