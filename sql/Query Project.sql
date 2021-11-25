go
use master
go
drop database Project
go


create database Project
go
use Project
go

create table kategori (
    nama_kategori varchar(100) primary key,
    deskripsi text
)

create table produk (
    product_id int primary key identity(1,1),
    nama_produk varchar(255) not null,
    jumlah_stok int not null default 0,
    harga_satuan int not null,
    deskripsi text,
    nutrition_facts text,
    kategori varchar(100) foreign key references kategori(nama_kategori) on delete set null
)

create table cabang(
    id_cabang int primary key identity(1,1),
    nama_cabang varchar(100) not null,
    alamat_cabang text not null
)

create table jabatan(
    nama_jabatan varchar(50) primary key,
    gaji int not null,
)

create table karyawan (
    id_karyawan int primary key identity(1,1),
    nama varchar(255) not null,
    jenis_kelamin char not null,
    tanggal_lahir date not null,
    nomor_hp varchar(25) not null,
    email varchar(50) not null,
    alamat text not null,
    id_cabang int foreign key references cabang(id_cabang) on delete set null,
    jabatan varchar(50) foreign key references jabatan(nama_jabatan) on delete set null
)

create table customer_account (
    id_pelanggan int primary key identity(1,1),
    email varchar(50) not null,
    password varchar(255) not null,
)

create table customer_profile (
    id_pelanggan int primary key,
    nama_pelanggan varchar(255) not null,
    tanggal_lahir date not null,
    nomor_hp varchar(25) not null,
    nomor_rumah int not null,
    desa_kecamatan varchar(50) not null,
    kabupaten_kota varchar(50) not null,
    jalan varchar(50) not null,
    jenis_kelamin char not null,
    kode_pos varchar(5) not null

    foreign key (id_pelanggan) references customer_account(id_pelanggan) on delete cascade
)

create table supplier (
    id_supplier int primary key identity(1,1),
    nama_supplier varchar(255) not null,
    alamat text not null,
    nomor_hp varchar(20) not null,
    email varchar(50) not null,
    kode_pos varchar(5) not null,
)

create table supplied_product (
    product_id int not null,
    id_supplier int,
    jumlah_produk int not null,
    primary key (product_id, id_supplier),
    foreign key (product_id) references produk(product_id) on delete cascade,
    foreign key (id_supplier) references supplier(id_supplier) on delete no action
)

-- Nama aslinya order : jadi order_product karena nama "order" tidak bisa digunakan
create table order_product(
    order_id int primary key identity(1,1),
    tanggal_kirim date not null,
    status_order bit not null default 0,
    id_pelanggan int not null,
    id_karyawan int,
    foreign key (id_pelanggan) references customer_account(id_pelanggan) on delete cascade,
    foreign key (id_karyawan) references karyawan(id_karyawan) on delete set null,
)

create table ordered_product (
    order_id int,
    product_id int,
    kuantitas int not null,
    harga_product int not null,
    primary key (order_id, product_id),
    foreign key (order_id) references order_product(order_id) on delete cascade,
    foreign key (product_id) references produk(product_id)
)

-- view untuk subtotal di ordered product
go
create view subtotal as
select order_id, product_id, harga_product * kuantitas as subtotal
from ordered_product
go

-- view total di order product
create view total as
select op.order_id, coalesce(st.total, 0) as total 
from (
    select order_id, sum(subtotal) as total
    from subtotal
    group by order_id
) as st
left outer join
order_product as op 
on st.order_id=op.order_id
go
