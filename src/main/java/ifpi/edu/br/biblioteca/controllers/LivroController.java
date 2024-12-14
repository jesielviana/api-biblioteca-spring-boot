package ifpi.edu.br.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifpi.edu.br.biblioteca.models.Livro;
import ifpi.edu.br.biblioteca.repositories.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

  @Autowired
  private LivroRepository livroRepository;

  @GetMapping
  public List<Livro> get(@RequestParam(required = false) String nome) {
    if (nome == null) {
      return livroRepository.findAll();
    }
    return livroRepository.findByNome(nome);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Livro> getById(@PathVariable long id) {
    Optional<Livro> optional = livroRepository.findById(id);
    if (optional.isPresent()) {
      return ResponseEntity.ok().body(optional.get());
    }
    return ResponseEntity.notFound().build();

  }

  @PostMapping
  @Transactional
  public ResponseEntity<Livro> add(@RequestBody @Valid Livro livro) {
    livroRepository.save(livro);
    return ResponseEntity.status(HttpStatus.CREATED).body(livro);
  }
}
