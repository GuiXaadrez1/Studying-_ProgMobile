# INTRODUÇÃO

O switch é uma estrutura de controle condicional que substitui múltiplos if-else encadeados, tornando o código mais legível e organizado quando estamos comparando um mesmo valor com diferentes casos fixos.

# Capítulo 1 — Sintaxe Básica

switch (expressao) {
    case valor1:
        // bloco de código
        break;
    case valor2:
        // outro bloco
        break;
    default:
        // executa se nenhum case combinar
}

- Explicação:

switch: palavra-chave que inicia a estrutura.

expressao: pode ser do tipo int, char, String, enum, ou byte, short.

case: representa um valor a ser comparado.

break: encerra o fluxo dentro do switch. Evita o "fall through", que é quando os cases "caem" uns nos outros.

default: opcional. Executa se nenhum case corresponder.

# Capítulo 2 — Exemplo Clássico com Inteiros

int dia = 3;

switch (dia) {
    case 1:
        System.out.println("Domingo");
        break;
    case 2:
        System.out.println("Segunda-feira");
        break;
    case 3:
        System.out.println("Terça-feira");
        break;
    default:
        System.out.println("Dia inválido");
}

-  Interpretação linha por linha:

A variável dia contém o valor 3.

O switch compara dia com os valores dos case.

Ao encontrar case 3, executa System.out.println("Terça-feira") e depois break.

default é ignorado porque houve correspondência.

## Capítulo 3 — Usando switch com String

String opcao = "listar";

switch (opcao) {
    case "cadastrar":
        System.out.println("Cadastrando...");
        break;
    case "listar":
        System.out.println("Listando registros...");
        break;
    default:
        System.out.println("Opção inválida.");
}

A partir do Java 7, switch aceita String.

## Capítulo 5 — Sem break: Fall Through

Se você omitir o break, os casos seguintes também serão executados:

int numero = 2;

switch (numero) {
    case 1:
        System.out.println("Um");
    case 2:
        System.out.println("Dois");
    case 3:
        System.out.println("Três");
    default:
        System.out.println("Número não encontrado");
}


- Saída:

    Dois  
    Três  
    Número não encontrado

Use isso somente quando quiser executar vários blocos em sequência intencionalmente.

## Capítulo 6 — Exemplo com Menu de Sistema

Scanner scanner = new Scanner(System.in);

System.out.println("Escolha uma opção: [1] Cadastrar [2] Listar [3] Sair");
int opcao = scanner.nextInt();

switch (opcao) {
    case 1:
        System.out.println("Cadastro realizado.");
        break;
    case 2:
        System.out.println("Listagem exibida.");
        break;
    case 3:
        System.out.println("Encerrando o sistema.");
        break;
    default:
        System.out.println("Opção inválida.");
}


## Capítulo 7 — switch com enum

Enums funcionam bem com switch:

enum Dia { SEGUNDA, TERCA, QUARTA }

Dia hoje = Dia.SEGUNDA;

switch (hoje) {
    case SEGUNDA:
        System.out.println("Início da semana.");
        break;
    case TERCA:
        System.out.println("Terça-feira.");
        break;
}


##  Capítulo 8 — Boas Práticas

Use switch apenas quando comparar um mesmo valor com valores literais fixos.

Prefira if-else quando for necessário comparar intervalos (idade > 18) ou condições booleanas.

Use break para evitar comportamento inesperado.

Sempre inclua default para tratamento de valores não previstos

## Capítulo 9 — Quando NÃO usar switch

Evite switch quando:

| Situação                          | Use if ou else                   |
|----------------------------------|----------------------------------|
| Comparações com operadores lógicos | ✅ `if (a > b && c < d)`         |
| Comparações com null             | ✅ `if (obj == null)`            |
| Intervalos ou faixas de valores  | ✅ `if (nota >= 90)`             |

## Capítulo 10 — Conclusão

O switch em Java é uma estrutura poderosa para controle de fluxo baseado em valores fixos. Ele substitui if-else com mais legibilidade, mas exige atenção ao break e ao uso correto do tipo da expressão.