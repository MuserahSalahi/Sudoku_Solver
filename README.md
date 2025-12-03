# Sudoku_Solver
Sudoku Solver Game by using Java 

A modern and user‑friendly **Java Swing–based Sudoku Solver** that allows users to:

* Input their own Sudoku puzzle
* Automatically solve the puzzle
* Auto‑fill missing entries while keeping user‑entered values separate
* Clear the grid and start fresh

This project uses **backtracking algorithm** to solve Sudoku efficiently and provides a clean GUI interface for easy interaction.

# Features

1. Interactive 9×9 Sudoku Grid
   
* Neat and spacious layout
* Alternating 3×3 box highlight for clarity
* Large input font for readability

2. Solve Button

* Solves the Sudoku using a recursive backtracking algorithm
* Highlights solved values in **green**
* Shows error notification if puzzle is invalid or unsolvable

 3. Auto‑Fill Button

* Completes the Sudoku while keeping:

  * **User inputs in black**
  * **Auto‑generated values in blue**
* Very useful for learners

 4. Clear Button

* Resets the entire grid instantly

 5. Clean User Interface

* Centered window
* Smooth button colors
* Professional fonts

## How the Solver Works

This project uses a **classic backtracking algorithm**:

1. Select the next empty cell
2. Try placing numbers `1–9`
3. Check if the placement is **safe** (row, column, and box)
4. If valid → move forward
5. If invalid → backtrack and try another number
6. Continue until solved or no solution exists

## Project Structure

```
SudokuSolver/
│
├── src/
│   └── SudokuSolverGUI.java
│
└── README.md
```

---

## How to Run

1. Install **Java JDK 8 or later**
2. Compile the program:

```
javac SudokuSolverGUI.java
```

3. Run the program:

```
java SudokuSolverGUI
```

## Technologies Used

* **Java**
* **Java Swing (GUI)**
* **Backtracking Algorithm**

## Future Enhancements

Here are some optional improvements you can add later:

* Dark mode
* Prevent user from entering >1 digit
* Highlight wrong inputs in red
* Add undo/redo feature
* Add built‑in Sudoku puzzles
* Add solving animation

## Contributing

Pull requests are welcome! If you have new ideas, feel free to open an issue.

## Author

**_ MUSERAH SALAHI**

Feel free to ⭐ star the repo if you like it!

