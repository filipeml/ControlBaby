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
import com.projeto.teste.model.Bebe;


public class AdicionarBebesActivity extends AppCompatActivity {

    private TextInputEditText campoNome;
    private TextInputEditText campoPeso;
    private TextInputEditText campoComprimento;
    public TextInputEditText campoNascimento;
    private RadioButton campoSexoF;
    private RadioButton campoSexoM;
    private Button btnSalvar;
    private Button btnCancelar;
    private String nome, peso, comprimento, sexo,nascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_bebes );
        inicializarCampos();  //faltava esse incializar campos aqui ao criar a tela, para carregar os inputs...
    }
    public void adicionarBebe(View view){
        if(validarCampos()){
            Bebe bebe = new Bebe(nome, peso, comprimento, nascimento, sexo);
            bebe.setType("Bebe");
            bebe.salvarBebe ();
            Toast.makeText(AdicionarBebesActivity.this, "Bebê Salvo Com Sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean validarCampos() {  //agr está validando certinho, pode usar essa função
        getInputs();
        if (nome.isEmpty()) {
            Toast.makeText(AdicionarBebesActivity.this, "Nome Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (peso.isEmpty()) {
            Toast.makeText(AdicionarBebesActivity.this,"Peso Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (comprimento.isEmpty()) {
            Toast.makeText(AdicionarBebesActivity.this, "Comprimento Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (nascimento.length() < 10) {
            Toast.makeText(AdicionarBebesActivity.this, "Nascimento Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!validarData(nascimento)) {
            Toast.makeText(AdicionarBebesActivity.this, "Data Inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!campoSexoF.isChecked() && !campoSexoM.isChecked()) {
            Toast.makeText(AdicionarBebesActivity.this, "Sexo Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (campoSexoF.isChecked()) {
            sexo = "Menina";
        } else if (campoSexoM.isChecked()) {
            sexo = "Menino";
        }
        return true;
    }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        nome = campoNome.getText().toString();
        peso = campoPeso.getText().toString();
        comprimento = campoComprimento.getText().toString();
        nascimento  = campoNascimento.getText().toString ();
        sexo = "";
    }

    public void cancelarBebe(View view){
        Toast.makeText(AdicionarBebesActivity.this, "Operação Cancelada", Toast.LENGTH_LONG).show();
        finish();
    }

    public void inicializarCampos(){
        campoNome           =   findViewById(R.id.editNomeBebe);
        campoPeso           =   findViewById(R.id.editPesoBebe);
        campoComprimento    =   findViewById(R.id.editCompBebe);  //ajustei esse cara estava errado e com espaço no "findViewById (R.id.editCompBebe);"
        campoNascimento     =   findViewById(R.id.editDataNascBebe);
        campoSexoF          =   findViewById(R.id.radioSexoBebeMenina);
        campoSexoM          =   findViewById(R.id.radioSexoBebeMenino);
        btnSalvar           =   findViewById(R.id.btnSalvarBebe);
        btnCancelar         =   findViewById(R.id.btnCancelarBebe);
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