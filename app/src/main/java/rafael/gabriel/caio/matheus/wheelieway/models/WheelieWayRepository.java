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

    public boolean login (String email, String senha){
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "login.php", "POST", "UTF-8" )


    }
}
