create table meetup
(
    id                    SERIAL primary key,
    active                bool,
    closed                bool,
    date                  date,
    extended_registration bool
);

create table attendees
(
    id              SERIAL primary key,
    meetup_id       int references meetup (id) not null,
    username        text not null,
    first_name      text,
    last_name       text,
    date_of_birth   date,
    registry_date   date not null,
    attendee_status text not null,
    companies       int not null
);
