import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MemorizationGame {
    final int TOTAL_ROUNDS = 5;
    final int SEQUENCE_LENGTH = 7;

    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    // level 변수 삭제! (Pico에서 관리할 거니까)
    boolean isTimeout = false;

    // 반환 타입을 void에서 int로 변경 (획득/감소할 레벨 값을 반환)
    public int start() {
        try {
            for (int round = 1; round <= TOTAL_ROUNDS; round++) {
                String[] answer = generateSequence(SEQUENCE_LENGTH);

                System.out.println("🐥🐥 [ " + round + "라운드 ] 🐥🐥");
                System.out.println("");
                System.out.println("방향을 외우세요!");
                printSequence(answer);
                System.out.println("");
                System.out.println("   ( •ө•)  ");
                System.out.println("   (   > ) ");
                System.out.println("    \"  \"   ");

                Thread.sleep(5000);

                clearConsoleLike();

                System.out.println("🐥🐥 이제 입력하세요. 예: < > < < > > < 🐥🐥");
                System.out.println("🐥🐥 띄어쓰기는 없어도 된답니다 🐥🐥");
                System.out.println("🐥🐥 10초 안에 입력해야 한답니다 🐥🐥");
                System.out.print("입력: ");

                isTimeout = false;

                Timer timer = new Timer();
                TimerTask timeoutTask = new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("\n( •ө•)💢 시간 초과! 게임을 종료합니다.");
                        isTimeout = true;
                        System.out.print("엔터를 누르면 종료됩니다: ");
                    }
                };

                timer.schedule(timeoutTask, 10000);

                String input = sc.nextLine();

                timeoutTask.cancel();
                timer.cancel();

                if (isTimeout) {
                    return -5; // 시간 초과 시 5레벨 감소 반환
                }

                String userInput = input.replaceAll("\\s+", "");

                if (checkAnswer(answer, userInput)) {
                    System.out.println("✨ 정답! ✨\n");
                } else {
                    System.out.println("\n( •ө•)💢 오답! 게임을 종료합니다.");
                    return -5; // 오답 시 5레벨 감소 반환
                }
            }

            System.out.println("🎉 모든 라운드 성공! 🎉");
            return 20; // 5라운드 모두 성공 시 20레벨 획득 반환

        } catch (InterruptedException e) {
            System.out.println("게임 진행 중 문제가 발생했습니다.");
            return 0; // 에러 시 레벨 변동 없음
        }
    }

    String[] generateSequence(int length) {
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextBoolean() ? "<" : ">";
        }
        return arr;
    }

    void printSequence(String[] arr) {
        String line = "";
        for (String s : arr) {
            line += s + " ";
        }
        line = "  " + line;
        int length = line.length();

        System.out.print("┌");
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println("┐");

        System.out.println(line);

        System.out.print("└");
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
    }

    boolean checkAnswer(String[] answer, String userInput) {
        if (answer.length != userInput.length()) return false;

        for (int i = 0; i < answer.length; i++) {
            if (!answer[i].equals(String.valueOf(userInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    void clearConsoleLike() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}