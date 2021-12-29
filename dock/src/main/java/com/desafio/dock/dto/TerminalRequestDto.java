package com.desafio.dock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object that'll help to validate input attributes for the {@link Terminal} entity. 
 * @author Jefferson
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TerminalRequestDto {
	
	@NotNull(message= "logic may not be empty")
	private Integer logic;
	
	@NotBlank(message= "serial may not be empty")
	private String serial;
	
	@NotBlank(message= "model may not be empty")
	private String model;
	
	@Range(min = 0, message = "sam must be greater than zero")
	private Integer sam;
	
	private String ptid;
	private Integer plat;
	
	@NotNull(message= "version may not be empty")
	private String version;
	
	private Integer mxr;
	private Integer mxf;
	
	private String pverfm;
}
