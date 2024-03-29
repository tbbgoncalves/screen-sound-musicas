package br.com.alura.ScreenSoundMusicas.main;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import br.com.alura.ScreenSoundMusicas.model.Musica;
import br.com.alura.ScreenSoundMusicas.model.TipoArtista;
import br.com.alura.ScreenSoundMusicas.repository.ArtistaRepository;
import br.com.alura.ScreenSoundMusicas.repository.MusicaRepository;
import br.com.alura.ScreenSoundMusicas.service.ConsultaChatGPT;
import org.davidmoten.text.utils.WordWrap;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    Scanner leitura = new Scanner(System.in);
    private ArtistaRepository artistaRepository;

    private MusicaRepository musicaRepository;

    public Main(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void showMenu() {
        int opcao = 0;

        do {
            System.out.println("""
                \nOpções Disponíveis
                1 - Cadastrar artista
                2 - Cadastrar música
                3 - Listar músicas
                4 - Listar artistas
                5 - Buscar músicas por artista
                6 - Pesquisar dados sobre um artista
                0 - Sair
                
                Digite a opção desejada:""");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch(opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarArtistas();
                    break;
                case 5:
                    buscarMusicasPorArtista();
                    break;
                case 6:
                    pesquisarDadosArtista();
                    break;
                case 0:
                    System.out.println("Encerrando aplicação");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while(opcao != 0);
    }
    private void cadastrarArtista() {
        var cadastrarNovo = "S";

        do {
            System.out.println("Digite o nome do artista:");
            var nomeArtista = leitura.nextLine();
            System.out.println("Digite o tipo do artista (solo, dupla, ou banda):");
            var tipoArtista = leitura.nextLine();

            Artista artista = new Artista(nomeArtista, TipoArtista.fromString(tipoArtista));

            artistaRepository.save(artista);

            System.out.println("Artista salvo com sucesso");

            System.out.println("Deseja cadastrar um novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        } while (cadastrarNovo.equalsIgnoreCase("s"));
    }

    private void cadastrarMusica() {
        System.out.println("Digite o nome do artista da música:");
        Optional<Artista> artistaMusica = artistaRepository.buscarArtista(leitura.nextLine());

        if(artistaMusica.isPresent()) {
            Artista artista = artistaMusica.get();

            System.out.println("Digite o nome da música:");
            var nome = leitura.nextLine();
            System.out.println("Digite o nome do álbum:");
            var album = leitura.nextLine();

            Musica musica = new Musica(nome, album, artista);

            musicaRepository.save(musica);

            System.out.println("Música salva com sucesso");
        }
        else {
            System.out.println("Artista não encontrado. Realize o cadastro dele primeiramente para cadastrar a música.");
        }
    }

    private void listarMusicas() {
        List<Musica> musicas = musicaRepository.findAll();

        if(!musicas.isEmpty()) {
            musicas.forEach(System.out::println);
        }
        else {
            System.out.println("Nenhuma música cadastrada");
        }
    }

    private void listarArtistas() {
        List<Artista> artistas = artistaRepository.findAll();

        if(!artistas.isEmpty()) {
            artistas.forEach(System.out::println);
        }
        else {
            System.out.println("Nenhum artista cadastrado");
        }
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Digite o nome do artista:");
        Optional<Artista> artistaBuscado = artistaRepository.buscarArtista(leitura.nextLine());

        if(artistaBuscado.isPresent()) {
            Artista artista = artistaBuscado.get();
            List<Musica> musicas = artista.getMusicas();

            if(!musicas.isEmpty()) {
                System.out.println("Músicas de " + artista.getNome() + ":");
                musicas.forEach(System.out::println);
            }
            else {
                System.out.println("Nenhuma música cadastrada para o artista " + artista.getNome());
            }
        }
        else {
            System.out.println("Artista não cadastrado");
        }
    }

    private void pesquisarDadosArtista() {
        System.out.println("Digite o nome do artista:");
        var nomeArtista = leitura.nextLine();
        var retornoFormatado = WordWrap.from(ConsultaChatGPT.pesquisarInformacaoArtista(nomeArtista))
                        .maxWidth(150)
                        .insertHyphens(true)
                        .wrap();

        System.out.println(retornoFormatado);
    }
}
