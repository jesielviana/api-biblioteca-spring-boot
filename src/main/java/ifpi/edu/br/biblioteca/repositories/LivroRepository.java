package ifpi.edu.br.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifpi.edu.br.biblioteca.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
  List<Livro> findByNome(String nome);
}
