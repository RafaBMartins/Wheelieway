package rafael.gabriel.caio.matheus.wheelieway.models;

import java.util.ArrayList;

public class Perfil {
    public String id;
    public String nome;
    public String img;
    ArrayList<ComentarioItem> comentarios = new ArrayList<>();

    public ArrayList<ComentarioItem> getComentarios() {
        ComentarioItem comentario1 = new ComentarioItem();
        comentarios.add(comentario1);
        return comentarios;
    }
}
