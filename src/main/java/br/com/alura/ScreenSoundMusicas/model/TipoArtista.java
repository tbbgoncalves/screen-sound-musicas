package br.com.alura.ScreenSoundMusicas.model;

public enum TipoArtista {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipo;

    TipoArtista(String tipoArtista) {
        this.tipo = tipoArtista;
    }

    public static TipoArtista fromString(String texto) {
        for(TipoArtista tipoArtista: TipoArtista.values()) {
            if(tipoArtista.tipo.equalsIgnoreCase(texto)) {
                return tipoArtista;
            }
        }

        throw new IllegalArgumentException("Tipo de artista não encontrado para: " + texto);
    }
}
