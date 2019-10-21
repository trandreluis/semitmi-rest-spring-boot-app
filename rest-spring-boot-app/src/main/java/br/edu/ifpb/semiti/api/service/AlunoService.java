package br.edu.ifpb.semiti.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.semiti.api.exceptions.AlunoNaoEncontradoException;
import br.edu.ifpb.semiti.api.model.Aluno;
import br.edu.ifpb.semiti.api.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CursoService cursoService;

	public Aluno salvar(Aluno aluno) {
		validarAluno(aluno);
		return alunoRepository.save(aluno);
	}

	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	public Aluno buscarPorCodigo(Long codigo) {
		Optional<Aluno> alunoRecuperado = alunoRepository.findById(codigo);
		if (alunoRecuperado.isPresent()) {
			return alunoRecuperado.get();
		}
		throw new AlunoNaoEncontradoException();
	}

	public Aluno atualizar(Long codigo, Aluno aluno) {
		Aluno alunoSalvo = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(aluno, alunoSalvo, "codigo");
		return alunoRepository.save(alunoSalvo);
	}

	public void deletar(Long codigo) {
		Aluno alunoRecuperado = buscarPorCodigo(codigo);
		alunoRepository.delete(alunoRecuperado);
	}

	private void validarAluno(Aluno aluno) {
		// caso o código do curso não corresponda a nenhum curso cadastrado, então uma
		// exception será lançada pelo método buscarPoCodigo() do curso service
		cursoService.buscarPorCodigo(aluno.getCurso().getCodigo());
	}

}