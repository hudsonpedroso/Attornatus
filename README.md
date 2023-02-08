# Avaliação Desenvolvedor Back-end Attornatus

O objetivo deste documento é identificar seus conhecimentos quanto às tecnologias utilizadas no cotidiano de desenvolvimento da equipe de Back-end na Attornatus Procuradoria Digital.<br />

## Esta análise propõe avaliar os seguintes temas: 
Qualidade de código<br />
Java, Spring boot<br />
API REST<br />
Testes<br />

## Sumário
_Links:_<br />
[Testes TDD](https://github.com/hudsonpedroso/Attornatus/blob/main/teste_java/src/test/java/com/dev/app) <br />
[Estrutura dos pacotes](https://github.com/hudsonpedroso/Attornatus/tree/main/teste_java/src/main/java/com/dev) <br />
[Models](https://github.com/hudsonpedroso/Attornatus/tree/main/teste_java/src/main/java/com/dev/model) <br />
[Controllers](https://github.com/hudsonpedroso/Attornatus/tree/main/teste_java/src/main/java/com/dev/controller) <br />
[Repositorys](https://github.com/hudsonpedroso/Attornatus/tree/main/teste_java/src/main/java/com/dev/repository) <br />
[Services](https://github.com/hudsonpedroso/Attornatus/tree/main/teste_java/src/main/java/com/dev/service) <br />
[Exceptions](https://github.com/hudsonpedroso/Attornatus/tree/main/teste_java/src/main/java/com/dev/exceptions) <br />

## Observações
_A utilização do banco de dados H2 teve problemas na minha máquina, portanto a aplicação está sem a parte de banco de dados por enquanto._


# A entrega deverá ser feita da seguinte forma:
O prazo para entrega da avaliação será de até 7 dias após envio da mesma <br />
Encaminhar este documento com as perguntas respondidas e com o link do código público em sua conta do GitHub <br />
Opcionalmente, caso você consiga fazer o build da aplicação, poderá também informar o link de acesso. 

Link de acesso: [Desafio Attornatus](https://github.com/hudsonpedroso/Attornatus)


## Qualidade de código

### Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software? <br />
R: 	Estrutura e separação das responsabilidades em diferentes pacotes e classes pertinentes, além de usar de método de desenvolvimento guiado por testes (TDD). Assim como refatoramento quando necessário, baseando-se baseando em princípios de Clean Code.


### Em qual etapa da implementação você considera a qualidade de software?
R: 	A qualidade de software deve ser considerada em todas as etapas da implementação, desde o planejamento até o manutenção pós-entrega. Algumas etapas onde é importante enfatizar a qualidade incluem:

_Análise de requisitos:_ é importante definir claramente os requisitos do software para garantir que ele atenda às expectativas do usuário e seja de alta qualidade.

_Projeto:_ durante a fase de projeto, é importante definir as estratégias para garantir a qualidade, como a realização de testes de unidade e integração (utilizado TDD).

_Implementação:_ na implementação, é importante seguir as boas práticas de programação para garantir a qualidade do software, como escrever código claro e legível, realizar testes de unidade frequentes e tratar corretamente as exceções.

_Teste:_ a fase de teste é crucial para identificar e corrigir problemas antes da entrega ao cliente. É importante realizar testes de unidade, integração, sistema e aceitação.

_Manutenção:_ após a entrega, o software pode requerer manutenções e correções. É importante continuar monitorando e melhorando a qualidade do software durante todo o seu ciclo de vida.

Em resumo, a qualidade de software deve ser considerada em todas as etapas da implementação para garantir um software confiável, funcional e eficiente.


## Desafio Java

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:  

Criar uma pessoa 
Editar uma pessoa
Consultar uma pessoa
Listar pessoas
Criar endereço para pessoa
Listar endereços da pessoa
Poder informar qual endereço é o principal da pessoa  

Uma Pessoa deve ter os seguintes campos:  
Nome
Data de nascimento
Endereço:
Logradouro
CEP
Número
Cidade

## Requisitos  
Todas as respostas da API devem ser JSON  
Banco de dados H2

## Diferencial
Testes
Clean Code
 
## Será levado em avaliação 
Estrutura, arquitetura e organização do projeto  
Boas práticas de programação  
Alcance dos objetivos propostos.
