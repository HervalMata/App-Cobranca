package com.algaworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.model.StatusTitulo;
import com.algaworks.model.Titulo;
import com.algaworks.repository.Titulos;

@Service
public class CadastroTituloService {
	
	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void delete(Long codigo) {
		titulos.delete(codigo);
		
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.findOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		return titulo.getStatus().getDescricao();
	}

}
