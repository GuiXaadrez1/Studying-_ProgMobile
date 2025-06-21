# INTRODUÇÃO
    IF e Else é uma estrutura , bloco de código de tomada de decisão
    usamos para fazer validações no código.

## Capítulo 1 — Estrutura Básica de Decisão

- Sintaxe do if:

    if (condição) {
    // bloco executado se a condição for verdadeira
}


- Explicação:

if significa "se".

A condição deve retornar um valor booleano (true ou false).

Se a condição for verdadeira, o bloco de código dentro das chaves será executado.

Se for falsa, o bloco será ignorado.

## Capítulo 2 — else: o Caminho Alternativo

if (condição) {
    // bloco se for verdadeiro
} else {
    // bloco se for falso
}

## Exemplo:

int idade = 17;

if (idade >= 18) {
    System.out.println("Maior de idade");
} else {
    System.out.println("Menor de idade");
}

- Explicação:

Se idade >= 18, imprime "Maior de idade".

Senão, executa o bloco do else.


## Capítulo 3 — else if: Várias Condições

- Sintaxe:

if (condição1) {
    // executa se condição1 for verdadeira
} else if (condição2) {
    // executa se condição2 for verdadeira
} else {
    // executa se nenhuma das anteriores for verdadeira
}

- Exemplo:

int nota = 75;

if (nota >= 90) {
    System.out.println("A");
} else if (nota >= 80) {
    System.out.println("B");
} else if (nota >= 70) {
    System.out.println("C");
} else {
    System.out.println("Reprovado");
}

- Explicação:

    O Java testa as condições de cima para baixo.

    Assim que encontra uma condição verdadeira, executa seu bloco e ignora os demais.

    Se nenhuma for verdadeira, cai no else.

## Capítulo 4 — Operadores Lógicos em Condições

| Operador | Nome             | Exemplo              | Resultado                                |
|----------|------------------|----------------------|-------------------------------------------|
| &&       | E lógico (AND)   | a > 0 && b < 10      | true se **ambas** as condições forem verdadeiras |
| \|\|     | OU lógico (OR)   | a > 5 \|\| b < 3     | true se **pelo menos uma** for verdadeira |
| !        | Negação (NOT)    | !(a > 5)             | inverte o resultado da expressão          |

- Exemplo:

int idade = 20;
boolean possuiAutorizacao = false;

if (idade >= 18 || possuiAutorizacao) {
    System.out.println("Pode entrar");
} else {
    System.out.println("Entrada negada");
}

## Capítulo 5 — Exemplo Completo com Menu

Scanner scanner = new Scanner(System.in);

System.out.println("Escolha uma opção: [1] Cadastrar [2] Listar [3] Sair");
int opcao = scanner.nextInt();

if (opcao == 1) {
    System.out.println("Cadastrando...");
} else if (opcao == 2) {
    System.out.println("Listando registros...");
} else if (opcao == 3) {
    System.out.println("Encerrando programa.");
} else {
    System.out.println("Opção inválida!");
}

## Capítulo 6 — Encadeamentos e Escopos

Você pode ter if dentro de if, chamado de condicional aninhada:

int idade = 20;
boolean possuiCarteira = true;

if (idade >= 18) {
    if (possuiCarteira) {
        System.out.println("Pode dirigir");
    } else {
        System.out.println("Precisa da carteira");
    }
}


Capítulo 7 — Boas Práticas:

Use parênteses sempre, mesmo quando a condição parecer simples.

Evite múltiplos else if seguidos quando você puder usar switch.

Comente blocos complexos de decisão.

Evite duplicação de código nos blocos — extraia métodos quando possível.

## Capítulo 8 — Comparando com switch

Use if/else if/else para valores booleanos e intervalos:

if (idade >= 60) // intervalo

Use switch para valores exatos (casos):

switch (opcao) {
  case 1: ...
}

