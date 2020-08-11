package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStuck {
    //52枚のカードを入れるリストを作る
    /**
     * カード山
     * コンストラクタ、createNewStackが呼び出された際に新しく52枚のカードが設定される。
     * Shuffleを呼び出さない限りS1～C13の並びになる。
     */
    public List<Card> cards = new ArrayList<>();


    /**
     * コンストラクタ
     * 同時にカード山の初期化とシャッフルが行われる
     */
    //コンストラクタ(クラス名と同じ名前)
    public CardStuck() {
        //初期化：カードを52枚設定する
        createNewStack();
        //シャッフルメソッドを呼ぶ
        Shuffle();
    }

    /**
     * カードの山の初期化
     * 使用する前にシャッフルを呼び出す事
     */
    public void createNewStack() {
        // カード山に52枚のカードを積み込む
        for (int i = 0; i < Card.CARD_ALL_NUMBER; i++) {
            Card card = new Card(i);
            cards.add(card);
        }
    }

    /**
     * カードをシャッフルする
     */
    public void Shuffle() {
        // カード山をシャッフルする
        Collections.shuffle(cards);
    }

    /**
     * カード山の上から１枚カードを取得する
     *
     * @return 取得されたカード
     */
    public Card takeCard() {
        // 最上段(index=0)のカードを取得する
        Card card = cards.get(0);
        // 最上段のカードをカード山から削除する
        cards.remove(0);
        // 最初に取得したカードを戻り値として返す
        return card;
    }

    /**
     * 指定した枚数分のカードを取得する
     *
     * @param takeCount 取得するカード枚数
     * @return 取得されたカードのリスト
     */
    //取ったカードを入れるリスト
    public List<Card> takeCards(int takeCount) {
        // 取得したカードリストを保持する変数
        List<Card> takeCard = new ArrayList<>();
        // 指定枚数までカードを取得する
        for (int i = 0; i < takeCount; i++) {
            takeCard.add(takeCard());
        }
        // 取得されたカードリストを戻り値として返す
        return takeCard;
    }

}