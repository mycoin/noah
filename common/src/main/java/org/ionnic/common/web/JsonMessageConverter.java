package org.ionnic.common.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.ionnic.common.support.AppConfig;
import org.ionnic.common.util.JsonUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.google.gson.JsonSyntaxException;

/**
 * @author apple
 *
 */
public class JsonMessageConverter implements HttpMessageConverter<Object> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> supportedList = new ArrayList<MediaType>();
        supportedList.add(MediaType.APPLICATION_JSON);
        return supportedList;
    }

    @Override
    public Object read(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            InputStream stream = inputMessage.getBody();
            String requestBody = StreamUtils.copyToString(stream, Charset.forName(AppConfig.CHARSET));

            return JsonUtils.fromJson(requestBody, clazz);
        } catch (JsonSyntaxException e) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public void write(Object t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String result = JsonUtils.toJson(t);
        outputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        outputMessage.getBody().write(result.getBytes());
    }

}
