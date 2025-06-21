📘 Capítulo 5: Navegação entre Activities no Android (Java)
No Android, cada tela é uma Activity. Para navegar entre telas, usamos a classe Intent, que representa a intenção de iniciar outra tela.

🧱 Estrutura básica de navegação
Criamos duas Activities: MainActivity (tela inicial) e SegundaActivity (nova tela).

Usamos um botão para iniciar a nova tela.

Podemos enviar dados usando extras no Intent.

🖼️ Etapa 1: Criar segunda tela
Clique com o botão direito em java > seu pacote.

Selecione New > Activity > Empty Activity.

Nomeie como: SegundaActivity.

O Android Studio criará:

SegundaActivity.java

activity_segunda.xml

E registrará no AndroidManifest.xml.

🧪 Exemplo prático com duas telas
📄 XML da tela principal (activity_main.xml):
xml
Copiar
Editar
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <EditText
        android:id="@+id/entradaNome"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Digite seu nome" />

    <Button
        android:id="@+id/botaoAvancar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Ir para próxima tela" />

</LinearLayout>
🎯 Java da tela principal (MainActivity.java):
java
Copiar
Editar
public class MainActivity extends AppCompatActivity {

    EditText entradaNome;
    Button botaoAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entradaNome = findViewById(R.id.entradaNome);
        botaoAvancar = findViewById(R.id.botaoAvancar);

        botaoAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = entradaNome.getText().toString();

                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                intent.putExtra("nomeUsuario", nome); // passando dados
                startActivity(intent); // inicia nova tela
            }
        });
    }
}
📄 XML da segunda tela (activity_segunda.xml):
xml
Copiar
Editar
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/textoRecebido"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Aqui aparecerá o nome..." />
</LinearLayout>
📦 Java da segunda tela (SegundaActivity.java):
java
Copiar
Editar
public class SegundaActivity extends AppCompatActivity {

    TextView textoRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textoRecebido = findViewById(R.id.textoRecebido);

        // Pegando os dados enviados da MainActivity
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nomeUsuario");

        textoRecebido.setText("Olá, " + nome + "!");
    }
}

🧠 Como funciona esse fluxo?
O botão chama um Intent para a SegundaActivity.

O método putExtra() envia um valor (chave: "nomeUsuario", valor: texto).

A SegundaActivity recupera isso com getStringExtra().

🛡️ Segurança e validação
Sempre valide se o dado foi recebido corretamente:


if (intent.hasExtra("nomeUsuario")) {
    String nome = intent.getStringExtra("nomeUsuario");
    // usa o nome
}

📌 Resumo dos métodos:

| Método                    | O que faz                          |
| ------------------------- | ---------------------------------- |
| `startActivity(intent)`   | Abre uma nova tela                 |
| `putExtra(chave, valor)`  | Envia dados para outra tela        |
| `getIntent()`             | Recupera o intent recebido         |
| `getStringExtra("chave")` | Extrai um valor de texto do intent |
