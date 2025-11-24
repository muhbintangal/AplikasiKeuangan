/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplkeuangan;

/**
 *
 * @author ASUS
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class MySavingsFrame extends JFrame {

    // --- Konstanta ---
    private static final String FONT_FAMILY = "Calibri";
    private static final Color PRIMARY_BG_COLOR = new Color(39, 9, 193); 
    private static final Color SECONDARY_BG_COLOR = new Color(50, 20, 200);

    /**
     * Kelas kustom untuk membuat tombol bulat sempurna.
     */
    private class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(60, 60)); 
            setForeground(Color.WHITE); 
            setFont(new Font(FONT_FAMILY, Font.BOLD, 36)); 
            setVerticalAlignment(SwingConstants.CENTER);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isArmed()) {
                g2.setColor(new Color(255, 255, 255, 120)); 
            } else {
                g2.setColor(new Color(255, 255, 255, 80)); 
            }
            
            g2.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
            
            g2.dispose();
            super.paintComponent(g);
        }

        Shape shape;
        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
            }
            return shape.contains(x, y);
        }
    }

    /**
     * Panel kustom untuk satu baris riwayat transaksi.
     */
    private class TransactionItemPanel extends JPanel {
        public TransactionItemPanel(String description, String amount, String date, Color amountColor) {
            setLayout(new BorderLayout(10, 5));
            setBackground(SECONDARY_BG_COLOR);
            setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

            // Kiri: Deskripsi
            JLabel descLabel = new JLabel(description);
            descLabel.setForeground(Color.WHITE);
            descLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
            
            // Kanan Atas: Jumlah
            JLabel amountLabel = new JLabel(amount);
            amountLabel.setForeground(amountColor);
            amountLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
            amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // Kanan Bawah: Tanggal
            JLabel dateLabel = new JLabel(date);
            dateLabel.setForeground(Color.LIGHT_GRAY);
            dateLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, 12));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // Panel untuk jumlah dan tanggal
            JPanel rightPanel = new JPanel(new GridLayout(2, 1));
            rightPanel.setOpaque(false);
            rightPanel.add(amountLabel);
            rightPanel.add(dateLabel);

            add(descLabel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);
        }
    }
    
    // --- Konstruktor Utama ---
    public MySavingsFrame() {
        
        // --- Setup Frame (Full Screen) ---
        setTitle("Pocket Detail");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 
        getContentPane().setBackground(PRIMARY_BG_COLOR); 

        // 1. Header (Judul)
        JLabel pocketDetailLabel = new JLabel("Pocket Detail", SwingConstants.LEFT);
        pocketDetailLabel.setForeground(Color.WHITE);
        pocketDetailLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 18));
        pocketDetailLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        add(pocketDetailLabel, BorderLayout.NORTH);
        
        // --- Panel Utama (Container untuk Konten Tengah, dibungkus JScrollPane) ---
        JScrollPane mainScrollPane = new JScrollPane();
        mainScrollPane.getViewport().setBackground(PRIMARY_BG_COLOR);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder()); 
        mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBackground(PRIMARY_BG_COLOR);
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS)); 
        
        mainScrollPane.setViewportView(mainContentPanel);
        
        // --- Panel Detail Tabungan (Bagian Atas) ---
        JPanel savingsDetailPanel = new JPanel();
        savingsDetailPanel.setBackground(PRIMARY_BG_COLOR); 
        savingsDetailPanel.setLayout(new GridBagLayout()); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);

        // Icon (Tas Uang)
        JLabel bagIconLabel = new JLabel("<html><font size='+8'>&#36;</font></html>"); 
        bagIconLabel.setForeground(Color.WHITE);
        bagIconLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 60)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        savingsDetailPanel.add(bagIconLabel, gbc);

        // Pocket Name
        JLabel pocketNameLabel = new JLabel("My Savings");
        pocketNameLabel.setForeground(Color.WHITE);
        pocketNameLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, 28)); 
        gbc.gridy = 1; 
        savingsDetailPanel.add(pocketNameLabel, gbc);

        // Balance
        JLabel balanceLabel = new JLabel("Rp100.000");
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 60)); 
        gbc.gridy = 2; 
        savingsDetailPanel.add(balanceLabel, gbc);
        
        // Add Money Button (Bulat tanpa kotak)
        JButton addMoneyButton = new RoundedButton("+"); 
        
        JPanel addMoneyContainer = new JPanel();
        addMoneyContainer.setOpaque(false); // Penting: membuat container tidak terlihat
        addMoneyContainer.setLayout(new BoxLayout(addMoneyContainer, BoxLayout.Y_AXIS));
        
        addMoneyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMoneyContainer.add(addMoneyButton);
        
        JLabel addMoneyText = new JLabel("Add Money", SwingConstants.CENTER);
        addMoneyText.setForeground(Color.WHITE);
        addMoneyText.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14)); 
        addMoneyText.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMoneyContainer.add(addMoneyText);
        
        gbc.gridy = 3; 
        gbc.insets = new Insets(40, 0, 0, 0); 
        savingsDetailPanel.add(addMoneyContainer, gbc); 

        // Tambahkan Panel Detail ke mainContentPanel
        savingsDetailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(Box.createVerticalGlue()); 
        mainContentPanel.add(savingsDetailPanel);
        mainContentPanel.add(Box.createVerticalStrut(50)); 

        // --- Riwayat Transaksi Fungsional ---
        
        // Panel Header Riwayat
        JPanel historyHeaderPanel = new JPanel(new BorderLayout());
        historyHeaderPanel.setBackground(PRIMARY_BG_COLOR);
        historyHeaderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel riwayatTransaksiLabel = new JLabel("Riwayat Transaksi", SwingConstants.LEFT);
        riwayatTransaksiLabel.setForeground(Color.WHITE);
        riwayatTransaksiLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 18)); 
        historyHeaderPanel.add(riwayatTransaksiLabel, BorderLayout.WEST);
        
        historyHeaderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(historyHeaderPanel);

        // Kontainer Daftar Transaksi
        JPanel historyListPanel = new JPanel();
        historyListPanel.setLayout(new BoxLayout(historyListPanel, BoxLayout.Y_AXIS));
        historyListPanel.setBackground(SECONDARY_BG_COLOR); 
        
        // Data Transaksi Contoh
        List<TransactionItemPanel> transactions = new ArrayList<>();
        transactions.add(new TransactionItemPanel("Transfer Masuk", "+Rp50.000", "Hari ini", new Color(100, 255, 100)));
        transactions.add(new TransactionItemPanel("Pembayaran Listrik", "-Rp75.000", "Kemarin", Color.RED));
        transactions.add(new TransactionItemPanel("Isi Pulsa", "-Rp25.000", "15 Nov 2025", Color.RED));
        // Tambahkan 10 transaksi lagi agar scrollbar muncul
        for(int i=0; i<10; i++) {
             transactions.add(new TransactionItemPanel("Transaksi Tambahan " + (i+1), "-Rp10.000", "01 Nov 2025", Color.GRAY));
        }

        // Menambahkan item ke panel
        for (TransactionItemPanel item : transactions) {
            historyListPanel.add(item);
            historyListPanel.add(Box.createVerticalStrut(2)); 
        }

        // Panel Pembungkus agar daftar transaksi memiliki lebar penuh dan margin
        JPanel historyWrapperPanel = new JPanel(new BorderLayout());
        historyWrapperPanel.setBackground(PRIMARY_BG_COLOR);
        historyWrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); 
        historyWrapperPanel.add(historyListPanel, BorderLayout.CENTER);
        
        historyWrapperPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(historyWrapperPanel);
        mainContentPanel.add(Box.createVerticalGlue()); 
        
        add(mainScrollPane, BorderLayout.CENTER); 

        // --- Aksi Tombol Add Money ---
        addMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Memanggil AddMoneyDialog.java
                new AddMoneyDialog(MySavingsFrame.this).setVisible(true);
            }
        });

        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MySavingsFrame();
        });
    }
}