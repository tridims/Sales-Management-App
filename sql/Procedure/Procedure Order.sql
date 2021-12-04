-- DELETE RIWAYAT PESANAN (ORDER)
go
create procedure delete_order @id int
as
delete from order_product where order_id = @id


-- MENDAPATKAN DAFTAR PRODUK PADA SUATU ORDER
go
create procedure get_list_product_from_order @orderId int
as
select p.nama_produk, p.kategori, op.kuantitas, op.harga_product, s.subtotal
from ordered_product op
join produk p on op.product_id=p.product_id
join subtotal s on op.order_id=s.order_id and op.product_id=s.product_id
where op.order_id = @orderId


-- MENDAPATKAN DAFTAR ODER DARI SUATU PELANGGAN
go
create procedure get_order_pelanggan @idPelanggan int
as
select order_id, status_order, tanggal_kirim, (select nama from karyawan k where k.id_karyawan=op.id_karyawan) as karyawan
from order_product op
where op.id_pelanggan=@idPelanggan
order by tanggal_kirim asc


-- MENDAPATKAN DATA ORDER MASUK (Semua, selesai, belum selesai)
go
create procedure get_data_order_masuk
as
select op.order_id, cp.nama_pelanggan, op.tanggal_kirim, op.status_order, k.nama
from order_product op
join customer_profile cp on op.id_pelanggan=cp.id_pelanggan
join karyawan k on op.id_karyawan=k.id_karyawan
order by op.tanggal_kirim desc

go
create procedure get_data_order_masuk_belum_selesai
as
select op.order_id, cp.nama_pelanggan, op.tanggal_kirim, op.status_order, k.nama
from order_product op
join customer_profile cp on op.id_pelanggan=cp.id_pelanggan
join karyawan k on op.id_karyawan=k.id_karyawan
where op.status_order=0
order by op.tanggal_kirim desc

go 
create procedure get_data_order_masuk_selesai
as
select op.order_id, cp.nama_pelanggan, op.tanggal_kirim, op.status_order, k.nama
from order_product op
join customer_profile cp on op.id_pelanggan=cp.id_pelanggan
join karyawan k on op.id_karyawan=k.id_karyawan
where op.status_order=1
order by op.tanggal_kirim desc


-- SET STATUS ORDER
go
create procedure set_status_order
    @id int,
    @status bit
as
update order_product
set status_order=@status
where order_id=@id


-- VAREL Y. S
-- ORDER PRODUCT BARU -> VAREL
go
create procedure new_order_product 
    @date date, @status bit, @idPel int, @idProd int as
insert into order_product values(@date, @status, @idPel, @idProd)
go

-- ORDERED PRODUCT BARU -> VAREL
create procedure new_ordered_product 
    @order_id int, @product_id int, @kuantitas int, @harga int
as
insert into ordered_product 
values (@order_id, @product_id, @kuantitas, @harga)
go


-- DETAIL ORDERED PRODUCT -> VAREL
create procedure get_ordered_product_detail @order_id int as
select * from ordered_product op join produk p on op.product_id=p.product_id
where op.order_id=@order_id
go

-- DELETE ORDERED PRODUCT -> VAREL
create procedure delete_ordered_product 
	@order_id int, @product_id int as
delete from ordered_product where order_id=@order_id and product_id=@product_id
go

-- DETAIL ORDERED PRODUCT (KHUSUS UNTUK MENCARI PRODUCT_ID) -> VAREL
create procedure get_ordered_product_detail2 @order_id int, @namaProduk varChar(30) as
select op.product_id from ordered_product op join produk p on op.product_id=p.product_id
where op.order_id=@order_id and p.nama_produk=@namaProduk
go

-- TRANSACT DELETE ORDERAN -> VAREL
create procedure delete_order_product 
	@order_id int 
as
delete from order_product where order_id=@order_id

-- UPDATE STOK PRODUK -> VAREL
go
create procedure update_stok_produk 
	@product_id int, @jumlah int 
as
update produk set jumlah_stok=(jumlah_stok-@jumlah) where product_id=@product_id
go


--GET RIWAYAT PESANAN -> GAFFY
go
create proc get_riwayatPesanan
	@id_pelanggan int
as
select order_id, tanggal_kirim, status_order
from order_product where id_pelanggan = @id_pelanggan;

--GET DETAIL PESANAN -> GAFFY
go
create proc get_detailpesanan
	@order_id int
as
select op.order_id,p.product_id, p.nama_produk, p.kategori, op.harga_product, op.kuantitas, (op.harga_product* op.kuantitas)as subtotal
from ordered_product op
join produk p on p.product_id = op.product_id
where op.order_id = @order_id;


--GET PESANAN AKTIF -> GAFFY
go
create proc get_pesananaktif
	@id_pelanggan int
as
select tanggal_kirim, order_id
from order_product
where id_pelanggan = @id_pelanggan and status_order = 0;
