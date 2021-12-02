delete from supplier;
delete from kategori;
delete from produk;
delete from supplied_product;
delete from jabatan;
delete from order_product;
delete from ordered_product;
delete from cabang;
delete from karyawan;
delete from customer_profile;
delete from customer_account;

--TABEL SUPPLIER
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Ahmad', 'Jalan Merdeka', '0871254562', 'ahmad@gmail.com', '9001');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Joni', 'Jalan Bandung', '0852887756', 'sayajoni@gmail.com', '9002');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Bambang', 'Jalan Ambarawa', '0821339803', 'bambang123@gmail.com', '9003');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Eko', 'Jalan Apel', '0897665493', 'ekolala@gmail.com', '9004');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Jono', 'Jalan Nanas', '0899657492', 'jonojoni@gmail.com', '9005');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Subagyo', 'Jalan Mangga', '0812345666', 'ahmdsubagyo@gmail.com', '9006');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Sukijan', 'Jalan Jakarta', '0811897653', 'sukijin@gmail.com', '9007');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Sukimin', 'Jalan Merak', '0878657399', 'sukiimin@gmail.com', '9008');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Sarimin', 'Jalan Manggis', '0898776438', 'sarimin420@gmail.com', '9009');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Joe', 'Jalan Soekarno', '0821390875', 'ahmad@gmail.com', '9010');
insert into supplier (nama_supplier, alamat, nomor_hp, email, kode_pos) values ('Jojo', 'Jalan Daendels', '0876990272', 'jojojeje@gmail.com', '9011');

--TABEL KATEGORI
insert into kategori (nama_kategori, deskripsi) values ('Buah', 'Kategori yang berisi bermacam jenis buah-buahan');
insert into kategori (nama_kategori, deskripsi) values ('Sayur', 'Kategori yang berisi bermacam jenis sayur-sayuran');
insert into kategori (nama_kategori, deskripsi) values ('Snack Sayur', 'Kategori yang berisi bermacam jenis snack sayur');
insert into kategori (nama_kategori, deskripsi) values ('Snack Buah', 'Kategori yang berisi bermacam jenis snack buah');


--TABEL PRODUK
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Durian', 50, 30000, 'durian manis dan legit', 'vitamin c,d','buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Mangga', 40, 15000, 'mangga segar dan sehat', 'vitamin a', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Pisang', 30, 10000, 'pisang segar dan sehat', 'vitamin a,b,c', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Timun', 60, 7000, 'timun segar dan sehat', 'vitamin c,k', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Tomat', 50, 8000, 'tomat segar dan sehat', 'vitamin b,c', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Apel', 15, 30000, 'apel segar dan sehat', 'vitamin a,c,k', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Markisa', 33, 9000, 'markisa segar dan kaya akan vitamin', 'vitamin a,c', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Brokoli', 62, 7500, 'brokoli segar,sehat, dan mempunyai banyak nutrisi', 'vitamin a,c,e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Anggur', 46, 31000, 'anggur segar dan kaya akan vitamin', 'vitamin a,c,e', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Jeruk', 28, 13000, 'jeruk segar kaya vitamin', 'vitamin a,c', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Rambutan', 61, 14000, 'rambutan manis dan kaya vitamin', 'vitamin a,c,e', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Almond', 70, 10000, 'almond yang sehat dan kaya nutrisi', 'vitamin c,e', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Aprikot', 47, 14000, 'aprikot manis dan kaya vitamin', 'vitamin d,e', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Arbei', 18, 19000, 'arbei segar, manis dan kaya vitamin', 'vitamin a,c,e', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Belimbing', 22, 21000, 'belimbing manis dan kaya vitamin', 'vitamin a,c', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Bengkuang', 37, 20500, 'bengkuang manis dan kaya vitamin', 'vitamin a', 'buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Cabai', 40, 11000, 'cabai segar dan kaya vitamin', 'vitamin a,c', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Buncis', 52, 12000, 'buncis segar dan sehat', 'vitamin a,c,d', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Asparagus', 45, 12500, 'asparagus segar dan kaya akan vitamin', 'vitamin a,c,d,e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Bawang Bombay', 30, 14000, 'bawang bombay segar dan sehat', 'vitamin e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Bawang Putih', 31, 16000, 'bawang putih kaya nutrisi', 'vitamin d,e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Bawang Merah', 36, 15500, 'bawang merah kaya nutrisi', 'vitamin a,c', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Gambas', 90, 20000, 'gambas segar dan sehat', 'vitamin c,e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Jagung', 20, 5000, 'jagung segar dan kaya vitamin', 'vitamin a,c,e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Jamur', 80, 2000, 'jamur sehat dan segar', 'vitamin c,e', 'sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Keripique', 65, 5000, 'snack yang berasal dari singkong', 'kalori 320', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Veegiegie', 70, 6000, 'snack yang berasal dari sayur-sayuran', 'kalori 110', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Cornyummy', 80, 2000, 'snack yang berasal dari jagung', 'kalori 120', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Hotcrips', 50, 3000, 'snack yang berasal dari cabai', 'kalori 100', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Potatoiz', 80, 2500, 'snack yang berasal dari kentang', 'kalori 115', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Mushroomz', 40, 3000, 'snack yang berasal dari jamur', 'kalori 110', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('TelaQ', 45, 5000, 'snack yang berasal dari ketela', 'kalori 100', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Hotspinach', 54, 7000, 'snack yang berasal dari bayam', 'kalori 120', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Jalapenocrsp', 60, 5000, 'snack yang berasal dari jalapeno', 'kalori 150', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Carrotz', 20, 7500, 'snack yang berasal dari wortel', 'kalori 120', 'snack sayur');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Applekripkrip', 25, 3500, 'snack yang berasal dari apel', 'kalori 100', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Meloncrsp', 20, 5000, 'snack yang berasal dari buah', 'kalori 110', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('NangkaMu', 48, 7000, 'snack yang berasal dari nangka', 'kalori 150', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Grrps', 25, 9000, 'snack yang berasal dari anggur', 'kalori 140', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('BlueberryQ', 60, 10000, 'snack yang berasal dari blueberry', 'kalori 125', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Crisp Guava', 27, 8000,'snack yang berasal dari jambu', 'kalori 150', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Orange Candyy', 69, 9000, 'snack yang berasal dari jeruk', 'kalori 140', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('LemonLimeCrisp', 20, 7500, 'snack yang berasal dari lemon', 'kalori 120', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('MyLeccy', 80, 5000, 'snack yang berasal dari leci', 'kalori 170', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Mangosteen', 55, 9000, 'snack yang berasal dari mangga', 'kalori 190', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('MRBanana', 20, 3500, 'snack yang berasal dari pisang', 'kalori 150', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Strawbrrycrisp', 50, 9500, 'snack yang berasal dari stroberi', 'kalori 220', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Flamboyantz', 50, 8000, 'snack yang berasal dari flamboyan', 'kalori 320', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Tamarind', 100, 10000, 'snack yang berasal dari asam', 'kalori 170', 'snack buah');
insert into produk (nama_produk, jumlah_stok, harga_satuan, deskripsi, nutrition_facts, kategori) values ('Blackcurrant', 100, 6500, 'snack yang berasal dari blackcurrant', 'kalori 190', 'snack buah');

--TABEL SUPPLIED_PRODUCT
insert into supplied_product values (1, 1, 50, '2020-12-31');
insert into supplied_product values (2, 2, 25, '2020-12-31');
insert into supplied_product values (3, 3, 45, '2020-12-31');
insert into supplied_product values (4, 4, 10, '2020-12-31');
insert into supplied_product values (5, 6, 23, '2020-12-31');
insert into supplied_product values (1, 1, 20, '2021-01-02');
insert into supplied_product values (2, 2, 43, '2021-01-18');
insert into supplied_product values (6, 6, 15, '2021-01-19');
insert into supplied_product values (1, 1, 76, '2021-01-20');
insert into supplied_product values (7, 7, 26, '2021-01-20');

--TABEL JABATAN
insert into jabatan values ('Manager', 6000000);
insert into jabatan values ('Marketing', 4500000);
insert into jabatan values ('Kurir', 3000000);

--TABEL CABANG
insert into cabang (nama_cabang, alamat_cabang) values ('cabang malang', 'jalan veteran');
insert into cabang (nama_cabang, alamat_cabang) values ('cabang bali', 'jalan kuta');
insert into cabang (nama_cabang, alamat_cabang) values ('cabang jakarta', 'jalan kuningan');
insert into cabang (nama_cabang, alamat_cabang) values ('cabang surabaya', 'jalan ambarawa');
insert into cabang (nama_cabang, alamat_cabang) values ('cabang solo', 'jalan raden patah');
insert into cabang (nama_cabang, alamat_cabang) values ('cabang yogyakarta', 'jalan raden ageng');
insert into cabang (nama_cabang, alamat_cabang) values ('cabang bandung', 'jalan cisarua');

--TABEL KARYAWAN
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Faisal', 'L', '1998-08-14', '081745562', 'faisal@gmail.com', 'Jalan Sesetan', 1, 'Kurir');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Hakim', 'L', '1997-12-05', '082546587', 'hakim@gmail.com', 'Jalan Pedungan', 1, 'Manager');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Ridwan', 'L', '1997-02-11', '08695123', 'ridwan@gmail.com', 'Jalan Hautang', 2, 'Manager');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Mahameruh', 'L', '1998-06-13', '08265781', 'mahameruh@gmail.com', 'Jalan Veteran', 3, 'Manager');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Nisai', 'L', '1999-11-04', '0875422156', 'nisai@gmail.com', 'Jalan Merdeka', 3, 'Marketing');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Boy', 'L', '1997-01-14', '082998761', 'boyy@gmail.com', 'Jalan Ikan Hiu', 2, 'Kurir');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Jason', 'L', '1996-11-05', '081278765', 'jasonr@gmail.com', 'Jalan Ikan Paus', 1, 'Kurir');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Kiki', 'L', '1999-04-19', '085876381', 'kiki@gmail.com', 'Jalan Susanto', 2, 'Kurir');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Andrean', 'L', '1998-11-14', '089872191', 'aandrean@gmail.com', 'Jalan Suharto ', 3, 'Kurir');
insert into karyawan (nama, jenis_kelamin, tanggal_lahir, nomor_hp, email, alamat, id_cabang, jabatan) values ('Luqi', 'L', '1997-02-19', '0877908753', 'mluqi@gmail.com', 'Jalan Kepiting', 3, 'Kurir');

--TABEL CUSTOMER_ACCOUNT
insert into customer_account values ('victoria@gmail.com', 'victoriapw');
insert into customer_account values ('paramita@gmail.com', 'paramitapw');
insert into customer_account values ('samsul@gmail.com', 'samsulpw');
insert into customer_account values ('paiman@gmail.com', 'paimanpw');
insert into customer_account values ('bobby@gmail.com', 'bobbypw');
insert into customer_account values ('bobon@gmail.com', 'bobonpw');
insert into customer_account values ('indah@gmail.com', 'indahpw');
insert into customer_account values ('james@gmail.com', 'jamespw');
insert into customer_account values ('indira@gmail.com', 'indirapw');
insert into customer_account values ('joni@gmail.com', 'jonipw');

--TABEL CUSTOMER_PROFILE
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (1 ,'Victoria', '1913-04-02', '0526715754', '8A', 'Batu', 'Malang', 'Gang Surapati', 'P', '81124');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (2 ,'Paramita', '1920-12-15', '082608102', '99', 'Malang', 'Malang', 'Gang S. Parman', 'P', '51147');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (3 ,'Samsul', '1913-04-02', '0351367322', '2C', 'Pedungan', 'Denpasar', 'Jl. Jamika', 'L', '80002');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (4 ,'Paiman', '1924-09-14', '0886705467', '8F', 'Kuningan', 'Jakarta', 'Jl. Medokan Ayu', 'L', '25557');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (5 ,'Bobby', '1989-02-01', '0823357689', '11', 'Kedungkandang', 'Malang', 'Perum Permata Regency', 'L', '81111');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (6 ,'Bobon', '1990-12-19', '0819876253', '9C', 'Dau', 'Malang', 'Jl. Bandung', 'L', '81234');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (7 ,'Indah', '1988-11-02', '0811895309', '12', 'Wonokromo', 'Surabaya', 'Jl. Soekarno Hatta', 'P', '80055');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (8 ,'James', '1987-02-10', '0829085772', '67', 'Candi', 'Sidoarjo', 'Jl. Terusan Suez', 'L', '72145');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (9 ,'Indira', '1991-08-21', '0878945282', '76', 'Sawahan', 'Surabaya', 'Jl. Segitiga Bermuda', 'P', '67542');
insert into customer_profile (id_pelanggan, nama_pelanggan, tanggal_lahir, nomor_hp, nomor_rumah, desa_kecamatan, kabupaten_kota, jalan, jenis_kelamin, kode_pos) values (10 ,'Joni', '1990-12-25', '08908761521', '9Z', 'Rampal', 'Malang', 'Jl. Tanjakan Haha', 'L', '87651');

--TABEL ORDER_PRODUCT
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-11', 1, 1, 2)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-12', 0, 2, 1)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-15', 0, 3, 3)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-15', 0, 3, 3)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-17', 0, 4, 4)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-02', 1, 5, 10)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-03', 1, 3, 3)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-15', 0, 6, 7)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-16', 0, 7, 6)
insert into order_product (tanggal_kirim, status_order, id_pelanggan, id_karyawan) values ('2021-11-17', 0, 8, 4)

--TABEL ORDERED_PRODUCT
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (1, 1, 3, 90000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (2, 2, 9, 135000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (3, 3, 10, 100000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (4, 4, 5, 35000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (4, 5, 2, 16000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (3, 2, 11, 165000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (5, 1, 12, 360000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (4, 2, 9, 135000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (6, 3, 10, 100000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (7, 8, 5, 37500)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (8, 6, 2, 30000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (9, 9, 11, 341000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (10, 26, 11, 341000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (9, 9, 11, 341000)
insert into ordered_product (order_id, product_id, kuantitas, harga_product)values (9, 9, 11, 341000)


--MENGECEK SEMUA TABEL
select * from supplier;
select * from kategori;
select * from produk;
select * from supplied_product;
select * from jabatan;
select * from order_product;
select * from ordered_product;
select * from cabang;
select * from karyawan;
select * from customer_profile;
select * from customer_account;

select * from subtotal
order by subtotal  
