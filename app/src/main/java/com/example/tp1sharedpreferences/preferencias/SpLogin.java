package com.example.tp1sharedpreferences.preferencias;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tp1sharedpreferences.model.Usuario;

public class SpLogin {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("usuario",0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("email", usuario.getEmail());
        editor.putString("password", usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences sp = conectar(context);
        int dni = sp.getInt("dni",-1);
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String email = sp.getString("email","-1");
        String password = sp.getString("password","-1");

        Usuario usuario = new Usuario(dni, apellido, nombre, email, password);
        return usuario;
    }

    public static Usuario login(Context context, String email, String password){
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        int dni = sp.getInt("dni",-1);
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String mail = sp.getString("email","-1");
        String contra = sp.getString("password","-1");

        if(email.equals(mail)&password.equals(contra)){
            usuario = new Usuario(dni, apellido, nombre, mail, contra);
        }
        return usuario;
    }
}
