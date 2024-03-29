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

import br.edu.ifpb.semiti.api.model.Curso;
import br.edu.ifpb.semiti.api.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoResource {

	@Autowired
	private CursoService cursoService;

	@PostMapping
	public ResponseEntity<Curso> salvar(@RequestBody @Valid Curso curso) {
		Curso cursoSalvo = cursoService.salvar(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
	}

	@GetMapping
	public ResponseEntity<List<Curso>> listar() {
		List<Curso> cursos = cursoService.listar();
		if (cursos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(cursos);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Curso> buscarPorCodigo(@PathVariable Long codigo) {
		Curso cursoRecuperado = cursoService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(cursoRecuperado);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Curso> buscarPorCodigo(@PathVariable Long codigo, @RequestBody Curso curso) {
		Curso cursoAtualizado = cursoService.atualizar(codigo, curso);
		return ResponseEntity.ok(cursoAtualizado);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		cursoService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

}