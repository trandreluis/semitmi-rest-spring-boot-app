package br.edu.ifpb.semiti.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.semiti.api.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}