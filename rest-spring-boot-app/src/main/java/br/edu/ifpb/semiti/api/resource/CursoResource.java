package br.edu.ifpb.semiti.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.semiti.api.model.Curso;

@RestController("/cursos")
public class CursoResource {

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