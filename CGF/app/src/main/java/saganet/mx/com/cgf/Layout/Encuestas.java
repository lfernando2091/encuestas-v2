package saganet.mx.com.cgf.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import saganet.mx.com.cgf.R;

public class Encuestas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestas);
        //Mostrar boton de regresp
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EncuestasFragment fragment = (EncuestasFragment) getSupportFragmentManager().findFragmentById(R.id.activity_encuestas);
        if (fragment == null) {
            fragment = new EncuestasFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.activity_encuestas, fragment).commit();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Cuestionario();
            return true;
        }else if(id == R.id.action_sync){

            return true;
        }
        else if(id == R.id.action_mensages){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Cuestionario(){
        Intent i = new Intent(this, Cuestionario.class);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
    }
}
