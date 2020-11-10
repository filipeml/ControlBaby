package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Pediatra implements Serializable {
    private String idBebe;
    private String dataConsulta;
    private String horaConsulta;
    private String localConsulta;
    private String type;

    public Pediatra(){
        DatabaseReference pediatraRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "pediatra" );
        setIdBebe(pediatraRef.push ().getKey ());
    }

    public Pediatra(String dataConsulta , String horaConsulta , String localConsulta){
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.localConsulta = localConsulta;

        DatabaseReference pediatraRef = Firebase.getFirebase().child("atividades").child("pediatra");
        setIdBebe (pediatraRef.push().getKey());

    }

    public void salvarPediatra(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference pediatraRef = Firebase.getFirebase().child("atividades").child("pediatra");

        pediatraRef.child(idUser)
                .child(getIdBebe ())
                .setValue(this);
    }

    public String getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(String idBebe) {
        this.idBebe=idBebe;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta=dataConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta=horaConsulta;
    }

    public String getLocalConsulta() {
        return localConsulta;
    }

    public void setLocalConsulta(String localConsulta) {
        this.localConsulta=localConsulta;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

}
