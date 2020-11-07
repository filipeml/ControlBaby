package com.projeto.teste;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MenuPrincActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_menu_princ );
        Toolbar toolbar=findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        //FloatingActionButton fab=findViewById ( R.id.fab );
       /* fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Snackbar.make ( view , "Replace with your own action" , Snackbar.LENGTH_LONG )
                        .setAction ( "Action" , null ).show ( );
            }
        } );*/
       /*FloatingActionButton fab = findViewById ( R.id.fab );
       fab.setOnClickListener ( new View.OnClickListener ( ) {
           @Override
           public void onClick(View view) {
               startActivity ( new Intent ( MenuPrincActivity.this, LoginActivity.class ) );
           }
       } );*/
        DrawerLayout drawer=findViewById ( R.id.drawer_layout );
        NavigationView navigationView=findViewById ( R.id.nav_view );
        navigationView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //DEFINI CONFIGURACOES DO NAVIGATION DRAWER
        mAppBarConfiguration=new AppBarConfiguration.Builder (
                 R.id.nav_remedio, R.id.nav_bebe, R.id.nav_alimentacao, R.id.nav_fraldas, R.id.nav_crescimento, R.id.nav_vacinas, R.id.nav_pediatra)
                .setDrawerLayout ( drawer )
                .build ( );

        //configura a area que vai carregar os controle

        NavController navController=Navigation.findNavController ( this , R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController ( this , navController , mAppBarConfiguration );
        NavigationUI.setupWithNavController ( navigationView , navController );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate ( R.menu.menu_princ , menu );
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController=Navigation.findNavController ( this , R.id.nav_host_fragment );
        return NavigationUI.navigateUp ( navController , mAppBarConfiguration )
                || super.onSupportNavigateUp ( );
    }
}
