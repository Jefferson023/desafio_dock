package com.desafio.dock.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.dock.dto.TerminalRequestDto;
import com.desafio.dock.dto.TerminalResponseDto;
import com.desafio.dock.entity.Terminal;
import com.desafio.dock.repository.TerminalRepository;

import lombok.AllArgsConstructor;

/**
 * Service object that'll help to handle with data access for {@link #Terminal} entity. 
 * @author Jefferson
 *
 */
@Service
@AllArgsConstructor
public class TerminalService {

	private final String duplicatedIdMsg = "Duplicated ID";

	private TerminalRepository terminalRepository;

	private ModelMapper modelMapper;

	/**
	 * Search for an entity of {@link #Terminal} for a given Id, and returns 
	 * @param logic id parameter
	 * @return {@link #TerminalResponseDto} entity for specify Id or ResponseStatus not found
	 */
	public TerminalResponseDto findById(Integer logic) {
		try {
			TerminalResponseDto terminal = modelMapper.map(terminalRepository.findById(logic).get(),
					TerminalResponseDto.class);
			return terminal;
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Insert a new record of {@link #Terminal} entity into a database, and returns a 
	 * {@link #TerminalResponseDto} representation of the given entity. 
	 * @param terminalDTO a {@link #TerminalRequestDto} that'll be converted and saved 
	 * into a {@link #Terminal}
	 * @return {@link #TerminalResponseDto} representation of the new record persisted into database.  
	 */
	@Transactional
	public TerminalResponseDto create(TerminalRequestDto terminalDTO) {

		if (terminalRepository.findById(terminalDTO.getLogic()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, duplicatedIdMsg);
		}

		else {
			Terminal terminal = modelMapper.map(terminalDTO, Terminal.class);
			return modelMapper.map(terminalRepository.save(terminal), TerminalResponseDto.class);
		}
	}

	/**
	 * Update a record of {@link #Terminal} entity into a database, and returns a 
	 * {@link #TerminalResponseDto} representation of the given entity. 
	 * @param logic id of {@link #Terminal} that'll be updated. 
	 * @param responseDto a {@link #TerminalRequestDto} that'll be used to update the entity.
	 * @return {@link #TerminalResponseDto} representation of the new record persisted into database.
	 */
	@Transactional
	public TerminalResponseDto update(Integer logic, TerminalRequestDto responseDto) {

		if (terminalRepository.findById(logic).isPresent()) {

			Terminal terminal = modelMapper.map(responseDto, Terminal.class);
			terminal.setLogic(logic);

			return modelMapper.map(terminalRepository.save(terminal), TerminalResponseDto.class);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
}
