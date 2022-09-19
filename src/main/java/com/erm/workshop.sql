create table SERVICE_WORKER
(
    serviceId int,
    workerId int,
    hoursSpent int,
    foreign key (serviceId) references SERVICE(id),
    foreign key (workerId) references WORKER(id)
);

create table SERVICE_PART
(
    serviceId int,
    partId int,
    amount int,
    foreign key (serviceId) references SERVICE(id),
    foreign key (partId) references PART(id)
);

create table SERVICE
(
    id int not null,
    carId int, -- maybe it would be worth to consider creating a join table CAR_CUSTOMER and refer to record from this table because car can change its owner...
    carReported date,
    carRepaired date,
    carPaidUp date,
    carReclaimed date,
    primary key (id),
    foreign key (customerId) references CUSTOMER(id)
);

create table WORKER
(
    id int not null,
    specId int,
    name varchar(255),
    foreign key (specId) references SPECIALISATION(id)
);

create table SERVICE_TYPE -- table for services that are needed for a specific car, services that have no schedule (like repair after crash) could have interval = -1 or null
(
    id int not null,
    primary key (id),
    carId int,
    serviceName varchar(255),
    intervalInDays int,
    foreign key (carId) references CAR(id)
);

create table CAR
(
    id int not null,
    customerId int,
    primary key (id),
    foreign key (customerId) references CUSTOMER(id)
);

create table PART
(
    id int not null,
    name varchar(255),
    price double,
    primary key (id)
);

create table CUSTOMER
(
    id int not null,
    primary key (id)
);

create table SPECIALISATION
(
    id int not null,
    name varchar(255),
    hourlyPay double
)