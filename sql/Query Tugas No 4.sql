-- a. Apa saja 5 produk dengan penjualan tertinggi dari tiap toko/pembeli (merchant)? (atau
-- pertanyaan sejenis, bisa yang bukan makanan produk dari sistem yang dibuat)

-- 5 PRODUK PENJUALAN TERTINGGI DARI SETIAP KATEGORI



-- b. Apa 5 toko yang memiliki penjualan tertinggi dalam kurun waktu terakhir? (atau pertanyaan
-- sejenis, bisa yang bukan toko tergantung dari sistem yang dibuat)

-- 5 SUPPLIER YANG PALING BANYAK MENSUPPLY PRODUK DALAM KURUN WAKTU TERAKHIR



-- ##################################################################################################
-- 5 KARYAWAN YANG PALING BANYAK MENANGANI ORDER DALAM KURUN WAKTU TERAKHIR

go
create procedure karyawan_teraktif_sebulan_terakhir
as
declare @tanggalBulanLalu date = dateadd(month, -1, convert(date, getdate()));
with temp(id_karyawan, jumlah_order)
as
    (select top 5 id_karyawan, count(order_id) as jumlah_order
    from order_product
    where order_product.tanggal_kirim >= @tanggalBulanLalu
    group by id_karyawan
    order by jumlah_order desc)
select nama, c.nama_cabang, jumlah_order 
from temp t join karyawan k
on t.id_karyawan=k.id_karyawan
join cabang c on k.id_cabang=c.id_cabang


-- ##################################################################################################
-- c. Apa 3 barang yang paling banyak dibeli berbarengan dengan pembelian ‘sesuatu’? (atau
-- pertanyaan sejenis, bisa yang bukan makanan tergantung dari sistem yang dibuat)

-- 3 BARANG YANG PALING BANYAK DIBELI BERSAMAAN DENGAN PRODUK BUAH APEL
-- @BENDA ITU PRODUCT_ID-NYA BENDA YANG AKAN DIPASANGKAN, 
-- MISALNYA 3 BENDA TERLARIS YANG DIJUAL BERSAMAN DENGAN APEL
-- @BENDA MENJADI PRODUCT_ID NYA APEL
go
create procedure tigaTertinggiCampur @benda int as
with temp(order_id, product_id, kuantitas) 
as 
    (select order_id, product_id, kuantitas 
    from ordered_product 
    where order_id = any(
        select order_id 
        from ordered_product 
        group by order_id 
        having count(product_id)>1
        )
    )
select p.nama_produk, p.kategori, res.kuantitas
from 
    (select top 3 product_id, sum(kuantitas) kuantitas 
    from temp
    where product_id != @benda
    group by product_id
    order by kuantitas desc) as res
join produk p on p.product_id=res.product_id


exec tigaTertinggiCampur 1

-- ##################################################################################################
