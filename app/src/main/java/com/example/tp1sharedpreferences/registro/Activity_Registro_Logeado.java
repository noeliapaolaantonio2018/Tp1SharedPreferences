package com.example.tp1sharedpreferences.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp1sharedpreferences.R;
import com.example.tp1sharedpreferences.model.Usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class Activity_Registro_Logeado extends AppCompatActivity {

    private EditText etRDni, etRApellido, etRNombre, etREmail, etRPassword;
    private Button btnRRegistrar, btnREditar, btnRGuardar;
    private RegistroVM registroVM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarVistas();

        registroVM.getUsuarioMLD().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                registroVM.llenarFormularioInmueble(etRDni, etRApellido, etRNombre, etREmail, etRPassword, usuario);
            }
        });

        btnREditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registroVM.habilitarFormulario(etRDni, etRApellido, etRNombre, etREmail, etRPassword, btnREditar, btnRGuardar);
            }
        });

        btnRGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registroVM.guardar(etRDni, etRApellido, etRNombre, etREmail, etRPassword);
            }
        });

        registroVM.deshabilitarFormulario(etRDni, etRApellido, etRNombre, etREmail, etRPassword, btnRRegistrar, btnREditar);

    }

    private void cargarVistas() {
        registroVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroVM.class);

        etRDni = findViewById(R.id.etRegistroDni);
        etRApellido = findViewById(R.id.etRegistroApellido);
        etRNombre = findViewById(R.id.etRegistroNombre);
        etREmail = findViewById(R.id.etRegistroEmail);
        etRPassword = findViewById(R.id.etRegistroContrasena);
        btnRRegistrar = findViewById(R.id.btnRegistroRegistrar);
        btnREditar = findViewById(R.id.btnRegistroEditar);
        btnRGuardar = findViewById(R.id.btnRegistroModificar);
    }
}
