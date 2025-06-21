// ESSA PASTA COM ESSE ARQUIVO É RESPONSÁVEL POR CRIAR O BANCO DE DADOS


package Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.db; // caminho do nosso pacote onde o nosso banco de dados vai está

// Realizando import da biblioteca nativa do sqlite

import android.content.Context; // Traz uma classe base do Android usada para acessar quase tudo no sistema: arquivos, banco, UI, etc.
import android.database.sqlite.SQLiteOpenHelper; // facilita o gerenciamento do banco de dados
import android.database.sqlite.SQLiteDatabase; // Representa o banco de dados em si, permite executar SQL
import android.database.SQLException;   // Permite tratar exceções específicas de banco de dados
import android.util.Log; // "Log" com L maiúsculo — classe para registrar logs no Logcat


// Vamos criar uma classe que herda, vira uma classe filha de SQLiteOpenHelper
public class Database extends SQLiteOpenHelper{
    // Declarando variáveis staticas que não podem ser alteradas (static + final)

    private static final String DB_NAME = "petagenda.sqlite";
    private static final int DB_VERSION = 1; 

    /*
        private é um encapsulador serve para modificar acesso que define quem pode
        acessar um atributo, método ou classe interna. ou seja  somente a própria classe pode acessar.

        Para acessar em outra classe é necessário usar o método Get e Set
    */


    // Declarando uma instância para escrita e leitura para ser intanciada automaticamente no construtor
    // SQLiteDatabase db -> Será feito no Main Activy;

    // declarando variáveis staticas que não podem ser alteradas e que vão comportar nossas tabelas
    // iremos colocar essas variáveis com um objeto String no método db.execSQL(variável_tabela) dentro de onCreate;

    private static final String tableCliente = "CREATE TABLE cliente ( "
        + "idcliente INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "telefone TEXT NOT NULL, " // O + serve para concatenar strings
        + "email TEXT NOT NULL, "
        + "status BOOLEAN DEFAULT 1);";

    private static final String tableAdmin = "CREATE TABLE admin ( "
        + "idadmin INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "telefone TEXT NOT NULL, "
        + "email TEXT NOT NULL, "
        + "senha TEXT NOT NULL,"
        + "status BOOLEAN DEFAULT 1);";

    private static final String tableSoliAgenda = "CREATE TABLE soliagenda ("
        + "idsoliagenda INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "idcliente INTEGER NOT NULL, "
        + "dtsoli TEXT DEFAULT CURRENT_TIMESTAMP, "
        + "idadmin INTEGER, "
        + "confirmsoli BOOLEAN, " // lembrando que no sqlite é armazenado como 0 ou 1 
        + "descricao TEXT, "
        + "FOREIGN KEY (idadmin) REFERENCES admin(idadmin), "
        + "FOREIGN KEY (idcliente) REFERENCES cliente(idcliente)"
        + ");";

    private static final String tableAgenda = "CREATE TABLE agenda ("
        +"idagenda INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"idsoliagenda INTEGER, "
        +"diasemana TEXT, " // Fazer validação de dados no front-end - aceitar somente: Segunda, Terça, Quarta, Quinta, Sexta
        +"dthagenda TEXT DEFAULT CURRENT_TIMESTAMP, "
        +"FOREIGN KEY (idsoliagenda) REFERENCES soliagenda(idsoliagenda));";
    
    /* 
        Declarando construtuor - basicamente definimos os atributos que entram como parâmetro
        E tudo que ficar definido e instanciado nele, quando a classe passar ser objeto passa 
        a ser materializado automaticamente aquela propriedade
    */ 

    // O que importa é o Context
    public Database(Context context){
        super(context,DB_NAME,null,DB_VESION); 

        /* 
        
            Lembre-se: A palavra-chave super é usada dentro de uma classe filha para se referir
            à classe pai (aquela que foi estendida com extends).
            
            neste caso pegamos os atributos da classe pai que:
            acessa o nosso banco de dados: context
            pega o nome do banco de dados: DB_NAME
            pega a versão do banco de dados: DB_VERSION

        */
    
        /* db = getWritableDatabase(); Será feito no Main Activy*/
    
    };


    // Utilizando Método que executa 
    @Override
    public void onCreate(SQLiteDatabase db){

        try{
            
            // materilizando o objeto String dentro de db.execSQL para executar nossa tabela
            db.execSQL(tableCliente);
            db.execSQL(tableAdmin);
            db.execSQL(tableSoliAgenda);
            db.execSQL(tableAgenda);
        
        } catch (java.sql.SQLException e) {
            System.out.println("Aconteceu alguma cagada aqui: " + e);
            Log.e("DB_LOG", "Erro na criação do banco: " + e.getMessage());
        }


        // Utilizamos try catch para tratamento de erros


    } 

    /*
     
        @Override é uma anotação usada para dizer ao compilador que você está 
        sobrescrevendo (redefinindo) um método que vem de uma classe pai ou de 
        uma interface.
     
    */  

    // Criando método onUpgrade par aquando o banco de dados mudar de estrutura
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Exemplo simples: descarta tabelas e recria
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS admin");
        db.execSQL("DROP TABLE IF EXISTS soliagenda");
        db.execSQL("DROP TABLE IF EXISTS agenda");
        onCreate(db);

    /*
        
        Isso vai sobrescrever o banco de dados, caso a versão dele mude
        por exemplo: Houve alteração na versão do Banco de dados
    
    */



}







};
