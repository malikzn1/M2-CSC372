import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient balance or invalid amount.");
        }
    }
}

public class BankBalanceApp extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JLabel balanceLabel;

    public BankBalanceApp() {
        account = new BankAccount(0.0);

        // Set up the GUI components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel amountLabel = new JLabel("Enter amount:");
        amountField = new JTextField();

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Show Balance");

        balanceLabel = new JLabel("Balance: $0.00");

        // Add components to panel
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(balanceButton);
        panel.add(balanceLabel);

        // Add Action Listeners
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        balanceButton.addActionListener(this);

        // Set up frame
        this.add(panel);
        this.setTitle("Bank Balance App");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (command.equals("Deposit")) {
                account.deposit(amount);
            } else if (command.equals("Withdraw")) {
                account.withdraw(amount);
            } else if (command.equals("Show Balance")) {
                balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }

        // Clear the text field after the operation
        amountField.setText("");
    }

    public static void main(String[] args) {
        new BankBalanceApp();
    }
}
