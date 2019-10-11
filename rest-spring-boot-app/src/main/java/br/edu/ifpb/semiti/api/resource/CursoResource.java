package br.edu.ifpb.semiti.api.resource;

import java.util.ArrayList;
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
		List<Curso> cursos = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Curso curso = new Curso();

			// somente convertendo de int para Long para definir o codigo do curso
			// dinamicamente
			Long longCodigo = Long.parseLong(i + "");

			curso.setCodigo(longCodigo);
			curso.setNome("ADS " + i);
			curso.setQuantidadeDePeriodos(i);

			cursos.add(curso);
		}

		if (cursos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(cursos);
	}

}