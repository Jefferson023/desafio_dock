package com.desafio.dock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.desafio.dock.entity.Terminal;

/**
 * A interface for database operations in the {@link Terminal} entity.
 * @see CrudRepository 
 * @author Jefferson
 *
 */
@Repository
public interface TerminalRepository extends CrudRepository<Terminal, Integer>{

}
