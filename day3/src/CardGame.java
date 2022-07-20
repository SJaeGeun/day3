import java.util.Objects;
import java.util.Random;

// spades, hearts, diamonds, clubs
class Suit {
    String display;
    int order;

    public Suit(String display, int order){
        this.display = display;
        this.order = order;
    }

}

// A(1), 2, 3, 4, 5, 6, 7, 8, 9,10, J(11), Q(12), K(13),Joker(14)
class Number{
    int value;

    public Number(int value){
        this.value = value;
    }
}

class Card {
    private final Suit suit;
    private final Number number;

    public Card(Suit suit, Number number){
        this.suit = suit;
        this.number = number;
    }

    public static Card joker() {
        return new Card(new Suit("Joker", 5), new Number(14));
    }
    public String Display(){
        return this.suit.display;
    }
    public int Value(){
        return this.number.value;
    }
    public int Order(){
        return this.suit.order;
    }
    public void showCard(String user){
        if (this.suit.order == 5){
            System.out.println("사용자" + user + "=" + this.suit.display);
        } else{
            System.out.println("사용자" + user + "=" + this.suit.display+ " " + this.number.value);
        }
    }

}


public class CardGame {
    public static void main(String[] args) {
        Card userA;
        Card userB;
        do{
        userA = drawCard();
        userB = drawCard();
        }while(Objects.equals(userA.Display(), userB.Display()) && userA.Value()==userB.Value()
                && userB.Value()!=14 && userB.Value()!=15);
        //A와 B의 디스플레이가 같으면서 Value도 같으면서 Value가 14또는 15가아니면 반복한다.
        //cardgame클래스로 여러 조건문에 인자값을 가져오기위해 위해 Card클래스에 Display,Order,Value메소드 선언하여 본인 값을 리턴.

        userA.showCard("A");
        userB.showCard("B");

        result(userA,userB);
    }
    public static Card drawCard(){
        Random random = new Random();
        Number number = new Number(random.nextInt(15)+1);
        Suit[] suitArray = {
                new Suit("Diamond", 1),
                new Suit("Heart", 2),
                new Suit("Club", 3),
                new Suit("Spade", 4)
        };
        Suit suit = suitArray[random.nextInt(4)];
        if (number.value == 14 || number.value == 15){
            return Card.joker();
        } else {
            return new Card(suit, number);
        }
    }
    public static void result(Card A,Card B) {
        if (A.Order() == 5 && B.Order() == 5) { // 조커가 있는 경우
            System.out.println("두 사용자 모두 조커로 무승부입니다."); // 둘 다 조커일 때
        } else if (A.Order() == 5 && B.Order() != 5) {
            System.out.println("사용자 A가 조커로 승리하였습니다."); // A가 조커일 때
        } else if (A.Order() != 5 && B.Order() == 5) {
            System.out.println("사용자 B가 조커로 승리하였습니다."); // B가 조커일 때
        } else { //조커 경우를 제외한 숫자
            if (A.Value() > B.Value()) { // A의 숫자가 높을 때
                System.out.println("사용자 A가 더 높은 숫자로 승리하였습니다.");
            } else if (A.Value() < B.Value()) { // B의 숫자가 높을 때
                System.out.println("사용자 B가 더 높은 숫자로 승리하였습니다.");
            } else { //숫자가 동일할 때
                if (A.Order() > B.Order()) { //A의 order가 높을 때
                    System.out.println("사용자 A가 더 높은 무늬으로 승리하였습니다.");
                } else { //A의 order가 높을 때
                    System.out.println("사용자 B가 더 높은 무늬으로 승리하였습니다.");
                }
            }

        }
    }
}