package com.example.yeialel.comunicaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewSolo extends AppCompatActivity {

    private WebView webView; // donde se mostrada la pagina web
    private String url;  // direccion url recibida

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_solo);

        // aqui recibiremos el dato enviado desde la clase 'MainActivity.class'
        Intent datoRecibido = getIntent();
        Bundle extra = datoRecibido.getExtras();
        url = (String) extra.get("valorEnviado");  // se obtiene el valor enviado y se pasa a cadena

        webView = (WebView) findViewById(R.id.webViewSola);  // se declara el webView
        webView.getSettings().setJavaScriptEnabled(true); // configura el retorno a la anterior pagina
        webView.getSettings().getJavaScriptEnabled();  // Esto permite que algunas paginas funcionen bien
        webView.getSettings().setBuiltInZoomControls(true); // permite el zoom si pagina no es responsive

        //Este metodo evitara que la pagina web se abra fuera de la App y solo use el WebView interno de esta
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false; //Permite que la pagina se refresque en el webView
            }
        });
        webView.loadUrl(url);  // recibe la direccion de la pagina web
    }
    /**con esto metodo podemos hacer que la pagina web lea el retroceso a la anterior pagina y no
     * que se cierre la aplicacion, funciona en conjunto con 'webView.getSettings().setJavaScriptEnabled(true)' */
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
