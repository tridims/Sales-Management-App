go
create trigger tambah_stok_produk
on supplied_product
after insert
as
    update produk
    set jumlah_stok = jumlah_stok + jumlah_produk
    from inserted
    where produk.product_id = inserted.product_id

go 
create trigger kurangi_stok_produk
on supplied_product
after delete
as
    update produk
    set jumlah_stok = (select iif((jumlah_stok - jumlah_produk)>0, jumlah_stok - jumlah_produk, 0) 
    from deleted 
    where produk.product_id = deleted.product_id)

go 
create trigger kurangi_stok_dari_batal_order
on ordered_product
after delete
as
    update produk
    set jumlah_stok = (select iif((jumlah_stok - jumlah_produk)>0, jumlah_stok - jumlah_produk, 0) 
    from deleted 
    where produk.product_id = deleted.product_id)
