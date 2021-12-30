package com.desafio.dock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.desafio.dock.dto.TerminalRequestDto;
import com.desafio.dock.dto.TerminalResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TerminalControllerTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

	static Stream<Arguments> postTerminalShouldReturnResponseEntity() { 
		return Stream.of(
				  Arguments.of(44332211,"123","PWWIN", 0, "F04A2E4088B", 
							4, "8.00b3", 0, 16777216, "PWWIN"),
				  Arguments.of(2,"1","PWYE", 2, "F04A24000B", 
							4, "11.20a", 4, 16777, "PWYE")
				);
	}
	
	@Order(1)
	@ParameterizedTest
	@MethodSource
	public void postTerminalShouldReturnResponseEntity(Integer logic, String serial, String model, 
			Integer sam, String ptid, Integer plat, String version, Integer mxr, 
			Integer mxf, String pverfm) throws Exception {
		
		String htmlBody = String.format("%d;%s;%s;%d;%s;%d;%s;%d;%d;%s",logic, serial, model, sam, 
				ptid, plat, version, mxr, mxf, pverfm);
		
		String expectedRes = objectMapper.writeValueAsString(new TerminalResponseDto(logic, serial, model, sam, 
				ptid, plat, version, mxr, mxf, pverfm));
		
		mockMvc.perform(post("/").contentType(MediaType.TEXT_HTML_VALUE)
				.content(htmlBody)).andExpect(status().isCreated())
				.andExpect(content().json(expectedRes));
	}
	
	static Stream<Arguments> postTerminalShouldReturnConflictWhenIdExists() { 
		return Stream.of(
				  Arguments.of("44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN"),
				  Arguments.of("2;1;PWYE;2;F04A24000B;4;11.20a;4;16777;PWYE")
				);
	}
	
	@Order(2)
	@ParameterizedTest
	@MethodSource
	public void postTerminalShouldReturnConflictWhenIdExists(String htmlBody) throws Exception {
		
		mockMvc.perform(post("/").contentType(MediaType.TEXT_HTML_VALUE)
				.content(htmlBody)).andExpect(status().isConflict());
	}
	
	static Stream<Arguments> postTerminalShouldReturnBadRequestWhenBodyIsInvalid() { 
		return Stream.of(
					//invalid number of arguments
				  Arguments.of("44332211123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN"),
				  	//invalid number of arguments
				  Arguments.of("2"),
				  	//cannot convert String to Integer
				  Arguments.of("2A2;1;PWYE;2;F04A24000B;4;11.20a;4;16777;PWYE"),
				  //cannot convert String to Integer
				  Arguments.of("2123;1;PWYE;2;F04A24000B;4e;11.20a;4;16777;PWYE")
				);
	}
	
	@Order(3)
	@ParameterizedTest
	@MethodSource
	public void postTerminalShouldReturnBadRequestWhenBodyIsInvalid(String htmlBody) throws Exception {
		
		mockMvc.perform(post("/").contentType(MediaType.TEXT_HTML_VALUE)
				.content(htmlBody)).andExpect(status().isBadRequest());
	}
	
	static Stream<Arguments> getTerminalShouldReturnResponseEntity() { 
		return Stream.of(
				  Arguments.of(44332211,"123","PWWIN", 0, "F04A2E4088B", 
							4, "8.00b3", 0, 16777216, "PWWIN"),
				  Arguments.of(2,"1","PWYE", 2, "F04A24000B", 
							4, "11.20a", 4, 16777, "PWYE")
				);
	}
	
	@Order(4)
	@ParameterizedTest
	@MethodSource
	public void getTerminalShouldReturnResponseEntity(Integer logic, String serial, String model, 
			Integer sam, String ptid, Integer plat, String version, Integer mxr, 
			Integer mxf, String pverfm) throws Exception {
		
		String expectedRes = objectMapper.writeValueAsString(new TerminalResponseDto(logic, serial, 
				model, sam, ptid, plat, version, mxr, mxf, pverfm));
		
		mockMvc.perform(get("/v1/terminal/"+logic))
				.andExpect(status().isOk()).andExpect(content().json(expectedRes));
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(ints = {1, 3, 5, 15, Integer.MAX_VALUE})
	public void getTerminalShouldReturnNotFoundWhenIdNotExist(Integer logic) throws Exception {
		
		mockMvc.perform(get("/v1/terminal/"+logic))
				.andExpect(status().isNotFound());
	}
	
	static Stream<Arguments> putTerminalShouldReturnResponseEntity() { 
		return Stream.of(
				  Arguments.of(44332211,"1234","PWWIN", 0, "F010101", 
							4, "8.00b3", 0, 16777216, "PWWIN"),
				  Arguments.of(2,"1","CSA", 2, "F0", 
							4, "11.20a", 4, 16777, "PWQRL")
				);
	}
	
	@Order(6)
	@ParameterizedTest
	@MethodSource
	public void putTerminalShouldReturnResponseEntity(Integer logic, String serial, String model, 
			Integer sam, String ptid, Integer plat, String version, Integer mxr, 
			Integer mxf, String pverfm) throws Exception {
		
		String resBody = objectMapper.writeValueAsString(new TerminalRequestDto(logic, serial, 
				model, sam, ptid, plat, version, mxr, mxf, pverfm));
		String expectedRes = objectMapper.writeValueAsString(new TerminalResponseDto(logic, serial, 
				model, sam, ptid, plat, version, mxr, mxf, pverfm));
		
		mockMvc.perform(put("/v1/terminal/"+logic).contentType(MediaType.APPLICATION_JSON)
				.content(resBody))
				.andExpect(status().isOk()).andExpect(content().json(expectedRes));
	}
	
	static Stream<Arguments> putTerminalShouldReturnNotFoundWhenIdNotExist() { 
		return Stream.of(
				  Arguments.of(1,"1234","PWWIN", 0, "F010101", 
							4, "8.00b3", 0, 16777216, "PWWIN"),
				  Arguments.of(5,"1","CSA", 2, "F0", 
							4, "11.20a", 4, 16777, "PWQRL")
				);
	}
	
	@Order(7)
	@ParameterizedTest
	@MethodSource
	public void putTerminalShouldReturnNotFoundWhenIdNotExist(Integer logic, String serial, String model, 
			Integer sam, String ptid, Integer plat, String version, Integer mxr, 
			Integer mxf, String pverfm) throws Exception {
		
		String resBody = objectMapper.writeValueAsString(new TerminalRequestDto(logic, serial, 
				model, sam, ptid, plat, version, mxr, mxf, pverfm));
		
		mockMvc.perform(put("/v1/terminal/"+logic).contentType(MediaType.APPLICATION_JSON)
				.content(resBody))
				.andExpect(status().isNotFound());
	}
	
	static Stream<Arguments> putTerminalShouldReturnUnsupportedMediaWhenNotJson() { 
		return Stream.of(
				  Arguments.of(1, MediaType.TEXT_HTML_VALUE),
				  Arguments.of(3, MediaType.TEXT_XML_VALUE, 
							4, MediaType.APPLICATION_PDF_VALUE)
				);
	}
	
	@Order(8)
	@ParameterizedTest
	@MethodSource
	public void putTerminalShouldReturnUnsupportedMediaWhenNotJson(Integer logic, String mediaType) throws Exception {
		
		mockMvc.perform(put("/v1/terminal/"+logic).contentType(mediaType))
				.andExpect(status().isUnsupportedMediaType());
	}
	
}
