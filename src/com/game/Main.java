package com.game;

import java.util.Scanner;

public class Main {
    static int map[][] = { //0-7 - safe 8 - mine;
            {0, 0, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 2, 8, 1, 1, 8, 1, 0},
            {1, 8, 2, 1, 2, 1, 2, 1, 0},
            {1, 1, 1, 0, 1, 8, 1, 0, 0},
            {0, 0, 1, 1, 2, 2, 2, 1, 0},
            {0, 0, 1, 8, 1, 1, 8, 2, 1},
            {0, 1, 2, 2, 1, 1, 1, 2, 8},
            {0, 2, 8, 2, 1, 1, 1, 1, 1},
            {0, 2, 8, 2, 1, 8, 1, 0, 0}
    };
    static char mapVisible[][] = {
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},
            {'?', '?', '?', '?', '?', '?', '?', '?', '?'},


    };
    private static int visible = 0;

    public static void surrounding(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < map.length && j >= 0 && j < map.length) {
                    if (map[i][j] != 8 && mapVisible[i][j] == '?') {
                        if (map[i][j] == 0) {
                            mapVisible[i][j] = ' ';
                            visible++;
                            surrounding(i, j);
                        } else {
                            char value = Character.forDigit(map[i][j], 10);
                            if (value == '0') {
                                mapVisible[i][j] = ' ';
                            } else {
                                mapVisible[i][j] = value;
                            }
                            visible++;
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        boolean gameOver = false;
        while (gameOver == false || visible <= 70) {
            for (int y = 0; y <= map.length; y++) {
                for (int x = 0; x <= map.length; x++) {
                    if (y == 0 && x != 0) {
                        System.out.print(x - 1);
                        System.out.print(" ");
                    } else if (x == 0 && y != 0) {
                        System.out.print(y - 1);
                        System.out.print(" ");
                    } else if (y != 0 && x != 0) {
                        System.out.print(mapVisible[y - 1][x - 1]);
                        System.out.print(" ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }

            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter row number: ");
            int row = sc.nextInt();
            System.out.print("Enter column number: ");
            int column = sc.nextInt();
            System.out.println();

            if (row >= 0 && row < map.length && column >= 0 && column < map.length) {
                if (mapVisible[row][column] != '?') {
                    continue;
                } else if (map[row][column] == 8) {
                    gameOver = true;
                } else if (map[row][column] > 0 && map[row][column] < 8 && mapVisible[row][column] == '?') {
                    char value = Character.forDigit(map[row][column], 10);
                    mapVisible[row][column] = value;
                    visible++;
                } else {
                    surrounding(row, column);
                }
            } else {
                System.out.println("Row/Column does not exist !");
            }
            if (visible >= 71) {
                break;
            }
            System.out.println("visible: ");
            System.out.println(visible);
            System.out.println(" ");
        }
        if (gameOver == true) {
            System.out.println("GameOver!");
        } else {
            System.out.println("You Are The Winner!");
        }
        for (int y = 0; y <= map.length; y++) {
            for (int x = 0; x <= map.length; x++) {
                if (y == 0 && x != 0) {
                    System.out.print(x - 1);
                    System.out.print(" ");
                } else if (x == 0 & y != 0) {
                    System.out.print(y - 1);
                    System.out.print(" ");
                } else if (y != 0 && x != 0) {
                    if (map[y - 1][x - 1] != 8) {
                        if (map[y - 1][x - 1] == 0) {
                            System.out.print(" ");
                        } else {
                            System.out.print(map[y - 1][x - 1]);
                            System.out.print(" ");
                        }
                    } else {
                        System.out.print("X ");
                    }
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
