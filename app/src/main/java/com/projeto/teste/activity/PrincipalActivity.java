package com.projeto.teste.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.projeto.teste.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_principal );
        Toolbar toolbar=findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        FloatingActionButton fab=findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Snackbar.make ( view , "Replace with your own actionnn" , Snackbar.LENGTH_LONG )
                        .setAction ( "Action" , null ).show ( );

            }
        } );
    }

}
