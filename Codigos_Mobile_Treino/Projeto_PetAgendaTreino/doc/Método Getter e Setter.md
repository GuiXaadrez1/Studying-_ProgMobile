1. INTRODUÇÃO    

    - O QUE É? 

        Na programação orientada a objetos (OOP), getters e setters são métodos públicos 
        usados para acessar ou modificar atributos privados de uma classe.

    - QUANDO USAR? 
        
        Basicamente você usa get e set quando quer acessar ou modificar atributos 
        private de forma controlada e segura.

        LEMBRE-SE: Atributos são propriedades de uma classe pode ser:

            variáveis que comportam algum objeto,
            funções/métodos,
            até mesmo classes.

    - Porque não acessar diretamente?

        Se você faz isso:

            public String nome;
    
        Pode acessar direto:

            pessoa.nome = "Maria";
    
        ⚠️ Parece fácil, mas é perigoso: você perde o controle. 
        Qualquer classe pode alterar esse valor sem validação,
        lógica ou segurança.

    - REGRA DE OURO PARA QUANDO SABER USAR:

        Sempre que um atributo precisar de segurança, validação ou proteção futura, 
        use private + get e set.

2. Como usar GET e SET ?

    - PAPEL DOS GETTERS E SETTERS?

        get = getter → retorna o valor do atributo

        set = setter → define um novo valor para o atributo
        
        Esses métodos funcionam como portas controladas para entrar e sair de dentro da classe.

3. EXEMPLOS DE USO:

    // EXEMPLO SIMPLES:

    public class Pessoa {
        private String nome;
        private int idade;

        // Setter
        public void setNome(String nome) {
            this.nome = nome; 

            // LEMBRE-SE: this é uma palavra-chave (keyword) que representa a própria instância atual da classe.
            // this é uma referência ao objeto que está executando o código no momento.

        }

        // Getter
        public String getNome() {
            return nome;
        }

        // Setter
        public void setIdade(int idade) {
            this.idade = idade;
        }

        // Getter
        public int getIdade() {
            return idade;
        }
    }

    // USANDO:

    Pessoa p = new Pessoa();
    p.setNome("Ana");
    p.setIdade(30);

    System.out.println(p.getNome()); // Ana
    System.out.println(p.getIdade()); // 30


    // EXEMPLO INTERMEDIÁRIO - COM VALIDAÇÃO    

    objetivo: Impedir que a idade seja negativa e formatar o nome.

    public class Cliente {

        private String nome; // propriedade e atributo privado
        private int idade; // propriedade e atributo privado

        public void setNome(String nome) {
            this.nome = nome.trim(); // remove espaços no início e fim
        }

        public String getNome() {
            return nome.toUpperCase(); // retorna o nome em maiúsculas
        }

        public void setIdade(int idade) {
            if (idade >= 0) {
                this.idade = idade;
            } else {
                System.out.println("Idade inválida!");
            }
        }

        public int getIdade() {
            return idade;
        }
    }

    USANDO:

    Cliente c = new Cliente();
    c.setNome("  maria ");
    c.setIdade(-5); // Mostra: Idade inválida!

    System.out.println(c.getNome()); // MARIA
    System.out.println(c.getIdade()); // 0 (valor padrão, pois não foi aceito -5)


    // EXEMPLO AVANÇADO

    Objetivo - Criar um objeto ContaBancaria com:

        saldo privado

        apenas leitura do número da conta

        métodos para sacar, depositar e verificar saldo


    public class ContaBancaria {

        private final String numeroConta; // propriedade, atributo privado
        private double saldo; // propriedade , atributo privado

        // Construtor
        public ContaBancaria(String numeroConta) {
            this.numeroConta = numeroConta; 
            this.saldo = 0;
        }

        // Apenas leitura (getter), sem setter
        public String getNumeroConta() {
            return numeroConta;
        }

        public double getSaldo() {
            return saldo;
        }

        // Métodos públicos controlam alterações no saldo
        public void depositar(double valor) {
            if (valor > 0) {
                saldo += valor;
            }
        }

        public boolean sacar(double valor) {
            if (valor > 0 && saldo >= valor) {
                saldo -= valor;
                return true;
            }
            return false;
        }
    }

    // USANDO:

    ContaBancaria conta = new ContaBancaria("12345-6");

    conta.depositar(500.0);
    conta.sacar(100.0);

    System.out.println("Conta: " + conta.getNumeroConta()); // 12345-6
    System.out.println("Saldo: R$" + conta.getSaldo());     // Saldo: R$400.0


4. RESUMO DOS EXEMPLOS:

╔═══════════════════════════════════════════════╗
║         CONCLUSÃO GERAL - GET e SET           ║
╠═══════════════════════════════════════════════╣
║ NÍVEL       │ FOCO PRINCIPAL                  ║
╟─────────────┼─────────────────────────────────╢
║ SIMPLES     │ Encapsular e acessar dados      ║
║             │ básicos de forma segura         ║
╟─────────────┼─────────────────────────────────╢
║INTERMEDIÁRIO│ Validar e formatar valores ao   ║
║             │ definir ou obter um atributo    ║
╟─────────────┼─────────────────────────────────╢
║ AVANÇADO    │ Controlar acesso com lógica     ║
║             │ de negócio dentro dos métodos   ║
║             │ (ex: saque, depósito, etc.)     ║
╚═══════════════════════════════════════════════╝

