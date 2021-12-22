-- MENGAMBIL DATA KARYAWAN -> DIMAS T.M
go
create proc get_data_karyawan
as
select k.id_karyawan, k.nama, k.jenis_kelamin, k.tanggal_lahir, k.nomor_hp, k.email, k.alamat, k.jabatan,
    c.nama_cabang, j.gaji
from karyawan k
left outer join cabang c on k.id_cabang=c.id_cabang
left outer join jabatan j on j.nama_jabatan=k.jabatan
go


-- MENGAMBIL DATA KARYAWAN PADA SUATU CABANG -> DIMAS T.M
go
create procedure get_karyawan_at_cabang @idCabang int
as
select k.nama, k.jabatan, j.gaji from karyawan k
join jabatan j on k.jabatan=j.nama_jabatan
where k.id_cabang = @idCabang
go


-- TAMBAH KARYAWAN BARU -> DIMAS T.M
go
create procedure add_karyawan
    @nama varchar(255),
    @jenisKelamin char(1),
    @tanggalLahir date,
    @nomor varchar(25),
    @email varchar(50),
    @alamat text,
    @cabang int,
    @jabatan varchar(50)
as
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) 
values (@nama, @jenisKelamin, @tanggalLahir, @nomor, @email, @alamat, @cabang, @jabatan);


-- HAPUS KARYAWAN -> DIMAS T.M
go
create procedure delete_karyawan @id int
as
delete from karyawan where id_karyawan = @id


-- UPDATE KARYAWAN -> DIMAS T.M
go
create procedure update_karyawan
    @id int,
    @nama varchar(255),
    @jenisKelamin char(1),
    @tanggalLahir date,
    @nomor varchar(25),
    @email varchar(50),
    @alamat text,
    @cabang int,
    @jabatan varchar(50)
as
update karyawan
set nama = @nama, jenis_kelamin = @jenisKelamin, tanggal_lahir = @tanggalLahir,
    nomor_hp = @nomor, email = @email, alamat = @alamat, id_cabang = @cabang, jabatan = @jabatan
where id_karyawan = @id


-- SEARCH KARYAWAN -> DIMAS T.M
go
create procedure cari_karyawan 
    @masukan varchar(255)
as
declare @kataKunci varchar(102) = '%' + @masukan + '%';
select * from 
    (select k.id_karyawan, k.nama, k.jenis_kelamin, k.tanggal_lahir, k.nomor_hp, k.email, k.alamat, k.jabatan,
        c.nama_cabang, j.gaji
    from karyawan k
    left outer join cabang c on k.id_cabang=c.id_cabang
    left outer join jabatan j on j.nama_jabatan=k.jabatan) as data_karyawan
where nama like @kataKunci or
    jenis_kelamin like @kataKunci or
    tanggal_lahir like @kataKunci or
    nomor_hp like @kataKunci or
    email like @kataKunci or
    alamat like @kataKunci or 
    jabatan like @kataKunci or
    nama_cabang like @kataKunci or
    gaji like @kataKunci