



📦 Declaração do pacote (package):
            
        package Codigos_Mobile_Treino.Projeto_PetAgendaTreino.view;

    Indica em que estrutura de pastas (ou módulo) esse arquivo Java está dentro do seu projeto.
    Geralmente, usamos view para telas (interfaces visuais).


📦 Importa a classe Bundle:

        // Imports necessários do Android
        import android.os.Bundle;

    Usada para passar informações entre telas ou para salvar o estado da Activity.
    Aqui, ela é usada como parâmetro do método onCreate().


📦 Importa AppCompatActivity:

    import androidx.appcompat.app.AppCompatActivity;

    Essa é a superclasse da sua MainActivity.
    Ela oferece suporte a recursos modernos (como toolbar, temas etc.) mesmo em versões antigas do Android.



📦 Importa a classe de recursos R:

        import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.R;


    Essa classe é gerada automaticamente pelo Android e contém todos os recursos 
    visuais do app (layouts, strings, imagens, etc.).
    
        Exemplo: R.layout.activity_main aponta para o XML da interface.




📦 Importa sua classe Database:

        import Codigos_Mobile_Treino.Projeto_PetAgendaTreino.db.Database;

    É a classe que você criou para gerenciar o banco de dados SQLite. Aqui, será usada para criar ou abrir o banco.



🟩 Declaração da classe principal:


        public class MainActivity extends AppCompatActivity {

    MainActivity é a Activity principal do seu app (a tela que aparece ao abrir o app).
    Ela herda (extends) da AppCompatActivity, o que permite usar vários recursos Android.



🔁 Anotação @Override:

        @Override
    
    Diz ao compilador que o método a seguir (onCreate) está sobrescrevendo 
    (reescrevendo) um método da superclasse AppCompatActivity.


    
🔧 Método onCreate():

        protected void onCreate(Bundle savedInstanceState) {

    Chamado automaticamente pelo Android quando a Activity é criada.
    O parâmetro savedInstanceState contém informações sobre o estado anterior da Activity, 
    caso ela tenha sido recriada (por exemplo, ao girar a tela).


📞 Chamada ao método da superclasse:

        super.onCreate(savedInstanceState);

    Garante que a AppCompatActivity faça a parte dela na criação da Activity
    (carregar tema, configurações básicas etc.).


🖼️ Define a interface da tela:

        setContentView(R.layout.activity_main);

    Diz ao Android: "Use esse arquivo XML (res/layout/activity_main.xml) como a interface dessa Activity."
    Ou seja, é aqui que você conecta o visual (layout XML) com o código Java.





📂 Cria (ou abre) o banco de dados:

        // o this é para usarmos exatamente o contexto que está ativo agora!
        Database database = new Database(this);

    Aqui você está instanciando sua classe Database, que herda de SQLiteOpenHelper. 
    Isso faz o banco ser criado (caso ainda não exista). O this é o Contexto atual, ou seja,
    a própria MainActivity — e é obrigatório passar para o SQLiteOpenHelper.

