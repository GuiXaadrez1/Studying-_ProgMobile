# Introdução Componentes Visuais no Android com XML

Os componentes visuais (ou Views) são os elementos gráficos que você vê na tela: textos, botões, campos de entrada, caixas de seleção e muito mais. A seguir, você verá cada um deles com descrições detalhadas, usos práticos e explicações linha por linha.

## 📄 TextView – Exibe um texto na tela

<TextView
    android:id="@+id/textoPrincipal"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Bem-vindo!"
    android:textSize="20sp"
    android:textColor="#000000"
    android:gravity="center"
    android:layout_marginTop="20dp" />

- O que faz cada linha?

android:id: identifica a view.

layout_width e layout_height: define dimensões.

text: conteúdo exibido.

textSize: tamanho do texto (em sp).

textColor: cor do texto.

gravity: alinha o texto dentro da caixa.

layout_marginTop: espaço acima do texto.

## 📝 EditText – Campo para digitar texto

<EditText
    android:id="@+id/nomeUsuario"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Digite seu nome"
    android:inputType="textPersonName"
    android:layout_margin="16dp" />

- Detalhes importantes:

hint: texto de dica (aparece quando está vazio).

inputType: tipo de entrada. Exemplos:

textEmailAddress, number, textPassword, phone.

## 🔘 Button – Botão clicável

<Button
    android:id="@+id/botaoEnviar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Enviar"
    android:layout_marginTop="12dp" />

- Importante: o comportamento do clique é programado no código Java/Kotlin:

val botao = findViewById<Button>(R.id.botaoEnviar)
botao.setOnClickListener {
    // ação ao clicar
}

## 🖼️ ImageView – Exibe uma imagem

<ImageView
    android:id="@+id/imagemLogo"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/logo"
    android:contentDescription="Logo da empresa" />
src: imagem (deve estar em res/drawable).

contentDescription: acessibilidade para leitores de tela.

## ✅ CheckBox – Caixa de seleção múltipla

<CheckBox
    android:id="@+id/checkReceberEmail"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Quero receber e-mails" />

Usado quando o usuário pode marcar ou desmarcar opções sem exclusividade.

## 🔘 RadioButton + RadioGroup – Seleção única

<RadioGroup
    android:id="@+id/grupoSexo"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <RadioButton
        android:id="@+id/sexoMasculino"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Masculino" />

    <RadioButton
        android:id="@+id/sexoFeminino"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Feminino" />
</RadioGroup>

RadioGroup permite apenas uma opção selecionada por vez.

## 🔄 Switch – Interruptor (ligado/desligado)

<Switch
    android:id="@+id/switchNotificacoes"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Notificações"
    android:checked="true" />

checked="true" define como ativado por padrão.

## 📊 ProgressBar – Barra de carregamento

<ProgressBar
    android:id="@+id/progresso"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />

Pode ser indeterminado (círculo girando) ou com progresso controlado via código.

## 🖱️ Spinner – Menu suspenso (drop-down)

<Spinner
    android:id="@+id/spinnerOpcoes"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />

Os itens são definidos no código ou em arquivos XML de string-array.

## Boas práticas:

Use @string para textos reaproveitáveis.

Use dp para dimensões e sp para textos.

Nomeie ids de forma clara: btnSalvar, txtNome, imgLogo, etc.

Prefira ConstraintLayout quando quiser combinar vários componentes com controle preciso de posição.

