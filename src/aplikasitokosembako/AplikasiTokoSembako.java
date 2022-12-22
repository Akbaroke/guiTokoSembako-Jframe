/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasitokosembako;

import java.awt.Toolkit;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

/**
 *
 * @author akbaroke
 */
public class AplikasiTokoSembako extends javax.swing.JFrame {

    /**
     * Creates new form AplikasiTokoSembako
     */
    public AplikasiTokoSembako() {
        // cek session
        if(Session.session.getSession() == null){
            Login login = new Login();
            this.setVisible(false);
            login.setVisible(true);
        }else{
            setIcon();
            initComponents();
            setHeaderName();
            showTabelBarang();
            showTabelKeranjang();
            hitungKeranjang();
        }
    }
    
    
    public final void setHeaderName(){
        try {
            Connection conn = Koneksi.ConnectDB();
            String query = "SELECT * FROM tb_users WHERE id='"+Session.session.getSession()+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String namaToko = "null";
            while(rs.next()){
                namaToko = rs.getString("nama_toko");
            }
            NamaToko.setText(namaToko);
            NamaToko_struk.setText(namaToko);
            namaToko_struk.setText(namaToko);
        } catch (SQLException e) {
            System.out.println("Data barang Gagal di tampilkan...");
            System.out.println(e);
        }
    }
    
    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    public final void showTabelBarang(){
        
        try {
            Connection conn = Koneksi.ConnectDB();
            
            Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
            tableModel = new DefaultTableModel(null,columnTitle);
            TabelBarang.setModel(tableModel);
            Statement statement = conn.createStatement();
            tableModel.getDataVector().removeAllElements();
            
            resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"'");
            int i = 1;
            while(resultSet.next()){
                Object[] data = {
                    i++,
                    resultSet.getString("kode"),
                    resultSet.getString("nama"),
                    resultSet.getString("harga"),
                    resultSet.getString("stok")
                };
                tableModel.addRow(data);
            }
            
        } catch (SQLException e) {
            System.out.println("Data barang Gagal di tampilkan...");
            System.out.println(e);
        }
    }
    
    public void RefreshTampilanAdmin(){
        showTabelBarang();
        FilterTabel.setSelectedItem("-Pilih-");
        FieldCari.setSelectedItem("-Pilih-");
        cmbKategori_Tambah.setSelectedItem("-Pilih-");
        txtCariBarang.setText("");
        txtNama_Tambah.setText("");
        txtHarga_Tambah.setText("");
        txtStok_Tambah.setText("");
        txtKode_Hapus.setText("");
        txtNama_Ubah.setText("");
        txtHarga_Ubah.setText("");
        txtStok_Ubah.setText("");
        kodeUbahBarang = null;
        lblKode_Ubah.setText("-");
    }
    
    public final void showTabelKeranjang(){
        try {
            Connection conn = Koneksi.ConnectDB();
            
            Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Jumlah"};
            tableModel = new DefaultTableModel(null,columnTitle);
            TabelKeranjang.setModel(tableModel);
            Statement statement = conn.createStatement();
            tableModel.getDataVector().removeAllElements();
            
            resultSet = statement.executeQuery("SELECT * FROM tb_keranjang WHERE id_user='"+Session.session.getSession()+"'");
            int i = 1;
            while(resultSet.next()){
                Object[] data = {
                    i++,
                    resultSet.getString("kode_barang"),
                    resultSet.getString("nama"),
                    resultSet.getString("harga"),
                    resultSet.getString("jumlah")
                };
                tableModel.addRow(data);
            }
            
        } catch (SQLException e) {
            System.out.println("Data keranjang Gagal di tampilkan...");
            System.out.println(e);
        }
    }
    
    public void RefreshTampilanTransaksi(){
        showTabelKeranjang();
        hitungKeranjang();
        txtKode_Keranjang.setText("");
        txtJumlah_Keranjang.setText("");
        txtKode_KeranjangHapus.setText("");
        lblKode_UbahKeranjang.setText("-");
        txtJumlah_UbahKeranjang.setText("");
    }
    
    int KeranjangTotalBayar = 0;
    public final void hitungKeranjang(){
        try {
            Connection conn = Koneksi.ConnectDB();
            String query = "SELECT * FROM tb_keranjang WHERE id_user='"+Session.session.getSession()+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int resTotal = 0;
            while(rs.next()){
                String resHarga = rs.getString("harga");
                String resJumlah = rs.getString("jumlah");
                resTotal = resTotal + (Integer.parseInt(resHarga) * Integer.parseInt(resJumlah));
            }
            KeranjangTotalBayar = resTotal;
            totalKeranjang.setText(RupiahFromat(resTotal));
            totalKeranjang_Pembayaran.setText(RupiahFromat(resTotal));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public String RupiahFromat(int amount){
        Locale indonesia = new Locale("id", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(indonesia);
        String formattedAmount = formatter.format(amount);
        formattedAmount = formattedAmount.replace("Rp", "");
        formattedAmount = formattedAmount.replace(",00", "");
        return formattedAmount;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelKeranjang = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnSimpan_UbahBarang = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtKode_Hapus = new javax.swing.JTextField();
        txtStok_Tambah = new javax.swing.JTextField();
        txtStok_Ubah = new javax.swing.JTextField();
        txtJumlah_Keranjang = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        FieldCari = new javax.swing.JComboBox<>();
        FilterTabel = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHarga_Tambah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnSimpan_Tambah = new javax.swing.JButton();
        cmbKategori_Tambah = new javax.swing.JComboBox<>();
        txtHarga_Ubah = new javax.swing.JTextField();
        txtNama_Ubah = new javax.swing.JTextField();
        txtCariBarang = new javax.swing.JTextField();
        txtJumlahUang_Bayar = new javax.swing.JTextField();
        txtKode_KeranjangHapus = new javax.swing.JTextField();
        txtNama_Tambah = new javax.swing.JTextField();
        txtKode_Keranjang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelBarang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblTanggalValue = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblKode_UbahKeranjang = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        totalKeranjang = new javax.swing.JLabel();
        totalKeranjang_Pembayaran = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblRingkasan_Kembalian = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnSimban_UbahKeranjang = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtJumlah_UbahKeranjang = new javax.swing.JTextField();
        lblKode_Ubah = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblRingkasan_Uang = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblRingkasan_Total = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        struk_JumlahUang = new javax.swing.JLabel();
        NamaToko = new javax.swing.JLabel();
        NamaToko_struk = new javax.swing.JLabel();
        struk_KembalianUang = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        struk_TotalBayar = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        harga_listStruk = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        nama_listStruk = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jumlah_listStruk = new javax.swing.JTextArea();
        btnHapus_Keranjang = new javax.swing.JButton();
        namaToko_struk = new javax.swing.JLabel();
        link_history = new javax.swing.JButton();
        link_profile = new javax.swing.JButton();
        link_logout = new javax.swing.JButton();
        btnRefreshTransaksi = new javax.swing.JButton();
        btnRefreshStruk = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        btnHapus_SemuaKeranjang = new javax.swing.JButton();
        btnHapus_Barang = new javax.swing.JButton();
        btnKeranjang = new javax.swing.JButton();
        btnCariBarang = new javax.swing.JButton();
        btnRefreshAdmin = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI TOKO SEMBAKO");
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1440, 960));
        setMinimumSize(new java.awt.Dimension(1440, 960));
        getContentPane().setLayout(null);

        TabelKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TabelKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Kode", "Nama Barang", "Harga", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelKeranjang.setToolTipText("");
        TabelKeranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TabelKeranjang.setRowHeight(25);
        TabelKeranjang.setSelectionBackground(new java.awt.Color(204, 204, 204));
        TabelKeranjang.setSelectionForeground(new java.awt.Color(1, 1, 1));
        TabelKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelKeranjangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(TabelKeranjang);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(770, 220, 579, 130);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Stok            :");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(400, 650, 90, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Stok            :");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(90, 600, 90, 20);

        btnSimpan_UbahBarang.setBackground(new java.awt.Color(0, 0, 0));
        btnSimpan_UbahBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-simpan.png"))); // NOI18N
        btnSimpan_UbahBarang.setBorder(null);
        btnSimpan_UbahBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan_UbahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan_UbahBarangActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan_UbahBarang);
        btnSimpan_UbahBarang.setBounds(480, 720, 90, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kode          :");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(90, 830, 90, 20);

        txtKode_Hapus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtKode_Hapus);
        txtKode_Hapus.setBounds(180, 830, 150, 30);

        txtStok_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtStok_Tambah);
        txtStok_Tambah.setBounds(180, 600, 160, 30);

        txtStok_Ubah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtStok_Ubah);
        txtStok_Ubah.setBounds(490, 650, 160, 30);

        txtJumlah_Keranjang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtJumlah_Keranjang);
        txtJumlah_Keranjang.setBounds(880, 520, 160, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Harga         :");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(90, 550, 90, 20);

        FieldCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        FieldCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Kode", "Nama", "Harga", "Stok" }));
        FieldCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(FieldCari);
        FieldCari.setBounds(460, 400, 140, 30);

        FilterTabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        FilterTabel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Harga Termurah", "Harga Termahal", "Stok", "Kat. Makanan", "Kat. Minuman", "Kat. Lain-lain" }));
        FilterTabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FilterTabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterTabelActionPerformed(evt);
            }
        });
        getContentPane().add(FilterTabel);
        FilterTabel.setBounds(180, 360, 120, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jumlah Uang  : Rp");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(1100, 520, 130, 20);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Kategori     :");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(90, 650, 90, 20);

        txtHarga_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtHarga_Tambah);
        txtHarga_Tambah.setBounds(180, 550, 160, 30);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Harga         :");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(400, 600, 90, 20);

        btnSimpan_Tambah.setBackground(new java.awt.Color(0, 0, 0));
        btnSimpan_Tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-simpan.png"))); // NOI18N
        btnSimpan_Tambah.setBorder(null);
        btnSimpan_Tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan_TambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan_Tambah);
        btnSimpan_Tambah.setBounds(170, 720, 90, 30);

        cmbKategori_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cmbKategori_Tambah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Makanan", "Minuman", "Lain-lain" }));
        cmbKategori_Tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbKategori_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKategori_TambahActionPerformed(evt);
            }
        });
        getContentPane().add(cmbKategori_Tambah);
        cmbKategori_Tambah.setBounds(180, 650, 160, 30);

        txtHarga_Ubah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtHarga_Ubah);
        txtHarga_Ubah.setBounds(490, 600, 160, 30);

        txtNama_Ubah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtNama_Ubah);
        txtNama_Ubah.setBounds(490, 550, 160, 30);

        txtCariBarang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtCariBarang);
        txtCariBarang.setBounds(460, 360, 140, 30);

        txtJumlahUang_Bayar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtJumlahUang_Bayar);
        txtJumlahUang_Bayar.setBounds(1230, 520, 90, 30);

        txtKode_KeranjangHapus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtKode_KeranjangHapus);
        txtKode_KeranjangHapus.setBounds(880, 710, 160, 30);

        txtNama_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtNama_Tambah);
        txtNama_Tambah.setBounds(180, 500, 160, 30);

        txtKode_Keranjang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        getContentPane().add(txtKode_Keranjang);
        txtKode_Keranjang.setBounds(880, 480, 160, 30);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        TabelBarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Kode", "Nama Barang", "Harga", "Stok"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TabelBarang.setGridColor(new java.awt.Color(204, 204, 204));
        TabelBarang.setRowHeight(25);
        TabelBarang.setSelectionBackground(new java.awt.Color(204, 204, 204));
        TabelBarang.setSelectionForeground(new java.awt.Color(1, 1, 1));
        TabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelBarangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(TabelBarang);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(80, 220, 580, 130);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Field     :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(390, 400, 60, 20);

        lblTanggalValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTanggalValue.setText("0/0/0 ");
        getContentPane().add(lblTanggalValue);
        lblTanggalValue.setBounds(1560, 500, 90, 20);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Jumlah      :");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(1100, 750, 80, 20);

        lblKode_UbahKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKode_UbahKeranjang.setForeground(new java.awt.Color(250, 216, 16));
        lblKode_UbahKeranjang.setText("-");
        getContentPane().add(lblKode_UbahKeranjang);
        lblKode_UbahKeranjang.setBounds(1190, 710, 130, 20);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Jumlah      :");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(790, 520, 80, 20);

        totalKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalKeranjang.setForeground(new java.awt.Color(255, 255, 255));
        totalKeranjang.setText("0");
        getContentPane().add(totalKeranjang);
        totalKeranjang.setBounds(840, 360, 110, 20);

        totalKeranjang_Pembayaran.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalKeranjang_Pembayaran.setForeground(new java.awt.Color(255, 255, 255));
        totalKeranjang_Pembayaran.setText("0");
        getContentPane().add(totalKeranjang_Pembayaran);
        totalKeranjang_Pembayaran.setBounds(1230, 480, 90, 20);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total : Rp");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(770, 360, 72, 20);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nama         :");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(90, 500, 80, 20);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Jumlah Uang        : Rp");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(1480, 280, 150, 20);

        lblRingkasan_Kembalian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRingkasan_Kembalian.setForeground(new java.awt.Color(255, 255, 255));
        lblRingkasan_Kembalian.setText("0");
        getContentPane().add(lblRingkasan_Kembalian);
        lblRingkasan_Kembalian.setBounds(1630, 320, 80, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Kode         :");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(790, 480, 80, 20);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Total Bayar    : Rp ");
        getContentPane().add(jLabel30);
        jLabel30.setBounds(1100, 480, 130, 20);

        btnSimban_UbahKeranjang.setBackground(new java.awt.Color(0, 0, 0));
        btnSimban_UbahKeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-simpan.png"))); // NOI18N
        btnSimban_UbahKeranjang.setBorder(null);
        btnSimban_UbahKeranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimban_UbahKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimban_UbahKeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimban_UbahKeranjang);
        btnSimban_UbahKeranjang.setBounds(1170, 800, 90, 30);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Kode         :");
        getContentPane().add(jLabel32);
        jLabel32.setBounds(1100, 710, 80, 20);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Kode         :");
        getContentPane().add(jLabel25);
        jLabel25.setBounds(790, 710, 80, 20);

        txtJumlah_UbahKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtJumlah_UbahKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlah_UbahKeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(txtJumlah_UbahKeranjang);
        txtJumlah_UbahKeranjang.setBounds(1190, 750, 130, 30);

        lblKode_Ubah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKode_Ubah.setForeground(new java.awt.Color(250, 216, 16));
        lblKode_Ubah.setText("-");
        getContentPane().add(lblKode_Ubah);
        lblKode_Ubah.setBounds(490, 500, 160, 20);

        btnPrint.setBackground(new java.awt.Color(0, 0, 0));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-print.png"))); // NOI18N
        btnPrint.setBorder(null);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrint);
        btnPrint.setBounds(1780, 370, 50, 30);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Kode         :");
        getContentPane().add(jLabel27);
        jLabel27.setBounds(400, 500, 80, 20);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Total Bayar          : Rp");
        getContentPane().add(jLabel31);
        jLabel31.setBounds(1480, 240, 150, 20);

        lblRingkasan_Uang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRingkasan_Uang.setForeground(new java.awt.Color(255, 255, 255));
        lblRingkasan_Uang.setText("0");
        getContentPane().add(lblRingkasan_Uang);
        lblRingkasan_Uang.setBounds(1630, 280, 80, 20);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Nama         :");
        getContentPane().add(jLabel29);
        jLabel29.setBounds(400, 550, 90, 20);

        lblRingkasan_Total.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRingkasan_Total.setForeground(new java.awt.Color(255, 255, 255));
        lblRingkasan_Total.setText("0");
        getContentPane().add(lblRingkasan_Total);
        lblRingkasan_Total.setBounds(1630, 240, 80, 20);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Kode         :");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(790, 710, 80, 20);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Kembalian Uang  : Rp");
        getContentPane().add(jLabel33);
        jLabel33.setBounds(1480, 320, 150, 20);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(250, 216, 16));
        jLabel17.setText("dan hapus pada tabel di atas.");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(490, 860, 200, 20);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel35.setText("Kembalian Uang   : ");
        getContentPane().add(jLabel35);
        jLabel35.setBounds(1500, 770, 120, 20);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(250, 216, 16));
        jLabel28.setText("* klik data barang yang akan diubah");
        getContentPane().add(jLabel28);
        jLabel28.setBounds(450, 840, 240, 20);

        struk_JumlahUang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        struk_JumlahUang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        struk_JumlahUang.setText("Rp 0");
        getContentPane().add(struk_JumlahUang);
        struk_JumlahUang.setBounds(1700, 740, 90, 30);

        NamaToko.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        NamaToko.setForeground(new java.awt.Color(255, 255, 255));
        NamaToko.setText("Nama Toko");
        getContentPane().add(NamaToko);
        NamaToko.setBounds(140, 20, 760, 70);

        NamaToko_struk.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        NamaToko_struk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(NamaToko_struk);
        NamaToko_struk.setBounds(1580, 470, 160, 0);

        struk_KembalianUang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        struk_KembalianUang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        struk_KembalianUang.setText("Rp 0");
        getContentPane().add(struk_KembalianUang);
        struk_KembalianUang.setBounds(1700, 770, 90, 20);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel37.setText("Total Bayar          : ");
        getContentPane().add(jLabel37);
        jLabel37.setBounds(1500, 720, 120, 20);

        struk_TotalBayar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        struk_TotalBayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        struk_TotalBayar.setText("Rp 0");
        getContentPane().add(struk_TotalBayar);
        struk_TotalBayar.setBounds(1700, 720, 90, 20);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setText("Tanggal   : ");
        getContentPane().add(jLabel38);
        jLabel38.setBounds(1500, 500, 60, 20);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel40.setText("Jumlah Uang        : ");
        getContentPane().add(jLabel40);
        jLabel40.setBounds(1500, 740, 120, 30);

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        harga_listStruk.setEditable(false);
        harga_listStruk.setBackground(new java.awt.Color(255, 255, 255));
        harga_listStruk.setColumns(7);
        harga_listStruk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        harga_listStruk.setRows(7);
        harga_listStruk.setToolTipText("");
        harga_listStruk.setWrapStyleWord(true);
        harga_listStruk.setBorder(null);
        harga_listStruk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        harga_listStruk.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        harga_listStruk.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(harga_listStruk);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(1710, 560, 80, 150);

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nama_listStruk.setEditable(false);
        nama_listStruk.setBackground(new java.awt.Color(255, 255, 255));
        nama_listStruk.setColumns(7);
        nama_listStruk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nama_listStruk.setRows(7);
        nama_listStruk.setToolTipText("");
        nama_listStruk.setWrapStyleWord(true);
        nama_listStruk.setBorder(null);
        nama_listStruk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nama_listStruk.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        nama_listStruk.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(nama_listStruk);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(1570, 560, 130, 150);

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jumlah_listStruk.setEditable(false);
        jumlah_listStruk.setBackground(new java.awt.Color(255, 255, 255));
        jumlah_listStruk.setColumns(7);
        jumlah_listStruk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jumlah_listStruk.setRows(7);
        jumlah_listStruk.setToolTipText("");
        jumlah_listStruk.setWrapStyleWord(true);
        jumlah_listStruk.setBorder(null);
        jumlah_listStruk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jumlah_listStruk.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jumlah_listStruk.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jumlah_listStruk);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(1500, 560, 50, 150);

        btnHapus_Keranjang.setBackground(new java.awt.Color(0, 0, 0));
        btnHapus_Keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-hapus_2.png"))); // NOI18N
        btnHapus_Keranjang.setBorder(null);
        btnHapus_Keranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus_Keranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus_KeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus_Keranjang);
        btnHapus_Keranjang.setBounds(870, 770, 80, 30);

        namaToko_struk.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        namaToko_struk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaToko_struk.setText("Nama Toko");
        getContentPane().add(namaToko_struk);
        namaToko_struk.setBounds(1550, 470, 230, 30);

        link_history.setBackground(new java.awt.Color(0, 0, 0));
        link_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-History.png"))); // NOI18N
        link_history.setBorder(null);
        link_history.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_historyActionPerformed(evt);
            }
        });
        getContentPane().add(link_history);
        link_history.setBounds(1580, 50, 80, 40);

        link_profile.setBackground(new java.awt.Color(0, 0, 0));
        link_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-profile.png"))); // NOI18N
        link_profile.setBorder(null);
        link_profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_profileActionPerformed(evt);
            }
        });
        getContentPane().add(link_profile);
        link_profile.setBounds(1690, 50, 80, 40);

        link_logout.setBackground(new java.awt.Color(0, 0, 0));
        link_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-logout.png"))); // NOI18N
        link_logout.setBorder(null);
        link_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_logoutActionPerformed(evt);
            }
        });
        getContentPane().add(link_logout);
        link_logout.setBounds(1790, 50, 80, 40);

        btnRefreshTransaksi.setBackground(new java.awt.Color(0, 0, 0));
        btnRefreshTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-refresh.png"))); // NOI18N
        btnRefreshTransaksi.setBorder(null);
        btnRefreshTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshTransaksi);
        btnRefreshTransaksi.setBounds(1300, 160, 50, 30);

        btnRefreshStruk.setBackground(new java.awt.Color(0, 0, 0));
        btnRefreshStruk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-refresh.png"))); // NOI18N
        btnRefreshStruk.setBorder(null);
        btnRefreshStruk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshStruk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshStrukActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshStruk);
        btnRefreshStruk.setBounds(1780, 160, 50, 30);

        btnBayar.setBackground(new java.awt.Color(0, 0, 0));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-bayar.png"))); // NOI18N
        btnBayar.setBorder(null);
        btnBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBayar);
        btnBayar.setBounds(1170, 570, 80, 30);

        btnHapus_SemuaKeranjang.setBackground(new java.awt.Color(0, 0, 0));
        btnHapus_SemuaKeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-hapus_2.png"))); // NOI18N
        btnHapus_SemuaKeranjang.setBorder(null);
        btnHapus_SemuaKeranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus_SemuaKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus_SemuaKeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus_SemuaKeranjang);
        btnHapus_SemuaKeranjang.setBounds(1270, 360, 80, 30);

        btnHapus_Barang.setBackground(new java.awt.Color(0, 0, 0));
        btnHapus_Barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-hapus.png"))); // NOI18N
        btnHapus_Barang.setBorder(null);
        btnHapus_Barang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus_BarangActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus_Barang);
        btnHapus_Barang.setBounds(340, 830, 50, 30);

        btnKeranjang.setBackground(new java.awt.Color(0, 0, 0));
        btnKeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-leranjang.png"))); // NOI18N
        btnKeranjang.setBorder(null);
        btnKeranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeranjang);
        btnKeranjang.setBounds(880, 570, 80, 30);

        btnCariBarang.setBackground(new java.awt.Color(0, 0, 0));
        btnCariBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-cari_1.png"))); // NOI18N
        btnCariBarang.setBorder(null);
        btnCariBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBarangActionPerformed(evt);
            }
        });
        getContentPane().add(btnCariBarang);
        btnCariBarang.setBounds(610, 360, 50, 30);

        btnRefreshAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnRefreshAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btn-refresh.png"))); // NOI18N
        btnRefreshAdmin.setBorder(null);
        btnRefreshAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshAdminMouseClicked(evt);
            }
        });
        btnRefreshAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshAdminActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshAdmin);
        btnRefreshAdmin.setBounds(610, 160, 50, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Cari      :");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(390, 360, 60, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Filter Tabel :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 360, 100, 20);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg-tokoSembako.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1920, 950);

        setSize(new java.awt.Dimension(1936, 988));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FilterTabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterTabelActionPerformed
        // TODO add your handling code here:
        String selectedValue = FilterTabel.getSelectedItem().toString();
        switch (selectedValue) {
            case "Harga Termurah" -> {
                try {
                    Connection conn = Koneksi.ConnectDB();

                    Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                    tableModel = new DefaultTableModel(null,columnTitle);
                    TabelBarang.setModel(tableModel);
                    Statement statement = conn.createStatement();
                    tableModel.getDataVector().removeAllElements();

                    resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' ORDER BY harga ASC ");
                    int i = 1;
                    while(resultSet.next()){
                        Object[] data = {
                            i++,
                            resultSet.getString("kode"),
                            resultSet.getString("nama"),
                            resultSet.getString("harga"),
                            resultSet.getString("stok")
                        };
                        tableModel.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println("Data barang Gagal di tampilkan...");
                    System.out.println(e);
                }
            }
            case "Harga Termahal" -> {
                try {
                    Connection conn = Koneksi.ConnectDB();

                    Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                    tableModel = new DefaultTableModel(null,columnTitle);
                    TabelBarang.setModel(tableModel);
                    Statement statement = conn.createStatement();
                    tableModel.getDataVector().removeAllElements();

                    resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' ORDER BY harga DESC");
                    int i = 1;
                    while(resultSet.next()){
                        Object[] data = {
                            i++,
                            resultSet.getString("kode"),
                            resultSet.getString("nama"),
                            resultSet.getString("harga"),
                            resultSet.getString("stok")
                        };
                        tableModel.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println("Data barang Gagal di tampilkan...");
                    System.out.println(e);
                }
            }
            case "Stok" -> {
                try {
                    Connection conn = Koneksi.ConnectDB();

                    Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                    tableModel = new DefaultTableModel(null,columnTitle);
                    TabelBarang.setModel(tableModel);
                    Statement statement = conn.createStatement();
                    tableModel.getDataVector().removeAllElements();

                    resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' ORDER BY stok ASC");
                    int i = 1;
                    while(resultSet.next()){
                        Object[] data = {
                            i++,
                            resultSet.getString("kode"),
                            resultSet.getString("nama"),
                            resultSet.getString("harga"),
                            resultSet.getString("stok")
                        };
                        tableModel.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println("Data barang Gagal di tampilkan...");
                    System.out.println(e);
                }
            }
            case "Kat. Makanan" -> {
                try {
                    Connection conn = Koneksi.ConnectDB();

                    Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                    tableModel = new DefaultTableModel(null,columnTitle);
                    TabelBarang.setModel(tableModel);
                    Statement statement = conn.createStatement();
                    tableModel.getDataVector().removeAllElements();

                    resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kategori='Makanan'");
                    int i = 1;
                    while(resultSet.next()){
                        Object[] data = {
                            i++,
                            resultSet.getString("kode"),
                            resultSet.getString("nama"),
                            resultSet.getString("harga"),
                            resultSet.getString("stok")
                        };
                        tableModel.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            case "Kat. Minuman" -> {
                try {
                    Connection conn = Koneksi.ConnectDB();

                    Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                    tableModel = new DefaultTableModel(null,columnTitle);
                    TabelBarang.setModel(tableModel);
                    Statement statement = conn.createStatement();
                    tableModel.getDataVector().removeAllElements();

                    resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kategori='Minuman'");
                    int i = 1;
                    while(resultSet.next()){
                        Object[] data = {
                            i++,
                            resultSet.getString("kode"),
                            resultSet.getString("nama"),
                            resultSet.getString("harga"),
                            resultSet.getString("stok")
                        };
                        tableModel.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println("Data barang Gagal di tampilkan...");
                    System.out.println(e);
                }
            }
            case "Kat. Lain-lain" -> {
                try {
                    Connection conn = Koneksi.ConnectDB();

                    Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                    tableModel = new DefaultTableModel(null,columnTitle);
                    TabelBarang.setModel(tableModel);
                    Statement statement = conn.createStatement();
                    tableModel.getDataVector().removeAllElements();

                    resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kategori='Lain-lain'");
                    int i = 1;
                    while(resultSet.next()){
                        Object[] data = {
                            i++,
                            resultSet.getString("kode"),
                            resultSet.getString("nama"),
                            resultSet.getString("harga"),
                            resultSet.getString("stok")
                        };
                        tableModel.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println("Data barang Gagal di tampilkan...");
                    System.out.println(e);
                }
            }
            default -> showTabelBarang();
        }
    }//GEN-LAST:event_FilterTabelActionPerformed

    private void cmbKategori_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKategori_TambahActionPerformed
        
    }//GEN-LAST:event_cmbKategori_TambahActionPerformed

    private void btnRefreshAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshAdminMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshAdminMouseClicked

    private void btnRefreshAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshAdminActionPerformed
        // TODO add your handling code here:
        RefreshTampilanAdmin();
    }//GEN-LAST:event_btnRefreshAdminActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        // TODO add your handling code here:
        FilterTabel.setSelectedItem("-Pilih-");
        if(!txtCariBarang.getText().isEmpty() && !txtCariBarang.getText().isBlank() && FieldCari.getSelectedItem() != "-Pilih-"){
            try {
                Connection conn = Koneksi.ConnectDB();
                Object[] columnTitle = {"No.","Kode","Nama Barang","Harga","Stok"};
                tableModel = new DefaultTableModel(null,columnTitle);
                TabelBarang.setModel(tableModel);
                Statement statement = conn.createStatement();
                tableModel.getDataVector().removeAllElements();

                resultSet = statement.executeQuery("SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND "+FieldCari.getSelectedItem()+" LIKE '%"+txtCariBarang.getText()+"%'");
                int i = 1;
                while(resultSet.next()){
                    Object[] data = {
                        i++,
                        resultSet.getString("kode"),
                        resultSet.getString("nama"),
                        resultSet.getString("harga"),
                        resultSet.getString("stok")
                    };
                    tableModel.addRow(data);
                }
                if(i == 1){
                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nMohon Maaf, Data tidak ditemukan!", "Gagal", JOptionPane.INFORMATION_MESSAGE);
                    showTabelBarang();
                }
            } catch (SQLException e) {
                System.out.println("Data barang Gagal di tampilkan...");
                System.out.println(e);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nInputan pencarian harus diisi dengan benar!", "Gagal", JOptionPane.ERROR_MESSAGE);
            showTabelBarang();
        }
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void btnSimpan_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan_TambahActionPerformed
        // TODO add your handling code here:
        String nama = txtNama_Tambah.getText();
        String harga = txtHarga_Tambah.getText();
        String stok = txtStok_Tambah.getText();
        String kategori = (String) cmbKategori_Tambah.getSelectedItem();
        
        if(!nama.isEmpty() && !nama.isBlank() && !harga.isEmpty() && !harga.isBlank() && !stok.isEmpty() && !stok.isBlank() && !"-Pilih-".equals(kategori)){
            if (harga.matches("\\d+")) {
                if(stok.matches("\\d+")){
                   if(Integer.parseInt(harga) >= 0){
                       if(Integer.parseInt(stok) > 0){
                            // generate kode barang
                            try {
                                Connection conn = Koneksi.ConnectDB();
                                String query = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kategori='"+ kategori+"'";
                                Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery(query);
                                int idAkhir = 0;
                                String resKode = "";
                                while(rs.next()){
                                    idAkhir++;
                                    resKode = rs.getString("kode");
                                }
                                
                                resKode = resKode.replace("MA", "");
                                resKode = resKode.replace("MI", "");
                                resKode = resKode.replace("LA", "");
                                
                                
                                if(idAkhir == 0){
                                    idAkhir++;
                                }else{
                                    idAkhir = Integer.parseInt(resKode) + 1;
                                }
                                
                                String kodeBarang;
                                if("Makanan".equals(kategori)){ 
                                    kodeBarang = idAkhir + "MA";
                                }else if("Minuman".equals(kategori)){
                                    kodeBarang = idAkhir + "MI";
                                }else{
                                    kodeBarang = idAkhir + "LA";
                                }
                                
                                String insrt = "INSERT INTO `tb_barang`(`kode`, `id_user`, `nama`, `harga`, `stok`, `kategori`) VALUES ('"+kodeBarang+"','"+Session.session.getSession()+"','"+nama+"','"+harga+"','"+stok+"','"+kategori+"')";
                                PreparedStatement preStmt = conn.prepareStatement(insrt);
                                preStmt.execute();
                                JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nBarang berhasil ditambahkan!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                RefreshTampilanAdmin();
                                
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(rootPane, "Oopss...\nData ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
                                System.out.println(e);
                                RefreshTampilanAdmin();
                            }
                       }else{
                           JOptionPane.showMessageDialog(rootPane, "Oopss...\nMinimum stok adalah 1", "Gagal", JOptionPane.ERROR_MESSAGE);
                       }
                   }else{
                       JOptionPane.showMessageDialog(rootPane, "Oopss...\nMinimum Harga adalah Rp 0", "Gagal", JOptionPane.ERROR_MESSAGE);
                   }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nStok hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nHarga hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nData harus diisi dengan benar!", "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSimpan_TambahActionPerformed

    private void btnHapus_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus_BarangActionPerformed
        // TODO add your handling code here:\
        
        try {
            Connection conn = Koneksi.ConnectDB();
            String query = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kode='"+ txtKode_Hapus.getText() +"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String resNama = null;
            while(rs.next()){
                resNama = rs.getString("nama");
            }
            
            if(resNama == null){
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nData tidak ditemukan!", "Gagal", JOptionPane.ERROR_MESSAGE);
                RefreshTampilanAdmin();
            }else{
                int Pilih = JOptionPane.showConfirmDialog(rootPane,"Yakin akan menghapus data ini ?\nKode  :  "+txtKode_Hapus.getText()+"\nNama  :  "+resNama+"\n\n","Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
                if(Pilih == JOptionPane.OK_OPTION){
                    String delete = "DELETE FROM `tb_barang` WHERE id_user='"+Session.session.getSession()+"' AND kode='"+ txtKode_Hapus.getText() +"'";
                    PreparedStatement prs = conn.prepareStatement(delete);
                    prs.execute();
                    JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nBarang berhasil diHapus!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    RefreshTampilanAdmin();
                }else if(Pilih == JOptionPane.CANCEL_OPTION){
                    RefreshTampilanAdmin();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nData tidak ditemukan!", "Gagal", JOptionPane.ERROR_MESSAGE);
            RefreshTampilanAdmin();
        }
    }//GEN-LAST:event_btnHapus_BarangActionPerformed

    String kodeUbahBarang = null;
    private void TabelBarangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelBarangMousePressed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel)TabelBarang.getModel();
        int selectedRowIndex = TabelBarang.getSelectedRow();
        
        txtNama_Ubah.setText(model.getValueAt(selectedRowIndex, 2).toString());
        txtHarga_Ubah.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txtStok_Ubah.setText(model.getValueAt(selectedRowIndex, 4).toString());
        
        kodeUbahBarang = model.getValueAt(selectedRowIndex, 1).toString();
        lblKode_Ubah.setText(kodeUbahBarang);
        txtKode_Hapus.setText(kodeUbahBarang);
    }//GEN-LAST:event_TabelBarangMousePressed

    private void btnSimpan_UbahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan_UbahBarangActionPerformed
        // TODO add your handling code here:
        String nama = txtNama_Ubah.getText();
        String harga = txtHarga_Ubah.getText();
        String stok = txtStok_Ubah.getText();
        
        if(kodeUbahBarang == null){
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nPilih data yang akan diubah pada tabel di atas!", "Gagal", JOptionPane.ERROR_MESSAGE);
        }else{
            if(!nama.isEmpty() && !nama.isBlank() && !harga.isEmpty() && !harga.isBlank() && !stok.isEmpty() && !stok.isBlank()){
                if (harga.matches("\\d+")) {
                    if(stok.matches("\\d+")){
                        if(Integer.parseInt(harga) >= 0){
                            if(Integer.parseInt(stok) > 0){
                                // generate kode barang
                                try {
                                    Connection conn = Koneksi.ConnectDB();
                                    String insrt = "UPDATE `tb_barang` SET `nama`='"+nama+"',`harga`='"+harga+"',`stok`='"+stok+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+kodeUbahBarang+"'";
                                    PreparedStatement preStmt = conn.prepareStatement(insrt);
                                    preStmt.execute();
                                    JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nBarang berhasil diubah!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                    RefreshTampilanAdmin();

                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nData ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
                                    System.out.println(e);
                                    RefreshTampilanAdmin();
                                }
                            }else{
                                JOptionPane.showMessageDialog(rootPane, "Oopss...\nMinimum stok adalah 1", "Gagal", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Oopss...\nMinimum Harga adalah Rp 0", "Gagal", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Oopss...\nStok hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nHarga hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nData harus diisi dengan benar!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSimpan_UbahBarangActionPerformed

    private void btnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangActionPerformed
        // TODO add your handling code here:
        String kode = txtKode_Keranjang.getText();
        String jumlah = txtJumlah_Keranjang.getText();
        
        try {
            if(!kode.isEmpty() && !kode.isBlank() && !jumlah.isEmpty() && !jumlah.isBlank()){
                if(jumlah.matches("\\d+")){
                    Connection conn = Koneksi.ConnectDB();
                    String query = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kode='"+kode+"'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    String resKode = null;
                    String resNama = null;
                    String resHarga = null;
                    int resStok = 0;
                    while(rs.next()){
                        resKode = rs.getString("kode");
                        resNama = rs.getString("nama");
                        resHarga = rs.getString("harga");
                        resStok = Integer.parseInt(rs.getString("stok"));
                    }

                    if(resKode == null){
                        JOptionPane.showMessageDialog(rootPane, "Oopss...\nBarang belum terdaftar!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(resStok > 0){
                            if(Integer.parseInt(jumlah) > resStok){
                                JOptionPane.showMessageDialog(rootPane, "Oopss...\nJumlah melebihi stok barang!", "Gagal", JOptionPane.ERROR_MESSAGE);
                            }else{
                                String queryy = "SELECT * FROM tb_keranjang WHERE id_user='"+Session.session.getSession()+"' AND kode_barang='"+resKode+"'";
                                Statement stt = conn.createStatement();
                                ResultSet rss = stt.executeQuery(queryy);
                                String ressKode = null;
                                String ressJumlah = null;
                                while(rss.next()){
                                    ressKode = rss.getString("kode");
                                    ressJumlah = rss.getString("jumlah");
                                }
                                if(Integer.parseInt(jumlah) > 0){
                                    if(ressKode == null){
                                        String insrt = "INSERT INTO `tb_keranjang`(`id_user`, `kode_barang`, `nama`, `harga`, `jumlah`) VALUES ('"+Session.session.getSession()+"','"+resKode+"','"+resNama+"','"+resHarga+"','"+jumlah+"')";
                                        PreparedStatement preStmt = conn.prepareStatement(insrt);
                                        preStmt.execute();

                                        String qry = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kode='"+resKode+"'";
                                        Statement sst = conn.createStatement();
                                        ResultSet rrs = sst.executeQuery(qry);
                                        while(rrs.next()){
                                            int rrsStok = Integer.parseInt(rrs.getString("stok"));
                                            String updt = "UPDATE `tb_barang` SET `stok`='"+(rrsStok-Integer.parseInt(jumlah))+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+resKode+"'";
                                            PreparedStatement pr = conn.prepareStatement(updt);
                                            pr.execute();
                                        }

                                    }else{
                                        String updt = "UPDATE `tb_keranjang` SET `jumlah`='"+(Integer.parseInt(ressJumlah)+Integer.parseInt(jumlah))+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode_barang`='"+resKode+"'";
                                        PreparedStatement preStmt = conn.prepareStatement(updt);
                                        preStmt.execute();

                                        String qry = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kode='"+resKode+"'";
                                        Statement sst = conn.createStatement();
                                        ResultSet rrs = sst.executeQuery(qry);
                                        while(rrs.next()){
                                            int rrsStok = Integer.parseInt(rrs.getString("stok"));
                                            String upd = "UPDATE `tb_barang` SET `stok`='"+(rrsStok-Integer.parseInt(jumlah))+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+resKode+"'";
                                            PreparedStatement pr = conn.prepareStatement(upd);
                                            pr.execute();
                                        }

                                    }
                                    JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nBarang berhasil ditambahkan ke keranjang!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                    RefreshTampilanTransaksi();
                                    RefreshTampilanAdmin();
                                }else{
                                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nJumlah min.1", "Gagal", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Oopss...\nStok barang telah Habis!", "Gagal", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nJumlah hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nData harus diisi dengan benar!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nData ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
            RefreshTampilanTransaksi();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnKeranjangActionPerformed

    private void btnRefreshTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshTransaksiActionPerformed
        // TODO add your handling code here:
        RefreshTampilanTransaksi();
    }//GEN-LAST:event_btnRefreshTransaksiActionPerformed

    private void btnHapus_SemuaKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus_SemuaKeranjangActionPerformed
        // TODO add your handling code here:
        try {
            int Pilih = JOptionPane.showConfirmDialog(rootPane,"Yakin akan mengkosongkan isi keranjang?","Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
            if(Pilih == JOptionPane.OK_OPTION){
                Connection conn = Koneksi.ConnectDB();
                
                String query = "SELECT * FROM tb_keranjang WHERE id_user='"+Session.session.getSession()+"'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    int resJumlah = Integer.parseInt(rs.getString("jumlah"));
                    String resKode = rs.getString("kode_barang");

                    String queryy = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kode='"+resKode+"'";
                    Statement stt = conn.createStatement();
                    ResultSet rss = stt.executeQuery(queryy);
                    while(rss.next()){
                        int resStok = Integer.parseInt(rss.getString("stok"));

                        String updt = "UPDATE `tb_barang` SET `stok`='"+(resStok+resJumlah)+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+resKode+"'";
                        PreparedStatement preStmt = conn.prepareStatement(updt);
                        preStmt.execute();
                    }
                }
                
                String delete = "DELETE FROM `tb_keranjang` WHERE id_user='"+Session.session.getSession()+"'";
                PreparedStatement prs = conn.prepareStatement(delete);
                prs.execute();
                JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nKeranjang berhasil diHapus!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                RefreshTampilanTransaksi();
                RefreshTampilanAdmin();
                
            }else if(Pilih == JOptionPane.CANCEL_OPTION){
                RefreshTampilanTransaksi();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nPermintaan ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
            RefreshTampilanTransaksi();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnHapus_SemuaKeranjangActionPerformed

    private void TabelKeranjangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelKeranjangMousePressed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)TabelKeranjang.getModel();
        int selectedRowIndex = TabelKeranjang.getSelectedRow();
        
        txtKode_KeranjangHapus.setText(model.getValueAt(selectedRowIndex, 1).toString());
        lblKode_UbahKeranjang.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txtJumlah_UbahKeranjang.setText(model.getValueAt(selectedRowIndex, 4).toString());
    }//GEN-LAST:event_TabelKeranjangMousePressed

    private void btnHapus_KeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus_KeranjangActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = Koneksi.ConnectDB();
            String query = "SELECT * FROM tb_keranjang WHERE id_user='"+Session.session.getSession()+"' AND kode_barang='"+ txtKode_KeranjangHapus.getText() +"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String resNama = null;
            String resJumlah = null;
            while(rs.next()){
                resNama = rs.getString("nama");
                resJumlah = rs.getString("jumlah");
            }
            
            if(resNama == null){
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nBarang tidak ada dikeranjang!", "Gagal", JOptionPane.ERROR_MESSAGE);
                RefreshTampilanTransaksi();
            }else{
                int Pilih = JOptionPane.showConfirmDialog(rootPane,"Yakin akan menghapus data keranjang ini ?\nKode  :  "+txtKode_KeranjangHapus.getText()+"\nNama  :  "+resNama+"\nJumlah  :  "+resJumlah+"\n\n","Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
                if(Pilih == JOptionPane.OK_OPTION){
                    String delete = "DELETE FROM `tb_keranjang` WHERE id_user='"+Session.session.getSession()+"' AND kode_barang='"+ txtKode_KeranjangHapus.getText() +"'";
                    PreparedStatement prs = conn.prepareStatement(delete);
                    prs.execute();
                    
                    String qry = "SELECT * FROM tb_barang WHERE id_user='"+Session.session.getSession()+"' AND kode='"+txtKode_KeranjangHapus.getText()+"'";
                    Statement sst = conn.createStatement();
                    ResultSet rrs = sst.executeQuery(qry);
                    while(rrs.next()){
                        int rrsStok = Integer.parseInt(rrs.getString("stok"));
                        String upd = "UPDATE `tb_barang` SET `stok`='"+(rrsStok+Integer.parseInt(resJumlah))+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+txtKode_KeranjangHapus.getText()+"'";
                        PreparedStatement pr = conn.prepareStatement(upd);
                        pr.execute();
                    }
                    
                    JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nBarang berhasil diHapus!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    RefreshTampilanTransaksi();
                    RefreshTampilanAdmin();
                }else if(Pilih == JOptionPane.CANCEL_OPTION){
                    RefreshTampilanTransaksi();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nPermintaan ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
            RefreshTampilanTransaksi();
        }
    }//GEN-LAST:event_btnHapus_KeranjangActionPerformed

    public String idTransaksi;
    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        String uang = txtJumlahUang_Bayar.getText();
        if(!uang.isEmpty() && !uang.isBlank()){
            if (uang.matches("\\d+")) {
                if(Integer.parseInt(uang) >= KeranjangTotalBayar){
                    lblRingkasan_Total.setText(RupiahFromat(KeranjangTotalBayar));
                    lblRingkasan_Uang.setText(RupiahFromat(Integer.parseInt(uang)));
                    int kembalian = Integer.parseInt(uang)-KeranjangTotalBayar;
                    lblRingkasan_Kembalian.setText(RupiahFromat(kembalian));
                    txtJumlahUang_Bayar.setText("");
                    totalKeranjang.setText("0");
                    totalKeranjang_Pembayaran.setText("0");
                    try {
                        Connection conn = Koneksi.ConnectDB();
                        
                        String qrry = "SELECT * FROM tb_transaksi WHERE id_user='"+Session.session.getSession()+"'";
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(qrry);
                        int count = 0;
                        while(rs.next()){
                            idTransaksi = rs.getString("id");
                            count ++;
                        }
                        
                        if(count == 0){
                            idTransaksi = "1";
                        }else{
                            int setId = Integer.parseInt(idTransaksi) + 1;
                            idTransaksi = Integer.toString(setId);
                        }
                        
                        String qry = "SELECT * FROM tb_keranjang WHERE id_user='"+Session.session.getSession()+"'";
                        Statement sst = conn.createStatement();
                        ResultSet rrs = sst.executeQuery(qry);
                        int JumlahBarang = 0;
                        String ListBarang = "";
                        while(rrs.next()){
                            String nama = rrs.getString("nama");
                            String harga = rrs.getString("harga");
                            String jumlah = rrs.getString("jumlah");
                            if(JumlahBarang == 0){
                                ListBarang += nama;
                            }else{
                                ListBarang += ","+nama;
                            }
                            JumlahBarang += Integer.parseInt(jumlah);
                            String tanggal = getTanggal();
                            String insrt = "INSERT INTO `tb_transaksi` (`id_keranjang`, `id_user`, `nama`, `jumlah`, `harga`, `tanggal`) VALUES ('"+idTransaksi+"','"+Session.session.getSession()+"','"+nama+"','"+jumlah+"','"+harga+"','"+tanggal+"')";
                            PreparedStatement prs = conn.prepareStatement(insrt);
                            prs.execute();
                        }
                        
                        String delete = "DELETE FROM `tb_keranjang` WHERE id_user='"+Session.session.getSession()+"'";
                        PreparedStatement prs = conn.prepareStatement(delete);
                        prs.execute();
                        
                        String qr = "SELECT * FROM `tb_users` WHERE id='"+Session.session.getSession()+"'";
                        Statement stt = conn.createStatement();
                        ResultSet rss = stt.executeQuery(qr);
                        String pendapatan = "0";
                        while(rss.next()){
                            pendapatan = rss.getString("pendapatan");
                        }
                        String totalBayar = lblRingkasan_Total.getText().replace(".", "");
                        int totalPen = Integer.parseInt(pendapatan) + Integer.parseInt(totalBayar);
                        String updt = "UPDATE `tb_users` SET `pendapatan`='"+totalPen+"' WHERE id='"+Session.session.getSession()+"'";
                        PreparedStatement pr = conn.prepareStatement(updt);
                        pr.execute();
                        
                        
                        
                        // => generate Id histori <=
                        String id_transaksi = "";
                        id_transaksi += idTransaksi;
                        id_transaksi += Session.session.getSession();
                        id_transaksi += JumlahBarang;
                        id_transaksi += getTanggal();
                        id_transaksi = id_transaksi.replace("/", "");
                        
                        // insert history
                        String insHistory = "INSERT INTO `tb_history`(`id`, `id_transaksi`, `id_user`, `list_barang`, `jumlah_barang`, `total_tagihan`, `jumlah_uang`, `kembalian_uang`, `tanggal`) VALUES ('"+id_transaksi+"','"+idTransaksi+"','"+Session.session.getSession()+"','"+ListBarang+"','"+JumlahBarang+"','"+KeranjangTotalBayar+"','"+uang+"','"+kembalian+"','"+getTanggal()+"')";
                        PreparedStatement pre = conn.prepareStatement(insHistory);
                        pre.execute();
                        
                        
                        JOptionPane.showMessageDialog(rootPane, "Berhasil... \nPembayaran Berhasil\nKeranjang akan dikosongkan!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(rootPane, "Oopss...\nPermintaan ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                    }
                    showTabelKeranjang();

                }else{
                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nUang Tidak cukup untuk membayar!", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nHarga hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nData harus diisi dengan benar!", "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnSimban_UbahKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimban_UbahKeranjangActionPerformed
        // TODO add your handling code here:
        String kode = lblKode_UbahKeranjang.getText();
        String jumlah = txtJumlah_UbahKeranjang.getText();
        
        if("-".equals(kode)){
            JOptionPane.showMessageDialog(rootPane, "Oopss...\nPilih data yang akan diubah pada tabel di atas!", "Gagal", JOptionPane.ERROR_MESSAGE);
        }else{
            if(!kode.isEmpty() && !kode.isBlank()){
                if (jumlah.matches("\\d+")) {
                    if(Integer.parseInt(jumlah) > 0){
                        try {
                            Connection conn = Koneksi.ConnectDB();
                            // cek barang di stok
                            String qr1 = "SELECT * FROM `tb_barang` WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+kode+"'";
                            Statement st1 = conn.createStatement();
                            ResultSet rs1 = st1.executeQuery(qr1);
                            String stokBarang = "0";
                            while(rs1.next()){
                                stokBarang = rs1.getString("stok");
                            }
                            
                            //cek jumlah keranjang
                            String qr2 = "SELECT * FROM `tb_keranjang` WHERE id_user='"+Session.session.getSession()+"' AND `kode_barang`='"+kode+"'";
                            Statement st2 = conn.createStatement();
                            ResultSet rs2 = st2.executeQuery(qr2);
                            String jumlahKeranjang = "0";
                            while(rs2.next()){
                                jumlahKeranjang = rs2.getString("jumlah");
                            }
                            
                            if(Integer.parseInt(jumlah) > Integer.parseInt(jumlahKeranjang)){
                                int selisih = Integer.parseInt(jumlah) - Integer.parseInt(jumlahKeranjang);
                                if(Integer.parseInt(stokBarang) < selisih){
                                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nStok barang tidak mencukupi jumlah yang ditambah ke keranjang!", "Gagal", JOptionPane.ERROR_MESSAGE);
                                }else{
                                    int hitung = Integer.parseInt(stokBarang) - selisih;
                                    String updt = "UPDATE `tb_barang` SET `stok`='"+hitung+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+kode+"'";
                                    PreparedStatement preSt = conn.prepareStatement(updt);
                                    preSt.execute();
                                    
                                    String insrt = "UPDATE `tb_keranjang` SET `jumlah`='"+jumlah+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode_barang`='"+kode+"'";
                                    PreparedStatement preStmt = conn.prepareStatement(insrt);
                                    preStmt.execute();
                                    JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nKeranjang berhasil diubah!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                    RefreshTampilanTransaksi();
                                    RefreshTampilanAdmin();
                                }
                            }else if(Integer.parseInt(jumlah) < Integer.parseInt(jumlahKeranjang)){
                                int selisih = Integer.parseInt(jumlahKeranjang) - Integer.parseInt(jumlah);
                                if(Integer.parseInt(stokBarang) < selisih){
                                    if(Integer.parseInt(stokBarang) == 0){
                                        int hitung = Integer.parseInt(stokBarang) + selisih;
                                        String updt = "UPDATE `tb_barang` SET `stok`='"+hitung+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+kode+"'";
                                        PreparedStatement preSt = conn.prepareStatement(updt);
                                        preSt.execute();

                                        String insrt = "UPDATE `tb_keranjang` SET `jumlah`='"+jumlah+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode_barang`='"+kode+"'";
                                        PreparedStatement preStmt = conn.prepareStatement(insrt);
                                        preStmt.execute();
                                        JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nKeranjang berhasil diubah!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                        RefreshTampilanTransaksi();
                                        RefreshTampilanAdmin();
                                    }else{
                                        JOptionPane.showMessageDialog(rootPane, "Oopss...\nStok barang tidak mencukupi jumlah yang ditambah ke keranjang!", "Gagal", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                    int hitung = Integer.parseInt(stokBarang) + selisih;
                                    String updt = "UPDATE `tb_barang` SET `stok`='"+hitung+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode`='"+kode+"'";
                                    PreparedStatement preSt = conn.prepareStatement(updt);
                                    preSt.execute();
                                    
                                    String insrt = "UPDATE `tb_keranjang` SET `jumlah`='"+jumlah+"' WHERE id_user='"+Session.session.getSession()+"' AND `kode_barang`='"+kode+"'";
                                    PreparedStatement preStmt = conn.prepareStatement(insrt);
                                    preStmt.execute();
                                    JOptionPane.showMessageDialog(rootPane, "Berhasil.. \nKeranjang berhasil diubah!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                    RefreshTampilanTransaksi();
                                    RefreshTampilanAdmin();
                                }
                            }

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(rootPane, "Oopss...\nData ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
                            System.out.println(e);
                            RefreshTampilanTransaksi();
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Oopss...\nMinimum jumlah adalah 1", "Gagal", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Oopss...\nJumlah hanya boleh berupa Angka!", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nData harus diisi dengan benar!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSimban_UbahKeranjangActionPerformed

    private void btnRefreshStrukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshStrukActionPerformed
        // TODO add your handling code here:
        lblRingkasan_Total.setText("0");
        lblRingkasan_Uang.setText("0");
        lblRingkasan_Kembalian.setText("0");
        lblTanggalValue.setText("0/0/0");
        jumlah_listStruk.setText("");
        struk_TotalBayar.setText("Rp 0");
        struk_JumlahUang.setText("Rp 0");
        struk_KembalianUang.setText("Rp 0");
        harga_listStruk.setText("");
        nama_listStruk.setText("");
        jumlah_listStruk.setText("");
    }//GEN-LAST:event_btnRefreshStrukActionPerformed

    public String getTanggal(){
        LocalDate tanggalSekarang = LocalDate.now();
        
        int tanggal = tanggalSekarang.getDayOfMonth();
        int bulan = tanggalSekarang.getMonthValue();
        int tahun = tanggalSekarang.getYear();

        return tanggal + "/" + bulan + "/" + tahun;
    }
    
    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        if("0".equals(lblRingkasan_Uang.getText())){
           JOptionPane.showMessageDialog(rootPane, "Oopss...\nHarap selesaikan pembayaran!", "Gagal", JOptionPane.ERROR_MESSAGE);
        } else{
            lblTanggalValue.setText(getTanggal());
            struk_TotalBayar.setText("Rp "+lblRingkasan_Total.getText());
            struk_JumlahUang.setText("Rp "+lblRingkasan_Uang.getText());
            struk_KembalianUang.setText("Rp "+lblRingkasan_Kembalian.getText());

            try {
                Connection conn = Koneksi.ConnectDB();
                String qry = "SELECT * FROM tb_transaksi WHERE id_user='"+Session.session.getSession()+"' AND id_keranjang='"+idTransaksi+"' AND tanggal='"+getTanggal()+"'";
                Statement sst = conn.createStatement();
                ResultSet rrs = sst.executeQuery(qry);
                String jumlahRes = "";
                String namaRes = "";
                String hargaRes = "";
                while(rrs.next()){
                    String jumlah = rrs.getString("jumlah");
                    jumlahRes +=  jumlah + "\n";
                    
                    String nama = rrs.getString("nama");
                    namaRes +=  nama + "\n";
                   
                    String harga = rrs.getString("harga");
                    hargaRes +=  String.format("%5s", "Rp "+RupiahFromat(Integer.parseInt(harga))+"\n");
                }
                jumlah_listStruk.append(jumlahRes);
                nama_listStruk.append(namaRes);
                harga_listStruk.append(hargaRes);
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, "Oopss...\nData ditolak!", "Gagal", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        }
        
    }//GEN-LAST:event_btnPrintActionPerformed

    private void link_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_logoutActionPerformed
        // TODO add your handling code here:
        
        int Pilih = JOptionPane.showConfirmDialog(rootPane,"Yakin ingin logout?","Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(Pilih == JOptionPane.OK_OPTION){
            //set session
            Session.session.setSession(null);
            Login login = new Login();
            this.setVisible(false);
            login.setVisible(true);
            
        }else if(Pilih == JOptionPane.CANCEL_OPTION){
            RefreshTampilanTransaksi();
        }
    }//GEN-LAST:event_link_logoutActionPerformed

    private void link_profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_profileActionPerformed
        // TODO add your handling code here:
        Profile profile = new Profile();
        this.setVisible(false);
        profile.setVisible(true);
    }//GEN-LAST:event_link_profileActionPerformed

    private void txtJumlah_UbahKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlah_UbahKeranjangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlah_UbahKeranjangActionPerformed

    private void link_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_historyActionPerformed
        // TODO add your handling code here:
        History history = new History();
        this.setVisible(false);
        history.setVisible(true);
    }//GEN-LAST:event_link_historyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiTokoSembako.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiTokoSembako.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiTokoSembako.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiTokoSembako.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiTokoSembako().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> FieldCari;
    private javax.swing.JComboBox<String> FilterTabel;
    private javax.swing.JLabel NamaToko;
    private javax.swing.JLabel NamaToko_struk;
    private javax.swing.JTable TabelBarang;
    private javax.swing.JTable TabelKeranjang;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnHapus_Barang;
    private javax.swing.JButton btnHapus_Keranjang;
    private javax.swing.JButton btnHapus_SemuaKeranjang;
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefreshAdmin;
    private javax.swing.JButton btnRefreshStruk;
    private javax.swing.JButton btnRefreshTransaksi;
    private javax.swing.JButton btnSimban_UbahKeranjang;
    private javax.swing.JButton btnSimpan_Tambah;
    private javax.swing.JButton btnSimpan_UbahBarang;
    private javax.swing.JComboBox<String> cmbKategori_Tambah;
    private javax.swing.JTextArea harga_listStruk;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jumlah_listStruk;
    private javax.swing.JLabel lblKode_Ubah;
    private javax.swing.JLabel lblKode_UbahKeranjang;
    private javax.swing.JLabel lblRingkasan_Kembalian;
    private javax.swing.JLabel lblRingkasan_Total;
    private javax.swing.JLabel lblRingkasan_Uang;
    private javax.swing.JLabel lblTanggalValue;
    private javax.swing.JButton link_history;
    private javax.swing.JButton link_logout;
    private javax.swing.JButton link_profile;
    private javax.swing.JLabel namaToko_struk;
    private javax.swing.JTextArea nama_listStruk;
    private javax.swing.JLabel struk_JumlahUang;
    private javax.swing.JLabel struk_KembalianUang;
    private javax.swing.JLabel struk_TotalBayar;
    private javax.swing.JLabel totalKeranjang;
    private javax.swing.JLabel totalKeranjang_Pembayaran;
    private javax.swing.JTextField txtCariBarang;
    private javax.swing.JTextField txtHarga_Tambah;
    private javax.swing.JTextField txtHarga_Ubah;
    private javax.swing.JTextField txtJumlahUang_Bayar;
    private javax.swing.JTextField txtJumlah_Keranjang;
    private javax.swing.JTextField txtJumlah_UbahKeranjang;
    private javax.swing.JTextField txtKode_Hapus;
    private javax.swing.JTextField txtKode_Keranjang;
    private javax.swing.JTextField txtKode_KeranjangHapus;
    private javax.swing.JTextField txtNama_Tambah;
    private javax.swing.JTextField txtNama_Ubah;
    private javax.swing.JTextField txtStok_Tambah;
    private javax.swing.JTextField txtStok_Ubah;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/icon-apk.png")));
    }
}
