
/* 

    Aqui neste pacote é onde vamos colocar nossas classes que representam 
    as nossas entidades do banco de dados, aqui é onde vamos captar os dados
    e enviar para ClienteDao.

    Boa Práticas:
    
        Sem lógica

        Apenas atributos + métodos de acesso

*/

package Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.model;

public class Cliente {

    // criando atributos, que representem a nossa tabela cliente
    
    private int idcliente;
    private String nome;  
    private String telefone;
    private String email;
    private String senha;
    private Boolean status; // deixando status opcional para ser usado no delete lógico
    
    /* 
      
        Lembre-se: primeiro colocamos o encapsulamento, tipo de dado    
        depois o nome da variável; 
    */
    
    // criando o nosso construtor

    // Construtor com status default = true
    public Cliente(int idcliente, String nome, String telefone, String email, String senha) {
        this(idcliente, nome, telefone, email, senha, true); // chama o construtor completo
    }

    // Construtor completo (controle manual do status)
    public Cliente(int idcliente, String nome, String telefone, String email, String senha, Boolean status) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.status = status;
    }

    // CRIANDO OS NOSSOS MÉTODOS GETTER E SETTERS

    // GETTERS     

    public int getID(){
        return idcliente;
    }

    public String getNome(){
        return nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }

    public Boolean getStatus(){
        return status;
    }

    // SETTERS
    public void setId(int idcliente){
        this.idcliente = idcliente;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

};
