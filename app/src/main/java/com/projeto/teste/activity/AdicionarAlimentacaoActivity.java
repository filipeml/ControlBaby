package com.projeto.teste.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.projeto.teste.R;
import com.projeto.teste.helper.DateHelper;
import com.projeto.teste.model.Alimentacao;

public class AdicionarAlimentacaoActivity extends AppCompatActivity {

    private TextInputEditText campoDataAlim;
    private TextInputEditText campoHoraAlim;
    private RadioButton campoAlimMaterno;
    private RadioButton campoAlimCompl;
    private Button btnSalvar;
    private Button btnCancelar;
    private String dataAlimentacao, horaAlimentacao, alimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_alimentacao );
        inicializarCampos ( );  //faltava esse incializar campos aqui ao criar a tela, para carregar os inputs...
    }
        public void adicionarAlimentacao ( View view){
            if (validarCampos ( )) {
                Alimentacao alimentacao=new Alimentacao ( dataAlimentacao , horaAlimentacao , alimento );
                alimentacao.setType ( "Alimentacao" );
                alimentacao.salvarAlimentacao ( );
                Toast.makeText ( AdicionarAlimentacaoActivity.this , "Alimentação Salva Com Sucesso!" , Toast.LENGTH_LONG ).show ( );
                finish ( );
            }
        }


        public boolean validarCampos ( ) {  //agr está validando certinho, pode usar essa função
            getInputs ( );
            if (horaAlimentacao.isEmpty ( )) {
                Toast.makeText ( AdicionarAlimentacaoActivity.this , "Hora Inválida" , Toast.LENGTH_SHORT ).show ( );
                return false;
            } else if (dataAlimentacao.length ( ) < 10) {
                Toast.makeText ( AdicionarAlimentacaoActivity.this , "Data de Alimentação Inválida" , Toast.LENGTH_SHORT ).show ( );
                return false;
            } else if (!campoAlimCompl.isChecked() && !campoAlimMaterno.isChecked()) {
                Toast.makeText(AdicionarAlimentacaoActivity.this, "Alimentação Inválida", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoAlimMaterno.isChecked()) {
                alimento = "Materno";
            } else if (campoAlimCompl.isChecked()) {
                alimento = "Complemento";
            }
            return true;
        }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        dataAlimentacao = campoDataAlim.getText().toString();
        horaAlimentacao = campoHoraAlim.getText().toString();
        alimento = "";
    }

    public void cancelarAlimentacao(View view){
        Toast.makeText(AdicionarAlimentacaoActivity.this, "Operação Cancelada", Toast.LENGTH_LONG).show();
        finish();
    }

    public void inicializarCampos(){
        campoDataAlim       =   findViewById(R.id.editDataAlimentacao);
        campoHoraAlim       =   findViewById(R.id.editHoraAlimentacao);
        campoAlimMaterno    =   findViewById(R.id.radioAlimLeiteMaterno);
        campoAlimCompl      =   findViewById(R.id.radioAlimComplemento);
        btnSalvar           =   findViewById(R.id.btnSalvarAlim );
        btnCancelar         =   findViewById(R.id.btnCancelarAlim);
    }

    public boolean validarData(String data){
        int dia, mes;
        dia = DateHelper.getDiaByData(data);
        mes = DateHelper.getMesByData(data);
        if((dia >= 1 && dia <= 31 ) && (mes >= 1 && mes <= 12 )){
            return true;
        }else{
            return false;
        }
    }
}

