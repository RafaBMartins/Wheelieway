package rafael.gabriel.caio.matheus.wheelieway.models;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import rafael.gabriel.caio.matheus.wheelieway.classes.Estabelecimento;

public class HomeViewModel extends ViewModel {

    ArrayList<Estabelecimento> estabelecimentos = new ArrayList<>();

    public ArrayList<Estabelecimento> getEstabelecimentos() {
        Estabelecimento estabelecimento1 = new Estabelecimento();
        estabelecimentos.add(estabelecimento1);
        return estabelecimentos;
    }
}