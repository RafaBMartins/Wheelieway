package rafael.gabriel.caio.matheus.wheelieway.models;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "login.php", "POST", "UTF-8");
        httpRequest.addParam("novo_nome", newName);
        httpRequest.addParam("novo_email", newEmail);
        httpRequest.addParam("nova_senha", newPassword);

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

        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "login.php", "POST", "UTF-8" );
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

    ///////////////////////
    Perfil loadPerfilDetail(String id) {

        // Para obter a lista de produtos é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "pegar_detalhes_produto.php", "GET", "UTF-8");
        httpRequest.addParam("id", id);

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(login, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1,"nome":"produto 1","preco":"10.00", "img":"www.imgur.com/img1.jpg", "descricao":"produto 1","criado_em":"2022-10-03 19:43:31.42905","criado_por":"daniel"}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao obter detalhes do produto"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os detalhes do produto são obtidos da String JSON e um objeto
            // do tipo Product é criado para guardar esses dados
            if(success == 1) {

                // obtém os dados detalhados do produto. A imagem não vem junto. Ela é obtida
                // separadamente depois, no momento em que precisa ser exibida na app. Isso permite
                // que os dados trafeguem mais rápido.
                String nome = jsonObject.getString("nome");
                String img = jsonObject.getString("img");


                // Cria um objeto Product e guarda os detalhes do produto dentro dele.
                Perfil p = new Perfil();
                p.nome = nome;
                p.id = id;
                p.img = img;

                return p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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

        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "cadastroestabelecimento.php", "POST", "UTF-8");
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


}
