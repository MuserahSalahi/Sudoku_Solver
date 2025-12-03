import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuSolverGUI extends JFrame {

    private JTextField[][] cells = new JTextField[9][9];
    private JButton solveButton, clearButton, autoFillButton;

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout(10, 10));

        //  Center window on screen
        setLocationRelativeTo(null);

        // Sudoku grid panel
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Font font = new Font("Arial", Font.BOLD, 20);
        Color boxColor = new Color(245, 245, 250);

        // Create cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(font);
                cells[i][j].setBackground(boxColor);
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Highlight 3x3 grids
                if ((i / 3 + j / 3) % 2 == 0) {
                    cells[i][j].setBackground(new Color(230, 230, 255));
                }
                gridPanel.add(cells[i][j]);
            }
        }

        // Buttons
        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        autoFillButton = new JButton("Auto Fill");

        solveButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        autoFillButton.setFont(new Font("Segoe UI", Font.BOLD, 18));

        solveButton.setBackground(new Color(102, 204, 255));
        clearButton.setBackground(new Color(255, 153, 153));
        autoFillButton.setBackground(new Color(153, 255, 153));

        solveButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);
        autoFillButton.setFocusPainted(false);

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(autoFillButton);

        add(new JLabel("Sudoku Solver", SwingConstants.CENTER), BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Solve Button Action
        solveButton.addActionListener(e -> {
            char[][] board = new char[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = cells[i][j].getText();
                    if (text.isEmpty() || text.equals(".")) {
                        board[i][j] = '.';
                    } else {
                        board[i][j] = text.charAt(0);
                    }
                }
            }

            if (helper(board, 0, 0)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        cells[i][j].setText(String.valueOf(board[i][j]));
                        cells[i][j].setForeground(new Color(0, 102, 0)); // green for solved
                    }
                }
                JOptionPane.showMessageDialog(null, "✅ Sudoku Solved!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ No solution exists!");
            }
        });

        // Clear Button Action
        clearButton.addActionListener(e -> {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cells[i][j].setText("");
                    cells[i][j].setForeground(Color.BLACK);
                }
            }
        });

        // Auto Fill Button Action
        autoFillButton.addActionListener(e -> {
            char[][] board = new char[9][9];
            boolean[][] userFilled = new boolean[9][9];

            // user ke input store karo
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = cells[i][j].getText();
                    if (text.isEmpty() || text.equals(".")) {
                        board[i][j] = '.';
                    } else {
                        board[i][j] = text.charAt(0);
                        userFilled[i][j] = true; // user input mark karo
                    }
                }
            }

            // solve the board completely
            if (helper(board, 0, 0)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        cells[i][j].setText(String.valueOf(board[i][j]));

                        if (userFilled[i][j]) {
                            cells[i][j].setForeground(Color.BLACK); // user input = black
                        } else {
                            cells[i][j].setForeground(new Color(0, 0, 200)); // auto-filled = blue
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Board Auto-Filled Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid entries. Cannot auto-fill.");
            }
        });

        setVisible(true);
    }

    // Sudoku solving logic
    public static boolean isSafe(char[][] board, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == (char) (num + '0') || board[x][col] == (char) (num + '0'))
                return false;
        }

        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (num + '0'))
                    return false;
            }
        }
        return true;
    }

    public static boolean helper(char[][] board, int row, int col) {
        if (row == 9) return true;

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        if (board[row][col] != '.')
            return helper(board, nextRow, nextCol);

        for (int i = 1; i <= 9; i++) {
            if (isSafe(board, row, col, i)) {
                board[row][col] = (char) (i + '0');
                if (helper(board, nextRow, nextCol))
                    return true;
                board[row][col] = '.';
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuSolverGUI::new);
    }
}
