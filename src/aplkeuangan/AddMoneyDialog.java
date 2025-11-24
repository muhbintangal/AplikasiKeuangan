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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

// Definisi Konstanta Font
class AppConstants {
    public static final String FONT_FAMILY = "Calibri";
}

public class AddMoneyDialog extends JDialog {

    private JTextField amountField;
    private String currentInput = ""; 
    private DecimalFormat formatter; 

    public AddMoneyDialog(JFrame parent) {
        super(parent, "Add Money", true); 
        setSize(400, 450); 
        setLocationRelativeTo(parent); 
        setLayout(new BorderLayout(10, 10)); 
        setBackground(Color.WHITE);
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter = new DecimalFormat("#,##0", symbols);

        // --- Panel Atas (Judul) ---
        JLabel titleLabel = new JLabel("Add Money", SwingConstants.CENTER);
        titleLabel.setFont(new Font(AppConstants.FONT_FAMILY, Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Panel Tengah (Input Field dan Keypad) ---
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 1. Input Field
        amountField = new JTextField();
        amountField.setFont(new Font(AppConstants.FONT_FAMILY, Font.PLAIN, 30));
        amountField.setHorizontalAlignment(JTextField.RIGHT);
        amountField.setEditable(false); 
        amountField.setPreferredSize(new Dimension(360, 50));
        amountField.setMaximumSize(new Dimension(360, 50));
        amountField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(amountField);
        centerPanel.add(Box.createVerticalStrut(20));

        // 2. Keypad Panel
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3, 5, 5)); 
        String[] buttons = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "0", "?" 
        };

        ActionListener keypadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.matches("[0-9]")) {
                    if (currentInput.length() < 12) {
                        currentInput += command;
                    }
                } else if (command.equals("?")) {
                    if (currentInput.length() > 0) {
                        currentInput = currentInput.substring(0, currentInput.length() - 1);
                    }
                }
                
                updateAmountField();
            }
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font(AppConstants.FONT_FAMILY, Font.BOLD, 18));
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.addActionListener(keypadListener);
            keypadPanel.add(button);
        }
        keypadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(keypadPanel);

        add(centerPanel, BorderLayout.CENTER);

        // --- Panel Bawah (Tombol Add Money) ---
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JButton addMoneyBtn = new JButton("Add Money");
        addMoneyBtn.setFont(new Font(AppConstants.FONT_FAMILY, Font.BOLD, 18));
        addMoneyBtn.setBackground(new Color(0, 100, 255)); 
        addMoneyBtn.setForeground(Color.WHITE);
        addMoneyBtn.setFocusPainted(false);
        addMoneyBtn.setPreferredSize(new Dimension(360, 50));

        addMoneyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = amountField.getText().trim();
                if (!amount.isEmpty() && !currentInput.equals("0") && !currentInput.isEmpty()) {
                     JOptionPane.showMessageDialog(AddMoneyDialog.this, 
                                                   "Anda akan menambahkan uang sebesar: Rp" + amount, 
                                                   "Konfirmasi", 
                                                   JOptionPane.PLAIN_MESSAGE);
                } else {
                     JOptionPane.showMessageDialog(AddMoneyDialog.this, 
                                                   "Masukkan jumlah uang yang valid.", 
                                                   "Perhatian", 
                                                   JOptionPane.WARNING_MESSAGE);
                     return;
                }
                dispose(); 
            }
        });
        
        southPanel.add(addMoneyBtn, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        
        updateAmountField();
    }
    
    private void updateAmountField() {
        if (currentInput.isEmpty()) {
            amountField.setText("");
        } else {
            try {
                Long number = Long.parseLong(currentInput);
                amountField.setText(formatter.format(number));
            } catch (NumberFormatException ex) {
                amountField.setText(currentInput);
            }
        }
    }
}