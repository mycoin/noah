package net.io.config.view;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import net.io.config.AppConfig;
import net.io.config.util.GsonUtils;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.google.gson.JsonSyntaxException;

public class JsonMessageConverter extends AbstractHttpMessageConverter<Object> {

	private static final MediaType contentType = new MediaType("application", "json", Charset.forName("UTF-8"));

	public JsonMessageConverter() {
		// TODO Auto-generated constructor stub
		super(contentType);
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		try {
			InputStream stream = inputMessage.getBody();

			String requestBody = StreamUtils.copyToString(stream, AppConfig.CHARSET);
			return GsonUtils.fromJson(requestBody, clazz);
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
		String result = GsonUtils.toJson(t);

		outputMessage.getHeaders().setContentType(contentType);
		outputMessage.getBody().write(result.getBytes());
	}
}
