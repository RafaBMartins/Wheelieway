package rafael.gabriel.caio.matheus.wheelieway.models;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;

public class CadastrarAvaliacaoViewModel extends ViewModel {

    String nome;
    float nota;
    int imgFotoUsuario;
    String comentario;
    ArrayList<Integer> fotos = new ArrayList();
    Date data;

    boolean cadastrarAvaliacao(int estabelecimentoId) {
        return true;
    }


}
