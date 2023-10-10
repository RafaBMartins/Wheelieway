package rafael.gabriel.caio.matheus.wheelieway.models;

public class EstabelecimentoItem {
    String nome;
    float nota;
    int selo;
    float distancia;
    int categoria;
    int imgEstabelecimento;

    public EstabelecimentoItem(String nome, float nota, int selo, float distancia, int categoria, int imgEstabelecimento) {
        this.nome = nome;
        this.nota = nota;
        this.selo = selo;
        this.distancia = distancia;
        this.categoria = categoria;
        this.imgEstabelecimento = imgEstabelecimento;
    }
}
