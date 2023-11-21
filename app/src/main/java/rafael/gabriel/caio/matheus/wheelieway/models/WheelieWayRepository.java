package rafael.gabriel.caio.matheus.wheelieway.models;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import rafael.gabriel.caio.matheus.wheelieway.util.Config;
import rafael.gabriel.caio.matheus.wheelieway.util.HttpRequest;
import rafael.gabriel.caio.matheus.wheelieway.util.Util;

public class WheelieWayRepository {

    Context context;

    public WheelieWayRepository(Context context) {this.context = context; }

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
     * Método que cria uma requisição HTTP para cadastrar um novo produto junto ao servidor web.
     * @param nome nome do estabelecimento
     * @param fotoEstabelecimento foto do estabelecimento
     * @param endereco endereço do estabelecimento
     * @param tipoEstabelecimento tipo do estabelecimento
     * @return true se o produto foi cadastrado junto ao servidor, false caso contrário
     */
    public boolean cadastrarEstabelecimento (Integer fotoEstabelecimento, String nome, String endereco, Integer tipoEstabelecimento){

        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "cadastroestabelecimento.php", "POST", "UTF-8");
        httpRequest.addParam("fotoEstabelecimento", String.valueOf(fotoEstabelecimento));
        httpRequest.addParam("nome", nome);
        httpRequest.addParam("endereco", endereco);
        httpRequest.addParam("tipoEstabelecimento",String.valueOf(tipoEstabelecimento));

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
     * Método que cria uma requisição HTTP para cadastrar uma nova avaliação junto ao servidor web.
     * @param fotoUsuario foto do usuário
     * @param nomeUsuario nome do usuário
     * @param descricao descrição do comentário
     * @param fotoAvaliacao foto do estabelecimento avaliado
     * @return true se o produto foi cadastrado junto ao servidor, false caso contrário
     */
    public boolean cadastrarAvaliacao (Integer fotoUsuario, String nomeUsuario, String descricao, Integer fotoAvaliacao){

        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL + "cadastroestabelecimento.php", "POST", "UTF-8");
        httpRequest.addParam("fotoUsuario", String.valueOf(fotoUsuario));
        httpRequest.addParam("nomeUsuario", nomeUsuario);
        httpRequest.addParam("descricao", descricao);
        httpRequest.addParam("fotoAvaliacao",String.valueOf(fotoAvaliacao));

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

    public List<EstabelecimentoItem> loadEstabelecimentos(Integer limit, Integer offSet, Double lat, Double lon) {

        List<EstabelecimentoItem> estabelecimentosList = new ArrayList<>();

        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL +"pegar_produtos.php", "GET", "UTF-8");
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

                    String id = jEstabelecimento.getString("id");
                    String nome = jEstabelecimento.getString("nome");
                    String imgEstabelecimento = jEstabelecimento.getString("imgEstabelecimento");
                    String distancia = jEstabelecimento.getString("distancia");
                    String nota = jEstabelecimento.getString("nota");
                    String selo = jEstabelecimento.getString("selo");
                    String categoria = jEstabelecimento.getString("categoria");

                    EstabelecimentoItem estabelecimento = new EstabelecimentoItem();
                    estabelecimento.id = id;
                    estabelecimento.nome = nome;
                    estabelecimento.selo = selo;
                    estabelecimento.categoria = categoria;
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

    /**
     * Método que cria uma requisição HTTP para obter os detalhes de um estabelecimento junto ao servidor web.
     * @param id id do estabelecimento que se deseja obter os detalhes
     * @return objeto do tipo estabelecimentoitem contendo os detalhes do produto
     */
    EstabelecimentoItem loadEstabelecimentosDetail(String id){

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
                String imgEstabelecimento = jsonObject.getString("imgEstabelecimento");
                String distancia = jsonObject.getString("distancia");
                String nota = jsonObject.getString("nota");
                String selo = jsonObject.getString("selo");
                String categoria = jsonObject.getString("categoria");

                EstabelecimentoItem estabelecimento = new EstabelecimentoItem();
                estabelecimento.id = id;
                estabelecimento.nome = nome;
                estabelecimento.selo = selo;
                estabelecimento.categoria = categoria;
                estabelecimento.distancia = distancia;
                estabelecimento.imgEstabelecimento = imgEstabelecimento;
                estabelecimento.nota = nota;

                return estabelecimento;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

