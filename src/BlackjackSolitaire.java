import java.util.Scanner;

public class BlackjackSolitaire {
    private Deck deck;
    private Board board;
    private int discardsRemaining;

    public BlackjackSolitaire() {
        this.deck = new Deck();
        this.board = new Board();
        this.discardsRemaining = 4;
    }

    public void play() {
        Scanner in = new Scanner(System.in);
        board.printBoard();
        while (!board.scoringSlotsFull()) {
            if (!deck.hasNext()) {
                System.out.println("Deck empty unexpectedly.");
                break;
            }
            Card next = deck.deal();

            System.out.println("Discards remaining: " + discardsRemaining);
            System.out.println("Next card: " + next.toString());
            System.out.print("Enter a position (1-20; 17-20 are discards): ");

            Integer choice = readInt(in);
            if (choice == null) {
                System.out.println("Invalid input. Please enter a number 1-20.");
                continue;
            }
            int pos = choice.intValue();

            if (pos < 1 || pos > 20) {
                System.out.println("Invalid spot. Please choose 1-20.");
                continue;
            }

            if (board.isOccupied(pos)) {
                System.out.println("That spot is already filled. Choose another.");
                continue;
            }

            if (board.isDiscardSlot(pos)) {
                if (discardsRemaining <= 0) {
                    System.out.println("No discards remaining.");
                    continue;
                }
                if (board.discard(pos, next)) {
                    discardsRemaining--;
                } else {
                    System.out.println("Cannot discard to that spot.");
                    continue;
                }
            }
            else {
                if (!board.place(pos, next)) {
                    System.out.println("Cannot place to that spot.");
                    continue;
                }
            }
            board.printBoard();
        }
        System.out.println("Scoring hands...");
        int total = Scorer.score(board);
        System.out.println("Game over! You scored " + total + " points.");
        in.close();
    }

    private Integer readInt(Scanner in) {
        if (!in.hasNextInt()) {
            in.next();
            return null;
        }
        return Integer.valueOf(in.nextInt());
    }
}
