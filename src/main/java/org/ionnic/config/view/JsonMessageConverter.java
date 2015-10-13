package org.ionnic.config.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.ionnic.config.AppConfig;
import org.ionnic.config.util.GsonUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.gson.JsonSyntaxException;

public class JsonMessageConverter extends AbstractHttpMessageConverter<Object> {

	private static final MediaType contentType = new MediaType("application", "json", Charset.forName("UTF-8"));

	// TODO: move this to a more appropriated utils class
	private static String convert(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the Reader.read(char[]
		 * buffer) method. We iterate until the Reader return -1 which means
		 * there's no more data to read. We use the StringWriter class to
		 * produce the string.
		 */
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, AppConfig.CHARSET));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public JsonMessageConverter() {
		// TODO Auto-generated constructor stub
		super(contentType);
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		try {
			String requestBody = convert(inputMessage.getBody());
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
