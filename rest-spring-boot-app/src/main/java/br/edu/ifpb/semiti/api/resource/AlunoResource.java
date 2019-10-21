package br.edu.ifpb.semiti.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.semiti.api.model.Aluno;
import br.edu.ifpb.semiti.api.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;

	@PostMapping
	public ResponseEntity<Aluno> salvar(@RequestBody @Valid Aluno aluno) {
		Aluno alunoSalvo = alunoService.salvar(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
	}

	@GetMapping
	public ResponseEntity<List<Aluno>> listar() {
		List<Aluno> alunos = alunoService.listar();
		if (alunos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(alunos);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Aluno> buscarPorCodigo(@PathVariable Long codigo) {
		Aluno alunoRecuperado = alunoService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(alunoRecuperado);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Aluno> buscarPorCodigo(@PathVariable Long codigo, @RequestBody Aluno aluno) {
		Aluno alunoAtualizado = alunoService.atualizar(codigo, aluno);
		return ResponseEntity.ok(alunoAtualizado);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		alunoService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

}