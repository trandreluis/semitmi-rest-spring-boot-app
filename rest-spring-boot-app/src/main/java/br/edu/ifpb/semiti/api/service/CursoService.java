package br.edu.ifpb.semiti.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.semiti.api.exceptions.CursoNaoEncontradoException;
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

	public Curso buscarPorCodigo(Long codigo) {
		Optional<Curso> cursoRecuperado = cursoRepository.findById(codigo);
		if (cursoRecuperado.isPresent()) {
			return cursoRecuperado.get();
		}
		throw new CursoNaoEncontradoException();
	}

	public Curso atualizar(Long codigo, Curso curso) {
		Curso cursoSalvo = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(curso, cursoSalvo, "codigo");
		return cursoRepository.save(cursoSalvo);
	}

	public void deletar(Long codigo) {
		Optional<Curso> cursoRecuperado = cursoRepository.findById(codigo);
		if (cursoRecuperado.isPresent()) {
			cursoRepository.delete(cursoRecuperado.get());
		} else {
			throw new CursoNaoEncontradoException();			
		}
	}

}