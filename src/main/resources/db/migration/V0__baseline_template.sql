create table meetup
(
    id                    SERIAL primary key,
    active                bool not null,
    closed                bool not null,
    date                  date not null,
    extended_registration bool not null,
    max_attendees         int
);

create table attendees
(
    id              SERIAL primary key,
    meetup_id       int references meetup (id) not null,
    username        text                       not null,
    first_name      text,
    last_name       text,
    date_of_birth   date,
    registry_date   date                       not null,
    attendee_status text                       not null,
    companies       int                        not null
);
