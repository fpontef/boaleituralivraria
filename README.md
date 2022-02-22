# Livraria Boa Leitura

> Hospedado em: 

### Tecnologias utilizadas:

* Java >= 11
* Spring Boot 
* Maven
* Spring Boot DevTools
* Spring Web + Thymeleaf
* Spring Data JPA + H2 (banco de dados em memória)

### Projeto:
Projeto de conclusão do curso de Java da Universidade do Trabalho Digital (UTD) em parceria com o CENTEC.

Curso com 120h/a ministrado pelo professor Osvaldo Souza (https://github.com/OsvaldoArk)

Além de tudo que foi aprendido no curso implementei:

- Autenticação simples de algumas páginas, via Cookies e Sessions para fins de aprendizado (não foi utilizado spring-security)
- Query personalizadas usando a JPA Repository
- Sistema de buscas por título e autor do livro
- Cadastro de Produto (Livros)
- Cadastro de Usuário
- Carrinho de compras simples com busca via cookies se usuário já possui carrinho em aberto.
- Menu administrativo e de usuário
- Relacionamento bidirecional Um para Muitos / Muitos pra Um
- Soft Delete para evitar quebra de relacionamentos em Book e User
- Estilo CSS seguindo a nomeclatura BEM (ou pelo menos ao máximo possível)

Dados para acesso:

Administrador - login: admin@asd.com / senha: admin

Usuário - login: user@asd.com / senha: user

- Por ser um banco de dados em Memória (H2) , após reinicialização, os dados não ficam salvos e retornam aos valores padrão.

### Autocrítica do projeto:

Foi um ótimo aprendizado e descobertas sobre a estrutura de arquivos do spring boot, especialmente pelo tempo (menos de 1 semana) para realização.

Procurei deixar o mais simples possível, sem fazer uso do spring-security que seria a alternativa que evitaria tantas repetições e seria uma barreira ideal.

Aprendi bastante contornando as limitações do Thymeleaf para tentar tornar os dados dinâmicos, como o menu administrativo e páginas reaproveitavéis como o Header e Footer.

### Guides
Com Maven instalado, executar na pasta do projeto:

```console
mvn install -U  (caso o eclipse não tenha instalado as dependências)

mvn spring-boot:run
```

