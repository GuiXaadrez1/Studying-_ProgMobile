
package db; // pacote model
// importando nossas libs para conexão com o banco de dados

import android.content.ContentValues; // Classe utilizada para armazenar pares chave/valor. Muito comum para inserir ou atualizar dados em um banco de dados SQLite.

import android.content.Context; //  Classe central do Android que fornece acesso a recursos do sistema (arquivos, banco de dados, preferences, etc.).

import android.database.Cursor; // Interface que permite navegar pelos resultados de uma consulta SQL no SQLite (como um iterador).
/*
    Importa todas as classes do pacote android.database.sqlite, incluindo:

    SQLiteDatabase: Interface principal para acesso ao banco de dados.

    SQLiteOpenHelper: Classe utilitária que ajuda na criação e atualização da base.

    SQLiteException, SQLiteQueryBuilder, etc.
*/
import android.database.sqlite.*; 

// Criando classe publica, essa classe esta herdando todos os atributos de SqliteOpenHelper
public class DataBase extends SQLiteOpenHelper{

    private static final  String mydb = "petagenda"; // nome do banco 
    private static final int version_db = 1;


        // Query string para criar a tabela Cliente
    private static final String tableCliente = "CREATE TABLE cliente ("
        + "idcliente INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "telefone TEXT NOT NULL, " // o  + serve para concatenar strings
        + "email TEXT NOT NULL, "
        + "senha TEXT NOT NULL);";

    // Query string para criar a tabela Admin
    private static final String tableAdmin = "CREATE TABLE admin ("
        + "idadmin INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "telefone TEXT NOT NULL, "
        + "senha TEXT NOT NULL);";

    // Query string para criar a tabela soliagenda
    private static final String tableSoliAgenda = "CREATE TABLE soliagenda ("
        + "idsoliagenda INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "idcliente INTEGER NOT NULL, "
        + "dtsoli TEXT DEFAULT CURRENT_TIMESTAMP, "
        + "idadmin INTEGER, "
        + "confirmsoli BOOLEAN, "
        + "descricao TEXT, "
        + "FOREIGN KEY (idadmin) REFERENCES admin(idadmin), "
    + "FOREIGN KEY (idcliente) REFERENCES cliente(idcliente));";

    // Query string para criar a tabela agenda
    private static final String tableAgenda = "CREATE TABLE agenda ("
        + "idagenda INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "idsoliagenda INTEGER, "
        + "diasemana TEXT, "
        + "dthagenda TEXT DEFAULT CURRENT_TIMESTAMP, "
        + "FOREIGN KEY(idsoliagenda) REFERENCES soliagenda(idsoliagenda));";

 
    /*
        private: O atributo só pode ser acessado dentro da própria classe. Encapsula o dado, evitando acesso externo direto.

        static: Pertence à classe e não ao objeto (instância). Pode ser acessado sem criar um objeto da classe.

        final: Torna o valor imutável após a atribuição inicial. Muito usado para constantes (ex: configurações fixas, chaves de banco, nomes de tabelas).

    */

    /* Criando um construtor */

    public DataBase(Context context){
        super(context, mydb,null,version_db);
    }

    /*
     * Construtor da classe DatabaseHelper. Ele recebe um parâmetro context, que é a referência ao contexto da aplicação
     *  ou da activity que está usando o banco. É necessário para acessar recursos do sistema, inclusive para abrir/criar o banco.
     */
    

    
    // MÉTODO CHAMADO AUTOMATICAMENTE QUANDO O BANCO NÃO EXISTE CRIANDO TODAS AS TABELAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableCliente);
        db.execSQL(tableAdmin);
        db.execSQL(tableSoliAgenda);
        db.execSQL(tableAgenda);
    }

    // MÉTODO APAGANDO E RECRIANDO TODAS AS TABELAS CASO A VERSÃO DO BANCO DE DADOS SEJA UMA NOVA VERSÃO
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS admin");
        db.execSQL("DROP TABLE IF EXISTS soliagenda");
        db.execSQL("DROP TABLE IF EXISTS agenda");
        onCreate(db);
    }

}