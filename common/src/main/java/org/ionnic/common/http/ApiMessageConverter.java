package org.ionnic.common.http;

import java.io.IOException;

import org.ionnic.common.support.JSONObject;
import org.ionnic.common.support.JSONParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * @author apple
 *
 */
public class ApiMessageConverter extends JsonMessageConverter {

    @Override
    protected Object readInternal(Class<? extends Object> type, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            JSONParameter object = (JSONParameter) super.readInternal(JSONParameter.class, inputMessage);
            object.init(inputMessage);
            return object;
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("Message Not Readable");
        }
    }

    @Override
    protected boolean supports(Class<?> type) {
        if (type.isAssignableFrom(JSONObject.class) || type.isAssignableFrom(JSONParameter.class)) {
            return true;
        }
        return false;
    }
}
