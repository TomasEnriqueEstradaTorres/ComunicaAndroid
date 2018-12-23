package com.example.yeialel.comunicaandroid;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

public class ReceptorRed extends BroadcastReceiver {

    /**Es paa poder recibir el context de la actividad donde se mostrada el mensaje
     * y para que el metodo 'getSystemService' que tambien hereda de 'context' funcione     */
    Context context;

    //CONSTRUCTOR - recibe un context de la activity principal
    public ReceptorRed(Context context) {
        this.context = context;
    }

    /**
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        actualizaEstadoRed();
    }


    public void actualizaEstadoRed(){
        // El metodo 'getSystemService()' funciona porque tambien hereda de 'Context'
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento - Con el 'context' estamos diciendo donde se mostrada el mensaje
            Toast mensajeEstadoRed = Toast.makeText(context, "ESTADO DE LA RED: " + networkInfo.getState(), Toast.LENGTH_LONG);
            mensajeEstadoRed.show();

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // sirve para ver a que red estas conectado
                Toast mensajeNombreWifi = Toast.makeText(context, "Nombre red Wi-Fi: " + networkInfo.getExtraInfo(), Toast.LENGTH_LONG);
                mensajeNombreWifi.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1500);
                mensajeNombreWifi.show();
            }
        } else {
            // No hay conexión a Internet en este momento
            Toast toast = Toast.makeText(context, "ERROR EN LA RED: " + networkInfo.getState(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
