package com.projeto.teste.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.projeto.teste.MenuPrincActivity;
import com.projeto.teste.R;
import com.projeto.teste.config.ConfiguracaoFirebase;
import com.projeto.teste.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cadastro );

        campoNome = findViewById ( (R.id.editNome) );
        campoEmail = findViewById ( (R.id.editEmail) );
        campoSenha = findViewById ( R.id.editSenha );
        botaoCadastrar = findViewById ( R.id.buttonCadastrar );

        botaoCadastrar.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String textoNome=campoNome.getText ( ).toString ( );
                String textoEmail=campoEmail.getText ( ).toString ( );
                String textoSenha=campoSenha.getText ( ).toString ( );

                //verificar se campos foram preenchidos
                if (!textoNome.isEmpty ( )) {
                    if (!textoEmail.isEmpty ( )) {
                        if (!textoSenha.isEmpty ( )) {

                            usuario = new Usuario ();
                            usuario.setNome ( textoNome );
                            usuario.setEmail ( textoEmail );
                            usuario.setSenha ( textoSenha );
                            cadastrarUsuario ();

                        } else {
                            Toast.makeText ( CadastroActivity.this , "Preencha senha" , Toast.LENGTH_SHORT ).show ( );
                        }
                    } else {
                        Toast.makeText ( CadastroActivity.this , "Preencha E-mail" , Toast.LENGTH_SHORT ).show ( );
                    }
                } else {
                    Toast.makeText ( CadastroActivity.this , "Preencha nome" , Toast.LENGTH_SHORT ).show ( );
                }

            }
        });
    }

    public void cadastrarUsuario(){

        autenticacao =ConfiguracaoFirebase.getFirebaseAutenticacao ();
        autenticacao.createUserWithEmailAndPassword (usuario.getEmail (),usuario.getSenha ()
        ).addOnCompleteListener ( this , new OnCompleteListener<AuthResult> ( ) {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful ()){
                   // Toast.makeText ( CadastroActivity.this , "Sucesso ao Cadastrar Usuário" , Toast.LENGTH_SHORT ).show ( );
                    abreTelaPrincipal();
                }else{
                    String excecao ="";
                    try{
                        throw task.getException ();
                    }catch(FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por gentileza, digite um E-mail válido";
                    }catch(FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta já foi cadastrada por outro usuário";
                    }catch(Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage ();
                        e.printStackTrace ();
                    }

                    Toast.makeText ( CadastroActivity.this , excecao , Toast.LENGTH_SHORT ).show ( );
                }
            }
        } );
    }
    public void abreTelaPrincipal(){
        startActivity ( new Intent ( this, MenuPrincActivity.class ) );
        finish ();
    }
}

