package org.ionnic.common.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.ionnic.common.support.AppConfig;
import org.ionnic.common.util.JsonUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.google.gson.JsonSyntaxException;

/**
 * @author apple
 *
 */
public class JsonMessageConverter extends AbstractHttpMessageConverter<Object> {

    public JsonMessageConverter() {
        super(MediaType.APPLICATION_JSON);
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            InputStream stream = inputMessage.getBody();
            String requestBody = StreamUtils.copyToString(stream, Charset.forName(AppConfig.CHARSET));

            return JsonUtils.fromJson(requestBody, clazz);
        } catch (JsonSyntaxException e) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected void writeInternal(Object t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String result = JsonUtils.toJson(t);
        outputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        outputMessage.getBody().write(result.getBytes());
    }
}
