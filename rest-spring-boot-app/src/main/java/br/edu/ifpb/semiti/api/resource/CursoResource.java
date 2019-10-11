package br.edu.ifpb.semiti.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.semiti.api.model.Curso;
import br.edu.ifpb.semiti.api.service.CursoService;

@RestController("/cursos")
public class CursoResource {

	@Autowired
	private CursoService cursoService;

	@PostMapping
	public ResponseEntity<Curso> salvar(@RequestBody Curso curso) {
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

}