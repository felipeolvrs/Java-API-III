API de Locadora de Itens

API RESTful desenvolvida como Trabalho Final da disciplina de Programação 3.
O sistema gerencia o ciclo de vida de locação de itens genéricos (livros, jogos, equipamentos), focando no controle rígido de estoque, regras de renovação e aplicação automática de multas financeiras por atraso.

Índice
Sobre o Projeto
Tecnologias
Arquitetura
Como Executar

Documentação da API
Regras de Negócio
Autores
 Sobre o Projeto
Este projeto tem como objetivo resolver o problema de gestão de empréstimos em pequenas bibliotecas ou locadoras. Diferente de sistemas simples de CRUD, esta API implementa validações de negócio complexas para garantir:
Consistência de Estoque: Impede empréstimos de itens esgotados (Concorrência).
Proteção Financeira: Bloqueia novos empréstimos para usuários inadimplentes.
Automação: Cálculo de dias de atraso e geração de multas sem intervenção manual.

Tecnologias
Java 17 (LTS): Linguagem base.
Spring Boot 3.4: Framework principal.
Spring Data JPA: Abstração de persistência.
MySQL 8: Banco de dados relacional.
Lombok: Redução de código boilerplate.
Maven: Gerenciamento de dependências.

Arquitetura
O projeto segue os princípios da Onion Architecture (Arquitetura Cebola), visando o desacoplamento entre o domínio e a infraestrutura.

src/main/java/com/example/api
├── application       # Camada de Serviços (Regras de Negócio)
├── domain            # Entidades, Enums e Interfaces de Repositório
├── infrastructure    # Implementação do Spring Data JPA
├── presentation      # Controllers REST
└── ApiApplication.java


Como Executar
Pré-requisitos
Java JDK 17+ instalado.
MySQL Server rodando na porta 3306.
Maven (Opcional, pois o projeto inclui o wrapper mvnw).
Passo a Passo
Clone o repositório:
git clone [https://github.com/seu-usuario/api-locadora.git](https://github.com/seu-usuario/api-locadora.git)
cd api-locadora


Configure o Banco de Dados:
Acesse seu MySQL e crie o banco:
CREATE DATABASE locadora_db;

Nota: Verifique o arquivo src/main/resources/application.properties se precisar alterar usuário/senha (padrão: root/root ou vazio).
Execute a aplicação:
Linux/macOS: ./mvnw spring-boot:run
Windows: mvnw.cmd spring-boot:run
Acesse:
A API estará disponível em: http://localhost:8080/api/v1

Documentação da API
Itens
Método
Endpoint
Descrição
POST
/itens
Cadastrar novo item
GET
/itens
Listar todos os itens
GET
/itens/{id}
Buscar detalhes de um item
GET
/itens/{id}/disponibilidade
Consultar saldo disponível

<details>
<summary><b>Exemplo de JSON (Criar Item)</b></summary>
{
  "nome": "Notebook Dell G15",
  "descricao": "Core i5, 8GB RAM, SSD 512GB",
  "categoria": "HARDWARE",
  "estoque": 3,
  "quantidade": 3
}
</details>

Empréstimos
Método
Endpoint
Descrição

POST
/emprestimos
Realizar novo empréstimo

POST
/emprestimos/{id}/renovar
Renovar (Máx. 2x)

POST
/emprestimos/{id}/devolver
Devolver e calcular multa

<details>
<summary><b>Exemplo de JSON (Novo Empréstimo)</b></summary>
{
  "usuario": { "id": 1 },
  "item": { "id": 1 }
}
</details>

Usuários & Finanças
Método
Endpoint
Descrição
GET
/usuarios/{id}/dividas
Consultar total de débitos pendentes

Regras de Negócio Implementadas

Controle de Estoque: O sistema valida atômicamente seu estoque > 0 antes de confirmar um empréstimo. Caso contrário, retorna 409 Conflict.
Renovação Limitada: Um empréstimo só pode ser renovado até 2 vezes. Na 3ª tentativa, o sistema lança uma exceção de negócio.
Bloqueio Financeiro: Ao tentar criar um empréstimo, o sistema verifica se o usuário possui multas com status PENDENTE. Se sim, retorna 422 Unprocessable Entity.
Multa Automática: Ao processar a devolução (/devolver), o sistema compara a data atual com a data prevista.
Fórmula: (Data Devolução - Data Prevista) * Valor Diária.

