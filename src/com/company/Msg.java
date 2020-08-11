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
        System.out.println();
        System.out.println("カードを配ります。");
        System.out.println();
    }

    public static void showDrawCardMsg() {
        System.out.println("カードを引かれる場合はYesを入力してください");
        System.out.println("カードの変更がない場合はYes以外の文字を入力するか何も入力せずエンターキーを押してください。");
        System.out.println();
    }

    /**
     * ゲームを続けますか？
     * 続ける場合はYesを入力するか、何も入力せずエンターキーを押してください。
     * 辞められる場合はYes以外の文字を入力してください
     */
    public static void showContinueMsg() {
        System.out.println();
        System.out.println("ゲームを続けますか？");
        System.out.println("続ける場合はYesを入力するか、何も入力せずエンターキーを押してください。");
        System.out.println("辞められる場合はYes以外の文字を入力してください");
        System.out.println();
    }

}
