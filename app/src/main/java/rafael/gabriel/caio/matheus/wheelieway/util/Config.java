package rafael.gabriel.caio.matheus.wheelieway.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Classe de suporte as configurações da app
 */
public class Config {

    // endereço base do servidor web
    public static String SERVER_URL = "https://pi2023-u7xly6uh.b4a.run/php/phpMobile/";

    /**
     * Salva o login no espaço reservado da app
     * @param context contexto da app
     * @param login o login
     */
    public static void setLogin(Context context, String login) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("login", login).commit();
    }

    /**
     * Obtem o login
     * @param context
     * @return
     */
    public static String getLogin(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("login", "");
    }

    /**
     * @param context
     * @param password
     */
    public static void setPassword(Context context, String password) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("password", password).commit();
    }

    /**
     * @param context
     * @return
     */
    public static String getPassword(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("password", "");
    }
}

