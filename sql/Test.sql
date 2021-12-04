go
create database Testing
go
use Testing
go
create table testing (
    id int primary key identity(1,1),
    tanggal date not null
)
go

create table test2 (
    id int primary key identity(1,1),
    gaji int
)

insert into test2 values (5000)

select * from test2
where gaji like '%10%'


select * from kategori
select product_id, nama_produk from produk

select * from order_product
select * from ordered_product
select * from produk where product_id = 40