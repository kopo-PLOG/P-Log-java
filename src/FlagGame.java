import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class FlagGame {
    final int totalRounds = 10;
    final int levelUp = 10;
    final int levelDown = 5;
    final int timeLimit = 3000;

    Random random = new Random();
    Scanner sc = new Scanner(System.in);
    boolean isTimeout = false;

    String[][] problems = {
            {"청기 들어!", "b"},
            {"백기 들어!", "w"},
            {"청기 들지 말고 백기 들어!", "w"},
            {"백기 들지 말고 청기 들어!", "b"},
            {"청기 들고 백기 들지 마!", "b"},
            {"백기 들고 청기 들지 마!", "w"},
            {"청기 말고 백기 들어!", "w"},
            {"백기 말고 청기 들어!", "b"},
            {"청기는 들지 말고 백기만 들어!", "w"},
            {"백기는 들지 말고 청기만 들어!", "b"}
    };

    public int start() {
        int score = 0;

        int[] order = new int[problems.length];
        for (int i = 0; i < problems.length; i++) {
            order[i] = i;
        }

        for (int i = problems.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = order[i];
            order[i] = order[j];
            order[j] = temp;
        }

        System.out.println("\n==============================");
        System.out.println("청기백기 게임 시작!");
        System.out.println("b = 청기 / w = 백기");
        System.out.println("3초 안에 입력 후 엔터를 누르세요!");
        System.out.println("==============================");

        for (int round = 0; round < totalRounds; round++) {
            int idx = order[round];
            String question = problems[idx][0];
            String answer = problems[idx][1];

            System.out.println("\n[" + (round + 1) + "/" + totalRounds + "]");
            System.out.println("문제: " + question);
            System.out.print("입력 (b / w): ");

            isTimeout = false;

            Timer timer = new Timer();
            TimerTask timeoutTask = new TimerTask() {
                @Override
                public void run() {
                    isTimeout = true;
                    System.out.print("\n⏰ 시간 초과! (아무 문자나 누르고 엔터를 치세요): ");
                }
            };

            // 3초 타이머 시작
            timer.schedule(timeoutTask, timeLimit);

            // 입력 대기 (여기서 스레드가 아닌 Scanner를 씁니다)
            String userInput = sc.next();

            timeoutTask.cancel();
            timer.cancel();

            if (isTimeout) {
                System.out.println("너무 늦었습니다! 오답 처리!");
            } else {
                String user = userInput.trim().toLowerCase();
                if (user.equals(answer)) {
                    System.out.println("( ^ө^)b  정답이에요!!");
                    score++;
                } else {
                    System.out.println("( TөT )  틀렸어요... (정답: " + answer + ")");
                }
            }
            System.out.println("현재 점수: " + score);
        }

        System.out.println("\n==============================");
        System.out.println(totalRounds + "문제 중 " + score + "문제 정답!");

        if (score >= 7) {
            System.out.println("피코가 즐거워합니다! 레벨 +" + levelUp);
            return levelUp;
        } else {
            System.out.println("피코가 아쉬워합니다... 레벨 -" + levelDown);
            return -levelDown;
        }
    }
}