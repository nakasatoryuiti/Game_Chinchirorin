import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.*;
class Sum3 {
    public static void main(String[] args) {
        String[] imagePaths = {"DiceImage/d1.png", "DiceImage/d2.png", "DiceImage/d3.png","DiceImage/d4.png", "DiceImage/d5.png", "DiceImage/d6.png"};
        String[] wavSound = {"DiceSound/DiceSound1.wav", "DiceSound/DiceSound2.wav"}; // MP3ファイルリスト


        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        

        boolean swapped;
        int temp=0;

        int TRprintst=0;
        int TRst=0;
        int Tred=0;

        int st=0;
        int parentst=0;
        
        

        int win = 0;
        int lose = 0;

        int maxPlayers = 4; // プレイヤー最大数

        // プレイヤー人数の入力
        System.out.print("人数を決めてください。(最大4人): ");
        int players = scanner.nextInt();
        scanner.nextLine(); // 次の行で名前を入力するために改行を消去

        // プレイヤー人数が不正な場合、再入力を促す
        while (players < 2 || players > maxPlayers) {
            System.out.println("人数が不正です。2から" + maxPlayers + "人の範囲で入力してください。");
            System.out.print("人数を決めてください。(最大" + maxPlayers + "人): ");
            players = scanner.nextInt();
            scanner.nextLine(); // 次の行で名前を入力するために改行を消去
        }

        // プレイヤーの名前を入力するための配列
        String[] playerNames = new String[players];

        // プレイヤー名の入力
        for (int i = 0; i < players; i++) {
            System.out.print("プレイヤー" + (i + 1) + "の名前を入力してください: ");
            playerNames[i] = scanner.nextLine();
        }

        // 入力されたプレイヤー名を表示
        System.out.println("プレイヤー名:");
        for (int i = 0; i < players; i++) {
            System.out.println("プレイヤー" + (i + 1) + ": " + playerNames[i]);
        }
    
        int[] points = new int[players]; // プレイヤーごとのポイントを格納する配列
        for (int i = 0; i < players; i++) {
            points[i] = 5000; // 初期ポイントを5000に設定
        }
outer:
        for (int playersMCount = 0; playersMCount < players; playersMCount++) {
            for (int i = 0; i < players; i++) {
                System.out.println(playerNames[i]+ "の現在のポイント: " + points[i]);

                if(i==players-1){
                    scanner.nextLine(); // Enterキーを待つ

                }
                
               
                if (points[i] <= 0) {
                    System.out.println(playerNames[i]+ "のポイントが0以下になったため、負けとなりゲームを終了します。");
                    scanner.nextLine(); // Enterキーを待つ
                    break outer;
                    
                }
            }
            
            int parentPlayerNumber = playersMCount + 1;
            System.out.println("\n[親はプレイヤー" + playerNames[parentPlayerNumber-1] + "]になりました\n他のプレイヤーは[子]になりました。");

            

            int[] childBets = new int[players - 1];
for (int playersKCount = 0; playersKCount < players - 1; playersKCount++) {
    int childNumber = (parentPlayerNumber + playersKCount + 1) % players;
   if(childNumber==0){
    System.out.println("[子プレイヤー" +playerNames[players-1]+ "]はいくら賭けますか？(現在の[" + points[players-1] + "]ポイント)\n:");
    temp=points[childNumber];
        points[childNumber] = points[players-1];
   }
    else{
        System.out.println("[子プレイヤー" +playerNames[childNumber-1] + "]はいくら賭けますか？(現在の[" + points[childNumber-1] + "]ポイント)\n:");
        temp=points[childNumber];
        points[childNumber] = points[childNumber-1];
        for (int i = 0; i < players; i++) {
        }
    }
    childBets[playersKCount] = scanner.nextInt();

    while (childBets[playersKCount] < 1 || childBets[playersKCount] > points[childNumber]) {
        System.out.println("賭け金が不正です。1ポイントから" + points[childNumber] + "ポイントの範囲で入力してください。");
        System.out.println("子はいくら賭けますか？(現在の[" + points[childNumber] + "]ポイント)\n:");
        childBets[playersKCount] = scanner.nextInt();
    }
    points[childNumber] = temp;
    
    
}




            System.out.println("Enterを押すとサイコロを投げます...");
            scanner.nextLine(); // 余分な入力を読み飛ばす
            

            for (int NextRound = 0; NextRound < players; NextRound++) {
                
                if(NextRound==0){
                    win=0;  lose=0;
                System.out.println("[親プレイヤー" + (playerNames[parentPlayerNumber-1]) +"]の番です。");
                    
            }
                else if(parentPlayerNumber==players){
                    parentPlayerNumber=1;
                    System.out.println("[子プレイヤー" + (playerNames[parentPlayerNumber-1]) +"]の番です。");
                }

                   

                        else{ 
                    parentPlayerNumber++;
                    System.out.println("[子プレイヤー" + (playerNames[parentPlayerNumber-1]) +"]の番です。");
                }
                    
                for (int again = 0; again < 3; again++) {
                    if (win==1) {
                        
                        break;
                    }else if(lose==1) {
                        
                        break;
                        }}

                for (int again = 0; again < 3; again++) {
                    if (win == 1 || lose == 1) {
                        break;
                    }

                    scanner.nextLine(); // Enterキーを待つ

                    int randomSoundIndex = rand.nextInt(wavSound.length);
                    String selectedWav = wavSound[randomSoundIndex];

                    try {
                        File audioFile = new File(selectedWav);
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.start(); // 再生開始
            
            
                        // 再生が終わるまで待機
                        long duration = clip.getMicrosecondLength() / 1000; // ミリ秒単位
                        String[] messages = {"待ってね.", "待ってね..", "待ってね..."};
    
    long interval = 300; // 0.5秒ごとにメッセージ更新
    long elapsedTime = 0;
    int messageIndex = 0;

    while (elapsedTime < duration) {
        System.out.print(messages[messageIndex] + "\n"); // 同じ行に表示
        messageIndex = (messageIndex + 1) % messages.length;
        Thread.sleep(interval);
        elapsedTime += interval;
    }
            
                        clip.close(); // 終了処理
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    int d1 = rand.nextInt(6) + 1;
                    int d2 = rand.nextInt(6) + 1;
                    int d3 = rand.nextInt(6) + 1;
                    
                    
                    

                    

                    
                    int sum = d1 + d2 + d3;
                    {
                        
                        
                    
                        do {
                            swapped = false;
                            if (d1 > d2) {
                                temp = d1;
                                d1 = d2;
                                d2 = temp;
                                swapped = true;
                            }
                            if (d1 > d3) {
                                temp = d1;
                                d1 = d3;
                                d3 = temp;
                                swapped = true;
                            }
                            if (d2 > d3) {
                                temp = d2;
                                d2 = d3;
                                d3 = temp;
                                swapped = true;
                            }
                        } while (swapped);
                    }{
                        ImageIcon icon1 = new ImageIcon(imagePaths[d1-1]);
                        ImageIcon icon2 = new ImageIcon(imagePaths[d2-1]);
                        ImageIcon icon3 = new ImageIcon(imagePaths[d3-1]);

        // 画像を小さくリサイズ（例えば、幅300px、高さ300pxに設定）
        int newWidth = 300; // 幅を300pxに設定
        int newHeight = 300; // 高さを300pxに設定

        // リサイズ後の画像を作成
        Image image1 = icon1.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image image2 = icon2.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image image3 = icon3.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // 新しいImageIconを作成
        ImageIcon resizedIcon1 = new ImageIcon(image1);
        ImageIcon resizedIcon2 = new ImageIcon(image2);
        ImageIcon resizedIcon3 = new ImageIcon(image3);

        // JLabelにリサイズされたImageIconを設定
        JLabel label1 = new JLabel(resizedIcon1);
        JLabel label2 = new JLabel(resizedIcon2);
        JLabel label3 = new JLabel(resizedIcon3);

        // GridLayoutを使用して画像を並べる
        // グリッドレイアウトの行数、列数を設定
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 10)); // 1行3列、横方向の間隔20px、縦方向の間隔10px
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        // JFrameを作成して、JPanelを追加
        JFrame frame = new JFrame("Image Display Example");
        frame.add(panel);

        // JFrameの設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 400); // フレームのサイズを大きく設定
        frame.setVisible(true);
                   }
                    

                    System.out.println("\n出た目は[" + d1 + ", " + d2 + ", " + d3 + "]ですね。");
                    int change=parentPlayerNumber-1;
                    
                    if (sum == 3) {
                        System.out.println(d1 + "のゾロ目なので[5倍即勝ち]ですね！\n------");
                        scanner.nextLine(); // Enterキーを待つ
                        if (NextRound == 0) {
                            if (parentPlayerNumber == players) {
                                parentPlayerNumber = players ;
                            }
                            
                            

                            for (int i = 0; i < players - 1; i++) {
                                change++;
                                if(change==players){
                                    change=0;

                                }
                                points[parentPlayerNumber-1] += childBets[i] * 5;
                                points[change]-=childBets[i]*5;
                            }
                            NextRound=players;
                            win++;
                            break;
                        }
                         else {
                            change-=NextRound;
                            
                            if (change <= -1) {
                                change+=players;
                            }
                            
                            
                            for (int j = 0; j < players; j++) {
                                System.out.println("プレイヤー" + (j + 1) + "の現在のポイント: " + points[j]+"\n");
                            }
                            points[change] -= childBets[NextRound-1] * 5;
                            points[parentPlayerNumber-1] += childBets[NextRound-1] * 5;
                            for (int i = 0; i < players; i++) {
                            }
                            break;
                        }
                    
                     
                       
                        
                                
                        } else if (d1 == 4 && d2 == 5 && d3 == 6) {

                        System.out.println("四五六なので[2倍即勝ち]ですね！\n----------------------------------------\n");
                        scanner.nextLine(); // Enterキーを待つ
                        if (NextRound == 0) {
                            if (parentPlayerNumber == players) {
                                parentPlayerNumber = players ;
                            }
                            
                            

                            for (int i = 0; i < players - 1; i++) {
                                change++;
                                if(change==players){
                                    change=0;

                                }
                                System.out.println("[親プレイヤー" + (childBets[i]) + "]番"+(change)+"です。");
                                points[parentPlayerNumber-1] += childBets[i] * 2;
                                points[change]-=childBets[i]*2;
                            }
                            win++;
                            break;
                        }
                         else {
                            change-=NextRound;
                            
                            if (change <= -1) {
                                change+=players;
                            }
                            
                            
                            points[change] -= childBets[NextRound-1] * 2;
                            points[parentPlayerNumber-1] += childBets[NextRound-1] * 2;
                            
                            break;
                        }} if (d1 == d2 && d2 == d3) {
                            System.out.println(d1 + "のゾロ目なので[勝ちで3倍]で勝負!。\n---------------------------------------------");
                            
                            Tred=1;


                    } if (d1 == d2 || d1 == d3 || d2 == d3) {
                       
                       
                        if(Tred==1){
                            TRst=d1;
                            Tred=0;
                            if(NextRound==0){
                                TRprintst=d1;
                                TRst=0;
                            }
                        }

                        if(d1 == d2){
                        System.out.println("[役名"+d3+"]で勝負");
                        st=d3;
                        
                    
                }
                       
                        else if(d1 == d3){
                            System.out.println("[役名"+d2+"]で勝負");
                        st=d2;
                    
                }
                        else if(d2 == d3){
                            System.out.println("[役名"+d1+"]で勝負");
                        st=d1;
                    }
                    scanner.nextLine(); // Enterキーを待つ
                    if(NextRound==0){
                        
                        parentst=st;
                        break;
                    }
                    else{
                        System.out.println("親役名："+parentst+"\nvs\n子の役名："+st);
                        scanner.nextLine(); // Enterキーを待つ
                        if(parentst==st){
                            System.out.println("引き分けです。");
                            break;
                        } 
                    
                        else if(TRprintst>TRst&&TRprintst>st){
                            Tred=1;
                            System.out.println("親プレイヤーの勝ちです。");
                            change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                        
                            points[parentPlayerNumber-1]-=childBets[NextRound-1]*3;                    
                            points[change]+=childBets[NextRound-1]*3;
                            if(players-1==NextRound){
                                TRprintst=0;
                            }
                            
                            

                            break;
                        
                    }
                        else if(parentst<TRst&&TRprintst<TRst){
                            Tred=1;
                            System.out.println("子プレイヤー["+parentPlayerNumber+"]の勝ちです。");
                            change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            points[parentPlayerNumber-1]+=childBets[NextRound-1]*3;                    
                            points[change]-=childBets[NextRound-1]*3;
                            TRst=0;
                            break;
                            
                        }
                        else if(parentst<st){
                            System.out.println("子プレイヤーの勝ちです。");
                            change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            
                            points[parentPlayerNumber-1]+=childBets[NextRound-1];                    
                            points[change]-=childBets[NextRound-1];
                            
                            
                            

                            break;
                        }
                        else if(parentst>st){
                            System.out.println("親プレイヤーの勝ちです。");
                            Tred=0;
                            change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            for (int i = 0; i < players; i++) {
                            }
                            points[parentPlayerNumber-1]-=childBets[NextRound-1];                    
                            points[change]+=childBets[NextRound-1];
                            for (int i = 0; i < players; i++) {
                            }
                            break;
                    }
                }
                            
                    }
                     else if (d1==1&&d2==2&&d3==3) {
                        System.out.println("一二三なので[即負け]ですね。\n--------------------------------------\n");
                        scanner.nextLine(); // Enterキーを待つ
                        
                         if (NextRound == 0) {
                            if (parentPlayerNumber == players) {
                                parentPlayerNumber = players ;
                            }
                            
                            

                            for (int i = 0; i < players - 1; i++) {
                                change++;
                                if(change==players){
                                    change=0;

                                }
                                System.out.println("[親プレイヤー" + (childBets[i]) + "]番"+(change)+"です。");
                                points[parentPlayerNumber-1] -= childBets[i];
                                points[change]+=childBets[i];
                            }
                            lose++;
                            break;
                        }
                         else {
                            if(TRprintst!=0){
                                change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            System.out.println("[親プレイヤー" + (childBets[NextRound-1]) + "]番"+(change)+"です。");
                            points[parentPlayerNumber-1]-=childBets[NextRound-1]*3;                    
                            points[change]+=childBets[NextRound-1]*3;
                            if(NextRound==players){
                                TRprintst=0;

                            }

                            break;

                            }else{
                            change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            System.out.println("[親プレイヤー" + (childBets[NextRound-1]) + "]番"+(change)+"です。");
                            points[parentPlayerNumber-1]-=childBets[NextRound-1];                    
                            points[change]+=childBets[NextRound-1];

                            break;
                        }
                        
                        }
                    } else if (again == 2) { // 目なしの場合の処理
                        System.out.println("目なしなので[即負け]ですね。\n--------------------------------------\n");
                        scanner.nextLine(); // Enterキーを待つ
                        if (NextRound == 0) {
                            if (parentPlayerNumber == players) {
                                parentPlayerNumber = players ;
                            }
                            
                            

                            for (int i = 0; i < players - 1; i++) {
                                change++;
                                if(change==players){
                                    change=0;

                                }
                                System.out.println("[親プレイヤー" + (childBets[i]) + "]番"+(change)+"です。");
                                points[parentPlayerNumber-1] -= childBets[i];
                                points[change]+=childBets[i];
                            }
                            lose++;
                            break;
                        }
                         else {
                            if(TRprintst!=0){
                                change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            System.out.println("[親プレイヤー" + (childBets[NextRound-1]) + "]番"+(change)+"です。");
                            points[parentPlayerNumber-1]-=childBets[NextRound-1]*3;                    
                            points[change]+=childBets[NextRound-1]*3;
                            if(NextRound==players){
                                TRprintst=0;

                            }

                            break;

                            }else{
                            change-=NextRound;
                            if (change <= -1) {
                                change+=players;
                            }
                            points[parentPlayerNumber-1]-=childBets[NextRound-1];                    
                            points[change]+=childBets[NextRound-1];

                            break;
                        }
                        
                        }
                    }
                }
            }
        }

        
        
        // ポイントの降順でソートするためのインデックス配列を作成
Integer[] indices = new Integer[players];
for (int i = 0; i < players; i++) {
    indices[i] = i;
}

// ポイントの降順でソート
Arrays.sort(indices, (a, b) -> points[b] - points[a]);

System.out.println("---最終ポイント---");
for (int rank = 0; rank < players; rank++) {
    int index = indices[rank];
    System.out.println((rank + 1) + "位: プレイヤー[" + playerNames[index] + "] - " + points[index] + "ポイント");
}


        scanner.close(); // Scannerを閉じる
    }
}