-- Testing procedure

-- mengambil data karyawan (menu admin)
go
create proc get_data_karyawan
as
select k.id_karyawan, k.nama, k.jenis_kelamin, k.tanggal_lahir, k.nomor_hp, k.email, k.alamat, k.jabatan,
    c.nama_cabang, j.gaji
from karyawan k
left outer join cabang c on k.id_cabang=c.id_cabang
left outer join jabatan j on j.nama_jabatan=k.jabatan
go

-- exec get_data_karyawan

-- mengambil data pelanggan (menu admin)
go
create proc get_data_pelanggan
as
select cp.id_pelanggan, cp.nama_pelanggan, cp.tanggal_lahir, cp.nomor_hp, cp.jenis_kelamin, ca.email,
    cp.jalan + ' no ' + cp.nomor_rumah + ' desa/kecamatan ' + cp.desa_kecamatan + ' kabupaten/kota ' + cp.kabupaten_kota + ' kode pos ' + cp.kode_pos as alamat,
    (select count(id_pelanggan) from order_product op where op.id_pelanggan=cp.id_pelanggan) as jumlah_order
from customer_profile cp
join customer_account ca on cp.id_pelanggan=ca.id_pelanggan
go

-- exec get_data_pelanggan

-- mengambil data supplier
go
create proc get_data_supplier
as
select s.id_supplier, s.nama_supplier, s.alamat, s.nomor_hp, s.email, 
    (select count(*) from supplied_product sp where sp.id_supplier=s.id_supplier) as jumlah_produk
from supplier s
go

-- exec get_data_supplier

-- Mengambil data supplier specific : digunakan untuk mengisi data pada form edit supplier
go
create procedure get_specific_supplier @id int
as
select nama_supplier, alamat, nomor_hp, email, kode_pos from supplier
where id_supplier = @id

-- exec get_specific_supplier 1

-- mengambil supplied product dari supplier tertentu
go
create procedure get_supplied_product @id_supplier int
as
select sp.id, sp.id_supplier, p.nama_produk, sp.jumlah_produk, sp.tanggal
from supplied_product sp
join produk p on p.product_id=sp.product_id
where sp.id_supplier = @id_supplier
go

-- exec get_supplied_product 2

-- mengambil data cabang (menu admin)
go

create procedure get_data_cabang
as
select id_cabang, nama_cabang, alamat_cabang, (select count(*) from karyawan where karyawan.id_cabang=cabang.id_cabang) as jumlah_karyawan
from cabang
go
-- exec get_data_cabang


-- mengambil data produk (tabel menu admin)
go
create procedure get_data_tabel_produk
as
select product_id, nama_produk, jumlah_stok, harga_satuan, kategori from produk
go
-- exec get_data_tabel_produk

-- mengambil data karyawan pada suatu cabang
go
create procedure get_karyawan_at_cabang @idCabang int
as
select k.nama, k.jabatan, j.gaji from karyawan k
join jabatan j on k.jabatan=j.nama_jabatan
where k.id_cabang = @idCabang
go
-- exec get_karyawan_at_cabang 1

-- detail produk
go
create procedure get_detail_produk @idProduk int
as
select nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori from produk
where produk.product_id = @idProduk
go
-- exec get_detail_produk 1

-- update detail product
go
create procedure update_detail_product 
    @id int,
    @nama varchar(255),
    @jumlah int,
    @harga int,
    @desc text,
    @nutrition text,
    @kat varchar(100)
as
update produk
set nama_produk = @nama, 
    jumlah_stok = @jumlah, 
    harga_satuan = @harga, 
    deskripsi = @desc,
    nutrition_facts = @nutrition, 
    kategori = @kat
where product_id = @id

go

-- tambah produk baru
go
create proc new_product 
    @nama varchar(255),
    @jumlah int,
    @harga int,
    @desc text,
    @nutrition text,
    @kat varchar(100)
as
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) 
values (@nama, @jumlah, @harga, @desc, @nutrition, @kat);
go

-- delete product
go
create procedure delete_product @id int
as
delete from produk where product_id = @id
go

-- cabang baru
go
create procedure new_cabang
    @nama varchar(100),
    @alamat text
as
insert into cabang values (@nama, @alamat)
go

-- delete cabang
go
create procedure delete_cabang @id int
as
delete from cabang where id_cabang = @id

-- tambah supplier
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

-- exec new_supplier 'dimas', 'dimas', 'dimas', 'dimas@gmail', '12345'

-- update data supplier
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

-- exec update_supplier 6, 'testing', 'testing', '932438', 'testing@gemail', '12345'

-- menghapus supplier
go
create procedure delete_supplier @id int
as
delete from supplier where id_supplier = @id

-- exec delete_supplier 10


-- menambah supplied_product baru : TambahDataSupply : otomatis mengupdate jumlah stok produk di bagian produk
go
create procedure add_supplied_product
    @idProduk int,
    @idSupplier int,
    @jumlah int
as
begin tran
begin try
    insert into supplied_product (product_id, id_supplier, jumlah_produk, tanggal)
    values (@idProduk, @idSupplier, @jumlah, getdate());

    update produk
    set jumlah_stok = (jumlah_stok + @jumlah)
    where product_id = @idProduk

    if @@trancount > 0
        begin commit tran end
end try
begin catch
    if @@trancount > 0
        begin rollback tran end
end catch

-- menghapus supplied_product
go
create procedure delete_supplied_product
    @id int
as
begin tran
begin try
    update produk
    set jumlah_stok = (select iif((jumlah_stok - jumlah_produk)>0, jumlah_stok - jumlah_produk, 0) 
        from supplied_product where id = @id)
    where product_id = (select sp.product_id from supplied_product sp where id = @id)

    delete from supplied_product where id = @id;

    if @@trancount > 0
        begin commit tran end
end try
begin catch
    if @@trancount > 0
        begin rollback tran end
end catch

-- exec delete_supplied_product 4

-- tambah karyawan
go
create procedure add_karyawan
    @nama varchar(255),
    @jenisKelamin char(1),
    @tanggalLahir date,
    @nomor varchar(25),
    @email varchar(50),
    @alamat text,
    @cabang int,
    @jabatan varchar(50)
as
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) 
values (@nama, @jenisKelamin, @tanggalLahir, @nomor, @email, @alamat, @cabang, @jabatan);

-- exec add_karyawan 'dimastri', 'L', '2000-12-12', '089656565', 'test@gmail.com', 'Malang', 1, 'Manager' 

