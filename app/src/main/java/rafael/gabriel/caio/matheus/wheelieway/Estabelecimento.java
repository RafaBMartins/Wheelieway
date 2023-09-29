package rafael.gabriel.caio.matheus.wheelieway;

public class Estabelecimento {
    String nome;
    float nota;
    int selo;
    float distancia;
    int categoria;
    int imgEstabelecimento;

    public Estabelecimento(String nome, float nota, int selo, float distancia, int categoria, int imgEstabelecimento) {
        this.nome = nome;
        this.nota = nota;
        this.selo = selo;
        this.distancia = distancia;
        this.categoria = categoria;
        this.imgEstabelecimento = imgEstabelecimento;
    }
}
