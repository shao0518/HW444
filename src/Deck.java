public class Deck {
    private Card[] cards;
    private int index;

    public Deck() {
        cards = new Card[52];
        int k = 0;
        String[] suits = {"H", "D", "C", "S"};
        for (int s = 0; s < suits.length; s++) {
            for (int num = 1; num <= 13; num++) {
                cards[k] = new Card(num, suits[s]);
                k++;
            }
        }
        shuffle();
    }

    public void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            Card t = cards[i];
            cards[i] = cards[j];
            cards[j] = t;
        }
        index = 0;
    }

    public boolean hasNext() {
        return index < cards.length;
    }

    public Card deal() {
        if (!hasNext()) {
            return null;
        }
        Card nextCard = cards[index];
        index++;
        return nextCard;
    }

    public int remaining() {
        return cards.length - index;
    }

    public String toString() {
        return "Deck(remaining=" + remaining() + ")";
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);

        for (int i = 0; i < 5; i++) {
            Card c = deck.deal();
            System.out.println(c.toString());
        }
        System.out.println(deck);
    }
}
