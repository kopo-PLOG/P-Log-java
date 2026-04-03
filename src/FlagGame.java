import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class FlagGame {
    final int totalRounds = 10;
    final int levelUp = 10;
    final int levelDown = 5;

    Random random = new Random();

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        System.out.println("카운트가 끝나면 바로 입력하세요!");
        System.out.println("==============================");

        for (int round = 0; round < totalRounds; round++) {
            int idx = order[round];
            String question = problems[idx][0];
            String answer = problems[idx][1];

            System.out.println("\n[" + (round + 1) + "/" + totalRounds + "]");
            System.out.println("문제: " + question);

            try {
                System.out.println("3...");
                Thread.sleep(700);
                System.out.println("2...");
                Thread.sleep(700);
                System.out.println("1...");
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("입력 (b / w): ");

            try {
                String user = br.readLine().trim().toLowerCase();

                if (user.equals(answer)) {
                    System.out.println("정답!");
                    score++;
                } else {
                    System.out.println("오답!");
                }
            } catch (Exception e) {
                System.out.println("입력 오류!");
            }

            System.out.println("현재 점수: " + score);
        }

        System.out.println("\n==============================");
        System.out.println(totalRounds + "문제 중 " + score + "문제 정답!");

        if (score >= 7) {
            System.out.println("삐코가 즐거워합니다! 게이지 +" + levelUp);
            return levelUp;
        } else {
            System.out.println("삐코가 아쉬워합니다... 게이지 -" + levelDown);
            return -levelDown;
        }
    }

    public static void main(String[] args) {
        FlagGame game = new FlagGame();
        int result = game.start();
        System.out.println("결과값: " + result);
    }
}