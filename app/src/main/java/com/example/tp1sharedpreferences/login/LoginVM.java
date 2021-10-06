package com.example.tp1sharedpreferences.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp1sharedpreferences.model.Usuario;
import com.example.tp1sharedpreferences.preferencias.SpLogin;
import com.example.tp1sharedpreferences.registro.Activity_Registro;
import com.example.tp1sharedpreferences.registro.Activity_Registro_Logeado;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginVM extends AndroidViewModel {
    private MutableLiveData<String> llaveUsuario;

    public LoginVM(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getLlaveUsuario(){
        if(llaveUsuario == null){
            llaveUsuario = new MutableLiveData<>();
        }
        return llaveUsuario;
    }

    public void login(EditText etEmail, EditText etPassword){
        if (!validarLogin(etEmail, etPassword)) return;
        Usuario usuario = SpLogin.login(getApplication(), etEmail.getText().toString(), etPassword.getText().toString());
        if(usuario != null) llaveUsuario.setValue("Acceso concedido");
        else llaveUsuario.setValue("Acceso no concedido");
    }

    public Intent registrarActivity(Context context) {
        Intent intent = new Intent(context, Activity_Registro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public void verificacion(Context context, String respuesta) {
        if(respuesta.equals("Acceso concedido")){
            Intent intent = new Intent(context, Activity_Registro_Logeado.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else Toast.makeText(getApplication(),"Tus credenciales no coinciden con una cuenta en nuestro sistema", Toast.LENGTH_LONG).show();
    }

    public boolean validarLogin(EditText email, EditText password) {
        boolean esValido = true;
        String vEmail = email.getText().toString();
        String vClave = password.getText().toString();

        if (vEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(vEmail).matches()) {
            email.setError("Introduzca una dirección de correo electrónico válida");
            esValido = false;
        } else {
            email.setError(null);
        }
        if (vClave.isEmpty() || vClave.length() < 4 || vClave.length() > 10) {
            password.setError("Entre 4 y 10 caracteres");
            esValido = false;
        } else {
            password.setError(null);
        }

        return esValido;
    }
}
