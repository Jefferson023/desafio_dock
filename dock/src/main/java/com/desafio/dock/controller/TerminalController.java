package com.desafio.dock.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dock.dto.TerminalRequestDto;
import com.desafio.dock.dto.TerminalResponseDto;
import com.desafio.dock.service.TerminalService;

import lombok.AllArgsConstructor;

/**
 * Rest contoller for the {@link Terminal} entity.
 * @author Jefferson
 *
 */
@RestController
@AllArgsConstructor
public class TerminalController {

	private TerminalService terminalService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.TEXT_HTML_VALUE)
	public TerminalResponseDto postTerminal(@Valid @RequestBody TerminalRequestDto terminal) {
		return terminalService.create(terminal);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping( value = "v1/terminal/{logic}")
	public TerminalResponseDto getTerminal(@PathVariable Integer logic) {
		return terminalService.findById(logic);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping(value = "v1/terminal/{logic}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TerminalResponseDto putTerminal(@PathVariable Integer logic, 
			@Valid @RequestBody TerminalRequestDto terminal ) {
		return terminalService.update(logic, terminal);
	}

}
