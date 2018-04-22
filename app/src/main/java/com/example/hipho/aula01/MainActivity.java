package com.example.hipho.aula01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //constante static para identifcar a mensaaem
    public final static String EXTRA_MESSAGE = "MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMess(View view){
        EditText editText = (EditText) findViewById(R.id.campo_mensagem);
        String message = editText.getText().toString();

        Intent intent = new Intent(this, ExibeDados.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}
