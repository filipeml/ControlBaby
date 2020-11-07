package com.projeto.teste.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.projeto.teste.R;
import com.projeto.teste.helper.DateHelper;
import com.projeto.teste.model.Vacina;

public class AdicionarVacinasActivity extends AppCompatActivity {

    private TextInputEditText campoNomeVacina;
    private TextInputEditText campoLocalVacina;
    private TextInputEditText campoDataAplicacao;
    private TextInputEditText campoNomeBebe;
    private Button btnSalvar;
    private Button btnCancelar;
    private String nome, vacinas, dataAplicacao, local;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adicionar_vacinas );
        inicializarCampos();
    }

    public void adicionarVacinas(View view){
        if(validarCampos()){
            Vacina vacina = new Vacina(nome, vacinas, dataAplicacao, local);
            vacina.setType("Vacinas");
            vacina.salvarVacina();
            Toast.makeText(AdicionarVacinasActivity.this, "Vacina Salva Com Sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean validarCampos() {  //agr está validando certinho, pode usar essa função
        getInputs();
        if (nome.isEmpty()) {
            Toast.makeText(AdicionarVacinasActivity.this, "Nome do Bebê Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (vacinas.isEmpty()) {
            Toast.makeText(AdicionarVacinasActivity.this,"Nome da Vacina é Inválido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dataAplicacao.length() < 10) {
            Toast.makeText(AdicionarVacinasActivity.this, "Data de Aplicação Inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (local.isEmpty ()){
            Toast.makeText(AdicionarVacinasActivity.this,"Local da Vacina é Inválido", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void getInputs() {  //e aqui tinha os espaços no: "getText ()" e "toString ()", tem que ser sempre junto "getText()" e "toString()"
        nome = campoNomeBebe.getText().toString();
        vacinas = campoNomeVacina.getText().toString();
        local = campoLocalVacina.getText().toString();
        dataAplicacao  = campoDataAplicacao.getText().toString ();
    }

    public void cancelarVacina(View view){
        Toast.makeText(AdicionarVacinasActivity.this, "Operação Cancelada", Toast.LENGTH_LONG).show();
        finish();
    }

    public void inicializarCampos(){
        campoNomeBebe           =   findViewById(R.id.editNomeBebe);
        campoNomeVacina           =   findViewById(R.id.editNomeVacina);
        campoLocalVacina    =   findViewById(R.id.editLocalAplicacao);
        campoDataAplicacao     =   findViewById(R.id.editDataAplicacao);
        btnSalvar           =   findViewById(R.id.btnSalvarVacina);
        btnCancelar         =   findViewById(R.id.btnCancelarVacina);
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
