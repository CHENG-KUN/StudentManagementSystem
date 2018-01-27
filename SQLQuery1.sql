--create students table

create table stu
(stuId varchar(30) primary key,
stuFirstName nvarchar(50) not null,
stuLastName nvarchar(50) not null,
stuSex nchar(1) check (stuSex in ('m', 'f')) default 'm',
stuAge int check (stuAge > 1),
stuDept nvarchar (40)
)

insert into stu values
('sp001', 'John', 'Lennon', 'm', 30, 'music');
insert into stu values
('sp002', 'Paul', 'McCartney', 'm', 32, 'business');
insert into stu values
('sp003', 'George', 'Harrison', 'm', 28, 'history');
insert into stu values
('sp004', 'Ringo', 'Starr', 'm', 33, 'art');