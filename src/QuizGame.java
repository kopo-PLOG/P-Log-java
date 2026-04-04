import java.util.Scanner;

public class QuizGame {

    static class Question {
        String q;
        String[] options;
        int ans; // 1 ~ 4번

        public Question(String q, String[] options, int ans) {
            this.q = q;
            this.options = options;
            this.ans = ans;
        }
    }

    public void printPicoQuestion() {
        System.out.println("   ( •ө•)?  [으음... 정답이 뭘까?]");
        System.out.println("   (   > ) ");
        System.out.println("    \"  \"   ");
    }

    public void printPicoCorrect() {
        System.out.println("   ( ^ө^)b  [정답입니다!!]");
        System.out.println("   (   > ) ");
        System.out.println("    \"  \"   ");
    }

    public void printPicoWrong() {
        System.out.println("   ( TөT )  [틀렸어요...]");
        System.out.println("   (   > ) ");
        System.out.println("    \"  \"   ");
    }

    public void printPicoCheer() {
        System.out.println("  \\( >ө< )/  [우와아아 짱이다!!]");
        System.out.println("   (   > ) ");
        System.out.println("    \"  \"   ");
    }

    public void printPicoAwkward() {
        System.out.println("   ( -ө- )💦 [머쓱... 더 공부해야겠다]");
        System.out.println("   (   > ) ");
        System.out.println("    \"  \"   ");
    }

    public int play(Scanner sc) {
        Question[] easy = {
                new Question("문제 1: 1 + 1 은?", new String[]{"1", "2", "3", "4"}, 2),
                new Question("문제 2: 강아지가 내는 소리는?", new String[]{"야옹", "멍멍", "짹짹", "음매"}, 2),
                new Question("문제 3: 바나나의 기본 색깔은?", new String[]{"빨강", "파랑", "노랑", "검정"}, 3),
                new Question("문제 4: 대한민국의 수도는?", new String[]{"서울", "부산", "인천", "대전"}, 1),
                new Question("문제 5: 여름에 주로 먹는 큰 과일은?", new String[]{"귤", "수박", "딸기", "사과"}, 2),
                new Question("문제 6: 밤하늘에 없는 것은?", new String[]{"달", "해", "구름", "별"}, 2),
                new Question("문제 7: 얼음이 녹으면 무엇이 되나요?", new String[]{"돌", "흙", "불", "물"}, 4),
                new Question("문제 8: 다음 중 날 수 있는 동물은?", new String[]{"개", "고양이", "새", "거북이"}, 3),
                new Question("문제 9: 병원에서 일하는 사람은?", new String[]{"의사", "교사", "경찰", "소방관"}, 1),
                new Question("문제 10: 한 손의 손가락은 몇 개인가요?", new String[]{"3개", "4개", "5개", "6개"}, 3)
        };

        Question[] medium = {
                new Question("문제 1: 광명융합기술교육원의 위치는?", new String[]{"수원", "인천", "광주", "광명"}, 4),
                new Question("문제 2: 웹 페이지의 뼈대를 구성하는 언어는?", new String[]{"CSS", "HTML", "JS", "JAVA"}, 2),
                new Question("문제 3: 빛의 삼원색이 아닌 것은?", new String[]{"빨강", "노랑", "초록", "파랑"}, 2),
                new Question("문제 4: 대한민국의 국화(나라꽃)는?", new String[]{"벚꽃", "장미", "무궁화", "들국화"}, 3),
                new Question("문제 5: 1년은 대략 몇 주인가?", new String[]{"48주", "50주", "52주", "54주"}, 3),
                new Question("문제 6: 태양계에서 가장 큰 행성은?", new String[]{"지구", "화성", "목성", "토성"}, 3),
                new Question("문제 7: 물의 화학식은?", new String[]{"H2O", "CO2", "O2", "NaCl"}, 1),
                new Question("문제 8: 한글을 창제한 왕은?", new String[]{"태조", "세종대왕", "영조", "정조"}, 2),
                new Question("문제 9: 한반도에서 가장 높은 산은?", new String[]{"지리산", "한라산", "백두산", "설악산"}, 3),
                new Question("문제 10: 10 + 15 * 2 의 값은?", new String[]{"40", "50", "25", "35"}, 1)
        };

        Question[] hard = {
                new Question("문제 1: HTML의 줄바꿈 태그는?", new String[]{"br", "hr", "p", "div"}, 1),
                new Question("문제 2: 파이썬의 함수 선언 키워드는?", new String[]{"var", "let", "def", "fun"}, 3),
                new Question("문제 3: OSI 1계층의 이름은?", new String[]{"물리", "링크", "망", "전송"}, 1),
                new Question("문제 4: 브라우저 탭 종료 시 초기화되는 것은?", new String[]{"로컬", "세션", "쿠키", "캐시"}, 2),
                new Question("문제 5: NoSQL 데이터베이스인 것은?", new String[]{"오라클", "몽고DB", "마리아", "레디스"}, 2),
                new Question("문제 6: 2진수 1010을 10진수로 변환하면?", new String[]{"8", "10", "12", "14"}, 2),
                new Question("문제 7: HTTP 상태 코드 'Not Found'는?", new String[]{"200", "403", "404", "500"}, 3),
                new Question("문제 8: 객체 생성을 담당하는 디자인 패턴은?", new String[]{"싱글톤", "옵저버", "팩토리", "전략"}, 3),
                new Question("문제 9: 리눅스에서 현재 경로를 확인하는 명령어는?", new String[]{"ls", "cd", "pwd", "rm"}, 3),
                new Question("문제 10: 자바스크립트의 약자는?", new String[]{"JS", "JC", "JB", "JA"}, 1)
        };

        System.out.println("\n========== 🧠 퀴즈 게임 🧠 ==========");
        System.out.println("난이도를 선택해주세요.");
        System.out.println("1. 쉬움");
        System.out.println("2. 조금 어려움");
        System.out.println("3. 어려움");
        System.out.println("4. 뒤로가기");
        System.out.print(">> 선택 (번호 입력): ");

        int diffChoice = sc.nextInt();
        Question[] currentQuestions = null;

        switch (diffChoice) {
            case 1: currentQuestions = easy; break;
            case 2: currentQuestions = medium; break;
            case 3: currentQuestions = hard; break;
            case 4: return 0; // 뒤로가기 시 레벨 변화 없음
            default:
                System.out.println("잘못된 선택입니다. 퀴즈를 종료합니다.");
                return 0;
        }

        int score = 0;

        for (int i = 0; i < currentQuestions.length; i++) {
            System.out.println("\n----------------------------------");
            printPicoQuestion();
            System.out.println("\n" + currentQuestions[i].q);
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + "번: " + currentQuestions[i].options[j]);
            }
            System.out.print(">> 정답은 몇 번일까요? : ");

            int answer = sc.nextInt();

            if (answer == currentQuestions[i].ans) {
                System.out.println();
                printPicoCorrect();
                score++;
            } else {
                System.out.println();
                printPicoWrong();
            }

            // 잠시 대기
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n========== 📊 퀴즈 결과 📊 ==========");
        System.out.println("10문제 중 " + score + "문제 정답!");

        if (score >= 7) {
            printPicoCheer();
            System.out.println("레벨이 10 올랐습니다!");
            return 10; // 레벨 10 증가
        } else {
            printPicoAwkward();
            System.out.println("레벨이 5 떨어졌습니다...");
            return -5; // 레벨 5 감소
        }
    }
}