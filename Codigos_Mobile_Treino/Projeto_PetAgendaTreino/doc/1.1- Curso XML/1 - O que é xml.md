# INTRODUÇÃO
XML (eXtensible Markup Language) é uma linguagem de marcação usada para armazenar e transportar dados de forma estruturada e legível tanto por humanos quanto por máquinas.

Também é considerado uma INTERFACE/VIEW para outros sistemas ou máquinas.

## CARACTERÍSTICAS DOS ARQUIVOS XML

1. Auto-descritivo: Os dados são acompanhados de tags que explicam o significado, por exemplo:


<cliente>
  <nome>João</nome>
  <idade>30</idade>
</cliente>

2. Hierárquico: Os dados são organizados em uma estrutura de árvore (nó raiz, filhos, etc.), o que facilita o agrupamento de informações relacionadas.

3. Personalizável: Diferente do HTML, você define suas próprias tags, de acordo com a necessidade do domínio.

4. Independente de plataforma: Pode ser lido e processado por aplicações escritas em qualquer linguagem (Java, Python, C#, etc.).

5. Muito usado em integração de sistemas: Especialmente quando se precisa padronizar comunicação entre serviços, como em Web Services (SOAP, por exemplo).


## Por que XML é considerado uma interface?

1. Padronização de formato de dados:

Ele define uma estrutura comum para que diferentes sistemas (com diferentes linguagens, bancos ou arquiteturas) possam interpretar os mesmos dados.

2. Independência de tecnologia:

Um sistema em Java pode gerar XML, e outro em .NET, Python, ou C++ pode interpretar esse mesmo XML sem problemas.

3. Uso em Web Services (SOAP):

No modelo SOAP (Simple Object Access Protocol), o XML é o corpo da mensagem trocada entre cliente e servidor. Nesse contexto, o XML é literalmente a interface de comunicação entre os sistemas.

4. Definição formal com XSD:

XML pode ser validado contra um esquema (XSD) que atua como uma descrição formal da interface de dados, assim como uma API REST define seu contrato com Swagger/OpenAPI.

5. Conclusão:

XML não é uma interface visual nem uma API no sentido tradicional, mas é sim uma interface de dados padronizada, usada para que sistemas troquem informações com segurança, estrutura e validação.

## EXEMPLO PRÁTICO:
Imagine um sistema de pedidos online. O XML poderia representar um pedido assim:

<pedido>
  <id>123</id>
  <cliente>Maria</cliente>
  <produtos>
    <produto>
      <nome>Camiseta</nome>
      <quantidade>2</quantidade>
    </produto>
    <produto>
      <nome>Calça</nome>
      <quantidade>1</quantidade>
    </produto>
  </produtos>
</pedido>

## USO COMUM DO XML
Configurações de sistemas (AndroidManifest.xml, pom.xml no Maven)

Troca de dados entre APIs (ex: SOAP)

Documentos (ex: arquivos .docx, .xlsx usam XML internamente)

Comunicação entre sistemas legados

