package com.company;

import java.util.List;

public class Judge {
    public String get_judgementResultName() {
        return _judgementResultName;
    }

    public int get_judgementResultNumber() {
        return _judgementResultNumber;
    }

    private String _judgementResultName = " ";
    private int _judgementResultNumber;
    public static final int BURST_NUMBER=21;

    /**
     * Handクラスを呼び出し判定を行う
     *
     * @param playerCards 　プレイヤーのカードが入ったリスト
     * @param dealerCards 　ディーラーのカードが入ったリスト
     */
    public void setResult(List<Card> playerCards, List<Card> dealerCards) {
        List<Card> _playerCards = playerCards;
        List<Card> _dealerCards = dealerCards;
        Hand playerHand = new Hand(_playerCards);
        Hand dealerHand = new Hand(_dealerCards);
        int playerCardsNumberSum = playerHand.isHandPoint();
        int dealerCardsNumberSum = dealerHand.isHandPoint();


        if (playerHand.isNaturalBlackJack() && dealerHand.isNaturalBlackJack()) {
            _judgementResultName = "引き分け";
            _judgementResultNumber = 2;
        } else if (dealerHand.isNaturalBlackJack()) {
            _judgementResultName = "負け";
            _judgementResultNumber = 1;
        } else if (playerHand.isNaturalBlackJack()) {
            _judgementResultName = "勝ち";
            _judgementResultNumber = 0;
        } else if (playerCardsNumberSum > BURST_NUMBER && dealerCardsNumberSum > BURST_NUMBER) {
            _judgementResultName = "負け";
            _judgementResultNumber = 1;
        } else if (dealerCardsNumberSum > BURST_NUMBER) {
            _judgementResultName = "勝ち";
            _judgementResultNumber = 0;
        } else if (playerCardsNumberSum > BURST_NUMBER) {
            _judgementResultName = "負け";
            _judgementResultNumber = 1;
        } else if (playerCardsNumberSum > dealerCardsNumberSum) {
            _judgementResultName = "勝ち";
            _judgementResultNumber = 0;
        } else if (playerCardsNumberSum == dealerCardsNumberSum) {
            _judgementResultName = "引き分け";
            _judgementResultNumber = 2;
        } else {
            _judgementResultName = "負け";
            _judgementResultNumber = 1;
        }
    }
}
