package rafael.gabriel.caio.matheus.wheelieway;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    ArrayList<Estabelecimento> estabelecimentos = new ArrayList<>();

    public ArrayList<Estabelecimento> getEstabelecimentos() {
        Estabelecimento estabelecimento1 = new Estabelecimento();
        estabelecimentos.add(estabelecimento1);
        return estabelecimentos;
    }
}
