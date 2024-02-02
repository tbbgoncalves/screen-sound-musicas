package br.com.alura.ScreenSoundMusicas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String album;

    @ManyToOne()
    private Artista artista;

    public Musica(){}

    public Musica(String nome, String album, Artista artista) {
        this.nome = nome;
        this.album = album;
        setArtista(artista);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        artista.getMusicas().add(this);
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nome='" + nome + '\'' +
                ", album='" + album + '\'' +
                ", artista=" + artista.getNome() +
                '}';
    }
}
