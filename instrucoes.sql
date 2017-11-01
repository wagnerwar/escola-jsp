CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'amesma';

GRANT ALL PRIVILEGES ON  aluno. * TO 'usuario'@'localhost';

FLUSH PRIVILEGES;

Após executar os comandos acima, necessário executar o conteúdo do arquivo 'schema.sql':

mysql -uusuario -pamesma aluno < schema.sql


