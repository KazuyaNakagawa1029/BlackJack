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
        int isSum = 0;
        int isOverTen = 0;
        boolean isAce = false;
        for (Card card : _Cards) {
            isSum += card.get_cardNumber();

            if (card.get_cardNumber() > 9) {
                isOverTen = card.get_cardNumber() - 10;
            }
            if (card.get_cardNumber() == 1) {
                isAce = true;
            }
            isSum -= isOverTen;
            isOverTen = 0;
        }
        if (isAce && 12 > isSum) {
            isSum += 10;
        }
        return isSum;
    }

    /**
     * ナチュラルブラックジャック(A+10,J,Q,K)か判定するメソッド
     *
     * @return
     */
    public boolean _isNaturalBlackJack() {
        for (int i = 0; i < _Cards.size(); i++) {
            if (cardsNumberSum == 21 && _Cards.size() == 2) {
                return true;
            }
        }
        return false;
    }


}
