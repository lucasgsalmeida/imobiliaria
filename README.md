Projeto Imobiliária Spring Boot

Este projeto é um sistema backend para uma imobiliária, permitindo o gerenciamento de casas, clientes e usuários. Ele utiliza Spring Boot, JPA e Spring Security.

Tecnologias utilizadas:

Spring Boot
JPA
Spring Security
Lombok
Estrutura do projeto:

src/main/java/com.lucas.imobiliaria:
model.domain: Pacote contendo modelos de domínio da aplicação.
cliente: Pacote contendo classes relacionadas a clientes.
Clientes: Entidade representando um cliente.
ClienteRequestDTO: DTO para criação de clientes.
ClienteResponseDTO: DTO para consulta de clientes.
imoveis: Pacote contendo classes relacionadas a imóveis.
Casas: Entidade representando um imóvel.
CasasRequestDTO: DTO para criação de imóveis.
CasasResponseDTO: DTO para consulta de imóveis.
CasasResponseImagensDTO: DTO para consulta de imóveis com imagens.
imagensImoveis: Pacote contendo classes relacionadas a imagens de imóveis.
ImagensImoveis: Entidade representando uma imagem de imóvel.
ImagensRequestDTO: DTO para criação de imagens de imóveis.
ImagensResponseDTO: DTO para consulta de imagens de imóveis.
repository: Pacote contendo repositórios da aplicação.
CasasRepository: Repositório para a entidade Casas.
ClienteRepository: Repositório para a entidade Clientes.
ImagensRepository: Repositório para a entidade ImagensImoveis.
users: Pacote contendo classes relacionadas a usuários.
Usuarios: Entidade representando um usuário.
UserRole: Enum representando os roles de usuário (ADMIN e USER).
LoginResponseDTO: DTO contendo o token de autenticação após o login.
UsuariosRequestDTO: DTO para criação de usuários.
UsuariosResponseDTO: DTO para consulta de usuários.
controller: Pacote contendo controladores da aplicação.
CasasController: Controlador para gerenciamento de casas.
ClienteController: Controlador para gerenciamento de clientes.
UsuariosController: Controlador para gerenciamento de usuários e autenticação.
infra.security: Pacote contendo classes relacionadas à segurança.
TokenService: Classe responsável por gerar e validar tokens JWT.
SecurityConfiguration: Classe de configuração do Spring Security.
Configuração:

Clone o projeto.
Adicione as dependências necessárias ao seu pom.xml.
Configure as propriedades da aplicação em application.properties, incluindo o segredo JWT.
Crie um banco de dados e execute as migrações JPA.
Executando a aplicação:

Execute mvn spring-boot:run.
Acesse a aplicação em http://localhost:8080.
Documentação da API:

GET /cliente/id/{id}: Recupera um cliente pelo ID.
GET /cliente/all: Recupera todos os clientes.
POST /cliente/register: Registra um novo cliente.
GET /casas: Recupera todas as casas com suas imagens.
POST /casas/register: Registra uma nova casa.
POST /auth/login: Autentica um usuário e retorna um token JWT.
POST /auth/register: Registra um novo usuário.
GET /auth/user: Recupera os dados de um usuário autenticado.
Contribuindo:

Você é bem-vindo para contribuir com este projeto enviando pull requests.

Licença:

Este projeto está licenciado sob a licença MIT.
