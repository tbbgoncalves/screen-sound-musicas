package br.com.alura.ScreenSoundMusicas.repository;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
