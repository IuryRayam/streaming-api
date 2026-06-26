create table director(
    id uuid not null primary key,
    name varchar(100) not null,
    date_of_birth date not null,
    nationality varchar(50) not null,
    registration_date timestamp,
    update_date timestamp,
    id_user uuid
);

create table movie(
    id uuid not null primary key,
    title varchar(150) not null,
    release_date date not null,
    duration int not null,
    rating int not null,
    studio varchar(150) not null,
    gender varchar(30) not null,
    price numeric(10, 2),
    id_director uuid not null references director(id),

    constraint chk_gender check (gender in ('FICTION', 'FANTASY', 'MYSTERY', 'ROMANCE', 'DRAMA'))
);