create database biblioteca_db;
use  biblioteca_db;
show databases;
create table tbl_livro (
						id_livro int primary key auto_increment , 
						autor varchar(255) not null ,
						titulo varchar(255) not null,
                        ano int not null,
                        categoria varchar(255) not null,
                        isbn varchar(20) not null
);

create table tbl_usuario(
	id_usuario int primary key auto_increment,
    nome varchar(255) not null,
    endereco varchar(255) not null,
    cpf varchar(255) not null,
    telefone varchar (255) not null,
    email varchar(255) not null
    
    
);

create table tbl_controle_estoque(
	id_estoque int primary key auto_increment,
    qtd_atual int not null,
    id_livro int not null,
    
    constraint fk_id_livro foreign key (id_livro) references tbl_livro (id_livro)
    
);

create table tbl_emprestimo(
id_emprestimo int primary key auto_increment,
data_emprestimo date not null,
data_devolucao date not null,
id_livro int not null,
id_usuario int not null,

constraint fk_id_livro_emprestimo foreign key(id_livro) references tbl_livro(id_livro),
constraint fk_id_usuario foreign key(id_usuario) references tbl_usuario(id_usuario)

);

