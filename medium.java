import java.util.*;

class Card {
    private final String symbol;
    private final String name;

    public Card(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Card{Name='" + name + "', Symbol='" + symbol + "'}";
    }
}

public class CardCollectionManager {
    private static final Collection<Card> cardCollection = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection Manager");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1: addCard(); break;
                case 2: searchCardsBySymbol(); break;
                case 3: displayAllCards(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Card Symbol: ");
        String symbol = scanner.nextLine();
        
        cardCollection.add(new Card(symbol, name));
        System.out.println("Card added successfully!");
    }

    private static void searchCardsBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        boolean found = false;
        
        for (Card card : cardCollection) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No cards found with the given symbol.");
        }
    }

    private static void displayAllCards() {
        if (cardCollection.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (Card card : cardCollection) {
                System.out.println(card);
            }
        }
    }
}
