package br.com.alura.ScreenSoundMusicas.main;

import br.com.alura.ScreenSoundMusicas.repository.ArtistaRepository;

public class Main {
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
                0 - Sair""");

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
                    System.out.println("Encerrado aplicação");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while(opcao != 0);
    }
    private void cadastrarArtista() {
    }

    private void cadastrarMusica() {
    }

    private void listarMusicas() {
    }

    private void pesquisarDadosArtista() {
    }

}
