create table Accessories (classroom varchar(100) primary key,
						  blackboard boolean,
						  whiteboard boolean,
                          projector boolean,
                          laboratory varchar(100));

alter table Accessories add foreign key (classroom) references classroom(nome)