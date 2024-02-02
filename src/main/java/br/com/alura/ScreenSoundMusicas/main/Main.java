package br.com.alura.ScreenSoundMusicas.main;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import br.com.alura.ScreenSoundMusicas.model.Musica;
import br.com.alura.ScreenSoundMusicas.model.TipoArtista;
import br.com.alura.ScreenSoundMusicas.repository.ArtistaRepository;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    Scanner leitura = new Scanner(System.in);
    private ArtistaRepository artistaRepository;

    public Main(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void showMenu() {
        int opcao = 0;

        do {
            System.out.println("""
                Opções Disponíveis
                1 - Cadastrar artista
                2 - Cadastrar música
                3 - Listar músicas
                4 - Buscar músicas por artista
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
        System.out.println("Digite o nome do artista:");
        var nomeArtista = leitura.nextLine();
        System.out.println("Digite o tipo do artista (solo, dupla, ou banda):");
        var tipoArtista = leitura.nextLine();

        Artista artista = new Artista(nomeArtista, TipoArtista.fromString(tipoArtista));

        artistaRepository.save(artista);

        System.out.println("Artista salvo com sucesso");
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

            Musica musica = new Musica(nome, album);
            artista.addMusica(musica);

            artistaRepository.save(artista);

            System.out.println("Música salva com sucesso");
        }
        else {
            System.out.println("Artista não encontrado. Realize o cadastro dele primeiramente para cadastrar a música.");
        }
    }

    private void listarMusicas() {
    }

    private void pesquisarDadosArtista() {
    }

}
