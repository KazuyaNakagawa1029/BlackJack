package com.company;

import java.util.List;

public class Judge {
    public String get_judgementResultName() {
        return _judgementResultName;
    }

    public int get_judgementResultNumber() {
        return _judgementResultNumber;
    }

    public List<Card> _playerCards;
    public List<Card> _dealerCards;
    public int playerCardsNumberSum;
    public int dealerCardsNumberSum;
    private String _judgementResultName = " ";
    private int _judgementResultNumber;

    /**
     * Handクラスを呼び出し判定を行う
     *
     * @param playerCards 　プレイヤーのカードが入ったリスト
     * @param dealerCards 　ディーラーのカードが入ったリスト
     */
    public void setResult(List<Card> playerCards, List<Card> dealerCards) {
        _playerCards = playerCards;
        _dealerCards = dealerCards;
        Hand playerHand = new Hand(_playerCards);
        Hand dealerHand = new Hand(_dealerCards);
        playerCardsNumberSum = playerHand.isHandPoint();
        dealerCardsNumberSum = dealerHand.isHandPoint();


        if (playerHand._isNaturalBlackJack() && dealerHand._isNaturalBlackJack()) {
            _judgementResultName = "引き分け";
            _judgementResultNumber = 2;
        } else if (dealerHand._isNaturalBlackJack()) {
            _judgementResultName = "負け";
            _judgementResultNumber = 1;
        } else if (playerHand._isNaturalBlackJack()) {
            _judgementResultName = "勝ち";
            _judgementResultNumber = 0;
        } else if (playerCardsNumberSum > 21 && dealerCardsNumberSum > 21) {
            _judgementResultName = "負け";
            _judgementResultNumber = 1;
        } else if (dealerCardsNumberSum > 21) {
            _judgementResultName = "勝ち";
            _judgementResultNumber = 0;
        } else if (playerCardsNumberSum > 21) {
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
