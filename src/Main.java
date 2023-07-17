import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Scanner scanner= new Scanner(System.in);
    private static int getNextMove(Game game, int autoPlay){
        if(game.isPlayer1Active()&& autoPlay!=1)return scanner.nextInt();
        if(game.isPlayer1Active()&& autoPlay==1){
            game.minimax(game.getBoard(),0,12,1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        else game.minimax(game.getBoard(),0,11,1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return game.getBoard().getNextId();
    }

    public static void main(String[] args) {

        System.out.println("Autoplay? Press:\n1. Yes\n2. No");
        int autoPlay = scanner.nextInt()%2;
        Game game = new Game();
        game.getBoard().printBoard();
        int totalMoves=0;
        while(totalMoves<50) {
            totalMoves++;
            boolean flag= game.isPlayer1Active();
            if(game.isPlayer1Active()){
                System.out.println("Player 1....");
            }
            else{
                System.out.println("Player 2.....");
            }
            System.out.println("Give Move...");
            int pit = getNextMove(game, autoPlay);
            System.out.println("Choice: "+pit);
            if (pit < 1 || pit > 6) {
                System.out.println("Invalid Pit selected...\nSelect Again!");
                continue;
            }
            if(game.getBoard().getStonesInPitByPlayer(pit, game.isPlayer1Active())==0){
//                if(game.getBoard().isMySideEmpty(game.isPlayer1Active())) {
//                    game.getBoard().printBoard();
//                    break;
//                }
                System.out.println("Invalid choice.\n Select a non-empty pit again!");
                continue;
            }
            game.getBoard().choosePitForMove(pit, game.isPlayer1Active());
            game.setPlayer1Active(game.getBoard().isPlayer1Active());
            game.getBoard().printBoard();
            if(game.getBoard().getStonesInPit(6)>24 || game.getBoard().getStonesInPit(13)>24) break;
            if(game.isAllEmpty(game.isPlayer1Active()) || game.isAllEmpty(!game.isPlayer1Active()))
            {
                System.out.println("Game finished");
                game.getBoard().printBoard();
                break;
            }
            if(flag==game.isPlayer1Active()){
                System.out.print("Another move gained for : ");
            }
        }

    }
}