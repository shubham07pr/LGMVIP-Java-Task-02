import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private boolean xTurn = true;
    private JLabel turnLabel;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();
    }

    private void initializeGame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 3));

        turnLabel = new JLabel("X's Turn", JLabel.CENTER);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(turnLabel);
        mainPanel.add(new JLabel()); // Empty label for spacing

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(row, col);
                    }
                });
                mainPanel.add(buttons[i][j]);
            }
        }

        add(mainPanel);
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            if (xTurn) {
                buttons[row][col].setText("X");
                turnLabel.setText("O's Turn");
            } else {
                buttons[row][col].setText("O");
                turnLabel.setText("X's Turn");
            }
            xTurn = !xTurn;

            if (checkWin("X")) {
                JOptionPane.showMessageDialog(this, "Player X wins!");
                resetGame();
            } else if (checkWin("O")) {
                JOptionPane.showMessageDialog(this, "Player O wins!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            }
        }
    }

    private boolean checkWin(String player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) &&
                    buttons[i][1].getText().equals(player) &&
                    buttons[i][2].getText().equals(player)) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(player) &&
                    buttons[1][i].getText().equals(player) &&
                    buttons[2][i].getText().equals(player)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
            return true;
        }

        if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
        turnLabel.setText("X's Turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}
