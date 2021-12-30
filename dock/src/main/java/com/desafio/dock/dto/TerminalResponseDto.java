package com.desafio.dock.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object that'll be used for responses of the {@link Terminal} entity. 
 * @author Jefferson
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalResponseDto {
	
	private Integer logic;
	
	private String serial;
	
	private String model;
	
	private Integer sam;
	
	private String ptid;
	private Integer plat;
	
	private String version;
	
	private Integer mxr;
	private Integer mxf;
	
	@JsonProperty("PVERFM")
	private String pverfm;
}