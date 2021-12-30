package com.desafio.dock.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple domain object representing a Terminal
 * @author Jefferson
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Terminal implements Serializable{

	private static final long serialVersionUID = -1390565164387113143L;
	
	@Id
	private Integer logic;
	private String serial;
	private String model;
	private Integer sam;
	private String ptid;
	private Integer plat;
	private String version;
	private Integer mxr;
	private Integer mxf;
	private String pverfm;
	
}
