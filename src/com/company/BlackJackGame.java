package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BlackJackGame {

    private CardStuck cardStuck = new CardStuck();
    //プレイヤー側のカードが入ったリスト
    public List<Card> playerCards;
    //ディーラー側のカードが入ったリスト
    public List<Card> dealerCards;
    //プレイヤー側の合計得点
    public int playerCardsNumberSum;
    //ディーラー側の合計得点
    public int dealerCardsNumberSum;
    //バーストした際に合計値を赤に変える
    public static final String RED = "\u001B[31m";
    //ナチュラルブラックジャックが出た際にカードの色を変える
    public static final String CYAN = "\u001b[00;36m";
    public static final String RESET = "\u001B[0m";

    //通算の勝敗をカウント
    int[] count = {0, 0, 0};


    public void Begin() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //開始メッセージ
        Msg.showOpeningMsg();
        //ポーカー1ゲームの勝負開始のメッセージ
        Msg.showBeginGameMsg();

        while (true) {
            //プレイヤーにカードを配る
            deliveryPlayerCard();
//            playerCards.set(0, new Card(0));
//            playerCards.set(1, new Card(13));

            //ディーラーにカードを配る
            deliveryDealerCard();
//            dealerCards.set(0, new Card(13));
//            dealerCards.set(1, new Card(22));

            Hand playerHand = new Hand(playerCards);
            Hand dealerHand = new Hand(dealerCards);

            //点数の計算を行う
            playerCardsNumberSum = playerHand.isHandPoint();
            dealerCardsNumberSum = dealerHand.isHandPoint();
            //カードの表示
            showDealerCards();
            showPlayerCards();
            //プレイヤーがカードを引く
            drawPlayerCard();
            //ディーラーが得点数が17を超えるまでカードを引き続ける
            drawDealerCard();
            //最終のカードを表示
            showAllDealerCards();
            showPlayerCards();
            //Judgeクラスを呼び出し判定を行う
            Judge judge = new Judge();
            judge.setResult(playerCards, dealerCards);
            //判定結果を表示する
            System.out.println(judge.get_judgementResultName());
            count[judge.get_judgementResultNumber()]++;
            System.out.println(count[0] + "勝" + count[1] + "敗" + count[2] + "分");
            //ゲームを続けるか辞めるかの入力を促すメッセージを表示する
            Msg.showContinueMsg();
            String selectHitOrStand = " ";
            try {
                selectHitOrStand = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //yesと入力された場合は引いたカードを戻しゲームを再開する
            if (selectHitOrStand.equalsIgnoreCase("yes") || selectHitOrStand.equals("")) {
                addDrawnCards(playerCards);
                addDrawnCards(dealerCards);
                cardStuck.Shuffle();
                continue;
            } else {
                break;
            }

        }
    }

    /**
     * プレイヤーのにカードを2枚配る
     */
    private void deliveryPlayerCard() {
        playerCards = cardStuck.takeCards(2);
    }

    /**
     * プレイヤーのカードを表示する
     */
    private void showPlayerCards() {
        Hand playerHand = new Hand(playerCards);
        System.out.print("あ　な　た: ");
        for (Card playerCard : playerCards) {
            //ブラックジャックが出た際にカードの色を変更する
            if (playerHand._isNaturalBlackJack()) {
                System.out.print(CYAN + playerCard.getDisplayString() + RESET + " ");
            } else {
                System.out.print(playerCard.getDisplayString() + " ");
            }
        }

        //バーストした際に合計値を赤に変える
        if (playerCardsNumberSum > 21) {
            System.out.println("(" + RED + playerCardsNumberSum + RESET + ")");
        } else {
            System.out.println("(" + playerCardsNumberSum + ")");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * プレイヤーがカードを引く
     */
    private void drawPlayerCard() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Hand playerHand = new Hand(playerCards);
        playerCardsNumberSum = playerHand.isHandPoint();
        while (true) {
            Msg.showDrawCardMsg();
            String playerDrawCardResponse = "";
            try {
                playerDrawCardResponse = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //yesと入力されるとカードを引く
            if (playerDrawCardResponse.equalsIgnoreCase("yes")) {
                Card newPlayerCard = cardStuck.takeCard();
                playerCards.add(newPlayerCard);
                playerCardsNumberSum = playerHand.isHandPoint();
                //バーストした時点で終了する
                if (playerCardsNumberSum > 21) {
                    break;
                }
                showPlayerCards();
            } else {
                break;
            }
        }
    }

    /**
     * ディーラーにカードを2枚配る
     */
    private void deliveryDealerCard() {
        dealerCards = cardStuck.takeCards(2);
    }

    /**
     * 合計値が17を超えるまでカードを引き続ける
     */
    private void drawDealerCard() {
        Hand dealerHand = new Hand(dealerCards);
        dealerCardsNumberSum = dealerHand.isHandPoint();
        while (true) {
            if (dealerCardsNumberSum > 16) {
                break;
            }
            Card newCard = cardStuck.takeCard();
            dealerCards.add(newCard);
            dealerCardsNumberSum = dealerHand.isHandPoint();
            if (dealerCardsNumberSum < 17) {
                continue;
            }
            break;
        }
    }

    /**
     * ディーラーのカードを2枚目は＊に変えて表示する
     */
    private void showDealerCards() {
        System.out.print("ディーラー: ");
        for (int i = 0; i < dealerCards.size(); i++) {
            Card dealerCard = dealerCards.get(i);
            if (i == 0) {
                System.out.print(dealerCard.getDisplayString() + " ");
            }
            System.out.print("*" + " ");
        }
        //バーストした際に合計値を赤に変える
        if (dealerCardsNumberSum > 21) {
            System.out.println("(" + RED + dealerCardsNumberSum + RESET + ")");
        } else {
            System.out.println("(" + dealerCardsNumberSum + ")");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * ディーラーのカードを全て表示する
     */
    private void showAllDealerCards() {
        Hand dealerHand = new Hand(dealerCards);
        System.out.print("ディーラー: ");
        for (int i = 0; i < dealerCards.size(); i++) {
            Card dealerCard = dealerCards.get(i);
            if (dealerHand._isNaturalBlackJack()) {
                System.out.print(CYAN + dealerCard.getDisplayString() + RESET + " ");
            } else {
                System.out.print(dealerCard.getDisplayString() + " ");
            }
        }
        //バーストした際に合計値を赤に変える
        if (dealerCardsNumberSum > 21) {
            System.out.println("(" + RED + dealerCardsNumberSum + RESET + ")");
        } else {
            System.out.println("(" + dealerCardsNumberSum + ")");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * リストから抜いたカードを一回のゲームが終わると
     * リストの中に戻すメソッド
     *
     * @param drawnCards リストから引き抜かれたカード
     */
    private void addDrawnCards(List<Card> drawnCards) {
        for (int i = 0; i < drawnCards.size(); i++) {
            cardStuck.cards.add(drawnCards.get(i));
        }
    }

}