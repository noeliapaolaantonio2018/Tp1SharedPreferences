package com.example.tp1sharedpreferences.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp1sharedpreferences.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class Activity_Registro extends AppCompatActivity {
    private EditText etRDni, etRApellido, etRNombre, etREmail, etRPassword;
    private Button btnRRegistrar;
    private RegistroVM registroVM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarVistas();

        btnRRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroVM.guardar(etRDni, etRApellido, etRNombre, etREmail, etRPassword);
            }
        });
    }

    private void cargarVistas() {
        registroVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroVM.class);
        etRDni = findViewById(R.id.etRegistroDni);
        etRApellido = findViewById(R.id.etRegistroApellido);
        etRNombre = findViewById(R.id.etRegistroNombre);
        etREmail = findViewById(R.id.etRegistroEmail);
        etRPassword = findViewById(R.id.etRegistroContrasena);
        btnRRegistrar = findViewById(R.id.btnRegistroRegistrar);
    }
}
