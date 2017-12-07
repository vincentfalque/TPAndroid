package io.falque.vincent.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

public class UserStorage {

    private static final String USER_NAME = "Name";
    private static final String USER_EMAIL = "Email";
    public static final String USER_UNDEFINED = "Undefined";

    public static void saveUserInfo(Context context, String name, String email) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(USER_NAME, name);
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public static void deleteUserInfo(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(USER_NAME);
        editor.remove(USER_EMAIL);
        editor.apply();
    }

    public static ArrayList<String> getUserInfo(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String nom = preferences.getString(USER_NAME, USER_UNDEFINED);
        String email = preferences.getString(USER_EMAIL, USER_UNDEFINED);

        ArrayList<String> identifiants = new ArrayList<String>();
        identifiants.add(nom);
        identifiants.add(email);
        return identifiants;
    }

    public static User getUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new User(preferences.getString(USER_NAME, USER_UNDEFINED), preferences.getString(USER_NAME, USER_UNDEFINED));
    }

}
