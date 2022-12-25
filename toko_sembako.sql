-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Des 2022 pada 11.42
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_sembako`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_barang`
--

CREATE TABLE `tb_barang` (
  `id` int(11) NOT NULL,
  `kode` varchar(100) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_barang`
--

INSERT INTO `tb_barang` (`id`, `kode`, `id_user`, `nama`, `harga`, `stok`, `kategori`) VALUES
(11, '1MI', 8, 'Sprite', 5000, 28, 'Minuman'),
(12, '2MI', 8, 'Fanta', 5000, 30, 'Minuman'),
(13, '3MI', 8, 'Cocacola', 5000, 29, 'Minuman'),
(14, '4MI', 8, 'Ademsari', 8000, 29, 'Minuman'),
(15, '5MI', 8, 'Indomilk', 3000, 30, 'Minuman'),
(16, '1MA', 8, 'Coklatos', 1000, 30, 'Makanan'),
(17, '2MA', 8, 'Indomie Goreng', 3500, 30, 'Makanan'),
(18, '3MA', 8, 'Indomie Rebus', 3000, 30, 'Makanan'),
(19, '4MA', 8, 'Donat', 3000, 28, 'Makanan'),
(20, '5MA', 8, 'Roti', 3000, 30, 'Makanan'),
(21, '1LA', 8, 'Tisu Wajah', 10000, 28, 'Lain-lain'),
(22, '2LA', 8, 'Tisu Toilet', 7000, 30, 'Lain-lain'),
(23, '3LA', 8, 'Handsanitizer', 12000, 30, 'Lain-lain'),
(24, '4LA', 8, 'Pewangi Lantai', 3000, 30, 'Lain-lain'),
(25, '5LA', 8, 'Gas 3kg', 26000, 10, 'Lain-lain');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_history`
--

CREATE TABLE `tb_history` (
  `id` varchar(200) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `list_barang` varchar(300) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `total_tagihan` int(11) NOT NULL,
  `jumlah_uang` int(11) NOT NULL,
  `kembalian_uang` int(11) NOT NULL,
  `tanggal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_history`
--

INSERT INTO `tb_history` (`id`, `id_transaksi`, `id_user`, `list_barang`, `jumlah_barang`, `total_tagihan`, `jumlah_uang`, `kembalian_uang`, `tanggal`) VALUES
('18225122022', 1, 8, 'Sprite,Cocacola', 2, 10000, 10000, 0, '25/12/2022'),
('208325122022', 20, 8, 'Tisu Wajah,Ademsari', 3, 28000, 30000, 2000, '25/12/2022');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_keranjang`
--

CREATE TABLE `tb_keranjang` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `kode_barang` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_transaksi`
--

CREATE TABLE `tb_transaksi` (
  `id` int(11) NOT NULL,
  `id_keranjang` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tanggal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_transaksi`
--

INSERT INTO `tb_transaksi` (`id`, `id_keranjang`, `id_user`, `nama`, `jumlah`, `harga`, `tanggal`) VALUES
(18, 1, 8, 'Sprite', 1, 5000, '25/12/2022'),
(19, 1, 8, 'Cocacola', 1, 5000, '25/12/2022'),
(20, 20, 8, 'Tisu Wajah', 2, 10000, '25/12/2022'),
(21, 20, 8, 'Ademsari', 1, 8000, '25/12/2022');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_users`
--

CREATE TABLE `tb_users` (
  `id` int(11) NOT NULL,
  `nama_toko` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `pendapatan` varchar(200) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_users`
--

INSERT INTO `tb_users` (`id`, `nama_toko`, `username`, `password`, `pendapatan`) VALUES
(8, 'Akbar Store', 'akbar', 'akbar123', '38000');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `kode` (`kode`);

--
-- Indeks untuk tabel `tb_history`
--
ALTER TABLE `tb_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `tb_keranjang`
--
ALTER TABLE `tb_keranjang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_keranjang` (`id_keranjang`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `tb_users`
--
ALTER TABLE `tb_users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT untuk tabel `tb_keranjang`
--
ALTER TABLE `tb_keranjang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT untuk tabel `tb_users`
--
ALTER TABLE `tb_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD CONSTRAINT `tb_barang_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_history`
--
ALTER TABLE `tb_history`
  ADD CONSTRAINT `tb_history_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`),
  ADD CONSTRAINT `tb_history_ibfk_3` FOREIGN KEY (`id_transaksi`) REFERENCES `tb_transaksi` (`id_keranjang`);

--
-- Ketidakleluasaan untuk tabel `tb_keranjang`
--
ALTER TABLE `tb_keranjang`
  ADD CONSTRAINT `tb_keranjang_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `tb_barang` (`kode`),
  ADD CONSTRAINT `tb_keranjang_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD CONSTRAINT `tb_transaksi_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;