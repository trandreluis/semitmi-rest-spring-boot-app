package br.edu.ifpb.semiti.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.semiti.api.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}