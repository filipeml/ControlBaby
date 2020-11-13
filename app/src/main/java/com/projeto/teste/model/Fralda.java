package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Fralda implements Serializable {
    private String idBebe;
    private String dataTroca;
    private String horaTroca;
    private String condicaoFralda;
    private String type;

    public Fralda(){
        DatabaseReference fraldaRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "fralda" );
        setIdBebe(fraldaRef.push ().getKey ());
    }

    public Fralda(String dataTroca , String horaTroca , String condicaoFralda){
        this.dataTroca = dataTroca;
        this.horaTroca = horaTroca;
        this.condicaoFralda = condicaoFralda;

        DatabaseReference fraldaRef = Firebase.getFirebase().child("atividades").child("fralda");
        setIdBebe(fraldaRef.push().getKey());

    }

    public void salvarFralda(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference fraldaRef = Firebase.getFirebase().child("atividades").child("fralda");

        fraldaRef.child(idUser)
                .child(getIdBebe ())
                .setValue(this);
    }

    public String getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(String idBebe) {
        this.idBebe=idBebe;
    }

    public String getDataTroca() {
        return dataTroca;
    }

    public void setDataTroca(String dataTroca) {
        this.dataTroca=dataTroca;
    }

    public String getHoraTroca() {
        return horaTroca;
    }

    public void setHoraTroca(String horaTroca) {
        this.horaTroca=horaTroca;
    }

    public String getCondicaoFralda() {
        return condicaoFralda;
    }

    public void setCondicaoFralda(String condicaoFralda) {
        this.condicaoFralda=condicaoFralda;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }
}
