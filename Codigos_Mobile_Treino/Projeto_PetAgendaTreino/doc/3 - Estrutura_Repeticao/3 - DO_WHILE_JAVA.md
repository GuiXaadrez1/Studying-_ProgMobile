# INTRODUÇÃO
    DO While é a Sintaxe inversa de estrutura de repetição WHILE
    Vamos destrinchar abaixo.

##  Capítulo 1 — Estrutura do do-while

- Sintaxe geral:

do {
    // bloco de código que será executado
} while (condição);


- Explicação linha por linha:

O bloco do { ... } é executado primeiro, antes da condição ser verificada.

Após a execução do bloco, o while (condição) é avaliado.

Se a condição for true, o loop recomeça.

Se a condição for false, o loop termina.

Garantia: o do-while sempre executa o bloco ao menos uma vez, mesmo que a condição seja false logo na primeira avaliação.

## Capítulo 2 — Exemplo Básico com Contador

int i = 0;

do {
    System.out.println("Valor de i: " + i);
    i++;
} while (i < 5);

- Explicação:

i = 0; → valor inicial do contador.

O System.out.println(...) será executado antes de verificar se i < 5.

O loop continua enquanto i for menor que 5.

Saída:

Valor de i: 0
Valor de i: 1
Valor de i: 2
Valor de i: 3
Valor de i: 4

## Capítulo 3 — Diferença entre while e do-while

1. Verifica antes

while: ✅ Sim O while verifica a condição antes de executar o bloco. Ou seja, se a condição for falsa logo no início, o código dentro do laço nunca será executado.

do-while: ❌ Não O do-while executa o bloco primeiro e só depois verifica a condição. Isso significa que o código será executado pelo menos uma vez, mesmo que a condição seja falsa.

2. Executa ao menos 1x

while: ❌ Não (pode ser 0x) Se a condição for falsa logo de cara, o laço while não roda nenhuma vez.

do-while: ✅ Sim (sempre 1x no mínimo) Como a verificação acontece depois da execução, o bloco roda pelo menos uma vez, mesmo que a condição falhe depois.

3. Uso comum

while: Quando precisa testar antes Usado quando você quer garantir que a repetição só ocorra se a condição estiver verdadeira logo de início.

do-while: Quando deve testar depois Ideal quando você precisa executar o bloco ao menos uma vez, e só então verificar se ele deve continuar.

## Exemplo:

- While:

int x = 10;
while (x < 5) {
    System.out.println("Nunca executa");
}

- Do While:

int x = 10;
do {
    System.out.println("Executa 1x");
} while (x < 5);


## Capítulo 4 — Validação de Entrada

Muito usado em validação de usuário:

Scanner scanner = new Scanner(System.in);
String senha;

do {
    System.out.print("Digite a senha: ");
    senha = scanner.nextLine();
} while (!senha.equals("1234"));

System.out.println("Acesso liberado!");

-  Por que usar do-while aqui?

Porque a primeira leitura da senha deve sempre acontecer.

A repetição só ocorre caso esteja errada.

##  Capítulo 5 — Controle com Contador

int cont = 1;

do {
    System.out.println("Número: " + cont);
    cont++;
} while (cont <= 10);

Imprime de 1 a 10. Você pode adaptar com -- para fazer contagem regressiva também.

## Capítulo 6 — Uso de break e continue

Interromper com break

int x = 0;

do {
    if (x == 3) break;
    System.out.println("x = " + x);
    x++;
} while (x < 5);


Pular com continue

int x = 0;

do {
    x++;
    if (x == 3) continue;
    System.out.println("x = " + x);
} while (x < 5);


## Capítulo 7 — Quando usar do-while

Use do-while quando:

    Você precisa garantir que o código será executado pelo menos uma vez.

    Precisa ler dados do usuário, fazer validação ou carregar recursos na primeira execução.

    O fluxo de decisão depende da ação anterior, e não de um estado prévio.

## Capítulo 8 — Boas práticas

    Controle manual da variável (inicialize antes, incremente dentro).

    Evite loops infinitos — garanta que a condição pode se tornar false.

    Sempre comente o propósito do laço em contextos complexos.
