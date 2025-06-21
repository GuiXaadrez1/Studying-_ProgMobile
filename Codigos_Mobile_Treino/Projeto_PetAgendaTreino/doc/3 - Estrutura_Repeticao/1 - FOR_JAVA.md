# INTRODUÇÃO
    O for é uma estrutura ou bloco de código de repetição
    Vamos aprender a usar o for e todos os seus tipos
    Nesse aquivo.md

## Capítulo 1 — O for tradicional (clássico)

- Sintaxe:

for (inicialização; condição; incremento/decremento) {
    // Bloco de código que será repetido
}

- Explicação parte por parte:

inicialização: executada uma única vez antes do loop iniciar. Normalmente, inicializa a variável de controle (int i = 0).

condição: testada a cada iteração. Se for true, o bloco é executado; se for false, o loop termina.

incremento/decremento: executado ao final de cada iteração. Costuma incrementar (i++) ou decrementar (i--) a variável de controle.

- Exemplo:

for (int i = 0; i < 5; i++) {
    System.out.println("Valor de i: " + i);
}

- Saida:

Valor de i: 0
Valor de i: 1
Valor de i: 2
Valor de i: 3
Valor de i: 4

# Capítulo 2 — O for-each (enhanced for loop)

- Sintaxe:

for (Tipo elemento : estrutura) {
    // Bloco de código usando o elemento
}

- Finalidade:
Usado para percorrer arrays e coleções (como ArrayList, List, Set).

Não é possível acessar o índice diretamente.

- Exemplo com array:

String[] nomes = {"Ana", "Bruno", "Carlos"};

for (String nome : nomes) {
    System.out.println("Nome: " + nome);
}

- Exemplo com ArrayList:

ArrayList<Integer> numeros = new ArrayList<>();

numeros.add(10);
numeros.add(20);
numeros.add(30);

for (int numero : numeros) {
    System.out.println("Número: " + numero);
}


## Capítulo 3 — for com múltiplas variáveis
Você pode controlar mais de uma variável dentro do for, separando com vírgula:

- Exemplo:

for (int i = 0, j = 10; i < j; i++, j--) {
    System.out.println("i: " + i + ", j: " + j);
}

- Saida:

i: 0, j: 10
i: 1, j: 9
i: 2, j: 8
i: 3, j: 7
i: 4, j: 6

##  Capítulo 4 — for com inicialização fora do laço
Você pode iniciar a variável fora e apenas testar/iterar dentro:

int i = 0;
for (; i < 5; i++) {
    System.out.println(i);
}

## Capítulo 5 — for sem bloco {}
Se houver apenas uma linha dentro do for, as chaves são opcionais:

for (int i = 0; i < 3; i++)
    System.out.println("Sem chaves: " + i);

Cuidado: é fácil errar se você tentar adicionar mais de uma linha depois.

## Capítulo 6 — for infinito
Um loop for pode ser infinito se você omitir tudo e deixar a condição sempre verdadeira:

for (;;) {
    System.out.println("Loop infinito!");
}

Use com break para interromper, senão seu app travará.