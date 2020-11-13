package com.projeto.teste.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.projeto.teste.R;
import com.projeto.teste.helper.DateHelper;
import com.projeto.teste.model.Remedio;

public class AdicionarRemedioActivity extends AppCompatActivity {
    private TextInputEditText campoNomeRemedio;
    private TextInputEditText campoDosagemRemedio;
    private TextInputEditText campoDataRemedio;
    private TextInputEditText campoHoraRemedio;
    private Button btnSalvar;
    private Button btnCancelar;
    private String nomeRemedio, dosagemRemedio, dataRemedio, horaRemedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_remedio );
        inicializarCampos ( );  //faltava esse incializar campos aqui ao criar a tela, para carregar os inputs...
    }

    public void adicionarRemedio(View view) {
        if (validarCampos ( )) {
            Remedio remedio = new Remedio ( nomeRemedio, dosagemRemedio , dataRemedio, horaRemedio );
            remedio.setType ( "Remedio" );
            remedio.salvarRemedio( );
            Toast.makeText ( AdicionarRemedioActivity.this , "Remédio Salvo Com Sucesso!" , Toast.LENGTH_LONG ).show ( );
            finish ( );
        }
    }

    public boolean validarCampos() {  //agr está validando certinho, pode usar essa função
        getInputs ( );
        if (nomeRemedio.isEmpty ()) {
            Toast.makeText ( AdicionarRemedioActivity.this , "Nome do Remédio Inválido" , Toast.LENGTH_SHORT ).show ( );
            return false;
        }else if(dosagemRemedio.isEmpty ()){
            Toast.makeText ( AdicionarRemedioActivity.this , "Dosagem do Remédio Inválido" , Toast.LENGTH_SHORT ).show ( );
            return false;
        } else if (dataRemedio.length ( ) < 10) {
            Toast.makeText ( AdicionarRemedioActivity.this , "Data do Remédio Inválida" , Toast.LENGTH_SHORT ).show ( );
            return false;
        } else if (horaRemedio.isEmpty ( )) {
            Toast.makeText ( AdicionarRemedioActivity.this , "Hora do Remédio Inválida" , Toast.LENGTH_SHORT ).show ( );
        }
        return true;
    }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        nomeRemedio = campoNomeRemedio.getText().toString();
        dosagemRemedio = campoDosagemRemedio.getText().toString();
        dataRemedio = campoDataRemedio.getText().toString();
        horaRemedio = campoHoraRemedio.getText().toString();

    }

    public void cancelarRemedio(View view) {
        Toast.makeText ( AdicionarRemedioActivity.this , "Operação Cancelada" , Toast.LENGTH_LONG ).show ( );
        finish ( );
    }

    public void inicializarCampos() {
        campoNomeRemedio = findViewById ( R.id.editNomeRemedio);
        campoDosagemRemedio = findViewById (R.id.editDosagemRemedio);
        campoDataRemedio = findViewById (R.id.editDataRemedio);
        campoHoraRemedio = findViewById (R.id.editHoraRemedio);
        btnSalvar = findViewById (R.id.btnSalvarRemedio);
        btnCancelar = findViewById (R.id.btnCancelarRemedio);
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



