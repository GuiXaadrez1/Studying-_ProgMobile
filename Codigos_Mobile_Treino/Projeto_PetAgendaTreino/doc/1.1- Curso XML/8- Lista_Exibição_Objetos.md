# Introdução Como usar ListView e RecyclerView em Java

- Visão geral
Componente	Simples, rápido	Moderno, mais eficiente

ListView	Bom para listas simples	Pior desempenho com listas longas

RecyclerView	Recomendado hoje	Altamente personalizável


## PARTE 1 – Usando ListView com lista simples

- Objetivo: Mostrar uma lista de nomes
- XML (activity_main.xml):

<ListView
    android:id="@+id/listaNomes"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

- Java (MainActivity.java):

public class MainActivity extends AppCompatActivity {

    ListView listaNomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNomes = findViewById(R.id.listaNomes);

        String[] nomes = {"João", "Maria", "Pedro", "Ana"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            nomes
        );

        listaNomes.setAdapter(adapter);

        listaNomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomeSelecionado = nomes[position];
                Toast.makeText(MainActivity.this, "Você clicou: " + nomeSelecionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

## PARTE 2 – Usando RecyclerView com objetos

- Objetivo: Mostrar uma lista de objetos Usuario

// Classe modelo Usuario.java

public class Usuario {
    private String nome;
    private int idade;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}

- XML do layout principal (activity_main.xml):

<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerUsuarios"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>

- XML do item da lista (item_usuario.xml):

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:padding="8dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/textNome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Nome" />

    <TextView
        android:id="@+id/textIdade"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Idade" />
</LinearLayout>

## Criar o Adapter (UsuarioAdapter.java)

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    private List<Usuario> listaUsuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.listaUsuarios = usuarios;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNome, textIdade;

        public ViewHolder(View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textIdade = itemView.findViewById(R.id.textIdade);
        }
    }

    @Override
    public UsuarioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_usuario, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.ViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.textNome.setText(usuario.getNome());
        holder.textIdade.setText("Idade: " + usuario.getIdade());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }
}

## Java da MainActivity.java com RecyclerView:

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerUsuarios;
    List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerUsuarios = findViewById(R.id.recyclerUsuarios);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));

        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("João", 30));
        listaUsuarios.add(new Usuario("Maria", 25));
        listaUsuarios.add(new Usuario("Ana", 22));

        UsuarioAdapter adapter = new UsuarioAdapter(listaUsuarios);
        recyclerUsuarios.setAdapter(adapter);
    }
}

## Dicas rápidas

Para listas com layout customizado ou objetos complexos → prefira RecyclerView.

Para listas simples de texto → ListView pode atender bem.

Com RecyclerView, é comum também adicionar cliques nos itens, animações, etc.

