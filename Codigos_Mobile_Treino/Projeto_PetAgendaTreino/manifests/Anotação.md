# Introdução:

O AndroidManifest.xml é um arquivo essencial em todo projeto Android. Ele funciona como o mapa de configuração do seu aplicativo – descrevendo tudo que o sistema Android precisa saber para executar, gerenciar e proteger seu app.

- O que é?

É um arquivo XML localizado na pasta manifests/ que serve como um registro oficial do seu aplicativo.

Ele informa ao sistema operacional o que existe no seu app (telas, permissões, temas, ícones, etc.).


- Para que serve ? 

1. Declara as Activities (telas)

Cada Activity (tela) deve ser registrada aqui. Sem isso, ela não pode ser iniciada.

<activity android:name=".MainActivity" />

2. Define a tela inicial

O intent-filter com MAIN e LAUNCHER diz ao sistema qual tela abrir quando o app for iniciado.

<intent-filter>
    <action android:name="android.intent.action.MAIN"/>
    <category android:name="android.intent.category.LAUNCHER"/>
</intent-filter>

3. Solicita permissões

Qualquer recurso sensível (internet, câmera, localização) precisa de autorização via manifest.

<uses-permission android:name="android.permission.INTERNET"/>

4. Declara componentes do app
Além de Activities, você pode declarar:

Services (tarefas em segundo plano)

Broadcast Receivers (responde a eventos do sistema)

Content Providers (compartilhamento de dados entre apps)

5. Configurações do app
Define ícones, tema visual, backup, compatibilidade, suporte a idiomas, e muito mais.

<application
    android:theme="@style/MeuTema"
    android:icon="@mipmap/ic_launcher"
    ... />

## Explicação linha por linha

<?xml version="1.0" encoding="utf-8"?>

Declara que o arquivo está em formato XML, versão 1.0, com codificação UTF-8.

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.exemplo.meuapp">

A tag <manifest> é obrigatória e envolve todo o arquivo.

xmlns:android="...": define o espaço de nomes XML da plataforma Android.

package="...": o nome do pacote raiz do app. Ele define o ID único do seu app no sistema (e na Play Store).

### <application ... >

Essa tag envolve toda a definição do seu aplicativo (ícones, tema, atividades, permissões internas, etc.).
    
<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/Theme.MeuApp">

### Linha por linha:

android:allowBackup="true"

Permite que o sistema faça backup automático dos dados do app (útil em troca de celular, por exemplo).

android:icon="@mipmap/ic_launcher"

Define o ícone do aplicativo (usado na launcher e multitarefa).

android:label="@string/app_name"

Define o nome do aplicativo (exibido abaixo do ícone e na barra de título).

android:roundIcon="@mipmap/ic_launcher_round"

Ícone alternativo usado em dispositivos com ícones circulares.

android:supportsRtl="true"

Permite que o app suporte idiomas da direita para a esquerda (como árabe ou hebraico).

android:theme="@style/Theme.MeuApp"

Define o tema visual usado por padrão em todas as telas (Activity) do app.

### <activity ... >

Define uma tela (Activity) do app. Aqui está a MainActivity.

<activity android:name=".MainActivity">

android:name=".MainActivity"

Define o caminho da classe da Activity. O ponto (.) indica que está dentro do mesmo pacote definido no manifest.

### <intent-filter>

Define quando e como essa Activity será iniciada.

<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>

### Linha por linha:

<action android:name="android.intent.action.MAIN" />

Diz que essa é a Activity principal, que será chamada primeiro quando o app for aberto.

<category android:name="android.intent.category.LAUNCHER" />

Diz ao sistema que essa tela deve aparecer na launcher do Android (menu de apps).

### Fechamento das tags
</activity>     <!-- fecha a tag da MainActivity -->
</application>  <!-- fecha a tag da aplicação -->
</manifest>     <!-- fecha o arquivo de manifest -->