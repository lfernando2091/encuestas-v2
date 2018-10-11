package saganet.mx.com.cgf.Layout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import saganet.mx.com.cgf.DataBase.Controler.Sesion;
import saganet.mx.com.cgf.R;

public class Cuestionario extends AppCompatActivity {
    private Sesion sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Mostrar boton de regresp
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sesion= new Sesion(this);
        CuestionarioFragment fragment = (CuestionarioFragment) getSupportFragmentManager().findFragmentById(R.id.content_cuestionario);
        if (fragment == null) {
            fragment = new CuestionarioFragment();
            fragment.Adata(sesion);
            getSupportFragmentManager().beginTransaction().add(R.id.content_cuestionario, fragment).commit();
        }else {
            fragment.Adata(sesion);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
