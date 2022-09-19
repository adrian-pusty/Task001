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

create table CAR
(
    id int not null,
    customerId int,
    primary key (id),
    foreign key (customerId) references CUSTOMER(id)
);

create table SERVICE -- table for services that are needed for a specific car, services that have no schedule (like repair after crash) could have interval = -1 or null
(
    id int not null,
    primary key (id),
    carId int, -- maybe it would be worth to consider creating a join table CAR_CUSTOMER and refer to record from this table because car can change its owner...
    serviceName varchar(255),
    intervalInDays int,
    foreign key (carId) references CAR(id)
);

create table SERVICE_LOG
(
    id int not null,
    type int,
    carReported date,
    carRepaired date,
    carPaidUp date,
    carReclaimed date,
    primary key (id),
    foreign key (type) references SERVICE(id)
);

create table WORKER
(
    id int not null,
    specId int,
    name varchar(255),
    foreign key (specId) references SPECIALISATION(id)
);

create table SERVICELOG_WORKER
(
    serviceLogId int,
    workerId int,
    hoursSpent int,
    foreign key (serviceLogId) references SERVICE_LOG(id),
    foreign key (workerId) references WORKER(id)
);

create table SERVICELOG_PART
(
    serviceLogId int,
    partId int,
    amount int,
    foreign key (serviceLogId) references SERVICE_LOG(id),
    foreign key (partId) references PART(id)
);