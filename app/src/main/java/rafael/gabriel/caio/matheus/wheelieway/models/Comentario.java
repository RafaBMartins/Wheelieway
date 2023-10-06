package rafael.gabriel.caio.matheus.wheelieway.models;

import java.util.ArrayList;
import java.util.Date;

public class Comentario {
    int imgFotoUsuario;
    String nome;
    float nota;
    String descricao;
    Date data;
    ArrayList<Integer> fotos = new ArrayList();
    int like;
    int dislike;


    public Comentario(int imgFotoUsuario, String nome, float nota, String descricao, Date data, ArrayList<Integer> fotos, int like, int dislike) {
        this.imgFotoUsuario = imgFotoUsuario;
        this.nome = nome;
        this.nota = nota;
        this.descricao = descricao;
        this.data = data;
        this.fotos = fotos;
        this.like = like;
        this.dislike = dislike;
    }
}
