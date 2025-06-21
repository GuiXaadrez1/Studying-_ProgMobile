# Intodução Ligando XML ao Código – Interação entre Interface e Lógica

### 📌 Objetivo:
Você aprenderá a:

Acessar componentes XML no Java.

Ler e alterar valores de views.

Criar botões que respondem a eventos.

Trabalhar com TextView, EditText, Button, CheckBox, RadioGroup, ImageView, etc.

## Exemplo XML (activity_main.xml):

<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/entradaNome"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Digite seu nome" />

    <Button
        android:id="@+id/botaoEnviar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Enviar" />

    <TextView
        android:id="@+id/textoResultado"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Resultado aparece aqui" />

</LinearLayout>

## Código Java (MainActivity.java):

public class MainActivity extends AppCompatActivity {

    EditText entradaNome;
    Button botaoEnviar;
    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando XML ao código Java
        entradaNome = findViewById(R.id.entradaNome);
        botaoEnviar = findViewById(R.id.botaoEnviar);
        textoResultado = findViewById(R.id.textoResultado);

        // Criando evento de clique
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = entradaNome.getText().toString();
                textoResultado.setText("Olá, " + nome + "!");
            }
        });
    }
}

## Explicação do código Java:

- findViewById(R.id.entradaNome)

Acessa o componente do layout via ID XML.

- .getText().toString()

Captura o texto digitado no EditText.

- .setText("Olá, ...")

Altera o conteúdo do TextView.

- setOnClickListener(...)

Cria uma ação que será executada quando o botão for clicado.

## CheckBox:

- No XML:

<CheckBox
    android:id="@+id/checkReceberEmail"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Receber emails?" />

- No Java:

CheckBox checkReceberEmail = findViewById(R.id.checkReceberEmail);
boolean selecionado = checkReceberEmail.isChecked();

## RadioGroup + RadioButton:

- No XML:

<RadioGroup android:id="@+id/grupoSexo" ...>
    <RadioButton android:id="@+id/sexoM" android:text="Masculino" .../>
    <RadioButton android:id="@+id/sexoF" android:text="Feminino" .../>
</RadioGroup>

- No Java:

RadioGroup grupoSexo = findViewById(R.id.grupoSexo);
int idSelecionado = grupoSexo.getCheckedRadioButtonId();
RadioButton selecionado = findViewById(idSelecionado);
String sexo = selecionado.getText().toString();

## ImageView (alterando imagem) no java:

ImageView imagem = findViewById(R.id.imgPerfil);
imagem.setImageResource(R.drawable.foto_nova);

## Switch no java:

Switch swNotificacoes = findViewById(R.id.switchNotificacoes);
boolean ativado = swNotificacoes.isChecked();

## 🧨 Dicas úteis:
Sempre use setContentView() antes de findViewById().

Evite nomes genéricos como text1, button2. Prefira txtNome, btnSalvar.

Use Log.d() ou Toast.makeText() para debugar.