/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikeuangan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class pemasukan extends JPanel {

    // Variabel untuk menyimpan gambar background
    private Image bgImage;

    // --- CONSTRUCTOR ---
    public pemasukan() {
        // 1. Load Gambar Background
        loadBackgroundImage();
        
        // 2. Siapkan Tampilan UI
        initUI();
    }

    // --- LOGIKA LOAD GAMBAR ---
    private void loadBackgroundImage() {
        try {
            // GANTI PATH GAMBAR DI SINI SESUAI LOKASI DI LAPTOP KAMU
            String path = "C:\\Users\\MyBook Hype AMD\\Downloads\\Rectangle 74.png";
            ImageIcon icon = new ImageIcon(path);
            
            // Cek apakah gambar berhasil di-load
            if (icon.getIconWidth() == -1) {
                System.err.println("Gagal memuat gambar: Cek path file (" + path + ")");
            } else {
                bgImage = icon.getImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- MENGGAMBAR BACKGROUND RESPONSIVE ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gambar background memenuhi seluruh panel (Stretch)
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Jika gambar gagal load, pakai warna biru polos sebagai cadangan
            g.setColor(new Color(50, 50, 200));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    // --- MENYUSUN KOMPONEN UI ---
    private void initUI() {
        // Gunakan GridBagLayout agar konten otomatis berada di TENGAH (Center)
        setLayout(new GridBagLayout());

        // Buat Panel Wadah (Container) Transparan
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); // Layout manual di dalam wadah
        contentPanel.setOpaque(false); // Transparan
        contentPanel.setPreferredSize(new Dimension(900, 600)); // Ukuran area form

        // --- MULAI MASUKKAN KOMPONEN KE DALAM contentPanel ---

        // 1. Header & Back Button
        JLabel backArrow = new JLabel("<");
        backArrow.setForeground(Color.WHITE);
        backArrow.setFont(new Font("SansSerif", Font.BOLD, 24));
        backArrow.setBounds(0, 30, 30, 30);
        contentPanel.add(backArrow);

        JLabel titleLabel = new JLabel("Add Income");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBounds(40, 30, 200, 30);
        contentPanel.add(titleLabel);

        // 2. Date & Time
        RoundedTextField dateField = new RoundedTextField(15);
        dateField.setText("Wed, 19 Nov 2025");
        dateField.setBounds(0, 80, 240, 40);
        contentPanel.add(dateField);

        RoundedTextField timeField = new RoundedTextField(15);
        timeField.setText("10.35");
        timeField.setHorizontalAlignment(SwingConstants.CENTER);
        timeField.setBounds(250, 80, 80, 40);
        contentPanel.add(timeField);

        // 3. Amount
        RoundedTextField amountField = new RoundedTextField(15);
        amountField.setPlaceholder("Enter the amount");
        amountField.setBounds(0, 135, 330, 40);
        contentPanel.add(amountField);

        // 4. Title
        RoundedTextField titleField = new RoundedTextField(15);
        titleField.setPlaceholder("Type here for new title");
        titleField.setBounds(0, 190, 330, 40);
        contentPanel.add(titleField);

        // 5. Chips Kategori Baris 1
        // Value tombol akan masuk ke 'titleField'
        JPanel chipPanel1 = createChipPanel(
            new String[]{"Gaji Bulanan", "Jualan"}, 
            titleField
        );
        chipPanel1.setBounds(0, 240, 400, 40);
        contentPanel.add(chipPanel1);

        // 6. Input Kategori Baru
        RoundedTextField newCatField = new RoundedTextField(15);
        newCatField.setPlaceholder("Type here for new category");
        newCatField.setBounds(0, 290, 330, 40);
        contentPanel.add(newCatField);

        // 7. Chips Kategori Baris 2
        // Value tombol akan masuk ke 'newCatField'
        JPanel chipPanel2 = createChipPanel(
            new String[]{"Gaji Bulanan", "Jualan", "Freelance"}, 
            newCatField
        );
        chipPanel2.setBounds(0, 340, 400, 40);
        contentPanel.add(chipPanel2);

        // 8. Description
        RoundedTextField descField = new RoundedTextField(15);
        descField.setPlaceholder("Description (optional)");
        descField.setBounds(0, 400, 330, 50);
        contentPanel.add(descField);

        // 9. Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(650, 500, 100, 40);
        saveButton.setBackground(Color.WHITE);
        saveButton.setForeground(new Color(30, 60, 200));
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(Color.WHITE, 1, true),
                new javax.swing.border.EmptyBorder(5, 15, 5, 15)
        ));
        contentPanel.add(saveButton);

        // 10. Visualisasi Dompet (Garis-garis)
        JPanel walletIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 60)); // Putih Transparan
                g2.setStroke(new BasicStroke(2));
                
                // Gambar ikon dompet
                g2.drawRoundRect(10, 10, 180, 130, 30, 30);
                g2.drawOval(160, 60, 20, 20); // Kancing
                g2.drawRoundRect(30, 0, 180, 130, 30, 30); // Layer belakang
            }
        };
        walletIcon.setOpaque(false);
        walletIcon.setBounds(500, 150, 250, 200);
        contentPanel.add(walletIcon);

        // TERAKHIR: Masukkan contentPanel ke Panel Utama
        add(contentPanel);
    }

    // --- HELPER METHODS (Untuk membuat komponen berulang) ---

    // 1. Membuat Panel Tombol Kategori (Chips)
    // UPDATE: Menerima parameter JTextField targetField untuk auto-fill
    private JPanel createChipPanel(String[] labels, JTextField targetField) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setOpaque(false);
        p.setSize(400, 35); 
        
        for (String label : labels) {
            // Gunakan Class RoundedButton
            RoundedButton btn = new RoundedButton(label);
            
            // Logika Klik: Isi text field
            btn.addActionListener(e -> {
                if (targetField != null) {
                    targetField.setText(label);
                }
            });
            
            p.add(btn);
        }
        return p;
    }

    // 2. Custom Class untuk Input Text Bulat
    class RoundedTextField extends JTextField {
        private int radius;
        private String placeholder = "";

        public RoundedTextField(int radius) {
            this.radius = radius;
            setOpaque(false); 
            setBorder(new EmptyBorder(5, 15, 5, 15)); 
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(Color.DARK_GRAY);
        }

        public void setPlaceholder(String text) {
            this.placeholder = text;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Gambar Background Putih
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            
            super.paintComponent(g2);
            
            // Gambar Placeholder Text
            if (getText().isEmpty() && !placeholder.isEmpty()) {
                g2.setColor(Color.GRAY);
                g2.drawString(placeholder, 15, getHeight() / 2 + 5);
            }
            g2.dispose();
        }
        
        @Override 
        protected void paintBorder(Graphics g) { } // Hapus border default
    }
    
    // 3. Custom Class untuk Tombol Bulat Penuh (Kapsul)
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("SansSerif", Font.PLAIN, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
            // Padding
            setBorder(new EmptyBorder(5, 15, 5, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Efek Klik
            if (getModel().isArmed()) {
                g2.setColor(new Color(255, 255, 255, 100)); // Putih saat ditekan
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, getHeight(), getHeight());
            } 
            
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            // Gambar Garis Melengkung Penuh (Kapsul)
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
            g2.dispose();
        }
    }

    // --- MAIN METHOD (Agar bisa dijalankan langsung) ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aplikasi Keuangan (Pure Java)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Gunakan BorderLayout agar panel mengisi penuh window
            frame.setLayout(new BorderLayout());
            frame.add(new pemasukan(), BorderLayout.CENTER);
            
            // Mode Full Screen
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }
}