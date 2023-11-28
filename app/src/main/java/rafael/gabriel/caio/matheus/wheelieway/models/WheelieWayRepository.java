package rafael.gabriel.caio.matheus.wheelieway.models;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import rafael.gabriel.caio.matheus.wheelieway.util.Config;
import rafael.gabriel.caio.matheus.wheelieway.util.HttpRequest;
import rafael.gabriel.caio.matheus.wheelieway.util.Util;

public class WheelieWayRepository {

    Context context;

    public WheelieWayRepository(Context context) {this.context = context;}

    public boolean cadastrarEstabelecimento (String fotoEstabelecimento, String nome, String distancia, String nota, String tipoEstabelecimento, String selo, String estado, String cidade, String bairro, String tipoLogradouro, String logradouro, String numero, String latitude, String longitude){

        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "insertEstabelecimento.php", "POST", "UTF-8");
        httpRequest.addFile("img_perfil", new File(fotoEstabelecimento));
        httpRequest.addParam("nome_estabelecimento", nome);
        httpRequest.addParam("tipo_estab", tipoEstabelecimento);
        httpRequest.addParam("estado", estado);
        httpRequest.addParam("cidade", cidade);
        httpRequest.addParam("bairro", bairro);
        httpRequest.addParam("tipo_logradouro", tipoLogradouro);
        httpRequest.addParam("logradouro", logradouro);
        httpRequest.addParam("numero", numero);
        httpRequest.addParam("latitude", latitude);
        httpRequest.addParam("longitude", longitude);


        httpRequest.setBasicAuth(login, password);

        String result = "";

        try{

            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP ADD PRODUCT RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, significa que o produto foi adicionado com sucesso.
            if(success == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }

    /**
     * Método que cria uma requisição HTTP para registrar um novo usuário junto ao servidor web.
     * @param newName o login do novo usuário
     * @param newEmail o login do novo usuário
     * @param newPassword a senha do novo usuário
     * @return true se o usuário foi cadastrado e false caso contrário
     */
    public boolean register(String newName, String newEmail, String newPassword){

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "registrar.php", "POST", "UTF-8");
        httpRequest.addParam("userName", newName);
        httpRequest.addParam("email", newEmail);
        httpRequest.addParam("password", newPassword);

        String result = "";
        try{
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            Log.i("HTTP REGISTER RESULT", result);

            httpRequest.finish();

            JSONObject jsonObject = new JSONObject(result);

            int success = jsonObject.getInt("sucesso");

            if(success == 1){
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }

    /**
     * Método que cria uma requisição HTTP para autenticar um usuário junto ao servidor web.
     * @param email o email do novo usuário
     * @param senha a senha do novo usuário
     * @return true se o usuário foi autenticado e false caso contrário
     */
    public boolean login (String email, String senha){

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "login.php", "POST", "UTF-8" );
        httpRequest.setBasicAuth(email,senha);

        String result = "";
        try {
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP LOGIN RESULT",result);

            JSONObject jsonObject = new JSONObject(result);

            int success = jsonObject.getInt("sucesso");

            if (success == 1) {
                return true;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }

    /**
     * Método que cria uma requisição HTTP para cadastrar uma nova avaliação junto ao servidor web.
     * @param fotoUsuario foto do usuário
     * @param nomeUsuario nome do usuário
     * @param descricao descrição do comentário
     * @param fotoAvaliacao foto do estabelecimento avaliado
     * @return true se o produto foi cadastrado junto ao servidor, false caso contrário
     */
    public boolean cadastrarComentario (String fotoUsuario, String nomeUsuario, String descricao, String fotoAvaliacao, String nota){

        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "cadastroestabelecimento.php", "POST", "UTF-8");
        httpRequest.addParam("fotoUsuario", fotoUsuario);
        httpRequest.addParam("nomeUsuario", nomeUsuario);
        httpRequest.addParam("descricao", descricao);
        httpRequest.addParam("fotoAvaliacao", fotoAvaliacao);
        httpRequest.addParam("nota", nota);

        httpRequest.setBasicAuth(login, password);

        String result = "";

        try{

            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP ADD PRODUCT RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, significa que o produto foi adicionado com sucesso.
            if(success == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }

    /**
     * Método que cria uma requisição HTTP para obter uma página/bloco de estabelecimentos junto ao servidor web.
     * @param limit a quantidade de estabelecimentos a serem obtidos
     * @param offSet a posição a partir da qual a página de estabelecimentos deve começar
     * @return lista de estabelecimentos
     */

    public List<EstabelecimentoItem> loadEstabelecimentos (Integer limit, Integer offSet, Double lat, Double lon) {

        List<EstabelecimentoItem> estabelecimentosList = new ArrayList<>();

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL +"insertEstabelecimento.php", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset", offSet.toString());


        String result = "";
        try{
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP PRODUCTS RESULT", result);

            JSONObject jsonObject = new JSONObject(result);

            int success = jsonObject.getInt("sucesso");

            if(success == 1){

                JSONArray jsonArray = jsonObject.getJSONArray("estabelecimentos");

                for(int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jEstabelecimento = jsonArray.getJSONObject(i);

                    String nome = jEstabelecimento.getString("nome");
                    String imgEstabelecimento = jEstabelecimento.getString("imgEstabelecimento");
                    String distancia = jEstabelecimento.getString("distancia");
                    String nota = jEstabelecimento.getString("nota");
                    String selo = jEstabelecimento.getString("selo");
                    String tipoEstabelecimento = jEstabelecimento.getString("tipoEstabelecimento");


                    EstabelecimentoItem estabelecimento = new EstabelecimentoItem();
                    estabelecimento.nome = nome;
                    estabelecimento.selo = selo;
                    estabelecimento.tipoEstabelecimento = tipoEstabelecimento;
                    estabelecimento.distancia = distancia;
                    estabelecimento.imgEstabelecimento = imgEstabelecimento;
                    estabelecimento.nota = nota;

                    estabelecimentosList.add(estabelecimento);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return estabelecimentosList;
    }

    public List<ComentarioItem> loadComentarios (Integer limit, Integer offSet) {

        List<ComentarioItem> comentariosList = new ArrayList<>();

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL +"registrar.php", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset", offSet.toString());


        String result = "";
        try{
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP PRODUCTS RESULT", result);

            JSONObject jsonObject = new JSONObject(result);

            int success = jsonObject.getInt("sucesso");

            if(success == 1){

                JSONArray jsonArray = jsonObject.getJSONArray("estabelecimentos");

                for(int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jComentario = jsonArray.getJSONObject(i);

                    String nome = jComentario.getString("nome");
                    String descricao = jComentario.getString("descricao");
                    String data = jComentario.getString("data");
                    String nota = jComentario.getString("nota");
                    String like = jComentario.getString("like");
                    String dislike = jComentario.getString("dislike");
                    String imgFotoUsuario = jComentario.getString("imgFotoUsuario");
                    /**Perguntar pro Daniel como que se faz o get de uma ArrayList*/
                    JSONArray fotos = jComentario.getJSONArray("fotos");


                    ComentarioItem comentario = new ComentarioItem();
                    comentario.nome = nome;
                    comentario.descricao = descricao;
                    comentario.data = data;
                    comentario.like = like;
                    comentario.dislike = dislike;
                    comentario.nota = nota;
                    comentario.imgFotoUsuario = imgFotoUsuario;
                    comentario.fotos = fotos;

                    comentariosList.add(comentario);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return comentariosList;
    }

    /**
     * Método que cria uma requisição HTTP para obter os detalhes de um estabelecimento junto ao servidor web.
     *  id do estabelecimento que se deseja obter os detalhes
     * @return objeto do tipo estabelecimentoitem contendo os detalhes do produto
     */
    EstabelecimentoItem loadEstabelecimentosDetail(String id1){

        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "pager_detalhes_produto.php", "GET", "UTF-8");
        httpRequest.addParam("id", id1);

        httpRequest.setBasicAuth(login, password);

        String result = "";
        try{
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);

            JSONObject jsonObject = new JSONObject(result);

            int success = jsonObject.getInt("sucesso");


            if(success == 1) {

                String numero = jsonObject.getString("numero");
                String nome = jsonObject.getString("nome");
                String imgEstabelecimento = jsonObject.getString("imgEstabelecimento");
                String distancia = jsonObject.getString("distancia");
                String nota = jsonObject.getString("nota");
                String selo = jsonObject.getString("selo");
                String tipoEstabelecimento = jsonObject.getString("categoria");
                String tipoLogradouro = jsonObject.getString("logradouroSelect");
                String logradouro = jsonObject.getString("logradouroWrite");
                String estado = jsonObject.getString("estado");
                String cidade = jsonObject.getString("cidade");
                String bairro = jsonObject.getString("bairro");
                String latitude = jsonObject.getString("latitude");
                String longitude = jsonObject.getString("longitude");

                EstabelecimentoItem estabelecimento = new EstabelecimentoItem();
                estabelecimento.numero = numero;
                estabelecimento.nome = nome;
                estabelecimento.selo = selo;
                estabelecimento.tipoEstabelecimento = tipoEstabelecimento;
                estabelecimento.distancia = distancia;
                estabelecimento.imgEstabelecimento = imgEstabelecimento;
                estabelecimento.nota = nota;
                estabelecimento.tipologradouro = tipoLogradouro;
                estabelecimento.logradouro = logradouro;
                estabelecimento.estado = estado;
                estabelecimento.cidade = cidade;
                estabelecimento.bairro = bairro;
                estabelecimento.latitude = latitude;
                estabelecimento.longitude = longitude;

                return estabelecimento;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    ComentarioItem loadComentariosDetail(String id){

        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "pager_detalhes_produto.php", "GET", "UTF-8");
        httpRequest.addParam("id", id);

        httpRequest.setBasicAuth(login, password);

        String result = "";
        try{
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);

            JSONObject jsonObject = new JSONObject(result);

            int success = jsonObject.getInt("sucesso");


            if(success == 1) {

                String nome = jsonObject.getString("nome");
                String imgFotoUsuario = jsonObject.getString("imgFotoUsuario");
                String descricao = jsonObject.getString("descricao");
                String nota = jsonObject.getString("nota");
                String like = jsonObject.getString("like");
                String dislike = jsonObject.getString("dislike");
                String data = jsonObject.getString("data");
                JSONArray fotos = jsonObject.getJSONArray("fotos");

                ComentarioItem comentario = new ComentarioItem();
                comentario.nome = nome;
                comentario.descricao = descricao;
                comentario.data = data;
                comentario.like = like;
                comentario.dislike = dislike;
                comentario.nota = nota;
                comentario.imgFotoUsuario = imgFotoUsuario;
                comentario.fotos = fotos;

                return comentario;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

