-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Des 2022 pada 15.42
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

INSERT INTO `tb_barang` (`kode`, `id_user`, `nama`, `harga`, `stok`, `kategori`) VALUES
('1LA', 3, 'obat nyamuk', 6000, 19, 'Lain-lain'),
('1MA', 3, 'sari roti', 7500, 9, 'Makanan'),
('1MI', 3, 'coca cola', 5000, 20, 'Minuman'),
('2MI', 3, 'aqua', 3000, 29, 'Minuman'),
('1MA', 5, 'Indomie', 3500, 21, 'Makanan'),
('2MA', 5, 'roti tawar', 5000, 17, 'Makanan'),
('1LA', 5, 'korek gas', 2500, 28, 'Lain-lain');

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
('15219122022', 1, 5, 'Indomie', 2, 7000, 8000, 1000, '19/12/2022'),
('55719122022', 5, 5, 'korek gas,Indomie,roti tawar', 7, 27000, 30000, 3000, '19/12/2022');

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
(4, 1, 5, 'Indomie', 2, 3500, '19/12/2022'),
(5, 5, 5, 'korek gas', 2, 2500, '19/12/2022'),
(6, 5, 5, 'Indomie', 2, 3500, '19/12/2022'),
(7, 5, 5, 'roti tawar', 3, 5000, '19/12/2022');

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
(3, 'Akbar Store', 'akbar', 'akbar123', '16500'),
(5, 'Argun Store', 'argun', 'argun123', '34000');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
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
-- AUTO_INCREMENT untuk tabel `tb_keranjang`
--
ALTER TABLE `tb_keranjang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `tb_users`
--
ALTER TABLE `tb_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
