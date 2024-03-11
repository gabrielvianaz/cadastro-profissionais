package tech.gviana.cadastroprofissionais.modules.profissional;

public enum Cargo {
    DESENVOLVEDOR("Desenvolvedor"),
    DESIGNER("Designer"),
    SUPORTE("Suporte"),
    TESTER("Tester");

    private String nome;

    private Cargo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
