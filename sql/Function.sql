-- MENDAPATKAN TANGGAL KIRIM -> VAREL
go
create function get_tanggal_kirim ()
returns date
as
begin
return dateadd(day, 7, (select cast (GETDATE() as date)))
end
go

select dbo.get_tanggal_kirim ()

