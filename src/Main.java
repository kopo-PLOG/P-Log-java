public class Main {
    public static void main(String[] args) {
        Pico pico = new Pico();

        while(true){
            pico.randomState();

            if(pico.isEvolution) break;
        }
    }
}