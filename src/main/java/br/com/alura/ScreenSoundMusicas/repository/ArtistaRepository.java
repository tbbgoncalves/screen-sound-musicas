package br.com.alura.ScreenSoundMusicas.repository;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE %:nomeArtista%")
    Optional<Artista> buscarArtista(String nomeArtista);
}
