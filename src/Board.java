public class Board {
    private Card[][] grid;
    private int[][] pos;
    private Card[] discards;

    public Board() {
        grid = new Card[4][5];
        pos = new int[4][5];
        discards = new Card[4];

        pos[0][0] = 1;  pos[0][1] = 2;  pos[0][2] = 3;  pos[0][3] = 4;  pos[0][4] = 5;
        pos[1][0] = 6;  pos[1][1] = 7;  pos[1][2] = 8;  pos[1][3] = 9;  pos[1][4] = 10;
        pos[2][0] = 0;  pos[2][1] = 11; pos[2][2] = 12; pos[2][3] = 13; pos[2][4] = 0;
        pos[3][0] = 0;  pos[3][1] = 14; pos[3][2] = 15; pos[3][3] = 16; pos[3][4] = 0;
    }

    public boolean isValidPos(int p) {
        return p >= 1 && p <= 20;
    }

    public boolean isScoringSlot(int p) {
        return p >= 1 && p <= 16;
    }

    public boolean isDiscardSlot(int p) {
        return p >= 17 && p <= 20;
    }

    public boolean isOccupied(int p) {
        if (!isValidPos(p)) return false;
        if (isDiscardSlot(p)) {
            return discards[p - 17] != null;
        }
        int[] rc = rcOf(p);
        if (rc == null) return false;
        return grid[rc[0]][rc[1]] != null;
    }

    public boolean place(int p, Card c) {
        if (!isScoringSlot(p)) return false;
        int[] rc = rcOf(p);
        if (rc == null) return false;
        if (grid[rc[0]][rc[1]] != null) return false;
        grid[rc[0]][rc[1]] = c;
        return true;
    }

    public boolean discard(int p, Card c) {
        if (!isDiscardSlot(p)) return false;
        int idx = p - 17;
        if (discards[idx] != null) return false;
        discards[idx] = c;
        return true;
    }

    public Card getAt(int p) {
        if (!isValidPos(p)) return null;
        if (isDiscardSlot(p)) {
            return discards[p - 17];
        }
        int[] rc = rcOf(p);
        if (rc == null) return null;
        return grid[rc[0]][rc[1]];
    }

    public boolean scoringSlotsFull() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                int p = pos[r][c];
                if (p >= 1 && p <= 16) {
                    if (grid[r][c] == null) return false;
                }
            }
        }
        return true;
    }

    public int[] getRowPositions(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= 4) return new int[0];
        int cnt = 0;
        for (int c = 0; c < 5; c++) if (pos[rowIndex][c] != 0) cnt++;
        int[] res = new int[cnt];
        int k = 0;
        for (int c = 0; c < 5; c++) {
            if (pos[rowIndex][c] != 0) res[k++] = pos[rowIndex][c];
        }
        return res;
    }

    public int[] getColPositions(int colIndex) {
        if (colIndex < 0 || colIndex >= 5) return new int[0];
        int cnt = 0;
        for (int r = 0; r < 4; r++) if (pos[r][colIndex] != 0) cnt++;
        int[] res = new int[cnt];
        int k = 0;
        for (int r = 0; r < 4; r++) {
            if (pos[r][colIndex] != 0) res[k++] = pos[r][colIndex];
        }
        return res;
    }

    public void printBoard() {
        printCellOrNumber(0,0); printCellOrNumber(0,1);
        printCellOrNumber(0,2); printCellOrNumber(0,3);
        printCellOrNumber(0,4);
        System.out.println();

        printCellOrNumber(1,0); printCellOrNumber(1,1);
        printCellOrNumber(1,2); printCellOrNumber(1,3);
        printCellOrNumber(1,4);
        System.out.println();

        printBlank();            printCellOrNumber(2,1);
        printCellOrNumber(2,2);  printCellOrNumber(2,3);
        printBlank();
        System.out.println();

        printBlank();            printCellOrNumber(3,1);
        printCellOrNumber(3,2);  printCellOrNumber(3,3);
        printBlank();
        System.out.println();
    }

    private void printCellOrNumber(int r, int c) {
        if (pos[r][c] == 0) {
            printBlank();
            return;
        }
        if (grid[r][c] != null) {
            System.out.print(grid[r][c].toString() + "\t");
        } else {
            System.out.print(pos[r][c] + "\t");
        }
    }

    private void printBlank() {
        System.out.print("\t");
    }

    private int[] rcOf(int p) {
        if (!isScoringSlot(p)) return null;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                if (pos[r][c] == p) return new int[]{r, c};
            }
        }
        return null;
    }
}
