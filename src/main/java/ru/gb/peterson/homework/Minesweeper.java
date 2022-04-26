package ru.gb.peterson.homework;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int MINE_COUNT = 5;

    private static final int MINE = -2;
    private static final int EMPTY = 0;
    private static final int CELL_OPEN = 1;
    private static final int CELL_CLOSE = 0;
    private static final int CELL_FLAG = -1;

    private static final int MOVE_LINE = 0;
    private static final int MOVE_COLUMN = 1;
    private static final int MOVE_FLAG = 2;

    private static int[][] board;
    private static int[][] moves;
    private static boolean isGameOver = false;
    private static boolean isGameWin = false;

    public static void main(String[] args) {
        play();
    }

    private static void play() {
        generateBoard();
        moves = new int[WIDTH][HEIGHT];
        drawBoardWithMoves();

        while (!isGameOver) {
            makeMove();
            checkWin();
            if (isGameWin) {
                System.out.println("Вы победили!");
                isGameOver = true;
            } else if (isGameOver) {
                System.out.println("Вы проиграли!");
            }
        }
    }

    private static void generateBoard() {
        board = new int[WIDTH][HEIGHT];
        addMines();
        addMinesNumbers();
    }

    private static void addMines() {
        int mines = MINE_COUNT;
        Random random = new Random();
        while (mines > 0) {
            int x = random.nextInt(HEIGHT), y = random.nextInt(WIDTH);
            if ((board[x][y]) == MINE) {
                continue;
            }
            board[x][y] = MINE;
            mines--;
        }
    }

    private static void addMinesNumbers() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if ((board[i][j]) == MINE) {
                    continue;
                }
                board[i][j] = getMinesCountAroundCell(i, j);
            }
        }
    }

    private static int getMinesCountAroundCell(int line, int row) {
        int mCount = 0;
        for (int i = line - 1; i <= line + 1; i++) {
            for (int j = row - 1; j <= row + 1; j++) {
                if (i < 0 || i >= HEIGHT || j < 0 || j >= WIDTH) {
                    continue;
                }
                if ((board[i][j]) == MINE) {
                    mCount++;
                }
            }
        }
        return mCount;
    }

    private static void makeMove() {
        int[] move = getMoveCoordinatesAndFlag();
        processMove(move);
        drawBoardWithMoves();
    }

    private static int[] getMoveCoordinatesAndFlag() {
        int[] moveCoordinates = new int[3];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Ваш ход (строка, столбец, флаг, например А1*): ");
            String s = scanner.nextLine().toUpperCase();
            int column = s.charAt(0) - 'A';
            int line = s.charAt(1) - '0';
            if (line >= 0 && line < HEIGHT && column >= 0 && column < WIDTH) {
                if (s.endsWith("*")) {
                    moveCoordinates[MOVE_FLAG] = CELL_FLAG;
                }
                moveCoordinates[MOVE_COLUMN] = column;
                moveCoordinates[MOVE_LINE] = line;
                break;
            }
            System.out.println("Неправильный ввод");
        }
        return moveCoordinates;
    }

    private static void processMove(int[] move) {
        int moveLine = move[MOVE_LINE];
        int moveColumn = move[MOVE_COLUMN];
        boolean isFlag = move[MOVE_FLAG] == CELL_FLAG;

        if (isFlag) {
            moves[moveLine][moveColumn] = CELL_FLAG;
        } else if (board[moveLine][moveColumn] == MINE) {
            moves[moveLine][moveColumn] = MINE;
            isGameOver = true;
        } else if (board[moveLine][moveColumn] == EMPTY) {
            moves[moveLine][moveColumn] = CELL_OPEN;
            openMoveCells(move);
        } else {
            moves[moveLine][moveColumn] = CELL_OPEN;
        }
    }

    private static void drawBoardWithMoves() {
        System.out.print("   ");
        for (char i = 'A'; i < 'A' + WIDTH; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < HEIGHT; i++) {
            System.out.printf("%3d", i);
            for (int j = 0; j < WIDTH; j++) {
                if (moves[i][j] == CELL_CLOSE) {
                    System.out.print("[]");
                    continue;
                }
                if (moves[i][j] == CELL_FLAG) {
                    System.out.print(" P");
                    continue;
                }
                String cellColor = getColorCode(board[i][j]);
                System.out.print(cellColor);
                if (board[i][j] == EMPTY) {
                    System.out.print(" .");
                } else if (board[i][j] == MINE) {
                    System.out.print(" *");
                } else {
                    System.out.printf("%2d", board[i][j]);
                }
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
    }

    public static void openMoveCells(int[] move) {
        int moveRow = move[MOVE_COLUMN];
        int moveColumn = move[MOVE_LINE];
        int[][] moveField = new int[WIDTH][HEIGHT];
        moveField[moveColumn][moveRow] = CELL_OPEN;

        int counter = WIDTH > HEIGHT ? WIDTH : HEIGHT;
        while (counter > 0) {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (moveField[i][j] == CELL_OPEN) {
                        openEmptyCellsAroundCell(i, j, moveField);
                    }
                }
            }
            counter--;
        }
        openCellsAroundEmptyCells(moveField);
        addOpenCellsToMoves(moveField);
    }

    private static void openEmptyCellsAroundCell(int i, int j, int[][] field) {
        //верхняя левая
        if (i - 1 >= 0 && j - 1 >= 0) {
            if (board[i - 1][j - 1] == EMPTY) {
                field[i - 1][j - 1] = CELL_OPEN;
            }
        }
        //верхняя
        if (j - 1 >= 0) {
            if (board[i][j - 1] == EMPTY) {
                field[i][j - 1] = CELL_OPEN;
            }
        }
        //верхняя правая
        if (i - 1 >= 0 && j + 1 < field.length) {
            if (board[i - 1][j + 1] == EMPTY) {
                field[i - 1][j + 1] = CELL_OPEN;
            }
        }
        //левая
        if (i - 1 >= 0) {
            if (board[i - 1][j] == EMPTY) {
                field[i - 1][j] = CELL_OPEN;
            }
        }
        //правая
        if (i + 1 < field.length) {
            if (board[i + 1][j] == EMPTY) {
                field[i + 1][j] = CELL_OPEN;
            }
        }
        //нижняя левая
        if (i - 1 >= 0 && j + 1 < field[i].length) {
            if (board[i - 1][j + 1] == EMPTY) {
                field[i - 1][j + 1] = CELL_OPEN;
            }
        }
        //нижняя
        if (j + 1 < field[i].length) {
            if (board[i][j + 1] == EMPTY) {
                field[i][j + 1] = CELL_OPEN;
            }
        }
        //нижняя правая
        if (i + 1 < field.length && j + 1 < field[i].length) {
            if (board[i + 1][j + 1] == EMPTY) {
                field[i + 1][j + 1] = CELL_OPEN;
            }
        }
    }

    private static void openCellsAroundEmptyCells(int[][] field) {
        int[][] fieldCopy = new int[WIDTH][HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                if (field[i][j] == CELL_OPEN) {
                    //верхняя левая
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        fieldCopy[i - 1][j - 1] = CELL_OPEN;
                    }
                    //верхняя
                    if (i - 1 >= 0) {
                        fieldCopy[i - 1][j] = CELL_OPEN;
                    }
                    //верхняя правая
                    if (i - 1 >= 0 && j + 1 < field[i].length) {
                        fieldCopy[i - 1][j + 1] = CELL_OPEN;
                    }
                    //левая
                    if (j - 1 >= 0) {
                        fieldCopy[i][j - 1]  = CELL_OPEN;
                    }
                    //правая
                    if (j + 1 < field[i].length) {
                        fieldCopy[i][j + 1] = CELL_OPEN;
                    }
                    //нижняя левая
                    if (i + 1 < fieldCopy.length && j -1 >= 0) {
                        fieldCopy[i + 1][j - 1]  = CELL_OPEN;
                    }
                    //нижняя
                    if (i + 1 < field.length) {
                        fieldCopy[i + 1][j] = CELL_OPEN;
                    }
                    //нижняя правая
                    if (i + 1 < field.length && j + 1 < field[i].length) {
                        fieldCopy[i + 1][j + 1] = CELL_OPEN;
                    }
                }
            }
        }
        addOpenCellsToMoves(fieldCopy);
    }

    private static void checkWin() {
        int openCell = 0;
        for (int[] lines : moves) {
            for (int cell : lines) {
                if (cell == CELL_OPEN) {
                    openCell++;
                }
            }
        }
        if (openCell == HEIGHT * WIDTH - MINE_COUNT) {
            isGameWin = true;
        }
    }

    private static void addOpenCellsToMoves(int[][] field) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (field[i][j] == CELL_OPEN) {
                    moves[i][j] = CELL_OPEN;
                }
            }
        }
    }

    private static String getColorCode(int i) {
        switch (i) {
            case EMPTY:
                return ANSI_WHITE;
            case MINE:
                return ANSI_PURPLE;
            case 1:
                return ANSI_BLUE;
            case 2:
                return ANSI_GREEN;
            case 3:
                return ANSI_RED;
            case 4:
                return ANSI_CYAN;
            case 5:
                return ANSI_YELLOW;
        }
        return null;
    }
}

