package com.example.tp1sharedpreferences.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp1sharedpreferences.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class Activity_Login extends AppCompatActivity {
    private EditText etLEmail,etLContrasena;
    private Button lBtnIngresar,lBtnRegistrar;
    private  LoginVM loginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarVistas();

        loginVM.getLlaveUsuario().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String respuesta) {
                loginVM.verificacion(getApplication(), respuesta);
            }
        });

        lBtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginVM.login(etLEmail, etLContrasena);
            }
        });

        lBtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginVM.registrarActivity(getApplication()));
            }
        });
    }

    private void cargarVistas() {
        loginVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginVM.class);
        etLEmail = findViewById(R.id.etLogin01Email);
        etLContrasena = findViewById(R.id.etLogin01Contrasena);
        lBtnIngresar = findViewById(R.id.btnLogin01Ingresar);
        lBtnRegistrar = findViewById(R.id.btnLogin01Registrar);
    }

}
