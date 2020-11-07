package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Bebe implements Serializable {
    private String idBebe;
    private String nomeBebe;
    private String dataNasc;
    private String sexo;
    private String comprimento;
    private String peso;
    private String type;

    public Bebe(){
        DatabaseReference bebeRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "bebe" );
        setIdBebe(bebeRef.push ().getKey ());
    }

    public Bebe(String nome , String peso , String comprimento , String nascimento, String sexo){
        this.nomeBebe = nome;
        this.peso = peso;
        this.comprimento = comprimento;
        this.dataNasc= nascimento;
        this.sexo = sexo;

        DatabaseReference bebeRef = Firebase.getFirebase().child("atividades").child("bebe");
        setIdBebe (bebeRef.push().getKey());

    }
    public void salvarBebe(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference bebesRef = Firebase.getFirebase().child("atividades").child("bebe");

        bebesRef.child(idUser)
                .child(getIdBebe ())
                .setValue(this);
    }

    public String getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(String idBebe) {
        this.idBebe=idBebe;
    }

    public String getNomeBebe() {
        return nomeBebe;
    }

    public void setNomeBebe(String nomeBebe) {
        this.nomeBebe=nomeBebe;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc=dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo=sexo;
    }

    public String getComprimento() {
        return comprimento;
    }

    public String setComprimento(String comprimento) {return comprimento;}

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso=peso;
    }

    public void setType(String bebe) {
    }
}
