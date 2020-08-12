package com.company;

import java.util.List;

public class Hand {

    private final List<Card> _Cards;
    int cardsNumberSum;

    /**
     * コンストラクタ
     *
     * @param cards 対象のカードリスト
     */
    public Hand(List<Card> cards) {
        _Cards = cards;
        cardsNumberSum = isHandPoint();
    }

    /**
     * カードの合計値を計算する
     *
     * @return カードの合計値
     */
    public int isHandPoint() {
        int Sum = 0;
        boolean isAce = false;
        for (Card card : _Cards) {
            if (card.get_cardNumber() > 9) {
                Sum+=10;
            }else{
                Sum+=card.get_cardNumber();
            }
            if (card.get_cardNumber() == 1) {
                isAce = true;
            }
        }
        if (isAce && 12 > Sum) {
            Sum += 10;
        }
        return Sum;
    }

    /**
     * ナチュラルブラックジャック(A+10,J,Q,K)か判定するメソッド
     *
     * @return
     */
    public boolean isNaturalBlackJack() {
        for (int i = 0; i < _Cards.size(); i++) {
            if (cardsNumberSum == 21 && _Cards.size() == 2) {
                return true;
            }
        }
        return false;
    }


}
