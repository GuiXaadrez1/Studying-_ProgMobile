# INTRODUÇÃO
    While é uma outra forma de estrutura de repetição
    Vamos destrinchar abaixo.

## Capítulo 1 — Estrutura do while

- Sintaxe geral:

while (condição) {
    // Bloco de código que será repetido
}

- Explicação detalhada:

    condição: é uma expressão booleana. O loop continua enquanto essa condição for true.

Antes de executar o corpo do laço, a condição é avaliada.

Se for false logo no início, o bloco não será executado nenhuma vez.

## Capítulo 2 — Exemplo Básico com Contador

int i = 0;

while (i < 5) {
    System.out.println("Valor de i: " + i);
    i++; // incremento manual da variável
}

- O que acontece linha por linha:

int i = 0;: cria o contador.

while (i < 5): testa se i é menor que 5.

System.out.println(...): imprime o valor atual de i.

i++: incrementa i em 1. Essencial! Sem isso, o loop seria infinito.

- Saida: 

Valor de i: 0
Valor de i: 1
Valor de i: 2
Valor de i: 3
Valor de i: 4

## Capítulo 3 — Controle Total (Inicialização e Incremento Separados)

O while exige que você controle manualmente tudo:

Inicialização

Condição

Incremento/Decremento

Se esquecer qualquer parte, o loop pode:

nunca iniciar (condição false)

nunca terminar (sem incremento)

int i = 10;
while (i >= 0) {
    System.out.println(i);
    i--;
}

##  Capítulo 4 — Cuidado com Loops Infinitos

Um erro comum: esquecer de alterar a variável de controle.

int x = 0;

while (x < 5) {
    System.out.println("Loop infinito!");
    // x++ esquecido aqui!
}


Esse código travará o programa, pois x nunca muda e a condição sempre será true.

## Capítulo 5 — Exemplo com Entrada de Dados

Uso típico: repetir até uma condição ser satisfeita (ex: senha correta).

Scanner scanner = new Scanner(System.in);
String senha;

do {
    System.out.print("Digite a senha: ");
    senha = scanner.nextLine();
} while (!senha.equals("1234"));

System.out.println("Acesso liberado!");

Esse é um do-while, mas se adaptado para while:

String senha = "";
while (!senha.equals("1234")) {
    System.out.print("Digite a senha: ");
    senha = scanner.nextLine();
}
System.out.println("Acesso liberado!");

## Capítulo 6 — Trabalhando com Arrays

Percorrendo um array com while:

int[] numeros = {10, 20, 30, 40};
int index = 0;

while (index < numeros.length) {
    System.out.println("Elemento: " + numeros[index]);
    index++;
}

## Capítulo 7 — Uso de break e continue com while

-  break: interrompe o laço:

int i = 0;
while (i < 10) {
    if (i == 5) break;
    System.out.println(i);
    i++;
}

- continue: pula para a próxima iteração

int i = 0;
while (i < 5) {
    i++;
    if (i == 3) continue;
    System.out.println("Valor: " + i);
}

## Capítulo 8 — Quando usar while ao invés de for

Use while quando:

    Não se sabe quantas vezes o loop deve rodar.

    O controle depende de evento externo, como entrada do usuário.

    Você quer máxima flexibilidade no controle da condição e do fluxo.




