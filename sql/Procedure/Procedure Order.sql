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