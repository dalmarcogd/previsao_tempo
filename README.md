# previsao_tempo
Projeto para demonstrar as previsões do tempo de determinada cidade.

# Arquitetura Backend
Na camada de modelagem de entidades e relacionamento foi utilizado o hibernate, par o gerenciamento injeção de dependencia foi utilizado spring e spring-mvc para disponibilizar os recursos.
# Arquitetura Frontend
No frontend foi utilizado angular2 para desenvolvimento da aplicação de pagina unica, utilizado o componente ng2-smart-table e a biblioteca de componentes ng-lightning.

# Como utilizar?
1 - Instalar servidor mysql.<br>
2 - Executar script = CREATE SCHEMA 'previsao_tempo';<br>
3 - Clonar o repositório.<br>
4 - Instalar wildfly9<br>
5 - Importar o projeto do backend no eclipse.<br>
6 - Instalar pluggin gradle, executar o comando = clean build jar war, no pluggin do gradle<br>
7 - Lozalizar a pasta dentro do projeto backend build/libs e fazer deploy do previsao-tempo.war no servidor.<br>
8 - Executar o script = INSERT INTO `previsao_tempo`.`user`(`user_id`,`version`,`user_email`,`user_name`,`user_password`,`user_username`) VALUES(1,"2017-12-31",'teste','teste','123456','teste');<br>
9 - Executar os sprits localizados em backend/script para inserção de cidade e estado.<br>
10 - Instalar nodejs, localizar o diretorio do frontend e executar o comando = npm install e npm start<br>
11 - Acessar a url localhost:3000



