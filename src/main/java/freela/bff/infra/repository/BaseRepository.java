package freela.bff.infra.repository;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
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

    public <T> T sendPatch(HttpPatch uri, Class<T> responseType) {
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

    public HttpPost generateBody(HttpPost post, Object objeto){
        String json = this.convertObjectToJson(objeto);
        HttpEntity entity = new StringEntity(json,"UTF-8");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);

        return post;
    }

    public HttpPatch generateBody(HttpPatch post, Object objeto){
        String json = this.convertObjectToJson(objeto);
        HttpEntity entity = new StringEntity(json,"UTF-8");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);

        return post;
    }

    public String convertObjectToJson(Object object) {
        try {
            Gson gson = new Gson();
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
