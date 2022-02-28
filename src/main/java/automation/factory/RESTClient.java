package automation.factory;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.Response;
import lombok.*;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RESTClient {

    protected static Logger log = Logger.getLogger(RESTClient.class);

    @Builder.Default
    private String contentType = "application/json";

    @Builder.Default
    private JSONObject requestBody = new JSONObject();

    @Builder.Default
    private String basicAuthentication = "";

    @Builder.Default
    private String baseURL = "";

    @Builder.Default
    private String path = "/";

    @Builder.Default
    private boolean withCustomHeaders = false;

    @Builder.Default
    private HashMap<String,String> requestHeaders = new HashMap<>();

    public Response doGet(){
        log.info("Executing a HTTP GET Request: ");
        Invocation.Builder invocationBuilder = frameRequest();
        Response response = invocationBuilder.get();
        response.bufferEntity();
        log.info("Response Code :: "+response.getStatus());
        log.info("Response Headers :: "+response.getHeaders());
        log.info("Response Body :: "+response.readEntity(String.class));
        return response;
    }

    public Response doPost(){
        log.info("Executing a HTTP POST Request: ");
        Invocation.Builder invocationBuilder = frameRequest();
        Response response = invocationBuilder.post(Entity.entity(this.requestBody.toString(),this.contentType));
        response.bufferEntity();
        log.info("Response Code :: "+response.getStatus());
        log.info("Response Headers :: "+response.getHeaders());
        log.info("Response Body :: "+response.readEntity(String.class));
        return response;
    }

    public Response doPut(){
        log.info("Executing a HTTP PUT Request: ");
        Invocation.Builder invocationBuilder = frameRequest();
        Response response = invocationBuilder.put(Entity.entity(this.requestBody.toString(),this.contentType));
        response.bufferEntity();
        log.info("Response Code :: "+response.getStatus());
        log.info("Response Headers :: "+response.getHeaders());
        log.info("Response Body :: "+response.readEntity(String.class));
        return response;
    }

    public Response doPatch(){
        log.info("Executing a HTTP PATCH Request: ");
        WebTarget webTarget = getWebTarget(this.baseURL);
        webTarget.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        webTarget = webTarget.path(this.path);
        Invocation.Builder invocationBuilder = webTarget.request(this.contentType);
        invocationBuilder = addAuthorizationHeader(invocationBuilder);
        Response response = invocationBuilder.method("PATCH",Entity.entity(this.requestBody.toString(),this.contentType));
        response.bufferEntity();
        log.info("Response Code :: "+response.getStatus());
        log.info("Response Headers :: "+response.getHeaders());
        log.info("Response Body :: "+response.readEntity(String.class));
        return response;
    }

    public Response doDelete(){
        log.info("Executing a HTTP DELETE Request: ");
        Invocation.Builder invocationBuilder = frameRequest();
        Response response = invocationBuilder.delete();
        response.bufferEntity();
        log.info("Response Code :: "+response.getStatus());
        log.info("Response Headers :: "+response.getHeaders());
        log.info("Response Body :: "+response.readEntity(String.class));
        return response;
    }

    private WebTarget getWebTarget(String url) {
        Client client = ClientBuilder.newClient();
        return client.target(url);
    }

    private Invocation.Builder addAuthorizationHeader(Invocation.Builder invocationBuilder) {
        log.info("Basic Auth :: "+this.basicAuthentication);
        invocationBuilder.header("Authorization", this.basicAuthentication);
        invocationBuilder.header("Content-Type", this.contentType);
        if(this.withCustomHeaders){
            log.info("Headers "+this.requestHeaders.toString());
            for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
                invocationBuilder.header(entry.getKey(),entry.getValue());
            }
        }
        return invocationBuilder;
    }

    private Invocation.Builder frameRequest(){
        WebTarget webTarget = getWebTarget(this.baseURL);
        webTarget = webTarget.path(this.path);
        log.info("Base URL "+this.baseURL);
        log.info("Path Parameter "+this.path);
        log.info("Content Type "+this.contentType);
        Invocation.Builder invocationBuilder = webTarget.request(this.contentType);
        invocationBuilder = addAuthorizationHeader(invocationBuilder);
        return invocationBuilder;
    }
}
