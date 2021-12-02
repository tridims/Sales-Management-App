-- MENGAMBIL DATA PRODUK (TABEL MENU ADMIN)
go
create procedure get_data_tabel_produk
as
select product_id, nama_produk, jumlah_stok, harga_satuan, kategori from produk
go

-- MELAKUKAN PENCARIAN PRODUK
go
create procedure cari_produk
    @masukan varchar(253)
as
declare @pola varchar(255) = '%' + @masukan + '%';
select product_id, nama_produk, jumlah_stok, harga_satuan, kategori 
from produk
where nama_produk like @pola or jumlah_stok like @pola or
    harga_satuan like @pola or kategori like @pola

-- DATA SPESIFIK PRODUK
go
create procedure get_detail_produk @idProduk int
as
select nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori 
from produk
where produk.product_id = @idProduk
go


-- UPDATE DETAIL PRODUCT
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


-- TAMBAH PRODUK BARU
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


-- DELETE PRODUCT
go
create procedure delete_product @id int
as
delete from produk where product_id = @id
go
