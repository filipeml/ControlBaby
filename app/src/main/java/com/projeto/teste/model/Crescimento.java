package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Crescimento implements Serializable {
    private String idBebe;
    private String dataMedicao;
    private String pesoBebe;
    private String tamanhoBebe;
    private String type;

    public Crescimento(){
        DatabaseReference crescimentoRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "crescimento" );
        setIdBebe(crescimentoRef.push ().getKey ());
    }

    public Crescimento(String dataMedicao , String pesoBebe , String tamanhoBebe){
        this.dataMedicao = dataMedicao;
        this.pesoBebe = pesoBebe;
        this.tamanhoBebe = tamanhoBebe;

        DatabaseReference crescimentoRef = Firebase.getFirebase().child("atividades").child("crescimento");
        setIdBebe (crescimentoRef.push().getKey());

    }

    public void salvarCrescimento(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference crescimentoRef = Firebase.getFirebase().child("atividades").child("crescimento");

        crescimentoRef.child(idUser)
                .child(getIdBebe ())
                .setValue(this);
    }

    public String getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(String idBebe) {
        this.idBebe=idBebe;
    }

    public String getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(String dataMedicao) {
        this.dataMedicao=dataMedicao;
    }

    public String getPesoBebe() {
        return pesoBebe;
    }

    public void setPesoBebe(String pesoBebe) {
        this.pesoBebe=pesoBebe;
    }

    public String getTamanhoBebe() {
        return tamanhoBebe;
    }

    public void setTamanhoBebe(String tamanhoBebe) {
        this.tamanhoBebe=tamanhoBebe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }
}
