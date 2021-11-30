-- MENGAMBIL DATA CABANG (MENU ADMIN)
go
create procedure get_data_cabang
as
select id_cabang, nama_cabang, alamat_cabang, (select count(*) from karyawan where karyawan.id_cabang=cabang.id_cabang) as jumlah_karyawan
from cabang


-- CABANG BARU
go
create procedure new_cabang
    @nama varchar(100),
    @alamat text
as
insert into cabang values (@nama, @alamat)


-- DELETE CABANG
go
create procedure delete_cabang @id int
as
delete from cabang where id_cabang = @id


-- EDIT CABANG
go
create procedure edit_cabang
    @id int, @nama varchar(100), @alamat text
as
update cabang
set nama_cabang=@nama, alamat_cabang=@alamat