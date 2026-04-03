import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MemorizationGame {
    static final int TOTAL_ROUNDS = 5;
    static final int SEQUENCE_LENGTH = 7;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static int level = 120;
    static boolean isTimeout = false;

    public static void main(String[] args) throws Exception {
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
                    // 콘솔 특성상 엔터를 함 눌러야 최종 종료처리가 됨
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
                level -= 5;
                System.out.println("현재 레벨: " + level);
                return;
            }

            String userInput = input.replaceAll("\\s+", "");

            if (checkAnswer(answer, userInput)) {
                System.out.println("✨ 정답! ✨");
            } else {
                level -= 5;
                System.out.println("\n( •ө•)💢 오답! 게임을 종료합니다.");
                System.out.println("현재 레벨: " + level);
                return;
            }
        }

        level += 20;
        System.out.println("🎉 모든 라운드 성공! 🎉");
        System.out.println("현재 레벨: " + level);
    }

    static String[] generateSequence(int length) {
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextBoolean() ? "<" : ">";
        }
        return arr;
    }

    static void printSequence(String[] arr) {

        // 이게 뭐냐면 말풍선...^+^
        // 1. 방향 문자열 만들기
        String line = "";
        for (String s : arr) {
            line += s + " ";
        }

        // 2. 좌우 여백 포함
        line = "  " + line;

        // 3. 말풍선 길이 생성
        int length = line.length();

        System.out.print("┌");
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println("┐");

        // 4. 내용 출력
        System.out.println(line);

        // 5. 아래 말풍선
        System.out.print("└");
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
    }

    static boolean checkAnswer(String[] answer, String userInput) {
        if (answer.length != userInput.length()) return false;

        for (int i = 0; i < answer.length; i++) {
            if (!answer[i].equals(String.valueOf(userInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    static void clearConsoleLike() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}