
create table if not exists tbUsuario (
id_usuario integer primary key autoincrement,
usuario text,
email text,
senha text
);

create table if not exists tbDisciplina (
id_disciplina integer primary key autoincrement,
nome_disciplina text,
id_usuario text,
FOREIGN KEY (id_usuario) REFERENCES tbUsuario (id_usuario) ON DELETE CASCADE
);

create table if not exists tbQuestoes (
id_questoes integer primary key autoincrement,
questoes text,
id_disciplina integer,
FOREIGN KEY (id_disciplina) REFERENCES tbDisciplina (id_disciplina) ON DELETE CASCADE
);

create table if not exists tbAlternativa (
id_alternativa integer primary key autoincrement,
alternativa text,
resposta text,
id_questoes integer,
FOREIGN KEY (id_questoes) REFERENCES tbQuestoes (id_questoes) ON DELETE CASCADE
);

create table if not exists tbHistorico(
id_historico integer primary key autoincrement,
id_disciplina integer,
id_usuario integer,
quantidade_acertos integer,
porcentagem integer,
FOREIGN KEY (id_disciplina) REFERENCES tbDisciplina (id_disciplina) ON DELETE CASCADE,
FOREIGN KEY (id_usuario) REFERENCES tbUsuario (id_usuario) ON DELETE CASCADE
);