
Vamos dissecar o padrão MVC + DAO de forma detalhada, passo a passo, explicando o fluxo: 
    View → Controller → DAO → Model (e vice-versa)
    com foco em aplicações como Android, Java ou sistemas em camadas.

    🧠 Conceitos Iniciais

        ▶ Padrão MVC (Model-View-Controller)
        
            Separa responsabilidades para organizar melhor o código:

                Model: Regras de negócio e representação dos dados (ex: Cliente.java).

                View: Interface com o usuário (ex: activity_main.xml no Android).

                Controller: Coordena as ações entre View e Model (ex: ClienteController.java).

        ▶ Padrão DAO (Data Access Object)
        
            Cria uma camada especializada para acesso ao banco de dados, 
            mantendo o Model limpo de lógica de persistência.

    🔄 Fluxo Completo - Exemplo com "Cliente"

        Imagine um app que cadastra clientes. Você quer separar as responsabilidades claramente.

        📦 1. MODEL: Representação do dado

            public class Cliente {
                private int id;
                private String nome;
                private String email;

                // Construtores
                public Cliente() {}

                public Cliente(int id, String nome, String email) {
                    this.id = id;
                    this.nome = nome;
                    this.email = email;
                }

                // Getters e Setters
                public int getId() { return id; }
                public void setId(int id) { this.id = id; }

                public String getNome() { return nome; }
                public void setNome(String nome) { this.nome = nome; }

                public String getEmail() { return email; }
                public void setEmail(String email) { this.email = email; }
            }

                ✔ Finalidade: Define como o dado é estruturado, sem saber onde será salvo.

        💾 2. DAO: Acesso ao banco

            public class ClienteDAO {
                private SQLiteDatabase db;

                public ClienteDAO(Context context) {
                    DatabaseHelper helper = new DatabaseHelper(context);
                    db = helper.getWritableDatabase();
                }

                public long inserir(Cliente cliente) {
                    ContentValues valores = new ContentValues();
                    valores.put("nome", cliente.getNome());
                    valores.put("email", cliente.getEmail());

                    return db.insert("cliente", null, valores);
                }

                public List<Cliente> listarTodos() {
                    List<Cliente> lista = new ArrayList<>();
                    Cursor cursor = db.query("cliente", null, null, null, null, null, "nome");

                    while (cursor.moveToNext()) {
                        Cliente c = new Cliente();
                        c.setId(cursor.getInt(cursor.getColumnIndex("id")));
                        c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                        c.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        lista.add(c);
                    }
                    cursor.close();
                    return lista;
                }

                // Atualizar, deletar etc. seguem o mesmo padrão
            }
            
                ✔ Finalidade: Esconde detalhes de banco. O Model não sabe como ele é salvo ou carregado.

        🧠 3. CONTROLLER: Lógica de aplicação

            public class ClienteController {
                private ClienteDAO dao;

                public ClienteController(Context context) {
                    dao = new ClienteDAO(context);
                }

                public boolean salvarCliente(String nome, String email) {
                    if (nome.isEmpty() || email.isEmpty()) return false;

                    Cliente cliente = new Cliente();
                    cliente.setNome(nome);
                    cliente.setEmail(email);

                    long id = dao.inserir(cliente);
                    return id != -1;
                }

                public List<Cliente> buscarClientes() {
                    return dao.listarTodos();
                }
            }
            
                ✔ Finalidade: Aplica regras de negócio, coordena ações. A View nunca chama diretamente o DAO!


        🖼 4. VIEW: Interface do usuário    

            XML (Android)
            <EditText android:id="@+id/editNome" ... />
            <EditText android:id="@+id/editEmail" ... />
            <Button android:id="@+id/btnSalvar" ... />

        public class MainActivity extends AppCompatActivity {
            private EditText editNome, editEmail;
            private Button btnSalvar;
            private ClienteController controller;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                editNome = findViewById(R.id.editNome);
                editEmail = findViewById(R.id.editEmail);
                btnSalvar = findViewById(R.id.btnSalvar);

                controller = new ClienteController(this);

                btnSalvar.setOnClickListener(v -> {
                    String nome = editNome.getText().toString();
                    String email = editEmail.getText().toString();

                    boolean sucesso = controller.salvarCliente(nome, email);
                    Toast.makeText(this, sucesso ? "Salvo!" : "Erro", Toast.LENGTH_SHORT).show();
                });
            }
        }

            ✔ Finalidade: Interage com o usuário. Só chama o Controller (e nunca diretamente o DAO ou Model).

🧭 RESUMO DO FLUXO:

    Usuário (View)
    ↓
    Controller → valida, aplica regra
    ↓
    DAO → insere ou lê do banco
    ↓
    Model → representa os dados

📚 Vantagens dessa Arquitetura

    Separação de responsabilidades	Facilita testes, manutenção, escalabilidade
    
    Reutilização de código	Controller e DAO podem ser usados em outras views
    
    Facilita manutenção	Você muda o banco (SQLite → Room) sem alterar o Controller ou Model
    
    Testes mais fáceis	Pode mockar DAO ou Controller
