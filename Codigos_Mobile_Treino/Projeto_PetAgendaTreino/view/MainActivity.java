/* AQUI É ONDE VAI FICAR A NOSSA APLICAÇÃO PRINCIPAL    ! */


package Codigos_Mobile_Treino.Projeto_PetAgendaTreino.view;

// Imports necessários do Android
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.R; // Importa os recursos XML (layouts, strings, etc.)
import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.db.Database; // Importa sua classe Database

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Materializando a nossa classe Database que irá gerar automaticamente um banco de dados!
        Database database = new Database(this); // ✅ lembre-se: precisa passar o Context, nosso contexto
        // o this é para usarmos exatamente o contexto que está ativo agora!
    }
}

