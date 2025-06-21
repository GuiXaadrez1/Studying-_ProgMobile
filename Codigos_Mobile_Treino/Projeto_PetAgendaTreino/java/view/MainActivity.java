/* AQUI É ONDE VAI FICAR A NOSSA APLICAÇÃO PRINCIPAL    ! */

package Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.view;

// Imports necessários do Android
import android.os.Bundle;

// componentes do nosso XML
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Importa os recursos XML (layouts, strings, etc.), bsicamente para pucharmos informações do nosso xml
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.R;
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.controller.ClienteController;
// Importando a nossa classe Database
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.java.db.Database; 

// Importando a classe para instanciar o SQLiteDatabase
import android.database.sqlite.SQLiteDatabase;


public class MainActivity extends AppCompatActivity {

    // instancinado a nossa Classe controller para se comunicar com a camada: Model e DAO
    private ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout); // layout com formulário

        // Materializando a nossa classe Database que irá gerar automaticamente um banco de dados!
        // ✅ lembre-se: precisa passar o Context, nosso contexto
        // o this é para usarmos exatamente o contexto que está ativo agora!
        Database databaseHelper = new Database(this); 

        // método herdado do SQLiteOpenHelper 
        SQLiteDatabase banco = databaseHelper.getWritableDatabase();

        /*
        O que essa linha faz:

        ✅ Abre (ou cria) o banco de dados físico "petagenda.sqlite"
        ✅ Se for a primeira vez, chama o método onCreate()
        ✅ Se a versão do banco mudou, chama onUpgrade()
        ✅ Retorna um objeto SQLiteDatabase, pronto para:
            - Inserir (insert)
            - Consultar (query)
            - Atualizar (update)
         - Deletar (delete)
        ✅ Centraliza o controle da infraestrutura aqui na Activity, evitando abrir múltiplas conexões no app
        */
        
        // Objeto clientecontroller inicializado, materializado
        controller = new ClienteController(banco);
        
        // Capturanco Elementos da Tela

        inputNome = findViewById(R.id.editTextNome);
        inputTelefone = findViewById(R.id.editTextTelefone);
        inputEmail = findViewById(R.id.editTextEmail);
        inputSenha = findViewById(R.id.editTextSenha);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);

        

        // Linester (Capturador de eventos do botão) do botão cadastrar

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = inputNome.getText().toString();
                String telefone = inputTelefone.getText().toString();
                String email = inputEmail.getText().toString();
                String senha = inputSenha.getText().toString();

                long resultado = controller.cadastrarCliente(nome, telefone, email, senha);

                if (resultado != -1) {
                    Toast.makeText(MainActivity.this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                } else {
                    Toast.makeText(MainActivity.this, "Erro: preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para limpar campos após cadastro
    private void limparCampos() {
        inputNome.setText("");
        inputTelefone.setText("");
        inputEmail.setText("");
        inputSenha.setText("");
    }

}


/* 
    ANOTAÇÃO IMPORTANTE!
  
    Não, você não precisa de uma Activity para cada DAO ou Model.
    O número de telas (Activity ou Fragment) depende das funcionalidades 
    visuais que você quer oferecer, e não da quantidade de modelos ou tabelas no banco.

    Como organizar de forma correta?
    Você organiza as telas com base em funções da interface, não nas classes de dados.

    Por exemplo, para Cliente, você pode ter:
        Tela / Activity	                    Objetivo
    MainActivity.java	        Tela inicial, menu ou dashboard
    ClienteFormActivity.java	Tela de cadastro ou edição de cliente
    ClienteListActivity.java	Tela que lista todos os clientes, usando RecyclerView
    ClienteDetailActivity.java	(opcional) Tela que mostra detalhes de um único cliente
    

    Arquitetura comum e escalável:

        ├── controller/
        │   └── ClienteController.java
        ├── dao/
        │   └── ClienteDao.java
        ├── db/
        │   └── Database.java
        ├── model/
        │   └── Cliente.java
        ├── view/
        │   ├── MainActivity.java
        │   ├── ClienteFormActivity.java
        │   └── ClienteListActivity.java

    Cada uma dessas telas pode utilizar o ClienteController para lidar com a lógica.

    Fluxo de uso no app:

        MainActivity → botão "Cadastrar Cliente" → abre ClienteFormActivity

        MainActivity → botão "Ver Clientes" → abre ClienteListActivity

        ClienteListActivity → clica num cliente → abre ClienteDetailActivity (opcional)

        Todas essas telas compartilham o mesmo Model, DAO e Controller — você reutiliza, 
        não duplica.


    Conclusão:
    
        Uma entidade (model/DAO) pode ser usada em várias telas.

        Você cria uma tela por funcionalidade, não por classe.

        Reutilize Controller e DAO em todas as Activity que precisarem.


*/