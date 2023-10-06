package rafael.gabriel.caio.matheus.wheelieway.models;

import java.util.ArrayList;

public class PerfilViewModel {
    public String id;
    public String nome;
    ArrayList<Comentario> comentarios = new ArrayList<>();

    public ArrayList<Comentario> getComentarios() {
        Comentario comentario1 = new Comentario();
        comentarios.add(comentario1);
        return comentarios;
    }
}