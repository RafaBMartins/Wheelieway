package rafael.gabriel.caio.matheus.wheelieway.models;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    String nome;
    float nota;
    int selo;
    float distancia;
    int categoria;
    int imgEstabelecimento;

    ArrayList<EstabelecimentoItem> estabelecimentos = new ArrayList<>();

    public ArrayList<EstabelecimentoItem> getEstabelecimentos() {
        EstabelecimentoItem estabelecimento1 = new EstabelecimentoItem(nome, nota, selo, distancia, categoria, imgEstabelecimento);
        estabelecimentos.add(estabelecimento1);
        return estabelecimentos;
    }
}
