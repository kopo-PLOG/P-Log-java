import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FlagGame {
    final int totalRounds = 10;
    final int levelUp = 10;
    final int levelDown = 5;
    final int timeLimit = 3000;

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

    private final BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();

    public int start() {
        int score = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        startInputThread(br);

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
            inputQueue.clear();

            int idx = order[round];
            String question = problems[idx][0];
            String answer = problems[idx][1];

            System.out.println("\n[" + (round + 1) + "/" + totalRounds + "]");
            System.out.println("문제: " + question);
            System.out.print("입력 (b / w): ");

            String userInput = null;

            try {
                userInput = inputQueue.poll(timeLimit, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\n입력 대기 중 오류가 발생했습니다.");
                break;
            }

            if (userInput == null) {
                System.out.println("\n⏰ 시간 초과! 오답!");
            } else {
                String user = userInput.trim().toLowerCase();

                if (user.equals(answer)) {
                    System.out.println("( ^ө^)b  정답이에요!!");
                    score++;
                } else {
                    System.out.println("( TөT )  틀렸어요... (입력: " + user + ", 정답: " + answer + ")");
                }
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

    private void startInputThread(BufferedReader br) {
        Thread inputThread = new Thread(() -> {
            while (true) {
                try {
                    String line = br.readLine();
                    if (line != null) {
                        inputQueue.offer(line);
                    }
                } catch (Exception e) {
                    break;
                }
            }
        });

        inputThread.setDaemon(true);
        inputThread.start();
    }

    public static void main(String[] args) {
        FlagGame game = new FlagGame();
        int result = game.start();
        System.out.println("결과값: " + result);
    }
}