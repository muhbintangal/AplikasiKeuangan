/*
 * FinFlowHome.java
 */
package aplikasikeuangan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinFlowHome extends JFrame {

    // Warna Palet
    private final Color COLOR_BG_BLUE = new Color(25, 60, 210); // Biru Background Utama
    private final Color COLOR_TEXT_DARK = new Color(20, 20, 80);
    private final Color COLOR_TEXT_GRAY = new Color(100, 100, 100);
    private final Color COLOR_GREEN = new Color(0, 180, 0);
    private final Color COLOR_RED = new Color(220, 0, 0);

    public FinFlowHome() {
        setTitle("FinFlow Dashboard");
        
        // --- SETTING FULLSCREEN ---
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // --------------------------

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Utama container (Background Biru)
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(COLOR_BG_BLUE);
        
        // 1. HEADER
        mainContainer.add(createHeader(), BorderLayout.NORTH);

        // 2. CONTENT (Grid Kartu)

        // 3. FOOTER (Tombol Plus)
        mainContainer.add(createFooter(), BorderLayout.SOUTH);        mainContainer.add(createContentGrid(), BorderLayout.CENTER);


        add(mainContainer);
    }

    // --- BAGIAN HEADER ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setOpaque(false); 

       

        // Judul Dashboard
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        // Icon Dompet
        JLabel walletIcon = new JLabel() {
             @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(2, 2, 24, 18, 5, 5);
                g2.fillOval(18, 8, 3, 3);
            }
        };
        walletIcon.setPreferredSize(new Dimension(30, 25));


        header.add(title);
        header.add(walletIcon);
        return header;
    }

    // --- BAGIAN KONTEN (GridBagLayout) ---
    private JPanel createContentGrid() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.BOTH;

        // --- KARTU KIRI: MENU UTAMA (Menabung, Report, Exit) ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3; 
        gbc.weightx = 0.35; 
        gbc.weighty = 1.0;
        contentPanel.add(createLeftCard(), gbc);

        // --- KARTU KANAN 1: BUDGET ---
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.65; 
        gbc.weighty = 0.3;
        contentPanel.add(createBudgetCard(), gbc);

        // --- KARTU KANAN 2: RECENT TRANSACTIONS (TABEL) ---
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 0.4;
        contentPanel.add(createTransactionCard(), gbc);

        // --- KARTU KANAN 3: SUMMARY (Income/Expense) ---
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        contentPanel.add(createSummaryCard(), gbc);

        return contentPanel;
    }

    // --- IMPLEMENTASI KARTU ---
    
    // 1. KARTU KIRI (MODIFIKASI: 3 TOMBOL)
    private RoundedPanel createLeftCard() {
        RoundedPanel panel = new RoundedPanel(30);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 25, 30, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; 
        gbc.insets = new Insets(0, 0, 30, 0); 

        // Judul Menu
        JLabel title = new JLabel("Main Menu", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(COLOR_TEXT_DARK);
        panel.add(title, gbc);

        gbc.insets = new Insets(10, 0, 10, 0); // Spasi antar tombol

        // --- TOMBOL 1: MENABUNG ---
        JButton btnSaving = createMenuButton("Saving", COLOR_BG_BLUE);
        btnSaving.addActionListener(e -> {
             // TODO: Sambungkan ke halaman menabung jika sudah ada
             // new FinFlowSavings().setVisible(true);
             // this.dispose();
             JOptionPane.showMessageDialog(this, "Membuka Halaman Menabung...");
        });
        gbc.gridy++;
        panel.add(btnSaving, gbc);

        // --- TOMBOL 2: REPORT ---
        JButton btnReport = createMenuButton("Report", COLOR_BG_BLUE);
        btnReport.addActionListener(e -> {
             // Membuka halaman Report
             try {
//                 new FinFlowReportPage().setVisible(true);
                 this.dispose(); // Tutup dashboard
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this, "Error: File FinFlowReportPage belum ditemukan!");
             }
        });
        gbc.gridy++;
        panel.add(btnReport, gbc);

        // --- TOMBOL 3: EXIT ---
        JButton btnExit = createMenuButton("Exit", COLOR_RED);
        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Keluar dari aplikasi?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        gbc.gridy++;
        panel.add(btnExit, gbc);

        return panel;
    }

    // Helper untuk membuat tombol menu rounded
    private JButton createMenuButton(String text, Color bg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(100, 50));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // 2. KARTU BUDGET
    private RoundedPanel createBudgetCard() {
        RoundedPanel panel = new RoundedPanel(30);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        
        JLabel title = new JLabel("Balance");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(COLOR_TEXT_DARK);
        
        JLabel sub = new JLabel("Cash Available");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        sub.setForeground(COLOR_TEXT_GRAY);
        
        textPanel.add(title);
        textPanel.add(sub);

        JLabel amount = new JLabel("Rp200.000");
        amount.setFont(new Font("SansSerif", Font.BOLD, 26));
        amount.setForeground(COLOR_TEXT_DARK);

        panel.add(textPanel, BorderLayout.WEST);
        panel.add(amount, BorderLayout.EAST);
        return panel;
    }

    // 3. KARTU TRANSAKSI (MODIFIKASI: TABEL)
    private RoundedPanel createTransactionCard() {
        RoundedPanel panel = new RoundedPanel(30);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 25, 15, 25));

        // Judul
        JLabel title = new JLabel("Recent Transactions");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(COLOR_TEXT_DARK);
        title.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Kolom & Data
        String[] columnNames = {"Date", "Description", "Category", "Type", "Amount"};
        Object[][] data = {
            {"01 Nov", "Grocery Store", "Food", "Expense", "-Rp100.000"},
            {"02 Nov", "Mie Ayam Yoskar", "Food", "Expense", "-Rp30.000"},
            {"03 Nov", "Dots Peruri", "Entmt", "Expense", "-Rp18.000"},
            {"04 Nov", "Gaji Bulanan", "Salary", "Income", "+Rp5.000.000"},
            {"05 Nov", "Bensin", "Transport", "Expense", "-Rp30.000"}
        };

        // Model Tabel (Tidak bisa diedit)
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setIntercellSpacing(new Dimension(0, 0));
        
        // Styling Header
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 12));
        tableHeader.setBackground(Color.WHITE); 
        tableHeader.setForeground(COLOR_TEXT_DARK);
        tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        // Styling Cell Center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    // 4. KARTU SUMMARY
    private RoundedPanel createSummaryCard() {
        RoundedPanel panel = new RoundedPanel(30);
        panel.setLayout(new GridLayout(1, 3)); 
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(createSummaryItem("Income", "200,000", COLOR_GREEN));
        panel.add(createSummaryItem("Expenses", "10,000", COLOR_RED));
        panel.add(createSummaryItem("Balance", "190,000", Color.GRAY));

        return panel;
    }

    private JPanel createSummaryItem(String label, String val, Color valColor) {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(10, 15, 10, 0));
        
        JLabel lTitle = new JLabel(label);
        lTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lTitle.setForeground(new Color(80, 80, 150));
        
        JLabel lVal = new JLabel(val);
        lVal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lVal.setForeground(valColor);
        
        p.add(lTitle);
        p.add(lVal);
        return p;
    }

    // --- BAGIAN FOOTER (TOMBOL PLUS) ---
    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(0, 0, 20, 0)); 

        // Menu Popup
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        popupMenu.setBackground(Color.WHITE);

        JMenuItem itemIncome = new JMenuItem("Add Income");
        itemIncome.setFont(new Font("SansSerif", Font.BOLD, 14));
        itemIncome.setForeground(new Color(0, 180, 0));
        itemIncome.setBackground(Color.WHITE);
        itemIncome.setOpaque(true);
        itemIncome.addActionListener(e -> JOptionPane.showMessageDialog(this, "Membuka halaman Add Income..."));

        JMenuItem itemExpense = new JMenuItem("Add Expense");
        itemExpense.setFont(new Font("SansSerif", Font.BOLD, 14));
        itemExpense.setForeground(new Color(220, 0, 0)); 
        itemExpense.setBackground(Color.WHITE);
        itemExpense.setOpaque(true);
        itemExpense.addActionListener(e -> JOptionPane.showMessageDialog(this, "Membuka halaman Add Expense..."));

        popupMenu.add(itemIncome);
        popupMenu.add(new JSeparator());
        popupMenu.add(itemExpense);

        // Tombol Plus
        JButton plusBtn = new JButton("+") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 150));
                g2.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        
        plusBtn.setFont(new Font("SansSerif", Font.BOLD, 30));
        plusBtn.setForeground(COLOR_BG_BLUE);
        plusBtn.setPreferredSize(new Dimension(60, 60));
        plusBtn.setContentAreaFilled(false);
        plusBtn.setFocusPainted(false);
        plusBtn.setBorderPainted(false);
        plusBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        plusBtn.addActionListener(e -> popupMenu.show(plusBtn, -30, 0)); 
        
        footer.add(plusBtn);
        return footer;
    }

    // --- CLASS HELPER: ROUNDED PANEL ---
    static class RoundedPanel extends JPanel {
        private int radius;

        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinFlowHome().setVisible(true));
    }
}