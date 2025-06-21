/* 
    camada DAO (Data Access Object)
    
    Aqui vamos ter Aplicações Responsáveis pelo CRUD do Banco de dados 
    Basicamente, iremos resceber  dados do MODEL. 
    
    Ele atua como um tradutor entre model e banco de dados
    que tem como papel intermediar o acesso ao banco de dados, 
    isolando a lógica de persistência da lógica de apresentação ou de negócio.
    
*/
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.model.Cliente;


// 📥 Importações essenciais para banco de dados

import android.content.ContentValues; 
/* 
    Usado para mapear os dados de um objeto Java para uma estrutura de chave-valor
    que pode ser inserida no banco
*/


import android.database.Cursor;
/* 
	Permite iterar sobre os resultados de uma consulta SQL (SELECT).
*/

import android.database.sqlite.SQLiteDatabase;
//	Classe principal para executar comandos SQL (insert, update, query, etc).

// Importa a classe Cliente do model
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.model.Cliente;
//	Representa o objeto que será persistido no banco de dados (model).

// Importando Lista para materializar um Objeto de ArrayList
import java.util.ArrayList;
import java.util.List;


public class ClienteDao {

    // atributo que espera um objeto do tipo SQLiteDatabase para realizar operações de banco
    private final SQLiteDatabase banco;

    /* 
        Criando construtor da classe ClienteDao
        Esse construtor recebe o objeto SQLiteDatabase já aberto
        que será utilizado para executar os comandos SQL.
    */ 

    public ClienteDao(SQLiteDatabase banco) {
        this.banco = banco;
    };

    // CRIANDO métodos como inserir(), listar(), atualizar(), deletar(), etc.


    public List <Cliente> listarTodos(){

        // Materializando um ArrayList pela interface List onde o tipo de dados  
        // é uma instância do cliente da camada Modal
        List <Cliente> lista = new ArrayList<>();   
        
        // Materializando uma instância do Cursor
        Cursor cursor = null;

        try {

            // Selecionando todos os clientes
            cursor = banco.rawQuery("SELECT * FROM cliente WHERE status = 1", null);

            // verifica se existe um primeiro resultados pelo index da coluna
            if(cursor.moveToFirst()){
                
                int idxId = cursor.getColumnIndex("idcliente"); // idcliente → INTEGER é o tipo da coluna
                int idxNome = cursor.getColumnIndex("nome"); // nome → TEXT 
                int idxTelefone = cursor.getColumnIndex("telefone"); // telefone → TEXT
                int idxEmail = cursor.getColumnIndex("email"); // email -> TEXT
                int idxSenha = cursor.getColumnIndex("senha"); // senha -> TEXT
                int idxStatus = cursor.getColumnIndex("status"); // senha -> Boolean

                // apos a verificação itera, percorre por todas as alinhas retornadas
                do{  
                   // fazendo valisação para listar clientes ativos
                    if (cursor.getInt(idxStatus) == 1){
                        // instanciando um objeto Cliente do Model Cliente
                        Cliente clienteListado = new Cliente(
                            cursor.getInt(idxId), // Aqui vamos pegar o atributo com seu respectivo tipo de dado
                            cursor.getString(idxNome),
                            cursor.getString(idxTelefone),
                            cursor.getString(idxEmail),
                            cursor.getString(idxSenha),
                            true
                        );
                        
                        /*
                        Resumindo o que foi feito na instanciação do objeto Cliente:
                        resumindo pegamos o atributo que está no index  
                        daquela coluna com seus respectivos tipo de dado

                        String nome = cursor.getString(1); // ou cursor.getString(idxNome);

                        Pegue o valor na coluna 1 (nome), interprete como String e me devolva.

                        */

                        // Adicionando um elemento no fim da lista
                        lista.add(clienteListado);
                        /*
                            Após instanciar um Cliente vamos passar 
                            o objeto para dentro de um array list
                            após iremos para a próxima linha com o 
                            método moveToNext();
                        */
                    }
                }while (cursor.moveToNext());

                /* 
                    Essa linha faz parte da estrutura de repetição do...while, que serve 
                    para percorrer linha por linha do resultado retornado pela 
                    consulta SQL (SELECT * FROM cliente), representado pelo Cursor.

                    Esse é um método do Cursor que move o “ponteiro” do cursor para a 
                    próxima linha da tabela retornada. Ele retorna true se ainda houver 
                    mais linhas a serem lidas, e false quando chega ao fim.
                        
                */
            };

        } catch (Exception e) {
            System.out.println("Error: " + e );
        }
        finally {
            if (cursor != null) cursor.close();
        }

        return lista; // retornando o nosso ARRAYLIST com todos os clientes Listados

    }

    /* 
        O método listar cliente vai retornar um objeto List com ArrayList 
        representando todos os clientes selecionados
    */


    public long inserirCliente(Cliente cliente){
        try{
            // instanciando um objeto de ContentValues dentro do nosso método para
            // mapear os dados de um objeto Java para uma estrutura de chave-valor

            ContentValues values = new ContentValues();
            
            values.put("nome", cliente.getNome());
            values.put("telefone", cliente.getTelefone());
            values.put("email", cliente.getEmail());
            values.put("senha", cliente.getSenha());
        
            return banco.insert("cliente", null, values);
        
        }catch(Exception e){
            System.out.println("Error: "+ e);
            return -1;
        };
    };

    /* 
        
        O método insert() da classe SQLiteDatabase retorna o ID da nova linha inserida,
        ou seja, o valor da chave primária autoincrementada (idcliente).

        O tipo long é usado porque o ID pode ser um número grande 
        (int pode não ser suficiente).
        
        Se você inseriu um novo cliente e o retorno for 5L, 
        isso quer dizer que esse cliente tem o idcliente = 5.      
    */

    public int atualizarCliente(Cliente cliente){
        try{
            
            ContentValues values = new ContentValues();
            
            values.put("nome",cliente.getNome());
            values.put("telefone",cliente.getTelefone());
            values.put("email",cliente.getEmail());
            values.put("senha",cliente.getSenha());

            /*  Craindo cláusula **WHERE** para selecionar **qual registro atualizar**. 
                O `?` é um placeholder (evita SQL Injection). */ 
            
            String whereClause = "idcliente = ?";

            String[] whereArgs = { String.valueOf(cliente.getID())};

            /* `whereArgs`: é o array com os valores que vão substituir os `?`. 
            Neste caso, usamos o ID do cliente. */

            /*
                As chaves {} são usadas para criar e inicializar arrays em Java.
                
                String.valueOF(...);

                Esse método estático da classe String converte qualquer tipo de dado
                em String. É muito usado para garantir que você esteja passando um String,
                mesmo que o valor original seja int, float, boolean
            */

            // Retornar o Clinete Atualizado
            return banco.update("cliente",values,whereClause,whereArgs);
            /* 
                Lembre-se: arg1 = tabela, arg2 = valores dos atributos de cada coluna,
                arg3 = Clasuala WHERE, arg4 = parâmetros que vão para o WHERE,
                arg5 = Outras Clasulas na ordem: GROUP BY, HAVING, ORDER BY
            */
            
        }catch(Exception e){
            System.out.println("Error: "+ e);
            return -1;
        }
    };    


        /* 
        O método update() retorna a quantidade de linhas afetadas pela atualização. 
        Por isso o tipo de retorno é int.
        
        Exemplo:

            Se você tentou atualizar o cliente de ID 3 e ele existe, o retorno será 1.
            Se o cliente não for encontrado, o retorno será 0.

            Se for outro atributo seria 2 ou + indicando a quantidade de linhas atualizadas

        O MESMO VAI VALER PARA O DELETE!
    */
    
    public int atualizarNome(Cliente cliente){
        try {
           
            ContentValues values = new ContentValues(); 

            values.put("nome",cliente.getNome());
            
            String whereClause = "idcliente = ?";

            String[] whereArgs = {String.valueOf(cliente.getID())};

           return banco.update("cliente",values,whereClause,whereArgs);
        
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return -1;
        };
    };

    public int atualizarEmail(Cliente cliente){
        try{
            ContentValues values = new ContentValues();

            values.put("email",cliente.getEmail());

            String whereClause = "idcliente = ?";

            String[] whereArgs = {String.valueOf(cliente.getID())};
            
            return banco.update("cliente",values,whereClause,whereArgs);
        } catch(Exception e ){
            System.out.println("Error: " + e);
            return -1;          
        }
    }

    public int atualizarSenha(Cliente cliente){

        try {
            
            ContentValues values = new ContentValues();

            values.put("senha", cliente.getSenha());

            String whereClause = "idcliente = ?";

            String[] whereArgs = {String.valueOf(cliente.getID())};

            return banco.update("cliente",values,whereClause,whereArgs);
        
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return -1;
        }

    }

    public int atualizarTelefone(Cliente cliente){
        try{

            ContentValues values = new ContentValues();

            values.put("telefone",cliente.getTelefone());

            String whereClause = "idcliente = ?";
            
            String[] whereArgs = { String.valueOf(cliente.getID())};
            
            // não esquecer de retornar o cliente atualizado
            return banco.update("cliente",values,whereClause,whereArgs);
        
        }catch(Exception e){
            System.out.println("Error: " + e);
            return -1;
        };
    };

    public int deleteLogico(Cliente cliente){
        try {
          
            ContentValues values = new ContentValues();

            values.put("status", cliente.getStatus() ? 1 : 0);

            /*
                ? 1 : 0
                
                Essa é uma expressão condicional (também chamada de operador ternário),
                que funciona assim:

                    condição ? valorSeVerdadeiro : valorSeFalso;
                
                No nosso caso: 
                
                cliente.getStatus() → deve ser um método que retorna boolean, 
                como true (ativo) ou false (inativo).

                Se for true, o valor será 1.

                Se for false, o valor será 0.
            
            */

            String whereClause = "idcliente = ?";

            String[] whereArgs = {String.valueOf(cliente.getID())};

            return banco.update("cliente",values,whereClause,whereArgs);

        } catch (Exception e) {
            System.out.println("Erro: "+ e);
            return -1;
        };
    }


    // método deletar do banco de dados
    public int deleteForcado(Cliente cliente){
        try {
            
            String whereClause = "idcliente = ?"; 
            
            String[] whereArgs = {String.valueOf(cliente.getID())};

           return banco.delete("cliente", whereClause, whereArgs);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return -1;
        };
    };
};

/*
 
    OBSERVAÇÕES IMPORTANTES
 
 
    Princípios aplicados

        Princípio: SRP (Single Responsibility)	

        Aplicação em ClienteDao: A classe tem única responsabilidade: 
        persistir dados de cliente.

        Princípio: Inversão de Dependência
    
        Aplicação em ClienteDao: O DAO recebe o banco por parâmetro, não o instancia 
        internamente.
    
        Princípio: Alta Coesão / Baixo Acoplamento	
        Aplicação em ClienteDao: O DAO depende apenas da SQLiteDatabase, sem conhecer
        o restante da infraestrutura.


    DISCUSSÃO:

        Inicialmente, havia a dúvida:

            “Devo instanciar o banco dentro do DAO ou fora?”

        ❌ Errado:
            
            Instanciar dentro do DAO cada vez que for usado.


        // exemplo problemático

            Database db = new Database(context);
            SQLiteDatabase banco = db.getWritableDatabase();

        Problemas:

            Reabre o banco a cada operação (desempenho).

            Quebra o princípio de responsabilidade única.

            Dificulta testes e manutenção.


        ✅ Correto:
            Instanciar o banco na Activity e passar ao DAO.

        Exemplo:

            Database databaseHelper = new Database(this);
            SQLiteDatabase banco = databaseHelper.getWritableDatabase();
            ClienteDao clienteDao = new ClienteDao(banco);
            
*/