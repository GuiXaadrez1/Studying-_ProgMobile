Cursor, ArrayList, rawQuery, etc.

## Introdução ao Cursor do  android.database.Cursor
A classe Cursor representa um conjunto de resultados de uma consulta (query) feita no banco de dados SQLite. Ele funciona como um ponteiro navegável, que permite ler cada linha e coluna retornada.

##  Quando o Cursor é usado?
Sempre que você faz uma consulta no banco de dados com métodos como:

    Cursor cursor = banco.rawQuery("SELECT * FROM cliente", null);

    ou 

    Cursor cursor = banco.query("cliente", ...);

##  Principais Métodos do Cursor (linha por linha)

cursor.moveToFirst();

- Move o cursor para a primeira linha do resultado.

- Retorna false se o resultado estiver vazio.s

cursor.moveToNext();

- Move para a próxima linha.

- Deve ser usado em laços (while (cursor.moveToNext())).

cursor.isAfterLast();

- Verifica se o cursor já passou da última linha.

cursor.getCount();

- Retorna o número total de linhas retornadas pela query.

## Leitura de colunas por índice ou nome

int id = cursor.getInt(0);

- Acessa o valor da coluna 0 como inteiro.

String nome = cursor.getString(cursor.getColumnIndex("nome"));

- Busca o índice da coluna "nome" e acessa o valor como string

## Dica
Evite getColumnIndex() em loops — use uma vez antes e salve em variáveis:

int idxNome = cursor.getColumnIndex("nome");
while (cursor.moveToNext()) {
    String nome = cursor.getString(idxNome);
}

## Outros tipos de dados

long valor = cursor.getLong(...); -> pega valores com strings grandes
float valor = cursor.getFloat(...); -> pega valores de ponto flutuante
double valor = cursor.getDouble(...); -> pega valores decimais
byte[] bytes = cursor.getBlob(...); -> pega um array de bytes

- byte[] -> representa um array de byte

##  Métodos principais de consulta que retornam Cursor

- rawQuery()
Cursor cursor = banco.rawQuery("SELECT * FROM cliente WHERE id = ?", new String[]{"1"});

- Executa SQL puro.

    ? são parâmetros substituíveis — proteção contra SQL Injection.

    O segundo argumento é um array com os valores que substituem os ?.

- query()

Cursor cursor = banco.query(
    "cliente",               // argumento tabela
    new String[]{"nome"},    // argumento colunas
    "idcliente = ?",         // argumento WHERE
    new String[]{"1"},       // argumento argumentos para WHERE
    null, null, null         // argumento GROUP BY, HAVING, ORDER BY
);

Mais estruturado e seguro que rawQuery.

## Importante: Sempre fechar o cursor

cursor.close();

- O Cursor mantém ponteiros abertos no banco.

- Se não for fechado, pode causar vazamento de memória ou erros de conexão

### Use try-finally:

Cursor cursor = null;

try {
    cursor = banco.rawQuery(...);
    // leitura dos dados
} finally {
    if (cursor != null) cursor.close();
}

# Exemplo Prático Completo

public List<Cliente> listarTodos() {
    List<Cliente> lista = new ArrayList<>();
    Cursor cursor = null;

    try {
        cursor = banco.rawQuery("SELECT * FROM cliente", null);

        if (cursor.moveToFirst()) {
            int idxId = cursor.getColumnIndex("idcliente");
            int idxNome = cursor.getColumnIndex("nome");
            int idxTelefone = cursor.getColumnIndex("telefone");
            int idxEmail = cursor.getColumnIndex("email");
            int idxSenha = cursor.getColumnIndex("senha");

            do {
                Cliente c = new Cliente(
                    cursor.getInt(idxId),
                    cursor.getString(idxNome),
                    cursor.getString(idxTelefone),
                    cursor.getString(idxEmail),
                    cursor.getString(idxSenha)
                );
                lista.add(c);
            } while (cursor.moveToNext());
        }

    } finally {
        if (cursor != null) cursor.close();
    }

    return lista;
}
