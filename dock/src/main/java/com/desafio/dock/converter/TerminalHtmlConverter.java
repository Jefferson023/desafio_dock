package com.desafio.dock.converter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.dock.dto.TerminalRequestDto;
import com.desafio.dock.entity.Terminal;

/**
 * Converter to handle with text/html media type
 * @author Jefferson
 *
 */
public class TerminalHtmlConverter extends AbstractHttpMessageConverter<TerminalRequestDto> {

	public TerminalHtmlConverter() {
		super(new MediaType("text", "html", Charset.forName("UTF-8")));
	}

	private static final String wrongParameters = "Wrong number of parameters for Terminal class";

	private static final String invalidType = "Invalid type of parameter for Terminal class. "
			+ "Could not convert String to Integer.";
	
	/**
	 * @See AbstractHttpMessageConverter
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return TerminalRequestDto.class.isAssignableFrom(clazz);
	}

	/**
	 * Converts HTML into String and returns a {@link TerminalRequestDto}} representation of the given String.
	 * @see AbstractHttpMessageConverter
	 */
	@Override
	protected TerminalRequestDto readInternal(Class<? extends TerminalRequestDto> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		List<String> rb = Arrays.asList(toString(inputMessage.getBody()).split(";"));
		try {
			TerminalRequestDto terminal = new TerminalRequestDto(toInteger(rb.get(0)), rb.get(1), rb.get(2), 
					toInteger(rb.get(3)), rb.get(4), 
					toInteger(rb.get(5)), rb.get(6), toInteger(rb.get(7)), 
					toInteger(rb.get(8)), rb.get(9));
			return terminal;
		} catch (IndexOutOfBoundsException e){
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, wrongParameters);
		} catch (NumberFormatException e2) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, invalidType);
		}
	}

	/**
	 * Cast a {@link String} to Integer if the String it's not blank. Otherwise, returns null.  
	 * @param string {@link String} to cast
	 * @return Integer or null. 
	 */
	private static Integer toInteger(String string) {
		return string.isBlank() ? null : Integer.parseInt(string);
	}
	
	/**
	 * Convert a inputStream to a single string. 
	 * @param inputStream
	 * @return a String representation of the inputStream. 
	 */
	private static String toString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		String stringBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		return stringBody;
	}

	/**
	 * @See AbstractHttpMessageConverter
	 */
	@Override
	protected void writeInternal(TerminalRequestDto t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
	}

}
