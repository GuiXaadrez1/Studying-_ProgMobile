# introdução ao SQLiteDatabase do android.database.sqlite.SQLiteDatabase
É a classe principal do Android que representa o banco de dados SQLite.
Com ela, você pode criar tabelas, inserir dados, fazer consultas, atualizações e deletar registros — ou seja, executar comandos SQL em tempo de execução.

Exemplo instanciando um objeto SQLiteDatabase
SQLiteDatabase banco;


## Como obter uma instância de SQLiteDatabase? 
A instância é criada a partir do seu helper (a classe que estende SQLiteOpenHelper):

Database dbHelper = new Database(context);
SQLiteDatabase banco = dbHelper.getWritableDatabase();

- getWritableDatabase() abre (ou cria) o banco para leitura e escrita.
- getReadableDatabase() abre apenas para leitura se houver problema de escrita (como disco cheio).

## O que o SQLiteDatabase faz?

    Ação	                Método
Inserir dados	            insert()
Atualizar dados	            update()
Deletar dados	            delete()
Consultar dados	            rawQuery(), query()
Executar SQL bruto	        execSQL() (ideal para CREATE, DROP, etc.)
Transações	                beginTransaction(), setTransactionSuccessful()


##  Métodos principais com exemplo e explicação

- insert()

ContentValues valores = new ContentValues();
valores.put("nome", "João"); # coluna é a chave e o valor é o atributo do puxado pelo get
valores.put("email", "joao@email.com");

banco.insert("cliente", null, valores);

Primeiro argumento: nome da tabela
Segundo: pode ser null (usado se todos os campos forem nulos — ignora-se isso)
Terceiro: objeto ContentValues, onde cada campo da tabela vira um par chave/valor

-  update()

ContentValues valores = new ContentValues();
valores.put("email", "novo@email.com");
banco.update("cliente", valores, "idcliente = ?", new String[]{"1"});

Atualiza a tabela cliente
Define novo valor para o campo email
Só atualiza onde o idcliente = 1

- lembrese-se: O ? é um placeholder para evitar sql injection

- delete()

    banco.delete("cliente", "idcliente = ?", new String[]{"1"});

Apaga o registro com idcliente = 1

- query()

Cursor cursor = banco.query(
    "cliente",                            // tabela
    new String[]{"idcliente", "nome"},   // colunas
    "idcliente = ?",                     // WHERE
    new String[]{"1"},                   // argumentos do WHERE
    null, null, null                     // GROUP BY, HAVING, ORDER BY
);

Mais seguro que rawQuery, pois já tem tratamento interno de parâmetros.

- rawQuery()

Cursor cursor = banco.rawQuery("SELECT * FROM cliente WHERE idcliente = ?", new String[]{"1"});

Permite escrever SQL puro, com parâmetros. Recomendado para consultas complexas.

- execSQL()

    banco.execSQL("DELETE FROM cliente WHERE idcliente = 2");

Executa SQL sem retorno

Usado para CREATE TABLE, DROP, DELETE, ALTER, etc.

## Exemplo completo com insert:

public long inserirCliente(Cliente cliente) {
    ContentValues values = new ContentValues();
    values.put("nome", cliente.getNome());
    values.put("telefone", cliente.getTelefone());
    values.put("email", cliente.getEmail());
    values.put("senha", cliente.getSenha());

    return banco.insert("cliente", null, values);
}

## Transações

banco.beginTransaction();
try {
    // várias inserções/atualizações
    banco.setTransactionSuccessful(); // tudo certo
} finally {
    banco.endTransaction(); // encerra (commit ou rollback)
}

Usar transações melhora desempenho e integridade em operações em lote.

## Boas práticas com SQLiteDatabase

Use getWritableDatabase() apenas uma vez e reutilize.

Feche o banco apenas quando não precisar mais dele (normalmente o Android gerencia isso).

Feche cursores depois de usar: cursor.close();

Evite SQL direto em execSQL com concatenação — prefira parâmetros.