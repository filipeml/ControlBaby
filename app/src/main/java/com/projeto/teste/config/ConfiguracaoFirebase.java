package com.projeto.teste.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;

    //retorna a instanciação do Firebase
    public static FirebaseAuth getFirebaseAutenticacao() {
        if (autenticacao == null) {
            autenticacao=FirebaseAuth.getInstance ( );
        }
        return autenticacao;
    }
}
