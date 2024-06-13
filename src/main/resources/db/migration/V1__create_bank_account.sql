create schema bank;
create table bank.account(
    id_bank_account int primary key ,
    num_bank_accounts numeric(20,0) not null,
    amount money default 0
);
insert into bank.account(id_bank_account, num_bank_accounts, amount)
VALUES(1,1,100000.98);
insert into bank.account(id_bank_account, num_bank_accounts, amount)
VALUES (2, 2, 98000.34);