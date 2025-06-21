# INTRODUÇÃO:

No Android, um layout é um tipo de "container visual" que define como os elementos (botões, textos, imagens, etc.) são organizados na tela.

# LinerLayout

Organiza os elementos em linha reta: vertical (um abaixo do outro) ou horizontal (um ao lado do outro).

- Exemplo:

<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Texto acima" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Botão abaixo" />
</LinearLayout>

- Propriedades, atributos importantes: 

android:orientation="vertical" ou "horizontal"

Pode usar android:layout_weight para dividir espaço proporcionalmente.

##  RelativeLayout (deprecated em muitos casos, mas ainda importante)
Permite posicionar elementos relativos a outros elementos ou ao próprio layout.

- Exemplo:

<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/texto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Texto central" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Abaixo"
        android:layout_below="@id/texto" />

</RelativeLayout>

- Propriedades comuns:

layout_above, layout_below, layout_toRightOf, layout_alignParentTop, etc.

Precisa sempre de android:id para referenciar outros elementos.

## ConstraintLayout (recomendado atualmente)

Mais moderno e poderoso, permite criar interfaces complexas e responsivas com menos aninhamento.

- Exemplo: 

<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Centro"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>

- Propriedades importantes:

app:layout_constraintX_toYOf="..." (você sempre referencia outro elemento ou o próprio "parent")

Ideal para interfaces que devem funcionar bem em múltiplas telas/dimensões

## FrameLayout
Coloca os elementos um sobre o outro, como camadas.

- Exemplo:

<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/fundo" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Texto por cima"
        android:layout_gravity="center" />
</FrameLayout>

## ScrollView
Permite que o conteúdo interno role verticalmente. Ideal para telas com muito conteúdo.

- Exemplo:

<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Muitos elementos aqui -->
    </LinearLayout>
</ScrollView>

## Dicas de boas práticas

Evite layouts aninhados demais (por exemplo: LinearLayout dentro de LinearLayout) → use ConstraintLayout quando possível.

Sempre use match_parent ou wrap_content com consciência.

Use IDs com clareza (ex: @+id/botaoEnviar) para facilitar a manipulação no código.

Use o modo Design e modo Code do Android Studio para visualizar e editar.