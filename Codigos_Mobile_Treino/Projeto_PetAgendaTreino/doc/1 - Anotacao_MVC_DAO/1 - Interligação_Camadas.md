# 📚 RECAPITULAÇÃO COMPLETA — ARQUITETURA ANDROID COM SQLite

## 🧱 Capítulo 1 — A Estrutura em Camadas do Projeto

Sua aplicação está dividida em camadas, o que é uma excelente prática de arquitetura. As camadas representam as responsabilidades separadas no projeto:

- **Camada Infra (infraestrutura):**

  - Responsável por configurar, criar e atualizar o banco de dados SQLite.

  - Aqui você tem a classe `Database` que herda de `SQLiteOpenHelper`.

- **Camada Model:**

  - Representa as entidades (como `Cliente`) com atributos e métodos getters/setters.

  - É uma camada apenas de dados (sem lógica de negócio).

- **Camada DAO (Data Access Object):**

  - Traduz operações de alto nível em comandos SQL sobre os modelos.

  - Interage com o banco de dados usando o objeto `SQLiteDatabase`.

- **Camada View (interface):**

  - Onde ficam as Activities e Fragments.

  - Responsável por receber ações do usuário e coordenar o uso do banco e dos dados.


## 🛠️ Capítulo 2 — A Classe Database (Infraestrutura)

A classe `Database` é o coração da infraestrutura. Ela herda de `SQLiteOpenHelper`, que já traz muita coisa pronta para facilitar o uso de SQLite no Android.

### Funções principais:

- Criação do banco: ocorre no método `onCreate(SQLiteDatabase db)` usando SQL.

- Atualização do banco: ocorre no método `onUpgrade(...)` quando a versão (`DB_VERSION`) é alterada.

- Gerenciamento de tabelas: suas queries de criação de tabelas (`CREATE TABLE`) estão centralizadas aqui.

- Uso de try-catch com logs: permite capturar e identificar erros de SQL.

### Observação:

Você tinha feito uma chamada para `getWritableDatabase()` dentro do construtor, o que funciona, mas é melhor abrir o banco fora da classe para que o controle da infraestrutura fique com a `Activity` (alta coesão, baixo acoplamento).

## 🔄 Capítulo 3 — O Papel do SQLiteDatabase
O SQLiteDatabase é a classe que representa o banco de dados em tempo de execução.

### Como ele é obtido?

Chamando: SQLiteDatabase banco = databaseHelper.getWritableDatabase();

### O que esse método faz?

- Cria fisicamente o banco de dados, se for a primeira vez.

- Verifica a versão e chama onCreate() ou onUpgrade(), se necessário.

- Retorna um objeto SQLiteDatabase que você usa para executar comandos SQL (insert, query, update, delete).

- Esse objeto deve ser passado para os DAOs, para que eles possam realizar operações no banco de maneira eficiente e controlada.

## 💼 Capítulo 4 — O DAO: ClienteDao
Você criou a classe ClienteDao, que representa um DAO especializado para a tabela cliente.

### Papel do DAO:

- Realiza operações de CRUD.

- Traduz objetos Java (Cliente) em comandos SQL.

- Separa regras de persistência da lógica da interface e das regras de negócio.

### Boas práticas adotadas:

- DAO não instancia o banco internamente.

- DAO recebe a instância de SQLiteDatabase pronta, centralizando o controle da conexão na Activity.

- Evita múltiplas aberturas do banco (otimização).

### Exemplo de construtor do DAO:

public class ClienteDao {
    private final SQLiteDatabase banco;

    public ClienteDao(SQLiteDatabase banco) {
        this.banco = banco;
    }
}

### Exemplo de método inserir():

public long inserir(Cliente cliente) {
    ContentValues values = new ContentValues();
    values.put("nome", cliente.getNome());
    values.put("telefone", cliente.getTelefone());
    values.put("email", cliente.getEmail());
    values.put("senha", cliente.getSenha());
    return banco.insert("cliente", null, values);
}

## 🧩 Capítulo 5 — O Papel da MainActivity
A MainActivity é onde você materializa o banco de dados pela primeira vez e passa a instância para os DAOs.

### Responsabilidades:

- Criar a instância do Database.

- Obter o SQLiteDatabase chamando getWritableDatabase().

- Criar instâncias dos DAOs passando esse banco.

- Acionar métodos como inserir, listar, etc.

### Exemplo real:

Database databaseHelper = new Database(this);
SQLiteDatabase banco = databaseHelper.getWritableDatabase();

ClienteDao clienteDao = new ClienteDao(banco);

Cliente novoCliente = new Cliente(0, "João", "61999999999", "joao@email.com", "1234");
clienteDao.inserir(novoCliente);

### Por que isso é importante?
Porque centraliza a abertura do banco, evita bugs com múltiplas conexões e torna o app mais eficiente e robusto.

## 💡 Capítulo 6 — Princípios Arquiteturais Aplicados
Você está aplicando diversos princípios arquiteturais de forma prática:

Princípio	Aplicação prática no seu projeto

SRP (Single Responsibility)	Cada classe tem uma única responsabilidade (infra, DAO, modelo, view)

DRY (Don't Repeat Yourself)	Uma única instância de banco é criada e reutilizada
Inversão de Dependência	O DAO depende de um banco externo, e não o cria por conta própria
Alta Coesão, Baixo Acoplamento	Classes se comunicam por contrato (parâmetros), não se conhecem internamente
Encapsulamento	O SQLiteDatabase é encapsulado nos DAOs

## 📌 Capítulo 7 — Sua Grande Sacada
Você corretamente concluiu que:

❝ Não faz sentido instanciar o banco dentro do DAO, pois a cada serviço eu teria que reabrir o banco. O ideal é instanciar uma vez no MainActivity e reaproveitar. ❞

### ✔️ Essa é a abordagem profissional de reaproveitamento de recurso (singleton por contexto), e ela te dá:

- Menos consumo de memória

- Abertura controlada de conexão

- Facilidade para gerenciar transações

- Flexibilidade para testes futuros
