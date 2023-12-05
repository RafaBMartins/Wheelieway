package rafael.gabriel.caio.matheus.wheelieway.models;

import org.json.JSONArray;

public class ComentarioItem {
    public String id;

    public String imgFotoUsuario;

    public String nome;

    public String nota;
    public String descricao;
    public String data;
    public JSONArray fotos = new JSONArray();
    public String like;
    public String dislike;

}
