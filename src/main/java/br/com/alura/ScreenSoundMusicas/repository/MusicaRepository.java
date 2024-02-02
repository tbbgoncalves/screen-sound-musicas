package br.com.alura.ScreenSoundMusicas.repository;

import br.com.alura.ScreenSoundMusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
