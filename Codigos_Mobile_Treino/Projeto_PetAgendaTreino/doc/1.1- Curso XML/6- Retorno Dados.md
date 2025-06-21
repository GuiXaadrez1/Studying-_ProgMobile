# Introdução Retorno de Dados com startActivityForResult e setResult (Java)
E
m alguns casos, além de navegar para outra tela, você precisa que essa segunda tela devolva um resultado para a tela anterior. É aí que usamos:

startActivityForResult() (até Android 10)

setResult() para enviar a resposta

onActivityResult() para receber a resposta

## ⚠️ Importante:

Este método clássico é usado até o Android 10 (API 29). No Android 11+ recomenda-se o ActivityResultLauncher, mas aqui vamos seguir com Java e startActivityForResult(), como você pediu.

## 🧪 Exemplo completo: A segunda tela devolve o nome editado

### Etapa 1 – Tela principal: envia e espera resposta

XML da tela principal (activity_main.xml):

<Button
    android:id="@+id/botaoEditarNome"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Editar nome" />

<TextView
    android:id="@+id/textoNomeEditado"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Nome atual: Guilherme" />

### Java da MainActivity.java:

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_REQUISICAO = 1;
    TextView textoNomeEditado;
    Button botaoEditarNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNomeEditado = findViewById(R.id.textoNomeEditado);
        botaoEditarNome = findViewById(R.id.botaoEditarNome);

        botaoEditarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                intent.putExtra("nomeAtual", "Guilherme"); // envia nome atual
                startActivityForResult(intent, CODIGO_REQUISICAO);
            }
        });
    }

    // recebe o retorno da segunda tela
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODIGO_REQUISICAO && resultCode == RESULT_OK && data != null) {
            String nomeEditado = data.getStringExtra("nomeEditado");
            textoNomeEditado.setText("Nome atual: " + nomeEditado);
        }
    }
}

### Etapa 2 – Segunda tela: edita e devolve o nome

XML (activity_segunda.xml):

<EditText
    android:id="@+id/entradaNome"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Digite o novo nome" />

<Button
    android:id="@+id/botaoConfirmar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Confirmar" />

### Java da SegundaActivity.java:

public class SegundaActivity extends AppCompatActivity {

    EditText entradaNome;
    Button botaoConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        entradaNome = findViewById(R.id.entradaNome);
        botaoConfirmar = findViewById(R.id.botaoConfirmar);

        // recebe nome atual enviado pela MainActivity
        Intent intent = getIntent();
        String nomeAtual = intent.getStringExtra("nomeAtual");
        entradaNome.setText(nomeAtual);

        // retorna novo nome para tela principal
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEditado = entradaNome.getText().toString();

                Intent resultado = new Intent();
                resultado.putExtra("nomeEditado", nomeEditado);
                setResult(RESULT_OK, resultado); // envia o resultado
                finish(); // fecha a Activity
            }
        });
    }
}

## 🔁 Explicação do ciclo completo

startActivityForResult(intent, código) → inicia a tela e espera retorno.

setResult(RESULT_OK, intentResultado) → envia a resposta.

onActivityResult() → método chamado automaticamente quando a tela retorna.

## 📌 Dicas finais
Sempre valide requestCode, resultCode e data != null antes de usar os dados.

É possível retornar vários dados usando vários putExtra.

Se quiser cancelar, use setResult(RESULT_CANCELED).