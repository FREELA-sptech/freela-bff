package freela.bff.infra.repository;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BaseRepository {
    private final HttpClient client;

    public BaseRepository() {
        this.client = HttpClients.createDefault();
    }

    public <T> T sendGet(HttpGet uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            T responseObj = gson.fromJson(responseBody, responseType);

            return responseObj;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendPost(HttpPost uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            T responseObj = gson.fromJson(responseBody, responseType);

            return responseObj;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendPut(HttpPut uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            T responseObj = gson.fromJson(responseBody, responseType);

            return responseObj;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendDelete(HttpDelete uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            T responseObj = gson.fromJson(responseBody, responseType);

            return responseObj;
        } catch (Exception e) {
            return null;
        }
    }

    public HttpPost generateBody(HttpPost post, String json){
        HttpEntity entity = new StringEntity(json,"UTF-8");
        post.setEntity(entity);

        return post;
    }
}
