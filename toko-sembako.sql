-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 15 Des 2022 pada 11.13
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
-- Database: `toko-sembako`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_barang`
--

CREATE TABLE `tb_barang` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `kode` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_barang`
--

INSERT INTO `tb_barang` (`id`, `user_id`, `kode`, `nama`, `harga`, `stok`, `kategori`) VALUES
(1, 1, '1MI', 'aqua', 5000, 30, 'Minuman'),
(2, 3, '1MI', 'aqua', 5000, 20, 'Minuman'),
(3, 1, '1MA', 'roti tawar', 8000, 31, 'Makanan'),
(4, 2, '1MI', 'coca cola', 5000, 15, 'Minuman'),
(5, 1, '1LA', 'korek gas', 2500, 24, 'Lain-lain'),
(6, 2, '1MA', 'popmie', 4000, 19, 'Makanan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_history`
--

CREATE TABLE `tb_history` (
  `id_history` varchar(100) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `list_barang` varchar(200) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `total_tagihan` int(11) NOT NULL,
  `jumlah_uang` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL,
  `tanggal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_history`
--

INSERT INTO `tb_history` (`id_history`, `id_transaksi`, `user_id`, `list_barang`, `jumlah_barang`, `total_tagihan`, `jumlah_uang`, `kembalian`, `tanggal`) VALUES
('2601615122022', 26, 1, 'aqua,korek gas,roti tawar', 6, 36500, 40000, 3500, '15/12/2022'),
('291115122022', 29, 1, 'aqua', 1, 5000, 5000, 0, '15/12/2022');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_keranjang`
--

CREATE TABLE `tb_keranjang` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `kode` varchar(100) NOT NULL,
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
  `id_transaksi` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `kode` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tanggal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_transaksi`
--

INSERT INTO `tb_transaksi` (`id`, `id_transaksi`, `user_id`, `kode`, `nama`, `jumlah`, `harga`, `tanggal`) VALUES
(1, 1, 1, '1MA', 'roti tawar', 2, 8000, '15/12/2022'),
(2, 1, 1, '1MI', 'aqua', 1, 5000, '15/12/2022'),
(3, 3, 1, '1MI', 'aqua', 1, 5000, '15/12/2022'),
(4, 3, 1, '1MA', 'roti tawar', 2, 8000, '15/12/2022'),
(5, 5, 1, '1MI', 'aqua', 1, 5000, '15/12/2022'),
(6, 5, 1, '1MA', 'roti tawar', 1, 8000, '15/12/2022'),
(7, 7, 1, '1MA', 'roti tawar', 1, 8000, '15/12/2022'),
(8, 8, 1, '1MA', 'roti tawar', 2, 8000, '15/12/2022'),
(9, 8, 1, '1MI', 'aqua', 2, 5000, '15/12/2022'),
(10, 10, 1, '1MA', 'roti tawar', 3, 8000, '15/12/2022'),
(11, 10, 1, '1LA', 'korek gas', 1, 2500, '15/12/2022'),
(12, 10, 1, '1MI', 'aqua', 2, 5000, '15/12/2022'),
(13, 13, 1, '1MA', 'roti tawar', 1, 8000, '15/12/2022'),
(14, 13, 1, '1LA', 'korek gas', 1, 2500, '15/12/2022'),
(15, 13, 1, '1MI', 'aqua', 1, 5000, '15/12/2022'),
(16, 16, 1, '1MI', 'aqua', 1, 5000, '15/12/2022'),
(17, 16, 1, '1LA', 'korek gas', 1, 2500, '15/12/2022'),
(18, 1, 2, '1MA', 'popmie', 1, 4000, '14/12/2022'),
(19, 1, 2, '1MI', 'coca cola', 2, 5000, '14/12/2022'),
(20, 18, 1, '1MA', 'roti tawar', 1, 8000, '15/12/2022'),
(21, 21, 1, '1MI', 'aqua', 1, 5000, '15/12/2022'),
(22, 22, 1, '1LA', 'korek gas', 1, 2500, '15/12/2022'),
(23, 23, 1, '1MI', 'aqua', 2, 5000, '15/12/2022'),
(24, 23, 1, '1LA', 'korek gas', 1, 2500, '15/12/2022'),
(25, 23, 1, '1MA', 'roti tawar', 1, 8000, '15/12/2022'),
(26, 26, 1, '1MI', 'aqua', 2, 5000, '15/12/2022'),
(27, 26, 1, '1LA', 'korek gas', 1, 2500, '15/12/2022'),
(28, 26, 1, '1MA', 'roti tawar', 3, 8000, '15/12/2022'),
(29, 29, 1, '1MI', 'aqua', 1, 5000, '15/12/2022');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_users`
--

CREATE TABLE `tb_users` (
  `user_id` int(11) NOT NULL,
  `nama_toko` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `pendapatan` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_users`
--

INSERT INTO `tb_users` (`user_id`, `nama_toko`, `username`, `password`, `pendapatan`) VALUES
(1, 'Akbar Store', 'akbaroke', 'akbar123', 271000),
(2, 'Rei Store', 'rei123', 'rei123', 14000),
(3, 'Joko Store', 'joko', 'joko123', 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_history`
--
ALTER TABLE `tb_history`
  ADD PRIMARY KEY (`id_history`);

--
-- Indeks untuk tabel `tb_keranjang`
--
ALTER TABLE `tb_keranjang`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_users`
--
ALTER TABLE `tb_users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `tb_keranjang`
--
ALTER TABLE `tb_keranjang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT untuk tabel `tb_users`
--
ALTER TABLE `tb_users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
