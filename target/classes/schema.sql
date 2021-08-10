CREATE TABLE bank (
	bank_uuid UUID not null,
        bank_name varchar(50),
        primary key (bank_uuid)
);

CREATE TABLE bank_client (
	bank_uuid UUID not null,
        client_uuid UUID not null,
        primary key (bank_uuid, client_uuid)
);

CREATE TABLE client (
	client_uuid UUID not null,
	    full_name varchar(100),
	    phone_number varchar(15),
        email varchar(320),
        passport_number varchar(20),
        primary key (client_uuid)
);

CREATE TABLE credit (
	credit_uuid UUID not null,
	    credit_name varchar(50),
        credit_limit bigint check (credit_limit>=1 AND credit_limit<=99999999999),
        interest_rate float(24),
        bank_uuid UUID,
        primary key (credit_uuid)
);


CREATE TABLE credit_offer (
	credit_offer_uuid UUID not null,
	    client_uuid UUID,
        credit_uuid UUID,
        amount bigint check (amount>=1 AND amount<=99999999999),
        interest_total bigint check (interest_total>=1 AND interest_total<=99999999999),
        credit_term integer check (credit_term>=1 AND credit_term<=240),
        first_payment_date date,
        primary key (credit_offer_uuid)
);

CREATE TABLE payment (
	payment_uuid UUID not null,
        date date,
        payment_amount bigint check (payment_amount>=0 AND payment_amount<=99999999999),
        principal_amount bigint check (principal_amount>=0 AND principal_amount<=99999999999),
        interest_amount bigint check (interest_amount>=0 AND interest_amount<=99999999999),
        credit_offer_uuid UUID,
        primary key (payment_uuid)
);
