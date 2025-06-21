# INTRODUÇÃO AO LIST
List é uma interface da biblioteca Java Collections Framework (JCF) que define uma sequência ordenada de elementos, acessível por índices.

Ela não é uma classe concreta — ou seja, você não pode instanciá-la diretamente, mas pode usá-la como tipo genérico de referência para diferentes implementações como ArrayList, LinkedList, etc.

## Hierarquia da Interface List

java.util.Collection
   └── java.util.List

## Principais implementações de List:

| Implementação | Característica principal                                 |
|---------------|----------------------------------------------------------|
| ArrayList     | Mais rápida para acesso direto via índice                |
| LinkedList    | Melhor performance para inserções/remoções no meio       |
| Vector        | Similar ao ArrayList, mas sincronizado                   |

## Importando List

import java.util.List;
import java.util.ArrayList; // ou LinkedList, Vector, etc.


## Exemplo básico usando List

List<String> nomes = new ArrayList<>();

nomes.add("João");
nomes.add("Maria");

System.out.println(nomes.get(0)); // João

## Por que usar List ao invés de ArrayList?
Porque permite trocar a implementação sem mudar o resto do código. Isso é programação orientada a interfaces, um pilar da arquitetura limpa.

List<String> nomes = new LinkedList<>(); // Troca de ArrayList para LinkedList

## Métodos principais de List

| Método               | Descrição                                 |
|----------------------|--------------------------------------------|
| add(E e)             | Adiciona um elemento no fim da lista       |
| add(int index, E e)  | Insere em uma posição específica           |
| get(int index)       | Retorna o elemento no índice               |
| set(int index, E e)  | Substitui um elemento                      |
| remove(int index)    | Remove por índice                          |
| remove(Object o)     | Remove a primeira ocorrência do objeto     |
| indexOf(Object o)    | Retorna o índice da primeira ocorrência    |
| contains(Object o)   | Verifica se o item está na lista           |
| size()               | Quantidade de elementos                    |
| isEmpty()            | Verifica se está vazia                     |
| clear()              | Remove todos os elementos                  |

##  Iterando com List

1. FOR CLASSICO

for (int i = 0; i < nomes.size(); i++) {
    System.out.println(nomes.get(i));
}


2. FOR-EACH

for (String nome : nomes) {
    System.out.println(nome);
}

3. Com lambda (Java 8+)

nomes.forEach(nome -> System.out.println(nome));

##  Exemplo com objetos

List<Cliente> clientes = new ArrayList<>();

clientes.add(new Cliente(1, "João", "6199999", "joao@email.com", "1234"));

for (Cliente c : clientes) {
    System.out.println(c.getNome());
}

## Observações importantes
List aceita duplicatas (elementos repetidos).

A ordem dos elementos é mantida.

Você pode trocar facilmente entre ArrayList, LinkedList e outras implementações.

Nunca instancie List diretamente, pois ela é uma interface, não uma classe concreta.

### ATENÇÃO EXEMPLO DA EXPLICAÇÃO ACIMA:

// Correto
List<String> lista = new ArrayList<>();

// Errado (interface não pode ser instanciada)
List<String> lista = new List<>(); // ERRO


## Quando usar List

Use List quando:

- Você quer manipular uma coleção de elementos ordenados.

- Precisa acessar itens por índice.

- Deseja manter flexibilidade para trocar a estrutura subjacente (ArrayList, LinkedList, etc).

- Está trabalhando com código que deve seguir princípios SOLID, especialmente o Princípio da Inversão de Dependência (usar abstrações ao invés de implementações).