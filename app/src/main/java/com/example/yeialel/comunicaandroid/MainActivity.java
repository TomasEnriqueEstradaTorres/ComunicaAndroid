package com.example.yeialel.comunicaandroid;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button irAlaWeb;  // boton para ir a la web
    private EditText direccionWeb;  // es donde se escribira la direcion web
    private WebView webView;  // donde se mostrada la pagina web

    private String direccionURL;  // almacenara la direccion web a al que queremos ir

    private ReceptorRed receptor;  // Serivira para poder


    //Primer Grupo----------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // se declara el contenido de la actividad
        webView = (WebView) findViewById(R.id.webViewPaginasWebs);
        direccionWeb = (EditText) findViewById(R.id.editTextDireccionWeb);

        /**con esto llamaremos al metodo qe verificara el estado de la red que esta en la clase 'ReceptorRed.class'
         * y le pasamos un 'Context' de la actividad para que el el Toast reciba en donde se mostrada el mensaje y
         * tambien para que el servicio 'ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)'
         * pueda funcionar y verificar es estado de la red.          */
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receptor = new ReceptorRed(getApplicationContext());
        this.registerReceiver(receptor, filter);

        //Se declara y se pone a la escucha el boton
        irAlaWeb = (Button) findViewById(R.id.buttonIrWeb);
        irAlaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direccionURL = direccionWeb.getText().toString();
                direccionURL = "http://" + direccionURL;

                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);  // configura
                webView.getSettings().getJavaScriptEnabled();  // Esto permite que algunas paginas funcionen bien
                webView.getSettings().setBuiltInZoomControls(true); // permite el zoom si pagina no es responsive
                webView.loadUrl(direccionURL);  // recibe la direccion de la pagina web

                //Cierra el teclado despues de hacer click en el boton
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(direccionWeb.getWindowToken(), 0);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }



    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //Restauracion----------------------------------------------------------------------------------
    public void onRestart() {
        super.onRestart();
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    // Segundo Grupo--------------------------------------------------------------------------------
    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        /**Damos de baja el receptor de broadcast cuando se destruye la aplicación para evitar un
         * consumo excesivo de los recursos del sistema.          */
        if (receptor != null) {
            this.unregisterReceiver(receptor);
        }
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //FUNCIONES CREADAS

    // con esto metodo podemos hacer que la pagina web lea el retroceso a la anterior pagina y no que se cierre la aplicacion
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }



}
