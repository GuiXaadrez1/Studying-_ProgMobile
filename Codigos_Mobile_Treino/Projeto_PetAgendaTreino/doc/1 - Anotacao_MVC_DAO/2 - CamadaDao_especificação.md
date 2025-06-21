1. O que é a camada DAO?

    DAO significa Data Access Object — é a camada responsável por acessar o banco de dados diretamente, 
    escondendo os detalhes técnicos do SQLite (ou outro banco) das outras partes do sistema.

    Ela atua como um "tradutor" entre os dados da aplicação (objetos) e os dados armazenados (tabelas do banco).

2. 🧱 Como a camada DAO funciona na prática?

    Responsabilidades da ClienteDAO, por exemplo:

        Salvar um Cliente no banco (insert)

        Atualizar dados de um cliente (update)

        Buscar clientes (select)

        Excluir um cliente (delete)

        Converter dados do banco para objetos Java usando Cursor

        Montar e usar ContentValues para inserir dados

3. 🛡️ Deve haver encapsulamento na camada DAO?

    Sim! O encapsulamento é essencial aqui.

    Exemplos:

        A classe DAO deve encapsular a lógica SQL: quem usa ClienteDAO não precisa saber como a SQL funciona.

        Os atributos internos (como SQLiteDatabase db) devem ser private.

        Os métodos insert(), getAll(), delete() etc. devem ser public, pois você quer expor só o necessário.

4. 🧠 Teoria: relação entre DAO e as demais camadas

    VIEW (MainActivity) → chama →  CONTROLLER (ClienteController) → chama → DAO (ClienteDAO) → usa → DB (SQLiteOpenHelper)

    VIEW: exibe dados, recebe interação do usuário.

    CONTROLLER: decide o que fazer com os dados.

    DAO: executa as operações de banco.

    DB: sabe como criar as tabelas.

    EM RELAÇÃO DO DADOS COM DAO E MODEL: 
        
        [DAO] ⬄ lê e grava ⬄ [MODEL] 

        A DAO trabalha com objetos da camada MODEL — ela lê dados do banco e cria objetos (como Cliente, Admin, etc.), e também pega
        objetos da sua aplicação e grava no banco.
    
    PENSE ASSIM (analogia):

        Imagine que a DAO é um secretário e o Model é um formulário preenchido.

        A DAO pega um formulário preenchido (objeto Cliente) e salva no banco.

        Ou então, a DAO vai até o banco, lê os dados salvos, e monta um novo formulário (novo Cliente) para entregar à aplicação.


5. 🎯 Exemplo de estrutura de DAO (resumido):

public class ClienteDAO {
    private SQLiteDatabase db;

    public ClienteDAO(Context context) {
        Database database = new Database(context);
        this.db = database.getWritableDatabase();
    }

    public void inserir(Cliente cliente) {
        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("telefone", cliente.getTelefone());
        valores.put("email", cliente.getEmail());
        valores.put("senha", cliente.getSenha());

        db.insert("cliente", null, valores);
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        Cursor cursor = db.query("cliente", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Cliente c = new Cliente();
            c.setId(cursor.getInt(cursor.getColumnIndex("idcliente")));
            c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            // ...
            lista.add(c);
        }

        cursor.close();
        return lista;
    }

    // métodos: atualizar(), deletar(), buscarPorId()...
}

6. EXEMPLO APROFUNDADO:

    🧩 A tabela no banco:

        CREATE TABLE cliente (
            idcliente INTEGER,
            nome TEXT,
            telefone TEXT,
            email TEXT
        );

    🧩 O model em Java:

        public class Cliente {
            private int idcliente;
            private String nome;
            private String telefone;
            private String email;

            // getters e setters aqui
        }

    🧩 A DAO fazendo leitura e gravação:

    🔻 Gravar (salvar no banco):

        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("telefone", cliente.getTelefone());
        valores.put("email", cliente.getEmail());

        db.insert("cliente", null, valores);

    Aqui a DAO pegou os dados do objeto Cliente (model) e os gravou no banco.

    🔺 Ler (buscar no banco):

        Cursor c = db.query("cliente", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            Cliente cliente = new Cliente();
            cliente.setIdcliente(c.getInt(c.getColumnIndex("idcliente")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
        }

    Aqui a DAO leu os dados do banco e criou um novo objeto Cliente (model) com esses dados.

    ✅ Em resumo:

        AÇÃO	     ACONTECE NA DAO	USA MODEL?	                BANCO DE DADOS?
    Salvar dados	    Sim	Sim (usa o objeto Cliente)	            Sim (usa SQL)
    Buscar dados	    Sim	Sim (monta objeto Cliente)	            Sim
