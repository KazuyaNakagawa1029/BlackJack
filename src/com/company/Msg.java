package com.company;

public class Msg {
    public static void showOpeningMsg() {
        System.out.println("*********************************");
        System.out.println("********  ブラックジャック  *******");
        System.out.println("*********************************");
        System.out.println();
    }

    public static void showBeginGameMsg() {
        System.out.println("ゲーム開始！");
        System.out.println("カードを配ります。");
        System.out.println();
    }

    public static void showDrawCardMsg() {
        System.out.println("カードを引きますか？");
        System.out.println("引く場合はYesを入力、引かない場合はNoを入力");
        System.out.println();
    }


    public static void showContinueMsg() {
        System.out.println();
        System.out.println("ゲームを続けますか？");
        System.out.println("続ける場合はYesを入力、続けない場合はNoを入力");
        System.out.println();
    }

}
