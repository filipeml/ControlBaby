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
import com.projeto.teste.model.Fralda;

public class AdicionarFraldaActivity extends AppCompatActivity {

    private TextInputEditText campoDataTroca;
    private TextInputEditText campoHoraTroca;
    private RadioButton campoCondicaoXixi;
    private RadioButton campoCondicaoCoco;
    private RadioButton campoCondicaoMisto;
    private Button btnSalvar;
    private Button btnCancelar;
    private String dataTroca, horaTroca, condicaoFralda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_fralda );
        inicializarCampos();  //faltava esse incializar campos aqui ao criar a tela, para carregar os inputs...
    }
    public void adicionarFralda(View view){
        if(validarCampos()){
            Fralda fralda = new Fralda(dataTroca, horaTroca, condicaoFralda);
            fralda.setType("Fralda");
            fralda.salvarFralda();
            Toast.makeText(AdicionarFraldaActivity.this, "Fralda Salva Com Sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean validarCampos() {  //agr está validando certinho, pode usar essa função
        getInputs();
        if (horaTroca.isEmpty()) {
            Toast.makeText(AdicionarFraldaActivity.this, "Hora Inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dataTroca.length() < 10) {
            Toast.makeText(AdicionarFraldaActivity.this, "Data da Troca Inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!validarData(dataTroca)) {
            Toast.makeText(AdicionarFraldaActivity.this, "Data Inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!campoCondicaoCoco.isChecked() && !campoCondicaoXixi.isChecked() && !campoCondicaoMisto.isChecked ()) {
            Toast.makeText(AdicionarFraldaActivity.this, "Condição da Fralda Inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (campoCondicaoXixi.isChecked()) {
            condicaoFralda = "Xixi";
        } else if (campoCondicaoCoco.isChecked()) {
            condicaoFralda = "Coco";
        } else if (campoCondicaoMisto.isChecked()) {
        condicaoFralda = "Misto";
        }
        return true;
    }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        dataTroca = campoDataTroca.getText().toString();
        horaTroca = campoHoraTroca.getText().toString();
        condicaoFralda = "";
    }

    public void cancelarFralda(View view){
        Toast.makeText(AdicionarFraldaActivity.this, "Operação Cancelada", Toast.LENGTH_LONG).show();
        finish();
    }

    public void inicializarCampos(){
        campoDataTroca           =   findViewById(R.id.editDataTroca);
        campoHoraTroca           =   findViewById(R.id.editHoraTroca );
        campoCondicaoXixi         =   findViewById(R.id.radioCondicaoFraldaXixi);
        campoCondicaoCoco       =   findViewById(R.id.radioCondicaoFraldaCoco);
        campoCondicaoMisto       =   findViewById(R.id.radioCondicaoFraldaMisto);
        btnSalvar           =   findViewById(R.id.btnSalvarFralda);
        btnCancelar         =   findViewById(R.id.btnCancelarFralda);
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
