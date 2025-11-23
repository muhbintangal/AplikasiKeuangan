/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikeuangan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FinFlowSignUp extends JFrame {

    // Warna Palet (Sama dengan Login agar konsisten)
    private final Color COLOR_PRIMARY_BLUE = new Color(43, 69, 212);
    private final Color COLOR_INPUT_BG = new Color(235, 238, 255);
    private final Color COLOR_TEXT_BLUE = new Color(50, 70, 200);
    private final Color COLOR_TEXT_GRAY = new Color(100, 100, 100);

    public FinFlowSignUp() {
        setTitle("FinFlow Sign Up");
        // --- TAMBAHKAN INI ---
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // ---------------------

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2)); 

        // --- BAGIAN KIRI (Sama persis dengan Login) ---
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(COLOR_PRIMARY_BLUE);
        leftPanel.setLayout(new GridBagLayout());

        JPanel leftContent = new JPanel();
        leftContent.setOpaque(false);
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));

        // Icon Dompet Custom
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 50));
                g2.drawRoundRect(10, 0, 60, 50, 15, 15);
                g2.fillRoundRect(10, 0, 60, 50, 15, 15);
                g2.setColor(Color.WHITE);
                g2.fillOval(55, 20, 8, 8);
            }
        };
        iconLabel.setPreferredSize(new Dimension(80, 60));
        iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel appTitle = new JLabel("FinFlow");
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 48));
        appTitle.setForeground(Color.WHITE);
        appTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tagLine = new JLabel("Take Control of Your Money Flow");
        tagLine.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tagLine.setForeground(new Color(220, 220, 220));
        tagLine.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftContent.add(iconLabel);
        leftContent.add(Box.createRigidArea(new Dimension(0, 10)));
        leftContent.add(appTitle);
        leftContent.add(tagLine);
        leftPanel.add(leftContent);

        // --- BAGIAN KANAN (FORM SIGN UP) ---
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(20, 60, 20, 60));

        // Header Kanan
        JLabel signUpTitle = new JLabel("Sign Up");
        signUpTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        signUpTitle.setForeground(COLOR_TEXT_BLUE);
        signUpTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitle = new JLabel("Create your account");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subTitle.setForeground(Color.BLACK); // Sesuai gambar (hitam/gelap)
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Input Fields ---
        
        // 1. Name Field
        RoundedTextField nameField = new RoundedTextField(20);
        nameField.setPlaceholder("Name");
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Email Field
        RoundedTextField emailField = new RoundedTextField(20);
        emailField.setPlaceholder("Email");
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 3. Password Field
        RoundedPasswordField passField = new RoundedPasswordField(20);
        passField.setPlaceholder("Password");
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Confirm Password Field
        RoundedPasswordField confirmPassField = new RoundedPasswordField(20);
        confirmPassField.setPlaceholder("Confirm Password");
        confirmPassField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Sign Up
        RoundedButton signUpButton = new RoundedButton("SIGN UP");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        signUpButton.addActionListener(e -> {
            // Di sini nanti tempat koding simpan data ke database
            
            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this, "Akun berhasil dibuat! Silakan Login.");

            // Pindah ke halaman Login
            // Pastikan file FinFlowLogin.java sudah ada
            try {
                new FinFlowLogin().setVisible(true);
                this.dispose(); // Tutup halaman Sign Up
            } catch (Exception ex) {
                System.out.println("File FinFlowLogin belum ada.");
            }
        });

        // Menyusun Komponen dengan Spasi (RigidArea)
        formPanel.add(signUpTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(subTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        formPanel.add(nameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        formPanel.add(passField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        formPanel.add(confirmPassField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        formPanel.add(signUpButton);

        rightPanel.add(formPanel);

        add(leftPanel);
        add(rightPanel);
    }

    // --- CUSTOM COMPONENTS (Sama dengan Login) ---

    // 1. Custom TextField (Rounded)
    class RoundedTextField extends JTextField implements FocusListener {
        private String placeholder;
        private boolean showingPlaceholder;

        public RoundedTextField(int columns) {
            super(columns);
            setOpaque(false);
            setBorder(new EmptyBorder(10, 20, 10, 20));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(Color.GRAY);
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

    // 2. Custom PasswordField (Rounded)
    class RoundedPasswordField extends JPasswordField implements FocusListener {
        private String placeholder;
        private boolean showingPlaceholder;

        public RoundedPasswordField(int columns) {
            super(columns);
            setOpaque(false);
            setBorder(new EmptyBorder(10, 20, 10, 20));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(Color.GRAY);
            setEchoChar((char) 0);
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
                setEchoChar('•');
                showingPlaceholder = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getPassword().length == 0) {
                setText(placeholder);
                setForeground(Color.GRAY);
                setEchoChar((char) 0);
                showingPlaceholder = true;
            }
        }
    }

    // 3. Custom Button (Rounded)
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
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COLOR_PRIMARY_BLUE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FinFlowSignUp().setVisible(true);
        });
    }
}