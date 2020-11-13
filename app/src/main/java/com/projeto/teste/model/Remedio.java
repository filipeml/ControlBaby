package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Remedio implements Serializable {
    private String idBebe;
    private String nomeRemedio;
    private String dosagemRemedio;
    private String dataRemedio;
    private String horaRemedio;
    private String type;

    public Remedio(){
        DatabaseReference remedioRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "remedio" );
        setIdBebe(remedioRef.push().getKey());
    }

    public Remedio(String nomeRemedio , String dosagemRemedio , String dataRemedio, String horaRemedio){
        this.nomeRemedio = nomeRemedio;
        this.dosagemRemedio = dosagemRemedio;
        this.dataRemedio = dataRemedio;
        this.horaRemedio = horaRemedio;

        DatabaseReference remedioRef = Firebase.getFirebase().child("atividades").child("remedio");
        setIdBebe (remedioRef.push().getKey());

    }

    public void salvarRemedio(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference remedioRef = Firebase.getFirebase().child("atividades").child("remedio");

        remedioRef.child(idUser)
                .child(getIdBebe ())
                .setValue(this);
    }

    public String getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(String idBebe) {
        this.idBebe=idBebe;
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio=nomeRemedio;
    }

    public String getDosagemRemedio() {
        return dosagemRemedio;
    }

    public void setDosagemRemedio(String dosagemRemedio) {
        this.dosagemRemedio=dosagemRemedio;
    }

    public String getDataRemedio() {
        return dataRemedio;
    }

    public void setDataRemedio(String dataRemedio) {
        this.dataRemedio=dataRemedio;
    }

    public String getHoraRemedio() {
        return horaRemedio;
    }

    public void setHoraRemedio(String horaRemedio) {
        this.horaRemedio=horaRemedio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }
}
