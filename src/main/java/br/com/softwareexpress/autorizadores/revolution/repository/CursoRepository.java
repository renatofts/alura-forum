package br.com.softwareexpress.autorizadores.revolution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softwareexpress.autorizadores.revolution.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);
}
