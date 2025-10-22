public class Card {
    private int num;
    private String suit;

    public Card(int num, String suit) {
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isAce() {
        return num == 1;
    }

    public int getValue() {
        if (num >= 2 && num <= 10) {
            return num;
        } else if (num == 11 || num == 12 || num == 13) {
            return 10;
        } else if (num == 1) {
            return 11;
        }
        return 0;
    }

    public String toString() {
        String poker;
        if (num == 1) {
            poker = "A";
        } else if (num == 11) {
            poker = "J";
        } else if (num == 12) {
            poker = "Q";
        } else if (num == 13) {
            poker = "K";
        } else {
            poker = String.valueOf(num);
        }
        return poker + suit;
    }
}
