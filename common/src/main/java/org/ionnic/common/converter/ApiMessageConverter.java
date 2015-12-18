package org.ionnic.common.converter;

import java.io.IOException;

import org.ionnic.common.model.JSONMessageType;
import org.ionnic.common.model.JSONParameter;
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
            throw new HttpMessageNotReadableException("Could not read JSON:" + e.getMessage());
        }
    }

    @Override
    protected boolean supports(Class<?> type) {
        Class<?>[] array = type.getInterfaces();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == JSONMessageType.class) {
                return true;
            }
        }
        return false;
    }
}
