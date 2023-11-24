package freela.bff.infra.repository;

import com.google.gson.Gson;
import freela.bff.domain.model.request.order.CreateOrderBFFRequest;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.request.order.UpdateOrderRequest;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.web.exceptions.GenericErrorException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode > HttpStatus.MULTIPLE_CHOICES.value()) {
                ErrorResponse error = gson.fromJson(responseBody, ErrorResponse.class);

                throw new GenericErrorException(error, statusCode);
            }

            return gson.fromJson(responseBody, responseType);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendPost(HttpPost uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode > HttpStatus.MULTIPLE_CHOICES.value()) {
                ErrorResponse error = gson.fromJson(responseBody, ErrorResponse.class);

                throw new GenericErrorException(error, statusCode);
            }

            return gson.fromJson(responseBody, responseType);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendPut(HttpPut uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode > HttpStatus.MULTIPLE_CHOICES.value()) {
                ErrorResponse error = gson.fromJson(responseBody, ErrorResponse.class);

                throw new GenericErrorException(error, statusCode);
            }

            return gson.fromJson(responseBody, responseType);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendPatch(HttpPatch uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode > HttpStatus.MULTIPLE_CHOICES.value()) {
                ErrorResponse error = gson.fromJson(responseBody, ErrorResponse.class);

                throw new GenericErrorException(error, statusCode);
            }

            return gson.fromJson(responseBody, responseType);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T sendDelete(HttpDelete uri, Class<T> responseType) {
        try {
            HttpResponse response = this.client.execute(uri);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode > HttpStatus.MULTIPLE_CHOICES.value()) {
                ErrorResponse error = gson.fromJson(responseBody, ErrorResponse.class);

                throw new GenericErrorException(error, statusCode);
            }

            return gson.fromJson(responseBody, responseType);
        } catch (GenericErrorException e) {
            throw e;
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

    public HttpPost generateMultipartBody(HttpPost post, CreateOrderRequest request, List<MultipartFile> photos) throws IOException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        String json = this.convertObjectToJson(request);
        builder.addTextBody("createOrderRequest", json, ContentType.APPLICATION_JSON);

        if (photos != null) {
            for (int i = 0; i < photos.size(); i++) {
                builder.addBinaryBody("photos", photos.get(i).getBytes(), ContentType.MULTIPART_FORM_DATA, "photo" + i + ".jpg");
            }
        }

        HttpEntity entity = builder.build();

        post.setEntity(entity);

        return post;
    }

    public HttpPatch generateMultipartBodyUpdateOrder(HttpPatch post, UpdateOrderRequest request, List<MultipartFile> newPhotos) throws IOException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        String json = this.convertObjectToJson(request);
        builder.addTextBody("updateOrderRequest", json, ContentType.APPLICATION_JSON);

        if (newPhotos != null) {
            for (int i = 0; i < newPhotos.size(); i++) {
                builder.addBinaryBody("newPhotos", newPhotos.get(i).getBytes(), ContentType.MULTIPART_FORM_DATA, "photo" + i + ".jpg");
            }
        }

        HttpEntity entity = builder.build();

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

    public HttpPut generateBody(HttpPut put, Object objeto){
        String json = this.convertObjectToJson(objeto);
        HttpEntity entity = new StringEntity(json,"UTF-8");
        put.setHeader("Content-Type", "application/json");
        put.setEntity(entity);

        return put;
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
