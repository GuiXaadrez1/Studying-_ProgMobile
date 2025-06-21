// Aqui é a camada de lógica do négocio, da programação lógica
package Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.controller;


// Realizando os imports

import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.dao.ClienteDao;
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.model.Cliente;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;

public class ClienteController {

    // instanciando o nosso cliente dao
    private final ClienteDao clienteDao;

    // Construtor do Controller: recebe o SQLiteDatabase já aberto e instancia o DAO
    public ClienteController(SQLiteDatabase banco) {
        this.clienteDao = new ClienteDao(banco);
    }

    // Método de cadastro de novo cliente com validação simples
    public long cadastrarCliente(String nome, String telefone, String email, String senha) {
        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            return -1; // erro: campos obrigatórios vazios
        }

        // instanciando um objeto Cliente para inserir no nosso ClienteDAO
        Cliente novoCliente = new Cliente(0, nome, telefone, email, senha); // status = true por padrão
        return clienteDao.inserirCliente(novoCliente);
    }

    // Método para listar todos os clientes ativos
    public List<Cliente> listarClientes() {
        return clienteDao.listarTodos();
    }

    // Método para deletar logicamente (status = false)
    public int desativarCliente(int idcliente) {
        Cliente cliente = new Cliente(idcliente, "", "", "", "", false);
        return clienteDao.deleteLogico(cliente);
    }

    // Método para atualização completa dos dados do cliente
    public int atualizarCliente(int id, String nome, String telefone, String email, String senha) {
        Cliente cliente = new Cliente(id, nome, telefone, email, senha, true);
        return clienteDao.atualizarCliente(cliente);
    }

    // Métodos específicos de atualização (nome, telefone, etc.)

    public int atualizarNome(int idcliente, String novoNome) {
        Cliente cliente = new Cliente(idcliente, novoNome, "", "", "", true);
        return clienteDao.atualizarNome(cliente);
    }

    public int atualizarEmail(int idcliente, String novoEmail) {
        Cliente cliente = new Cliente(idcliente, "", "", novoEmail, "", true);
        return clienteDao.atualizarEmail(cliente);
    }

    public int atualizarTelefone(int idcliente, String novoTelefone) {
        Cliente cliente = new Cliente(idcliente, "", novoTelefone, "", "", true);
        return clienteDao.atualizarTelefone(cliente);
    }

    public int atualizarSenha(int idcliente, String novaSenha) {
        Cliente cliente = new Cliente(idcliente, "", "", "", novaSenha, true);
        return clienteDao.atualizarSenha(cliente);
    }

    // Deleção forçada (DELETE físico)
    public int deletarClientePermanentemente(int idcliente) {
        Cliente cliente = new Cliente(idcliente, "", "", "", "", true);
        return clienteDao.deleteForcado(cliente);
    }
}
