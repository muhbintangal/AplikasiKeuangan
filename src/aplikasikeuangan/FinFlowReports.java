/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikeuangan;

/**
 *
 * @author LENOVO
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D; // Import ini penting agar Rectangle2D tidak error
import java.awt.geom.RoundRectangle2D;

public class FinFlowReports extends JFrame {

    // --- PALET WARNA ---
    private final Color COLOR_BG_BLUE = new Color(20, 50, 200); 
    private final Color COLOR_BTN_LIGHT = new Color(140, 160, 255); 
    private final Color COLOR_TEXT_DARK = new Color(20, 20, 60);
    private final Color COLOR_TEXT_LABEL = new Color(80, 80, 120);

    public FinFlowReports() {
        setTitle("FinFlow Reports");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // --- PANEL UTAMA (GridBagLayout) ---
        JPanel mainContent = new JPanel(new GridBagLayout());
        mainContent.setBackground(COLOR_BG_BLUE);
        mainContent.setBorder(new EmptyBorder(30, 40, 30, 40)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(0, 0, 20, 0); 
        gbc.weightx = 1.0; 
        gbc.gridx = 0; 

        // 1. HEADER (Back Arrow + Title)
        gbc.gridy = 0; 
        mainContent.add(createHeader(), gbc);

        // 2. FILTER SECTION (Select Month)
        gbc.gridy = 1;
        mainContent.add(createFilterSection(), gbc);

        // 3. SUMMARY CARDS
        gbc.gridy = 2;
        mainContent.add(createSummaryCards(), gbc);

        // 4. TABLE SECTION
        gbc.gridy = 3;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH; 
        mainContent.add(createTableSection(), gbc);

        // ScrollPane wrapper
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
    }

    // --- 1. HEADER (MODIFIKASI: BACK ARROW) ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        header.setOpaque(false);
        
        // --- TOMBOL BACK (PANAH) ---
        JLabel backIcon = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.setColor(Color.WHITE);
                
                // Gambar Panah Kiri (<)
                Path2D path = new Path2D.Double();
                path.moveTo(15, 5);  // Titik kanan atas panah
                path.lineTo(5, 12);  // Titik ujung kiri panah
                path.lineTo(15, 19); // Titik kanan bawah panah
                g2.draw(path);
            }
        };
        backIcon.setPreferredSize(new Dimension(25, 25));
        backIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Event Klik: Kembali ke Dashboard
        backIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Buka Dashboard
                try {
                    new FinFlowHome().setVisible(true); // Pastikan nama file dashboard kamu FinFlowHome
                    dispose(); // Tutup halaman Reports ini
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Halaman FinFlowHome tidak ditemukan!");
                }
            }
        });

        // Judul
        JLabel title = new JLabel("Reports");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        // Icon Dompet (Hiasan Kanan)
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

        header.add(backIcon); // Tambahkan ikon back
        header.add(title);
        header.add(walletIcon);
        return header;
    }

    // --- 2. FILTER SECTION (FIXED BUTTON CENTER) ---
    private JPanel createFilterSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel lblSelect = new JLabel("Select Month");
        lblSelect.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSelect.setForeground(Color.WHITE);
        lblSelect.setBorder(new EmptyBorder(0, 0, 5, 0));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        controls.setOpaque(false);

        String[] months = {"January", "February", "March", "April", "May", "June", 
                           "July", "August", "September", "October", "November", "December"};
        JComboBox<String> monthCombo = new JComboBox<>(months);
        monthCombo.setSelectedItem("November");
        monthCombo.setFont(new Font("SansSerif", Font.BOLD, 16));
        monthCombo.setPreferredSize(new Dimension(300, 50));
        monthCombo.setBackground(Color.WHITE);
        monthCombo.setForeground(COLOR_TEXT_DARK);
        
        ((JComponent) monthCombo.getEditor().getEditorComponent()).setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // --- BUTTON APPLY YANG SUDAH DIPERBAIKI ---
        JButton btnApply = new JButton("Apply") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 1. Gambar Background
                g2.setColor(COLOR_BTN_LIGHT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // 2. Gambar Teks (Manual Centering)
                g2.setFont(getFont());
                g2.setColor(getForeground());
                
                FontMetrics fm = g2.getFontMetrics();
                Rectangle2D r = fm.getStringBounds(getText(), g2);
                
                int x = (getWidth() - (int) r.getWidth()) / 2;
                int y = (getHeight() - (int) r.getHeight()) / 2 + fm.getAscent();
                
                g2.drawString(getText(), x, y - 2);

                g2.dispose();
            }
        };
        btnApply.setPreferredSize(new Dimension(150, 50));
        btnApply.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnApply.setForeground(Color.WHITE);
        btnApply.setContentAreaFilled(false);
        btnApply.setFocusPainted(false);
        btnApply.setBorder(null); // Hapus border agar tidak ada padding aneh
        btnApply.setCursor(new Cursor(Cursor.HAND_CURSOR));

        controls.add(monthCombo);
        JLabel spacer = new JLabel(""); 
        spacer.setPreferredSize(new Dimension(20, 1));
        controls.add(spacer);
        controls.add(btnApply);

        panel.add(lblSelect, BorderLayout.NORTH);
        panel.add(controls, BorderLayout.CENTER);

        return panel;
    }

    // --- 3. SUMMARY CARDS ---
    private JPanel createSummaryCards() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 150)); 

        panel.add(createSingleCard("Expenses", "10,000"));
        panel.add(createSingleCard("Income", "100,000"));
        panel.add(createSingleCard("Balance", "90,000"));

        return panel;
    }

    private JPanel createSingleCard(String title, String value) {
        RoundedPanel card = new RoundedPanel(20, Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(25, 25, 25, 25)); 

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblTitle.setForeground(COLOR_TEXT_LABEL);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("SansSerif", Font.BOLD, 36)); 
        lblValue.setForeground(COLOR_TEXT_DARK);
        lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblTitle);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(lblValue);
        
        return card;
    }

    // --- 4. TABLE SECTION ---
    private JPanel createTableSection() {
        RoundedPanel panel = new RoundedPanel(20, Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));
        
        JLabel title = new JLabel("Monthly Breakdown");
        // Gunakan "Segoe UI" agar lebih rapi dan modern
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(COLOR_TEXT_DARK);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        String[] columns = {"Date", "Category", "Nominal"};
        Object[][] data = {
             {"01 Nov", "Food & Beverage", "- 50.000"},
             {"05 Nov", "Salary", "+ 10.000.000"},
             {"12 Nov", "Transportation", "- 25.000"},
             {"15 Nov", "Internet Bill", "- 300.000"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
             @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(40); 
        table.setShowGrid(false); 
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.PLAIN, 16));
        header.setForeground(COLOR_BG_BLUE);
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230,230,230))); 

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        
        for(int i=0; i<table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(150); 
        table.getColumnModel().getColumn(1).setPreferredWidth(400); 
        table.getColumnModel().getColumn(2).setPreferredWidth(200); 

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.getViewport().setBackground(Color.WHITE);

        panel.add(title, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        return panel;
    }

    // --- HELPER CLASS: ROUNDED PANEL ---
    static class RoundedPanel extends JPanel {
        private int radius;
        private Color bgColor;

        RoundedPanel(int radius, Color bgColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> new FinFlowReports().setVisible(true));
    }
}
