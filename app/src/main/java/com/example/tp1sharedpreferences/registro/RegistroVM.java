package com.example.tp1sharedpreferences.registro;

import android.app.Application;
import android.view.View;
import android.widget.EditText;

import com.example.tp1sharedpreferences.model.Usuario;
import com.example.tp1sharedpreferences.preferencias.SpLogin;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegistroVM extends AndroidViewModel {

    private MutableLiveData<Usuario> usuarioMLD;

    public RegistroVM(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getUsuarioMLD() {
        if (usuarioMLD == null) {
            usuarioMLD = new MutableLiveData<>();
            usuarioMLD.setValue(SpLogin.leer(getApplication()));
        }
        return usuarioMLD;
    }

    public void guardar(EditText etDni, EditText etApellido, EditText etNombre, EditText etEmail, EditText etPassword){
        if (!validarRegistro(etDni, etApellido, etNombre, etEmail, etPassword)) return;

        Usuario usuario = new Usuario();
        usuario.setDni(Integer.parseInt(etDni.getText().toString()));
        usuario.setApellido(etApellido.getText().toString());
        usuario.setNombre(etNombre.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        usuario.setPassword(etPassword.getText().toString());

        SpLogin.guardar(getApplication(), usuario);
        etDni.getText().clear();
        etApellido.getText().clear();
        etNombre.getText().clear();
        etEmail.getText().clear();
        etPassword.getText().clear();
    }

    public void llenarFormularioInmueble(EditText etDni, EditText etApellido, EditText etNombre, EditText etEmail, EditText etPassword, Usuario usuario) {
        etDni.setText(String.format("%s", usuario.getDni()));
        etApellido.setText(usuario.getApellido());
        etNombre.setText(usuario.getNombre());
        etEmail.setText(usuario.getEmail());
        etPassword.setText(usuario.getPassword());
    }

    public boolean validarRegistro(EditText dni, EditText apellido, EditText nombre, EditText email, EditText password) {
        boolean esValido = true;
        String vDni = dni.getText().toString();
        String vApellido = apellido.getText().toString();
        String vNombre = nombre.getText().toString();
        String vEmail = email.getText().toString();
        String vClave = password.getText().toString();

        if (!vDni.matches("[0-9 ]{8,15}")) {
            dni.setError("Solo números y mínimo 8 caracteres");
            esValido = false;
        } else {
            dni.setError(null);
        }
        if (!vApellido.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]{3,30}")) {
            apellido.setError("Solo letras y mínimo 3 caracteres");
            esValido = false;
        } else {
            apellido.setError(null);
        }
        if (!vNombre.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]{3,30}")) {
            nombre.setError("Solo letras y mínimo 3 caracteres");
            esValido = false;
        } else {
            nombre.setError(null);
        }
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

    public void deshabilitarFormulario(EditText etDni, EditText etApellido, EditText etNombre, EditText etEmail, EditText etPassword, Button btnRegistrar, Button btnEditar) {
        etDni.setEnabled(false);
        etApellido.setEnabled(false);
        etNombre.setEnabled(false);
        etEmail.setEnabled(false);
        etPassword.setEnabled(false);
        btnRegistrar.setVisibility(View.GONE);
        btnEditar.setVisibility(View.VISIBLE);
    }

    public void habilitarFormulario(EditText etDni, EditText etApellido, EditText etNombre, EditText etEmail, EditText etPassword, Button btnEditar, Button btnGuardar) {
        etDni.setEnabled(true);
        etApellido.setEnabled(true);
        etNombre.setEnabled(true);
        etEmail.setEnabled(true);
        etPassword.setEnabled(true);
        btnEditar.setVisibility(View.GONE);
        btnGuardar.setVisibility(View.VISIBLE);
    }
}
