public class Scorer {
    public static int scoreHand(int[] positions, Board board) {
        Card[] hand = new Card[positions.length];
        int n = 0;
        for (int p : positions) {
            Card c = board.getAt(p);
            if (c != null) {
                hand[n++] = c;
            }
        }
        if (n == 0) return 1;
        int sum = 0;
        int aceCount = 0;
        for (int i = 0; i < n; i++) {
            sum += hand[i].getValue();
            if (hand[i].isAce()) aceCount++;
        }
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }

        if (sum >= 22) return 0;
        if (sum == 21 && n == 2) return 10;
        if (sum == 21) return 7;
        if (sum == 20) return 5;
        if (sum == 19) return 4;
        if (sum == 18) return 3;
        if (sum == 17) return 2;
        return 1;
    }

    public static int score(Board board) {
        int total = 0;
        for (int r = 0; r < 4; r++) {
            total += scoreHand(board.getRowPositions(r), board);
        }
        for (int c = 0; c < 5; c++) {
            total += scoreHand(board.getColPositions(c), board);
        }
        return total;
    }
}