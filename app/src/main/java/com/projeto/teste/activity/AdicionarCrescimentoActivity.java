package com.projeto.teste.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.projeto.teste.R;
import com.projeto.teste.helper.DateHelper;
import com.projeto.teste.model.Crescimento;

public class AdicionarCrescimentoActivity extends AppCompatActivity {
    private TextInputEditText campoDataMedicao;
    private TextInputEditText campoPesoBebe;
    private TextInputEditText campoTamanhoBebe;
    private Button btnSalvar;
    private Button btnCancelar;
    private String dataMedicao, pesoBebe, tamanhoBebe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_crescimento );
        inicializarCampos ( );  //faltava esse incializar campos aqui ao criar a tela, para carregar os inputs...
    }

    public void adicionarCrescimento(View view) {
        if (validarCampos ( )) {
            Crescimento crescimento=new Crescimento ( dataMedicao , pesoBebe , tamanhoBebe );
            crescimento.setType ( "Crescimento" );
            crescimento.salvarCrescimento ( );
            Toast.makeText ( AdicionarCrescimentoActivity.this , "Crescimento Salvo Com Sucesso!" , Toast.LENGTH_LONG ).show ( );
            finish ( );
        }
    }

    public boolean validarCampos() {  //agr está validando certinho, pode usar essa função
        getInputs ( );
        if (pesoBebe.isEmpty ( )) {
            Toast.makeText ( AdicionarCrescimentoActivity.this , "Peso Inválido" , Toast.LENGTH_SHORT ).show ( );
            return false;
        } else if (dataMedicao.length ( ) < 10) {
            Toast.makeText ( AdicionarCrescimentoActivity.this , "Data de Medição Inválida" , Toast.LENGTH_SHORT ).show ( );
            return false;
        } else if (tamanhoBebe.isEmpty ( )) {
            Toast.makeText ( AdicionarCrescimentoActivity.this , "Tamanho Inválido" , Toast.LENGTH_SHORT ).show ( );
        }
        return true;
    }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        dataMedicao = campoDataMedicao.getText().toString();
        pesoBebe = campoPesoBebe.getText().toString();
        tamanhoBebe = campoTamanhoBebe.getText().toString();
    }

    public void cancelarCrescimento(View view) {
        Toast.makeText ( AdicionarCrescimentoActivity.this , "Operação Cancelada" , Toast.LENGTH_LONG ).show ( );
        finish ( );
    }

    public void inicializarCampos() {
        campoDataMedicao = findViewById (R.id.editDataMedicao );
        campoPesoBebe = findViewById (R.id.editPeso );
        campoTamanhoBebe = findViewById (R.id.editTamanho );
        btnSalvar = findViewById (R.id.btnSalvarMedicao );
        btnCancelar = findViewById (R.id.btnCancelarMedicao );
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

