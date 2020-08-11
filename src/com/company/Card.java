package com.company;

import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card> {
    /**
     * カードマークの画面表示文字列格納リスト
     * S:0 H:1 H:2 C:3
     */
    private static final List<String> CARD_MARK = new ArrayList<String>() {{
        // S,H,D,Cの文字列を設定
        add("S");
        add("H");
        add("D");
        add("C");
    }};

    /**
     * 同一マークのカード枚数 1~13
     */
    public static final int CARD_NUMBER = 13;

    /**
     * 全カード枚数
     */
    public static final int CARD_ALL_NUMBER = 52;


    /**
     * 0~51の番号をもらってS1~C13を返す
     *
     * @param cardIndex 0~51の番号
     * @return S1~C13の文字列
     */
    public static String convertCardIndexToDisplayString(int cardIndex) {
        String dispNumber;
        //カードマーク部分のIndexを取得
        int cardMark = getCardMarkNumber(cardIndex);
        //カードの番号部分を取得
        int cardNumber = getCardNumber(cardIndex);
        //1はA、11はJ、12はQ、13はKに変換して出力
        if (cardNumber == 1) {
            dispNumber = "A";
        } else if (cardNumber == 11) {
            dispNumber = "J";
        } else if (cardNumber == 12) {
            dispNumber = "Q";
        } else if (cardNumber == 13) {
            dispNumber = "K";
        } else {
            dispNumber = String.valueOf(cardNumber);
        }

        // 画面表示用文字列を返す
        return CARD_MARK.get(cardMark) + dispNumber;
    }


    /**
     * 0~51の数字を受け取り、S,H,D,Cのマーク文字列に変換する
     *
     * @param cardIndex 0~51の数字
     * @return S, H, D, Cのマークに対応する数値
     */
    public static int getCardMarkNumber(int cardIndex) {
        // カードマークを表すの数値(0~3)を戻り値として返す
        return cardIndex / CARD_NUMBER;
    }


    /**
     * 0~51の数字を受け取り、1~13のカード番号に変換する
     *
     * @param cardIndex 0~51の数字
     * @return 1~13
     */
    public static int getCardNumber(int cardIndex) {
        // カード番号を表す数値を戻り値として返す
        return cardIndex % CARD_NUMBER + 1;
    }

    private final int _cardIndex;
    private final int _cardNumber;

    /**
     * カードIndex(0~51)の取得
     *
     * @return 0~51の数値
     */
    public int get_cardIndex() {
        return _cardIndex;
    }

    /**
     * @return 1~13のカード番号
     */
    public int get_cardNumber() {
        return _cardNumber;
    }

    /**
     * コンストラクタ
     *
     * @param cardIndex 0~51の数字
     */
    public Card(int cardIndex) {
        this._cardIndex = cardIndex;
        _cardNumber = Card.getCardNumber(cardIndex);
    }

    public String getDisplayString() {
        return Card.convertCardIndexToDisplayString(_cardIndex);
    }

    @Override
    public int compareTo(Card o) {
        return this.get_cardIndex() - o.get_cardIndex();
    }
}
