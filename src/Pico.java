import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Pico {
    int health = 80;
    int level = 120;

    boolean isTimeout;
    boolean isEvolution = false;

    String[] states = {
            "배가 고파요! (밥주기)",
            "심심해요! (놀아주기)",
            "화장실 급해! (화장실)"
    };

    public void printPico() {
        System.out.println("   ( •ө•)  ");
        System.out.println("   (   > ) ");
        System.out.println("    \"  \"   ");
    }

    public void randomState() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        isTimeout = false;

        System.out.println("\n==================================");
        System.out.println("피코가 평화롭게 혼자 놀고 있습니다~!");
        printPico();

        // 10초 대기
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int randomIndex = random.nextInt(states.length);

        System.out.println("\n앗! 피코의 상태가 변했습니다 : " + states[randomIndex]);
        System.out.println("1번 밥주기");
        System.out.println("2번 놀아주기");
        System.out.println("3번 화장실");
        System.out.print("선택: ");

        Timer timer = new Timer();
        TimerTask angryTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n\n( •ө•)💢 : 너무 늦었어!! 피코가 잔뜩 화가 났습니다!");
                isTimeout = true;
                // 팁: 여기서 health를 깎아도 재밌습니다 (예: health -= 10;)
                System.out.print(">> 다음으로 넘어가려면 아무 숫자나 누르세요: ");
            }
        };

        // 5초 반응 기다림
        timer.schedule(angryTask, 5000);

        int input = sc.nextInt();

        angryTask.cancel();
        timer.cancel();


        if (isTimeout) {
            System.out.println("\n피코는 이미 삐져서 쳐다보지도 않습니다...");
            if(health >= 10) health -= 10;
            System.out.println("현재 체력: " + health);

        } else if (randomIndex == input - 1) {
            System.out.println("\n휴~ 다행입니다. 피코가 다시 기분이 좋아졌습니다!");
            System.out.println("   ( •ө•)♡   ");
            System.out.println("현재 체력: " + health);
        } else {
            System.out.println("\n( •ө•)💢 : 원하는 게 아니잖아!! 피코가 실망했습니다.");
            if(health >= 10) health -= 10;
            System.out.println("현재 체력: " + health);
        }

        evolution();
    }

    public void evolution(){
        if(level >= 120 && health >= 80){
            System.out.println("\n==================================");
            System.out.println("오잉...? 피코의 상태가 이상합니다...!");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("✨ 뾰로롱! ✨");
            System.out.println("🎉 축하합니다! 쪼꼬만 피코가 [멋진 닭]으로 진화했습니다! 🎉");
            System.out.println("피코 : 꼬끼오오오오오-!!!");

            isEvolution = true;
        }
    }

    public void game(Scanner sc){
        System.out.println("\n========== 🎮 놀이 목록 🎮 ==========");
        System.out.println("1. 청기백기");
        System.out.println("2. 암기게임");
        System.out.println("3. 퀴즈");
        System.out.print(">> 어떤 놀이를 할까요? (번호 입력): ");

        int gameChoice = sc.nextInt();

        System.out.println("\n----------------------------------");
        switch (gameChoice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("잘못된 번호입니다. 피코가 어리둥절해 합니다.");
                break;
        }

        System.out.println("현재 레벨: " + level);
    }

}
