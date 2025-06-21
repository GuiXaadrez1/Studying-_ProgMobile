# Introdução ao Cursor do  android.content.ContentValues
A classe ContentValues funciona como um dicionário ou mapa de chave-valor onde:
    - As chaves são os nomes das colunas do banco de dados.
    - Os valores são os dados que você quer inserir ou atualizar nessas colunas.

Essa classe é usada principalmente com os métodos:

    - insert()
    - update()

##  Exemplo básico

ContentValues valores = new ContentValues();
valores.put("nome", "João");
valores.put("email", "joao@email.com");

Aqui, você está criando um "pacote" com os seguintes dados:

    Coluna	Valor

    nome	"João"
    email	"joao@email.com"

Depois, esse pacote é enviado para o banco.

##  Onde é usado?

    db.insert("cliente", null, valores);

cliente → nome da tabela.

null → usado se valores estiver vazio (não é o nosso caso).

valores → os dados que serão inseridos nas colunas da tabela.

## Atualização de dados

db.update("cliente", valores, "idcliente = ?", new String[]{"1"});

- Atualiza o cliente cujo ID é 1, com os dados contidos em valores.

## Métodos principais de ContentValues

| Método                    | Descrição                                      |
|---------------------------|-----------------------------------------------|
| put(String key, String)   | Adiciona uma string.                           |
| put(String key, int)      | Adiciona um inteiro.                           |
| put(String key, boolean)  | Adiciona um booleano (convertido internamente).|
| put(String key, double)   | Adiciona um double.                            |
| put(String key, long)     | Adiciona um long.                              |
| putNull(String key)       | Define valor nulo para uma coluna.             |
| clear()                   | Remove todos os dados armazenados.             |
| get(String key)           | Retorna o valor da chave.                      |
| containsKey(String key)   | Verifica se a chave existe.                    |


## Dica: Tipagem automática

O método put tem sobrecargas (overloads) para aceitar diferentes tipos de dados, e o Android trata as conversões automaticamente para os tipos esperados pelo SQLite:

    valores.put("ativo", true);    // armazenado como 1
    valores.put("idade", 30);      // armazenado como INTEGER   
    valores.put("salario", 2500.5);// armazenado como REAL


## Internamente, o que é?
public final class ContentValues implements Parcelable {
    private HashMap<String, Object> mValues;
}

Ou seja, por dentro ele funciona como um HashMap<String, Object> — um mapa genérico para armazenar os pares coluna/valor.

## Boas práticas com ContentValue

    Sempre use put() com os tipos corretos (evita erros de tipo no SQLite).
    Nunca reutilize o mesmo objeto ContentValues para múltiplas inserções sem limpar antes (clear()).
    Se algum campo puder ser nulo, use putNull("campo") explicitamente.

##  Exemplo real na prática

public long inserirCliente(Cliente cliente) {
    ContentValues valores = new ContentValues();
    valores.put("nome", cliente.getNome());
    valores.put("telefone", cliente.getTelefone());
    valores.put("email", cliente.getEmail());
    valores.put("senha", cliente.getSenha());

    return banco.insert("cliente", null, valores);
}

- Esse método faz o seguinte:

Cria um ContentValues.
Preenche com dados do cliente.
Insere no banco.
Retorna o ID do novo registro.