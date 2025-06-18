Guia Definitivo de SQLite com Java no Android

Capítulo 1: Introdução ao SQLite

O que é o SQLite?

    O SQLite é um sistema de gerenciamento de banco de dados relacional, leve, embutido e baseado em arquivos. 
    Ele segue o padrão SQL-92 e é amplamente utilizado em aplicações mobile, como Android, justamente por sua leveza,
    independência de servidor e facilidade de integração.

    Diferentemente de bancos como MySQL ou PostgreSQL, o SQLite não funciona com um processo de servidor. 
    O banco é, na verdade, um arquivo que reside no sistema de arquivos local do dispositivo.

Características principais:

    Armazenamento local.

    Suporte a SQL.

    Transações ACID.

    Leve, sem dependências.

    Cross-platform.

    No Android, o suporte a SQLite é nativo, acessado através de APIs como SQLiteDatabase e SQLiteOpenHelper.

Capítulo 2: Conceitos Fundamentais

    Banco de Dados

        Um banco de dados é uma coleção organizada de dados que podem ser acessados, gerenciados e atualizados eficientemente.

    Tabela

        Uma tabela é uma estrutura que armazena dados em colunas (campos) e linhas (registros).

    Esquema

        O esquema define a estrutura do banco de dados: nomes das tabelas, colunas, tipos de dados, chaves primárias, etc.

    Chave Primária

        Coluna (ou conjunto de colunas) que identifica de forma única cada registro.

    Cursor

        No Android, um cursor é uma interface que permite navegar pelos resultados de uma consulta ao banco de dados.

Capítulo 3: Arquitetura do SQLite no Android

Classe SQLiteOpenHelper

    Classe base fornecida pelo Android para criar e gerenciar a versão do banco de dados. 
    Ela facilita o gerenciamento do ciclo de vida do banco, como criação e upgrade.

Principais Métodos:

    onCreate(SQLiteDatabase db): chamado na primeira vez que o banco é acessado. É aqui que se define a estrutura inicial do banco.

     onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion): chamado quando a versão do banco muda. Usado para migrar dados ou modificar estrutura.

Classe SQLiteDatabase

    Responsável por executar comandos SQL como INSERT, UPDATE, DELETE, QUERY. É obtido através de getWritableDatabase() ou getReadableDatabase().

    Classe ContentValues

        Usada para encapsular dados em pares chave/valor. Facilita a inserção e atualização sem necessidade de escrever SQL manual.

    Classe Cursor

        Contêiner que permite navegar pelos resultados de uma consulta SQL. Você pode acessar valores de cada linha usando métodos como getString() ou getInt().

Capítulo 4: Criando um Banco de Dados SQLite (linha por linha explicada)


public class MeuBancoHelper extends SQLiteOpenHelper {

    Cria uma nova classe que estende SQLiteOpenHelper, o que nos permite gerenciar a criação e atualização do banco.

        private static final String NOME_BANCO = "app_banco.db";

Define o nome do arquivo de banco de dados. Ele será salvo no sistema de arquivos do app.

    private static final int VERSAO = 1;

Define a versão do banco. Alterar esse valor força a execução do onUpgrade para lidar com migrações de esquema.

    public static final String TABELA = "usuarios";

Define o nome da tabela principal que vamos criar dentro do banco.

    public MeuBancoHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

Construtor da classe. Ele passa os parâmetros para a superclasse, que gerencia a conexão com o banco.

    @Override
    public void onCreate(SQLiteDatabase db) {

Método chamado automaticamente quando o banco ainda não existe. Aqui criamos a tabela.

        String sql = "CREATE TABLE " + TABELA + " (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "nome TEXT NOT NULL, " +
                     "email TEXT NOT NULL, " +
                     "telefone TEXT)";

Montamos a string SQL de criação de tabela com 4 colunas:

    id: inteiro autoincrementável, usado como chave primária.

    nome: campo de texto obrigatório.

    email: campo de texto obrigatório.

    telefone: campo de texto opcional.

    db.execSQL(sql);

Executa a string SQL que acabamos de construir no banco de dados.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    Esse método é chamado automaticamente quando detecta mudança na versão do banco. Primeiro ele exclui a tabela, e depois recria com o método onCreate().

Capítulo 5: Operando com CRUD (Create, Read, Update, Delete)

    Inserindo Dados (com explicação)

    SQLiteDatabase db = helper.getWritableDatabase();

    Abre o banco em modo de escrita (permite inserir e atualizar dados).

        ContentValues valores = new ContentValues();
        valores.put("nome", "João");
        valores.put("email", "joao@email.com");
        valores.put("telefone", "61999999999");

    Cria um objeto ContentValues que armazena os valores das colunas. Cada put() define uma coluna e seu respectivo valor.

    long id = db.insert("usuarios", null, valores);

    Executa a inserção dos dados na tabela usuarios. Retorna o ID gerado automaticamente.

    Lendo Dados

        Cursor cursor = db.rawQuery("SELECT * FROM usuarios", null);

    Executa uma consulta SQL que retorna todos os registros da tabela usuarios. O resultado é armazenado em um Cursor.

        while (cursor.moveToNext()) {
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
        }

        Percorre cada linha do cursor, extraindo os valores das colunas com getString() e getColumnIndex().

    cursor.close();

        Encerra o cursor para liberar recursos.

Atualizando Dados

    ContentValues valores = new ContentValues();
    valores.put("telefone", "61988887777");

    Define os novos valores para as colunas que serão atualizadas.

    db.update("usuarios", valores, "id=?", new String[]{"1"});

        Atualiza o registro com id = 1, aplicando os valores definidos anteriormente.

Deletando Dados

    db.delete("usuarios", "id=?", new String[]{"1"});

        Remove o registro da tabela onde o id seja igual a 1.

Capítulo 6: Boas Práticas

    Sempre feche Cursor e SQLiteDatabase após o uso para liberar recursos.

    Use try/catch para capturar exceções de banco e evitar falhas.

    Nunca exponha dados sensíveis diretamente nos logs.

    Utilize versões incrementais para atualizações de esquema com onUpgrade().

    Crie uma classe DAO (Data Access Object) para abstrair o acesso ao banco e manter o código limpo.

Capítulo 7: Avançado

Criação de views SQL para consultas reutilizáveis e otimizadas.

Utilização de transações para garantir integridade em operações complexas:

SQLiteDatabase db = helper.getWritableDatabase();
db.beginTransaction();
try {
    // execuções SQL
    db.setTransactionSuccessful();
} finally {
    db.endTransaction();
}

Joins entre tabelas para relacionar dados de forma eficiente.

Normalização de dados e modelagem relacional com chaves estrangeiras.

Exportação e backup do banco com cópia do arquivo .db.

Migrações progressivas no onUpgrade() para alterar esquemas sem perder dados existentes.