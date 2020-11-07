package com.projeto.teste.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.projeto.teste.R;
import com.projeto.teste.activity.CadastroActivity;
import com.projeto.teste.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
    }


    public void btnCadastrar(View view){
        startActivity ( new Intent ( this, CadastroActivity.class ) );

    }

    public void btnEntrar(View view){
        startActivity ( new Intent ( this, LoginActivity.class ) );

    }


}
