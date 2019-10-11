package br.edu.ifpb.semiti.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.semiti.api.model.Curso;
import br.edu.ifpb.semiti.api.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository; 
	
	public Curso salvar(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public List<Curso> listar() {
		return cursoRepository.findAll();
	}
	
}