-- MENGAMBIL DATA SUPPLIER
go
create proc get_data_supplier
as
select s.id_supplier, s.nama_supplier, s.alamat, s.nomor_hp, s.email, 
    (select count(*) from supplied_product sp where sp.id_supplier=s.id_supplier) as jumlah_produk
from supplier s
go

-- MENGAMBIL DATA SUPPLIER SPECIFIC
go
create procedure get_specific_supplier @id int
as
select nama_supplier, alamat, nomor_hp, email, kode_pos from supplier
where id_supplier = @id


-- TAMBAH SUPPLIER
go
create procedure new_supplier
    @nama varchar(255),
    @alamat text,
    @nomor varchar(20),
    @email varchar(50),
    @pos varchar(5)
as
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) 
values (@nama, @alamat, @nomor, @email, @pos);


-- UPDATE DATA SUPPLIER
go
create procedure update_supplier
    @id int,
    @nama varchar(255),
    @alamat text,
    @nomor varchar(20),
    @email varchar(50),
    @pos varchar(5)
as
update supplier
set nama_supplier = @nama,
    alamat = @alamat,
    nomor_hp = @nomor, 
    email = @email,
    kode_pos = @pos
where id_supplier = @id


-- MENGHAPUS SUPPLIER
go
create procedure delete_supplier @id int
as
delete from supplier where id_supplier = @id

