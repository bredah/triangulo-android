package bredahe.calculadora;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://bredahe.calculadora/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://bredahe.calculadora/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void clickValidar(View view) {
        String msgValidacao = "";
        final EditText campoA = (EditText) findViewById(R.id.valor_a);
        final EditText campoB = (EditText) findViewById(R.id.valor_b);
        final EditText campoC = (EditText) findViewById(R.id.valor_c);
        final TextView campoResultado = (TextView) findViewById(R.id.textResult);
        // Verfica se os campos apresentam valores corretos
        if (campoA.getText().toString().matches("\\D") ||
                campoA.getText().toString().isEmpty() ||
                campoB.getText().toString().matches("\\D") ||
                campoB.getText().toString().isEmpty() ||
                campoC.getText().toString().matches("\\D") ||
                campoC.getText().toString().isEmpty()) {
            msgValidacao = "Somente digitos números inteiros e maiores que '0' são aceitos";
        } else {
            // Verifica o tipo do triãngulo
            int valorA = Integer.parseInt(campoA.getText().toString());
            int valorB = Integer.parseInt(campoB.getText().toString());
            int valorC = Integer.parseInt(campoC.getText().toString());

            if (valorA == valorB && valorB == valorC) {
                msgValidacao = "Triângulo Equilátero!\n3 lados iguais.";
            } else if (valorA == valorB || valorA == valorC || valorB == valorC) {
                msgValidacao = "Triângulo Isósceles!\n2 lados iguais.";
            } else {
                msgValidacao = "Triângulo Escaleno!\nNenhum lado igual.";
            }
        }
        campoResultado.setText(msgValidacao);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(campoResultado.getWindowToken(), 0);
    }


    public void clickLimpar(View view) {
        final EditText campoA = (EditText) findViewById(R.id.valor_a);
        final EditText campoB = (EditText) findViewById(R.id.valor_b);
        final EditText campoC = (EditText) findViewById(R.id.valor_c);
        final TextView campoResultado = (TextView) findViewById(R.id.textResult);
        campoA.setText("");
        campoB.setText("");
        campoC.setText("");
        campoResultado.setText("");
    }


}
