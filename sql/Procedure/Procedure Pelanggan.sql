-- TAMBAH CUSTOMER BARU
go
create procedure add_customer
    @email varchar(50), @password varchar(255),
    @nama varchar(20), @tgl date, @nomorHp varchar(14), 
    @nomorRumah varchar (5), @desaKec varchar(30), @kabKota varchar(30), 
    @jalan varchar(50), @jk varchar(1), @kodePos varchar(10)
as
begin tran
begin try
    insert into customer_account 
    values (@email, @password);

    declare @id as int = (select max(id_pelanggan) from customer_account)

    insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) 
    values (@id, @nama, @tgl, @nomorHp, @nomorRumah, @desaKec, @kabKota, @jalan, @jk, @kodePos);

    if @@trancount > 0
        begin commit tran end
end try
begin catch
    if @@trancount > 0
        begin rollback tran end
end catch


-- DELETING CUSTOMER
go
create procedure delete_customer
    @id int
as
begin transaction
begin try
    delete from customer_account
    where id_pelanggan = @id

    delete from customer_profile
    where id_pelanggan = @id

    if @@trancount > 0
        begin commit tran end
end try
begin catch
    if @@trancount > 0
        begin rollback tran end
end catch


-- MENGAMBIL DATA PELANGGAN (MENU ADMIN)
go
create proc get_data_pelanggan
as
select cp.id_pelanggan, cp.nama_pelanggan, cp.tanggal_lahir, cp.nomor_hp, cp.jenis_kelamin, ca.email,
    cp.jalan + ' no ' + cp.nomor_rumah + ' desa/kecamatan ' + cp.desa_kecamatan + ' kabupaten/kota ' + cp.kabupaten_kota + ' kode pos ' + cp.kode_pos as alamat,
    (select count(id_pelanggan) from order_product op where op.id_pelanggan=cp.id_pelanggan) as jumlah_order
from customer_profile cp
join customer_account ca on cp.id_pelanggan=ca.id_pelanggan


-- MENGAMBIL PROFIL PELANGGAN
go
create procedure get_profil_pelanggan 
    @email varchar(100), @password varchar(40) 
as
select * from customer_account ca join customer_profile cp on ca.id_pelanggan = cp.id_pelanggan
where email=@email and password=@password


-- MENGUPDATE AKUN PELANGGAN
go
create procedure update_akun_pelanggan 
    @id int, @email varchar(100), @password varchar(40) 
as
update customer_account set email=@email, password=@password
where id_pelanggan=@id



-- UPDATE PROFIL PELANGGAN
go
create procedure update_profil_pelanggan 
    @id int, @nama varchar(20), @tgl date, @nomorHp varchar(14), 
    @nomorRumah varchar (5), @desaKec varchar(30), @kabKota varchar(30), 
    @jalan varchar(50), @jk varchar(1), @kodePos varchar(10) 
as
update customer_profile 
set nama_pelanggan=@nama, tanggal_lahir=@tgl,
    nomor_hp=@nomorHp, nomor_rumah=@nomorRumah ,desa_kecamatan=@desaKec,
    kabupaten_kota=@kabKota, jalan=@jalan, jenis_kelamin=@jk, kode_pos=@kodePos
where id_pelanggan=@id


-- MENDAPATKAN DATA PELANGGAN SPESIFIK
go
create procedure get_specific_pelanggan @id int
as
select ca.id_pelanggan, ca.email, ca.[password], cp.nama_pelanggan, cp.tanggal_lahir, cp.nomor_hp, cp.nomor_rumah,
    cp.desa_kecamatan, cp.kabupaten_kota, cp.jalan, cp.jenis_kelamin, cp.kode_pos
from customer_account ca
join customer_profile cp
on ca.id_pelanggan=cp.id_pelanggan
where ca.id_pelanggan = @id


