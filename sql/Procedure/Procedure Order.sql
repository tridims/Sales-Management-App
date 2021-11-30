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
