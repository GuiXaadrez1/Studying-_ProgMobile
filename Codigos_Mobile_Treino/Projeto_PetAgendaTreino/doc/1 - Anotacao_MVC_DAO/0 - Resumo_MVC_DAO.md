# RESUMO ESCRITO:

1. Model (Modelo)
    
    ✅ Função:

        A camada Model é responsável por representar os dados da aplicação. São classes simples (JavaBeans ou POJOs)
        que contêm os atributos (campos) e, às vezes, regras básicas de negócio (validações simples).

    🧠 Regras:

        Não deve acessar banco de dados.

        Não deve conter lógica de interface.

        Não conhece nada do Android SDK (em regra).

    📄 Exemplo:

        public class Cliente {
            private int idcliente;
            private String nome;
            private String telefone;
            private String email;
            private String senha;

            // Getters e setters para encapsulamento
        }
    
    🧩 Observações:
        
        O Model é apenas um espelho da estrutura das tabelas.

        Pode conter métodos como validarSenha(), mas não deve chamar SQLiteDatabase ou ContentValues.

2. DAO (Data Access Object)

    ✅ Função:
        
        Camada responsável por acessar, inserir, atualizar, deletar e consultar os dados no banco de dados,
        transformando as informações que vêm do SQLite em objetos Model.

    🧠 Regras:
        
        Usa SQLiteDatabase, Cursor, ContentValues e comandos SQL.

        Trabalha com instâncias do Model para mapear dados.

    📄 Exemplo:

        public class ClienteDAO {
            private SQLiteDatabase db;

            public ClienteDAO(Context context) {
                DataBase helper = new DataBase(context); // Usa a camada infra
                db = helper.getWritableDatabase();
            }

            public long inserir(Cliente cliente) {
                ContentValues cv = new ContentValues();
                cv.put("nome", cliente.getNome());
                cv.put("telefone", cliente.getTelefone());
                cv.put("email", cliente.getEmail());
                cv.put("senha", cliente.getSenha());
                return db.insert("cliente", null, cv);
            }
        }
    
    🧩 Observações:
        
        O DAO não deve ter interface gráfica, nem instanciar TextView, EditText ou Intent.

        É usado apenas pelo Controller.

3. Infra/db (Infraestrutura)

    ✅ Função:
    
        Essa camada contém classes que suportam tecnicamente o funcionamento da aplicação, como o gerenciador do banco (SQLiteOpenHelper),
        constantes globais, configuradores etc.

    📄 Exemplo:

        public class DataBase extends SQLiteOpenHelper {
            public DataBase(Context context) {
                super(context, "petagenda", null, 1);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE cliente (...)");
                db.execSQL("CREATE TABLE admin (...)");
                // outras tabelas...
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS cliente");
                // etc...
                onCreate(db);
            }
        }

    🧩 Observações:

        Não é DAO, pois não manipula objetos Cliente, Admin, etc.

        Não é Model, pois não representa dados do negócio.

        Sua única função é preparar o ambiente para o DAO funcionar.

4. Controller (Controlador)

    ✅ Função:

        É o "cérebro" da aplicação. Orquestra a lógica de negócio, recebe ações da View, decide o que fazer
         (salvar, buscar, validar), e coordena o uso do DAO.

    🧠 Regras:
    
        Interage com o DAO e o Model.

        Não acessa diretamente SQLiteDatabase, sempre usa o DAO.

        Pode ser uma Activity ou uma classe separada de lógica.

    📄 Exemplo:

        public class ClienteController {
            private ClienteDAO dao;

            public ClienteController(Context context) {
                dao = new ClienteDAO(context);
            }

            public boolean salvarCliente(String nome, String telefone, String email, String senha) {
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setSenha(senha);
                return dao.inserir(cliente) > 0;
            }
        }

    🧩 Observações:

        Idealmente, a Activity não deve conter muita lógica. Essa lógica vai pro Controller.

        Ajuda a manter a MainActivity limpa e organizada.

5. View (Visão ou Interface do Usuário)

    ✅ Função:

        A camada responsável pela interação com o usuário. Mostra dados na tela, coleta inputs e repassa comandos para o Controller.

    Inclui:

        Activity, Fragment, XML Layouts, EditText, TextView, Button, etc.

    📄 Exemplo:

        btnSalvar.setOnClickListener(v -> {
            String nome = etNome.getText().toString();
            String telefone = etTelefone.getText().toString();
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();

            ClienteController controller = new ClienteController(this);
            boolean sucesso = controller.salvarCliente(nome, telefone, email, senha);

            if (sucesso) {
                Toast.makeText(this, "Cliente salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

    🧩 Observações:

        View não deve acessar SQLite diretamente.

        Deve ser o mais burra possível: só exibe ou coleta dados.

## 📋 Resumo Tabular das Camadas da Arquitetura (MVC - Android)

| Camada      | Responsabilidades                                                                                      |
|-------------|--------------------------------------------------------------------------------------------------------|
| **View**    | - Interface gráfica: Activities, Fragments, XMLs                                                       |
|             | - Exibe dados ao usuário e coleta inputs                                                               |
|             | - Não contém regras de negócio nem acesso ao banco                                                     |
|             | - Exemplos: `MainActivity.java`, arquivos XML (`activity_main.xml`, etc.)                             |
| **Controller** | - Coordena a lógica da aplicação                                                                      |
|             | - Recebe dados da View, instancia Models e interage com DAOs                                           |
|             | - Atua como ponte entre View e persistência de dados                                                   |
|             | - Exemplo: `ClienteController.java`                                                                    |
| **Model**   | - Representa entidades do domínio da aplicação                                                         |
|             | - Contém atributos e métodos `get/set`, podendo incluir validações simples                             |
|             | - Não acessa banco de dados, nem usa recursos do Android SDK                                           |
|             | - Exemplos: `Cliente.java`, `Admin.java`                                                               |
| **DAO**     | - Realiza o CRUD no banco de dados                                                                     |
|             | - Mapeia dados do SQLite para objetos Model                                                            |
|             | - Utiliza `SQLiteDatabase`, `Cursor`, `ContentValues`                                                  |
|             | - Exemplos: `ClienteDAO.java`, `AdminDAO.java`                                                         |
| **Infra**   | - Suporte técnico (criação e manutenção do banco de dados)                                             |
|             | - Contém a classe que estende `SQLiteOpenHelper`                                                       |
|             | - Responsável por fornecer instância de `SQLiteDatabase` para o DAO                                    |
|             | - Exemplo: `DataBase.java`                                                                             |

RESUMO GRÁFICO:

View ───> Controller ───> Model ───> DAO ───> Infra (DataBase)
         ↑                                                    ↓
         └────────────── resultado/dados processados ─────────┘

            
RESUMO DA ESTRUTURA DO PROJETO:

app/
│
├── manifests/
│   └── AndroidManifest.xml        ← Registro de activities, permissões, config. global
│
├── java/
│   └── com.seupacote.app/
│       ├── db/                    ← Camada Infra (DataBase.java)
│       ├── model/                 ← Modelos de dados (Cliente.java, Admin.java)
│       ├── dao/                   ← DAO (ClienteDAO.java)
│       ├── controller/            ← Lógica de negócio (ClienteController.java)
│       └── view/                  ← Interface (MainActivity.java)
│
├── res/
│   └── layout/
│       └── activity_main.xml      ← Layout da tela principal
│
└── Gradle Scripts/