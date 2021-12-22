-- TAMBAH CUSTOMER BARU -> Dimas T.M
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


-- DELETING CUSTOMER -> Dimas T.M
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


-- MENGAMBIL DATA PELANGGAN -> Dimas T.M
go
create proc get_data_pelanggan
as
select cp.id_pelanggan, cp.nama_pelanggan, cp.tanggal_lahir, cp.nomor_hp, cp.jenis_kelamin, ca.email,
    cp.jalan + ' no ' + cp.nomor_rumah + ' desa/kecamatan ' + cp.desa_kecamatan + ' kabupaten/kota ' + cp.kabupaten_kota + ' kode pos ' + cp.kode_pos as alamat,
    (select count(id_pelanggan) from order_product op where op.id_pelanggan=cp.id_pelanggan) as jumlah_order
from customer_profile cp
join customer_account ca on cp.id_pelanggan=ca.id_pelanggan


-- MENGAMBIL PROFIL PELANGGAN -> Varel Y.S
go
create procedure get_profil_pelanggan 
    @email varchar(100), @password varchar(40) 
as
select * from customer_account ca join customer_profile cp on ca.id_pelanggan = cp.id_pelanggan
where email=@email and password=@password


-- MENGUPDATE AKUN PELANGGAN -> Varel Y.S
go
create procedure update_akun_pelanggan 
    @id int, @email varchar(100), @password varchar(40) 
as
update customer_account set email=@email, password=@password
where id_pelanggan=@id


-- UPDATE PROFIL PELANGGAN -> Varel Y.S
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


-- MENDAPATKAN DATA PELANGGAN SPESIFIK -> Varel Y.S
go
create procedure get_specific_pelanggan @id int
as
select ca.id_pelanggan, ca.email, ca.[password], cp.nama_pelanggan, cp.tanggal_lahir, cp.nomor_hp, cp.nomor_rumah,
    cp.desa_kecamatan, cp.kabupaten_kota, cp.jalan, cp.jenis_kelamin, cp.kode_pos
from customer_account ca
join customer_profile cp
on ca.id_pelanggan=cp.id_pelanggan
where ca.id_pelanggan = @id


-- MENCARI PELANGGAN -> Varel Y.S
go
create procedure cari_pelanggan
    @masukan varchar(100)
as
declare @kataKunci varchar(102) = '%' + @masukan + '%';
select * from
    (select cp.id_pelanggan, cp.nama_pelanggan, cp.tanggal_lahir, cp.nomor_hp, cp.jenis_kelamin, ca.email,
        cp.jalan + ' no ' + cp.nomor_rumah + ' desa/kecamatan ' + cp.desa_kecamatan + ' kabupaten/kota ' + cp.kabupaten_kota + ' kode pos ' + cp.kode_pos as alamat,
        (select count(id_pelanggan) from order_product op where op.id_pelanggan=cp.id_pelanggan) as jumlah_order
    from customer_profile cp
    join customer_account ca on cp.id_pelanggan=ca.id_pelanggan) as data_pelanggan
where nama_pelanggan like @kataKunci or
    tanggal_lahir like @kataKunci or
    nomor_hp like @kataKunci or
    jenis_kelamin like @kataKunci or
    email like @kataKunci or
    alamat like @kataKunci


-- EDIT PROFIL + AKUN PELANGGAN -> Varel Y.S
go
create procedure edit_profile_customer
    @id int, @email varchar(100), @password varchar(40),
    @nama varchar(20), @tgl date, @nomorHp varchar(14), 
    @nomorRumah varchar (5), @desaKec varchar(30), 
    @kabKota varchar(30), @jalan varchar(50), 
    @jk varchar(1), @kodePos varchar(10) as
begin transaction epc
begin try
	update customer_account set email=@email, password=@password
	where id_pelanggan=@id
	update customer_profile set nama_pelanggan=@nama, tanggal_lahir=@tgl,
	nomor_hp=@nomorHp, nomor_rumah=@nomorRumah ,desa_kecamatan=@desaKec,
	kabupaten_kota=@kabKota, jalan=@jalan, jenis_kelamin=@jk, kode_pos=@kodePos
	where id_pelanggan=@id
	if @@TRANCOUNT>0
		begin commit transaction trans
	end
end try
begin catch
	print 'Error Occurred: Rollback All'
	if @@TRANCOUNT >0
		begin rollback transaction trans
	end
end catch
go

--TAMBAH AKUN PELANGGAN -> Gaffy R.M.A
go
create procedure add_customer_account
	@email varchar(50),
	@password varchar(255)
as
insert into customer_account (email, password)
values (@email, @password);

-- TAMBAH PROFIL PELANGGAN -> Gaffy R.M.A
go
create proc add_customer_profile
	@id_pelanggan int,
	@nama_pelanggan varchar(255),
	@tanggal_lahir date,
	@nomor_hp varchar(25),
	@nomor_rumah varchar(5),
	@desa_kecamatan varchar(50),
	@kabupaten_kota varchar(50),
	@jalan varchar(50),
	@jenis_kelamin char(1),
	@kode_pos varchar(5)
as
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos)
values (@id_pelanggan, @nama_pelanggan,@tanggal_lahir ,@nomor_hp ,@nomor_rumah , @desa_kecamatan,@kabupaten_kota , @jalan,@jenis_kelamin ,@kode_pos);
