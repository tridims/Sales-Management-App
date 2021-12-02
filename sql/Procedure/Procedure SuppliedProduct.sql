-- MENGAMBIL SUPPLIED PRODUCT DARI SUPPLIER TERTENTU
go
create procedure get_supplied_product @id_supplier int
as
select sp.id, sp.id_supplier, p.nama_produk, sp.jumlah_produk, sp.tanggal
from supplied_product sp
join produk p on p.product_id=sp.product_id
where sp.id_supplier = @id_supplier


-- MENAMBAH SUPPLIED_PRODUCT BARU
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


-- MENGHAPUS SUPPLIED_PRODUCT
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
