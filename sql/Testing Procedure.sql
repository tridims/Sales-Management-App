-- Testing procedure

-- mengambil data karyawan (menu admin)
go
create proc get_data_karyawan
as
select k.id_karyawan, k.nama, k.jenis_kelamin, k.tanggal_lahir, k.nomor_hp, k.email, k.alamat, k.jabatan,
    c.nama_cabang, j.gaji
from karyawan k
join cabang c on k.id_cabang=c.id_cabang
join jabatan j on j.nama_jabatan=k.jabatan
go

exec get_data_karyawan

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

exec get_data_pelanggan

-- mengambil data supplier
go
create proc get_data_supplier
as
select s.id_supplier, s.nama_supplier, s.alamat, s.nomor_hp, s.email, 
    (select count(*) from supplied_product sp where sp.id_supplier=s.id_supplier) as jumlah_produk
from supplier s
go
exec get_data_supplier

-- mengambil supplied product dari supplier tertentu
go
create procedure get_supplied_product @id_supplier int
as
select sp.id_supplier, p.nama_produk, sp.jumlah_produk, sp.tanggal
from supplied_product sp
join produk p on p.product_id=sp.product_id
where sp.id_supplier = @id_supplier
go

exec get_supplied_product 3