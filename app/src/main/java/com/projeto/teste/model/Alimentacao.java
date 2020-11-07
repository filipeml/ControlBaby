package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Alimentacao implements Serializable {
    private String idBebe;
    private String dataAlimentacao;
    private String horaAlimentacao;
    private String alimento;
    private String type;

    public Alimentacao(){
        DatabaseReference alimentacaoRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "alimentacao" );
        setIdBebe(alimentacaoRef.push ().getKey ());
    }

    public Alimentacao(String dataAlimentacao , String horaAlimentacao , String alimento){
        this.dataAlimentacao = dataAlimentacao;
        this.horaAlimentacao = horaAlimentacao;
        this.alimento = alimento;

        DatabaseReference alimentacaoRef = Firebase.getFirebase().child("atividades").child("alimentacao");
        setIdBebe (alimentacaoRef.push().getKey());

    }

    public void salvarAlimentacao(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference alimentacaoRef = Firebase.getFirebase().child("atividades").child("alimentacao");

        alimentacaoRef.child(idUser)
                .child(getIdBebe ())
                .setValue(this);
    }

    public String getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(String idBebe) {
        this.idBebe=idBebe;
    }

    public String getDataAlimentacao() {
        return dataAlimentacao;
    }

    public void setDataAlimentacao(String dataAlimentacao) {
        this.dataAlimentacao=dataAlimentacao;
    }

    public String getHoraAlimentacao() {
        return horaAlimentacao;
    }

    public void setHoraAlimentacao(String horaAlimentacao) {
        this.horaAlimentacao=horaAlimentacao;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento=alimento;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }
}
