package com.feseju.android.gameoflife;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.feseju.android.gameoflife.tablero.Tablero;


public class MainActivity extends ActionBarActivity {

    //Tablero
    Tablero miTablero = new Tablero(8,8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void generaVida(View view) {
        Tablero.generateRandomTablero();
        String tablero = Tablero.tableroACadena();
        Tablero.calculaVecinos();

        EditText cajaTexto = (EditText) findViewById(R.id.fila1);
        cajaTexto.setText(tablero);

        String tablero2 = miTablero.vecinosACadena();
        EditText cajaTexto2 = (EditText) findViewById(R.id.fila2);
        cajaTexto2.setText(tablero2);
    }

    public void generaTick(View view) {

        Tablero.tick();
        EditText cajaTexto = (EditText) findViewById(R.id.fila1);
        cajaTexto.setText( Tablero.tableroACadena() );

        String tablero2 = miTablero.vecinosACadena();
        EditText cajaTexto2 = (EditText) findViewById(R.id.fila2);
        cajaTexto2.setText(tablero2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
