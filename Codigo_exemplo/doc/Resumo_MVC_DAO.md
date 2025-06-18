# RESUMO INSCRITO:

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


RESUMO TABELAR:

    +------------+---------------------------------------------------------------------------------------------+
    | Camada     | Responsabilidade                                                                            |
    +------------+---------------------------------------------------------------------------------------------+
    | View       | - Interface gráfica (Activities, Fragments, XMLs)                                           |
    |            | - Coleta dados do usuário e exibe resultados                                                |
    |            | - Não contém regras de negócio nem acesso ao banco                                          |
    |            | - Ex: MainActivity.java, layouts XML                                                        |
    +------------+---------------------------------------------------------------------------------------------+
    | Controller | - Coordena a lógica da aplicação                                                            |
    |            | - Recebe dados da View, instancia o Model e chama o DAO                                     |
    |            | - Intermediário entre View e DAO                                                            |
    |            | - Ex: ClienteController.java                                                                |
    +------------+---------------------------------------------------------------------------------------------+
    | Model      | - Representa os dados do domínio da aplicação                                               |
    |            | - Apenas atributos + get/set, pode ter validações simples                                   |
    |            | - Não acessa banco e não sabe de onde vêm os dados                                          |
    |            | - Ex: Cliente.java, Admin.java                                                              |
    +------------+---------------------------------------------------------------------------------------------+
    | DAO        | - Responsável pelo CRUD no banco de dados                                                   |
    |            | - Recebe objetos Model, usa DataBase (SQLiteOpenHelper) para acessar o banco                |
    |            | - Retorna objetos preenchidos com dados da tabela                                           |
    |            | - Ex: ClienteDAO.java, AdminDAO.java                                                        |
    +------------+---------------------------------------------------------------------------------------------+
    | Infra      | - Infraestrutura de apoio, como criação e manutenção do banco                               |
    |            | - Contém a classe que estende SQLiteOpenHelper                                              |
    |            | - Fornece instância de SQLiteDatabase para o DAO                                            |
    |            | - Ex: DataBase.java                                                                          |
    +------------+---------------------------------------------------------------------------------------------+


RESUMO GRÁFICO:

    View ───> Controller ───> DAO ───> Infra (DataBase)
                │               ↑
                ↓               │
            Model <────────────

RESUMO DA ESTRUTURA DO PROJETO:

    app/
    ├── db/                        <-- CAMADA INFRA
    │   └── DataBase.java         <-- SQLiteOpenHelper com criação das tabelas
    │
    ├── model/                    <-- CAMADA MODEL
    │   ├── Cliente.java
    │   ├── Admin.java
    │   └── Agenda.java
    │
    ├── dao/                      <-- CAMADA DAO
    │   └── ClienteDAO.java       <-- CRUD com ContentValues e Cursor
    │
    ├── controller/               <-- CAMADA CONTROLLER
    │   └── ClienteController.java
    │
    ├── view/                     <-- CAMADA VIEW
    │   └── MainActivity.java     <-- Interface e interação com o usuário
    │
    └── res/
        └── layout/
            └── activity_main.xml <-- Layout da interface


 