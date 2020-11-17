package com.projeto.teste.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projeto.teste.R;
import com.projeto.teste.adapter.AdapterCrescimento;
import com.projeto.teste.helper.DateHelper;
import com.projeto.teste.model.Bebe;
import com.projeto.teste.model.Crescimento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class AdicionarCrescimentoActivity extends AppCompatActivity {
    private TextInputEditText campoDataMedicao;
    private TextInputEditText campoPesoBebe;
    private TextInputEditText campoTamanhoBebe;
    private Button btnSalvar;
    private Button btnCancelar;
    private String dataMedicao, pesoBebe, tamanhoBebe;
    private DatabaseReference refCrescimentoFirebase;
    private AdapterCrescimento adapterCrescimento;
    private List<Bebe> listBebes = new ArrayList<> ();
    private List<String> listNomeBebes = new ArrayList<>();
    private HashMap<String, String> posicoesBebes = new HashMap<>();
    private Spinner spinnerCrescimentoBebe;
    private int index;
    private AlertDialog dialog;

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


    public void recuperarCrescimentoFirebase(){
        dialog = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(AdicionarCrescimentoActivity.this)
                .setMessage("carregando crescimento")
                .setCancelable(false)
                .build();
        dialog.show();

        refCrescimentoFirebase.addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listBebes.clear();
                index   =   0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listBebes.add(ds.getValue(Bebe.class));
                    listNomeBebes.add(listBebes.get(index).getNomeBebe());
                    index++;
                }
                for(int i = 0; i < index; i++){
                    posicoesBebes.put(listBebes.get(i).getNomeBebe(), listBebes.get(i).getIdBebe());
                }

                //Adapter Spinner
                ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                        AdicionarCrescimentoActivity.this,
                        android.R.layout.simple_spinner_item,
                        listNomeBebes
                );
                adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerCrescimentoBebe.setAdapter(adapterSpinner);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        spinnerCrescimentoBebe = findViewById ( R.id.spinnerCrescimentoBebe );
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

