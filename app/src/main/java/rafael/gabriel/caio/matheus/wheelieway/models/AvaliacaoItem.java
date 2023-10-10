package rafael.gabriel.caio.matheus.wheelieway.models;

import java.util.ArrayList;
import java.util.Date;

public class AvaliacaoItem {
    String nome;
    float nota;
    int imgFotoUsuario;
    String comentario;
    ArrayList<Integer> fotos = new ArrayList();
    Date data;

    public AvaliacaoItem(String nome, float nota, int imgFotoUsuario, String comentario, ArrayList<Integer> fotos, Date data) {
        this.nome = nome;
        this.nota = nota;
        this.imgFotoUsuario = imgFotoUsuario;
        this.comentario = comentario;
        this.fotos = fotos;
        this.data = data;
    }


    public AvaliacaoItem(String nome, float nota, int imgFotoUsuario, String comentario, Date data) {
        this.nome = nome;
        this.nota = nota;
        this.imgFotoUsuario = imgFotoUsuario;
        this.comentario = comentario;
        this.data = data;
    }
}
