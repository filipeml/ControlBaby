package com.projeto.teste.model;

import com.google.firebase.database.DatabaseReference;
import com.projeto.teste.helper.Firebase;

import java.io.Serializable;

public class Vacina implements Serializable {
    private String idBebe;
    private String nomeVacina;
    private String dataAplicacao;
    private String local;
    private String nomeBebe;
    private String type;

    public Vacina(){
        DatabaseReference vacinaRef =Firebase.getFirebase ( ).child ( "atividades" ).child ( "vacina" );
        setIdBebe(vacinaRef.push ().getKey ());
    }

    public Vacina(String nomeVacina , String local , String dataAplicacao , String nomeBebe){
        this.nomeVacina = nomeVacina;
        this.local = local;
        this.dataAplicacao = dataAplicacao;
        this.nomeBebe= nomeBebe;


        DatabaseReference vacinaRef = Firebase.getFirebase().child("atividades").child("vacina");
        setIdBebe (vacinaRef.push().getKey());

    }
    public void salvarVacina(){
        String idUser   = Firebase.getIdUser();
        DatabaseReference vacinaRef = Firebase.getFirebase().child("atividades").child("vacina");

        vacinaRef.child(idUser)
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

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao=dataAplicacao;
    }

    public String getLocal() {
        return local;
    }

    public String setLocal(String local) {return local;}

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina() {
        this.nomeVacina=nomeVacina;
    }

    public void setType(String nomeVacina) {
    }
}
