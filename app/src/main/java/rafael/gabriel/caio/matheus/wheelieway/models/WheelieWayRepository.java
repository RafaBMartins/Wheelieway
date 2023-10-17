package rafael.gabriel.caio.matheus.wheelieway.models;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

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

