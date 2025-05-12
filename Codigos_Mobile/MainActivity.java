package com.example.calcularimc;

import androidx.appcompat.app.AppCompatActivity; 
import android.os.Bundle;
import android.view.View; 
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // criando um construtor, representante

    private TextView textResultado; 
    private EditText editPeso;
    private EditText editAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 

        textResultado = findViewById(R.id.textResultado);
        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);
    }

    public void calcularimc(View view) {

        String pesoStr = editPeso.getText().toString();
        String alturaStr = editAltura.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            textResultado.setText("Valores vazios, não é possível realizar o cálculo.");
            return;
        }

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        double resultado = peso / (altura * altura);

        if (resultado < 19) {
            textResultado.setText("Abaixo do peso");
        } else if (resultado >= 19 && resultado < 25) {
            textResultado.setText("Peso normal");
        } else if (resultado >= 25 && resultado < 30) {
            textResultado.setText("Sobrepeso");
        } else if (resultado >= 30 && resultado < 40) {
            textResultado.setText("Obesidade tipo 1");
        } else if (resultado >= 40) {
            textResultado.setText("Obesidade tipo 2");
        }
    }
}
