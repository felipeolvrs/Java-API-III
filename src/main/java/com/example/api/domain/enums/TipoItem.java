package com.example.api.domain.enums;

public enum TipoItem {

    // --- MÍDIA FÍSICA ---
    DVD("DVD"),
    BLURAY("Blu-ray"),
    CD_MUSICA("CD de Música"),
    VINIL("Disco de Vinil"),
    JOGO_MIDIA_FISICA("Jogo (Mídia Física)"),

    // --- MÍDIA DIGITAL ---
    FILME_DIGITAL("Filme (Digital)"),
    SERIE_DIGITAL("Série (Digital)"),
    EBOOK("E-book"),
    AUDIOBOOK("Audiobook"),
    MUSICA_DIGITAL("Música (Digital)"),
    JOGO_DIGITAL("Jogo (Digital / Licença)"),
    SOFTWARE("Software / Licença"),

    // --- PUBLICAÇÕES IMPRESSAS ---
    LIVRO("Livro"),
    REVISTA("Revista"),
    HQ_MANGA("HQ / Mangá / Quadrinho"),
    JORNAL("Jornal"),
    MANUAL_TECNICO("Manual Técnico"),
    ARTIGO_ACADEMICO("Artigo Acadêmico"),

    // --- JOGOS ANALÓGICOS ---
    JOGO_TABULEIRO("Jogo de Tabuleiro"),
    JOGO_CARTAS("Jogo de Cartas (Baralho, TCG)"),
    RPG_LIVRO("Livro de RPG"),

    // --- HARDWARE E DISPOSITIVOS ---
    NOTEBOOK("Notebook"),
    TABLET("Tablet"),
    SMARTPHONE("Smartphone"),
    CONSOLE_VIDEO_GAME("Console de Videogame"),
    CONSOLE_PORTATIL("Console Portátil"),
    LEITOR_EBOOK("Leitor de E-book (Kindle)"),
    PROJETOR("Projetor"),

    // --- ACESSÓRIOS ELETRÔNICOS ---
    CONTROLE_JOGO("Controle de Jogo / Joystick"),
    FONE_OUVIDO("Fone de Ouvido"),
    TECLADO("Teclado"),
    MOUSE("Mouse"),
    CAIXA_SOM_BLUETOOTH("Caixa de Som Bluetooth"),
    CARREGADOR_ADAPTADOR("Carregador / Adaptador"),

    // --- EQUIPAMENTOS E FERRAMENTAS ---
    FERRAMENTA_ELETRICA("Ferramenta Elétrica"),
    FERRAMENTA_MANUAL("Ferramenta Manual"),
    EQUIPAMENTO_AUDIOVISUAL("Equipamento Audiovisual"),
    EQUIPAMENTO_ESPORTIVO("Equipamento Esportivo"),
    INSTRUMENTO_MUSICAL("Instrumento Musical"),

    // --- GERAL ---
    MATERIAL_ESCOLAR("Material Escolar"),
    BRINQUEDO("Brinquedo"),
    ITEM_COLECIONAVEL("Item Colecionável"),
    OUTRO("Outro");

    private final String descricao;

    TipoItem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoItem fromString(String nome) {
        for (TipoItem tipo : TipoItem.values()) {
            if (tipo.name().equalsIgnoreCase(nome) || tipo.descricao.equalsIgnoreCase(nome)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoItem inválido: " + nome);
    }
}
