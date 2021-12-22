-- MENDAPATKAN TANGGAL KIRIM -> VAREL Y.S
go
create function get_tanggal_kirim ()
returns date
as
begin
return dateadd(day, 7, (select cast (GETDATE() as date)))
end
go

-- MENDAPATKAN ID DARI KARYAWAN DENGAN JUMLAH ORDER PALING SEDIKIT -> Dimas T.M
-- SEBAGAI KARYAWAN YANG MENGURUS ORDERAN BERIKUTNYA
go
create function get_karyawan()
    returns int
as
begin return(
    select id_karyawan from 
        (select top 1 k.id_karyawan, count(order_id) as jumlah_order
        from order_product op
        join karyawan k on k.id_karyawan=op.id_karyawan
        group by k.id_karyawan
        order by jumlah_order asc
        ) r
)
end

