package com.projeto.teste.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.projeto.teste.R;
import com.projeto.teste.helper.DateHelper;
import com.projeto.teste.model.Pediatra;

public class AdicionarPediatraActivity extends AppCompatActivity {
    private TextInputEditText campoDataConsulta;
    private TextInputEditText campoHoraConsulta;
    private TextInputEditText campoLocalConsulta;
    private Button btnSalvar;
    private Button btnCancelar;
    private String dataConsulta, horaConsulta, localConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_pediatra );
        inicializarCampos ( );  //faltava esse incializar campos aqui ao criar a tela, para carregar os inputs...
    }

    public void adicionarPediatra(View view) {
        if (validarCampos ( )) {
            Pediatra pediatra = new Pediatra ( dataConsulta , horaConsulta , localConsulta );
            pediatra.setType ( "Pediatra" );
            pediatra.salvarPediatra( );
            Toast.makeText ( AdicionarPediatraActivity.this , "Pediatra Salvo Com Sucesso!" , Toast.LENGTH_LONG ).show ( );
            finish ( );
        }
    }

    public boolean validarCampos() {  //agr está validando certinho, pode usar essa função
        getInputs ( );
        if (horaConsulta.isEmpty ( )) {
            Toast.makeText ( AdicionarPediatraActivity.this , "Hora Inválida" , Toast.LENGTH_SHORT ).show ( );
            return false;
        } else if (dataConsulta.length ( ) < 10) {
            Toast.makeText ( AdicionarPediatraActivity.this , "Data Inválida" , Toast.LENGTH_SHORT ).show ( );
            return false;
        } else if (localConsulta.isEmpty ( )) {
            Toast.makeText ( AdicionarPediatraActivity.this , "Local Inválido" , Toast.LENGTH_SHORT ).show ( );
        }
        return true;
    }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        dataConsulta = campoDataConsulta.getText().toString();
        horaConsulta = campoHoraConsulta.getText().toString();
        localConsulta = campoLocalConsulta.getText().toString();
    }

    public void cancelarPediatra(View view) {
        Toast.makeText ( AdicionarPediatraActivity.this , "Operação Cancelada" , Toast.LENGTH_LONG ).show ( );
        finish ( );
    }

    public void inicializarCampos() {
        campoDataConsulta = findViewById (R.id.editDataConsulta );
        campoHoraConsulta = findViewById (R.id.editHoraConsulta );
        campoLocalConsulta = findViewById (R.id.editLocalConsulta );
        btnSalvar = findViewById (R.id.btnSalvarPediatra );
        btnCancelar = findViewById (R.id.btnCancelarPediatra );
    }

    public boolean validarData(String data) {
        int dia, mes;
        dia=DateHelper.getDiaByData ( data );
        mes=DateHelper.getMesByData ( data );
        if (( dia >= 1 && dia <= 31 ) && ( mes >= 1 && mes <= 12 )) {
            return true;
        } else {
            return false;
        }
    }
}


