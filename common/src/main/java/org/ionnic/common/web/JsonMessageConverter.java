package org.ionnic.common.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.ionnic.common.Config;
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
            String requestBody = StreamUtils.copyToString(stream, Charset.forName(Config.CHARSET));
            Object result = JsonUtils.fromJson(requestBody, clazz);

            if (result == null) {
                throw new JsonSyntaxException("Could not read JSON");
            } else {
                return result;
            }
        } catch (JsonSyntaxException e) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }
    }

    @Override
    /**
     * @see org.springframework.http.server.ServletServerHttpResponse#getBody()
     */
    public void write(Object t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String result = JsonUtils.toJson(t);

        // set header after getBody() invoked.

        outputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        outputMessage.getBody().write(result.getBytes());
    }

}
