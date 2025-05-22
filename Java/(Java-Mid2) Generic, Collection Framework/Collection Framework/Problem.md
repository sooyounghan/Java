-----
### 문제와 풀이 - 카드 게임
-----
1. 요구사항
  - 카드(Card)는 1 ~ 13까지 있으며, 각 번호당 다음 4개의 문양이 존재
    + ♠ : 스페이드
    + ♥ : 하트
    + ◆ : 다이아
    + ♣ : 클로버
  - 예) 1(♠), 1(♥), 1(◆), 1(♣), 2(♠), 2(♥), 2(◆), 2(♣) ... 13(♠), 13(♥), 13(䡫◆), 13(♣)
  - 따라서 13 * 4 = 총 52장의 카드가 존재
  - 52장의 카드가 있는 카드 뭉치를 덱(Deck)
  - 2명의 플레이어(Player)가 게임을 진행

  - 게임을 시작하면 다음 순서를 따름
    + 덱에 있는 카드를 랜덤하게 섞음
    + 각 플레이어는 덱에서 카드를 5장씩 뽑음
    + 각 플레이어는 5장의 카드를 정렬된 순서대로 보여주며, 정렬 기준은 다음과 같음
      * 작은 숫자가 먼저 나옴
      * 같은 숫자의 경우 ♠, ♥, ◆, ♣ 순으로 정렬하므로, ♠가 가장 먼저 나온다.
      * 예) 1(♠), 1(♥), 2(◆), 3(♣) 순서로 출력된다.
    + 카드 숫자의 합계가 큰 플레이어가 승리
    + 게임을 단순화 하기 위해 숫자만 출력
    + 합계가 같으면 무승부

2. 실행 결과 예시
```
플레이어1의 카드: [2(♠), 7(♥), 7(♦), 8(♣), 13(♠)], 합계: 37
플레이어2의 카드: [1(♠), 1(♣), 6(♠), 9(♠), 9(♣)], 합계: 26
플레이어1 승리
```

```
플레이어1의 카드: [2(♦), 3(♠), 6(♥), 10(♣), 13(♦)], 합계: 34
플레이어2의 카드: [2(♠), 4(♣), 5(♠), 11(♣), 12(♥)], 합계: 34
무승부
```

3. 참고
    - 스페이드, 하트 같은 아이콘을 직접 사용하기 어려운 경우 다음과 같이 \ (백슬래시 backslash)와 함께 다음 코드를 적어주면 아이콘을 출력할 수 있음
      + "\u2660" : 스페이드(♠)
      + "\u2665" : 하트(♥)
      + "\u2666" : 다이아몬드(䡫)
      + "\u2663" : 클로버(♣)
    - 예) System.out.println("\u2660")

4. Suit (/collection/comapre/test)
```java
package collection.compare.test;

public enum Suit {
    SPADE("♠"), // 스페이드(♠)
    HEART("♥"), // 하트(♥)
    DIAMOND("♦"), // 다이아몬드(♦)
    CLUB("♣"); // 클로버(♣)
    
    private String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
```
5. Card
```java
package collection.compare.test;

public class Card implements Comparable<Card>{
    private final int rank; // 카드의 숫자
    private final Suit suit; // 카드의 마크

    public Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card anotherCard) {
        // 숫자를 먼저 비교 후, 숫자가 같으면 마크 비교
        if(this.rank != anotherCard.rank) {
            return Integer.compare(this.rank, anotherCard.rank);
        } else {
            return this.suit.compareTo(anotherCard.suit);
        }
    }

    @Override
    public String toString() {
        return rank +  " (" + suit + ")";
    }
}
```
   - Suit 는 ENUM 타입
   - 스페이드, 하트 등의 문양의 순서는 변하지 않는다고 가정하고, ENUM의 기본 순서를 사용
   - 💡 ENUM 타입은 compareTo()가 열거형의 순서인 ordinal로 구현
   - 💡그리고 ENUM의 compareTo() 메서드는 final 선언되어 있어서 재정의 할 수 없음

6. Deck
```java
package collection.compare.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        initCard();
        shuffle();
    }

    private void initCard() {
        for(int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(i, suit));
            }
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}
```
   - List 에서 데이터를 앞에서 부터 꺼내고 있음
   - 지금처럼 데이터의 수가 작다면 ArrayList를 사용해도 괜찮지만, 데이터의 수가 많다면 LinkedList를 고려

7. Player
```java
package collection.compare.test;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public int rankSum() {
        int value = 0;
        for (Card card : hand) {
            value += card.getRank();
        }

        return value;
    }

    public void showHand() {
        hand.sort(null);
        System.out.println(name + "의 카드 : " + hand + ", 합계 : " + rankSum());
    }
}
```

8. CardGameMain
```java

```
