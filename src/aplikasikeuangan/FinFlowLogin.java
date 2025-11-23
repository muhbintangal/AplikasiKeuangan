/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikeuangan;

/**
 *
 * @author advan
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class FinFlowLogin extends JFrame {

    // Warna Palet (Diambil sampel dari gambar)
    private final Color COLOR_PRIMARY_BLUE = new Color(43, 69, 212); // Warna biru tua kiri
    private final Color COLOR_INPUT_BG = new Color(235, 238, 255);   // Warna background input
    private final Color COLOR_TEXT_BLUE = new Color(50, 70, 200);    // Warna teks judul kanan
    private final Color COLOR_TEXT_GRAY = new Color(100, 100, 100);

    public FinFlowLogin() {
        setTitle("FinFlow Login");
        // --- TAMBAHKAN INI ---
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // ---------------------

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2)); // Membagi layar jadi 2 (Kiri & Kanan)

        // --- BAGIAN KIRI (PANEL BIRU) ---
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(COLOR_PRIMARY_BLUE);
        leftPanel.setLayout(new GridBagLayout()); // Untuk menengahkan konten

        JPanel leftContent = new JPanel();
        leftContent.setOpaque(false);
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));

        // Icon (Simulasi Dompet dengan Graphics sederhana karena tidak ada file gambar)
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 50)); // Putih transparan
                g2.drawRoundRect(10, 0, 60, 50, 15, 15); // Garis luar dompet
                g2.fillRoundRect(10, 0, 60, 50, 15, 15);
                g2.setColor(Color.WHITE);
                g2.fillOval(55, 20, 8, 8); // Kancing dompet
            }
        };
        iconLabel.setPreferredSize(new Dimension(80, 60));
        iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Judul "FinFlow"
        JLabel appTitle = new JLabel("FinFlow");
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 48));
        appTitle.setForeground(Color.WHITE);
        appTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tagline
        JLabel tagLine = new JLabel("Take Control of Your Money Flow");
        tagLine.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tagLine.setForeground(new Color(220, 220, 220));
        tagLine.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Menambahkan komponen ke konten kiri
        leftContent.add(iconLabel);
        leftContent.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi
        leftContent.add(appTitle);
        leftContent.add(tagLine);
        leftPanel.add(leftContent);

        // --- BAGIAN KANAN (PANEL PUTIH) ---
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(20, 60, 20, 60)); // Padding kiri kanan form

        // Header Kanan
        JLabel welcomeTitle = new JLabel("Welcome");
        welcomeTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        welcomeTitle.setForeground(COLOR_TEXT_BLUE);
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitle = new JLabel("Log In to your account to continue");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subTitle.setForeground(COLOR_TEXT_GRAY);
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Fields (Custom Rounded)
        RoundedTextField emailField = new RoundedTextField(20);
        emailField.setPlaceholder("Email");
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedPasswordField passField = new RoundedPasswordField(20);
        passField.setPlaceholder("Password");
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Log In
        RoundedButton loginButton = new RoundedButton("LOG IN");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        

        loginButton.addActionListener(e -> {
            // 1. Buka Jendela Dashboard
            // Pastikan kamu sudah punya file FinFlowDashboard.java
            new FinFlowHome().setVisible(true); 
            
            // 2. Tutup Jendela Login saat ini
            this.dispose(); 
        });
        // ----------------------------------

        // Link Sign Up
        JLabel signUpLabel = new JLabel("Dont have an account? Sign Up Here");
        signUpLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        signUpLabel.setForeground(COLOR_TEXT_BLUE);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // --- TAMBAHKAN KODE INI DI SINI ---
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // 1. Buka halaman Sign Up
                // Pastikan kamu sudah punya file FinFlowSignUp.java
                new FinFlowSignUp().setVisible(true); 
                
                // 2. Tutup halaman Login saat ini
                FinFlowLogin.this.dispose(); 
            }
        });
        // ----------------------------------

        // Susun Form Kanan
        formPanel.add(welcomeTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(subTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Jarak ke input
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(passField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(loginButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(signUpLabel);

        rightPanel.add(formPanel);

        // Tambahkan Panel ke Frame Utama
        add(leftPanel);
        add(rightPanel);
    }

    // --- CUSTOM COMPONENTS (Untuk membuat tampilan Rounded) ---

    // 1. Custom TextField dengan Sudut Melengkung & Placeholder
    class RoundedTextField extends JTextField implements FocusListener {
        private String placeholder;
        private boolean showingPlaceholder;

        public RoundedTextField(int columns) {
            super(columns);
            setOpaque(false); // Transparan agar custom paint terlihat
            setBorder(new EmptyBorder(10, 20, 10, 20)); // Padding teks di dalam
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(Color.GRAY);
            addFocusListener(this);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Tinggi fix
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
            setText(placeholder);
            showingPlaceholder = true;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COLOR_INPUT_BG);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Radius 30
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (getText().equals(placeholder)) {
                setText("");
                setForeground(Color.BLACK);
                showingPlaceholder = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getText().isEmpty()) {
                setText(placeholder);
                setForeground(Color.GRAY);
                showingPlaceholder = true;
            }
        }
    }

    // 2. Custom PasswordField dengan Sudut Melengkung
    class RoundedPasswordField extends JPasswordField implements FocusListener {
        private String placeholder;
        private boolean showingPlaceholder;

        public RoundedPasswordField(int columns) {
            super(columns);
            setOpaque(false);
            setBorder(new EmptyBorder(10, 20, 10, 20));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(Color.GRAY);
            setEchoChar((char) 0); // Awalnya text biasa (placeholder)
            addFocusListener(this);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
            setText(placeholder);
            showingPlaceholder = true;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COLOR_INPUT_BG);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (String.valueOf(getPassword()).equals(placeholder)) {
                setText("");
                setForeground(Color.BLACK);
                setEchoChar('•'); // Ubah jadi titik-titik saat mengetik
                showingPlaceholder = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getPassword().length == 0) {
                setText(placeholder);
                setForeground(Color.GRAY);
                setEchoChar((char) 0); // Kembali ke text biasa
                showingPlaceholder = true;
            }
        }
    }

    // 3. Custom Button dengan Sudut Melengkung & Gradient sederhana
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("SansSerif", Font.BOLD, 14));
            setPreferredSize(new Dimension(150, 45));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Lebar full
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Warna Tombol
            g2.setColor(COLOR_PRIMARY_BLUE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            
            super.paintComponent(g);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FinFlowLogin().setVisible(true);
        });
    }
}
