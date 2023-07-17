public class Game {
    private Board board;
    private boolean isPlayer1Active;
    public Game(){
        board= new Board();
        board.setPlayer1Active(true);
        isPlayer1Active=true;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isPlayer1Active() {
        return isPlayer1Active;
    }

    public void setPlayer1Active(boolean player1Active) {
        isPlayer1Active = player1Active;
    }

    public boolean isAllEmpty(boolean isPlayer1Active){
        if(board.isMySideEmpty(isPlayer1Active)){
            for(int i=1;i<=6;i++){
                board.setStonesInPitByPlayer(7, board.getStonesInPitByPlayer(7,!isPlayer1Active)+board.getStonesInPitByPlayer(i,!isPlayer1Active),!isPlayer1Active );
                board.setStonesInPitByPlayer(i,0,!isPlayer1Active);
            }
            return true;
        }
        return false;
    }
    public int minimax(Board board , int depth, int maxDepth, int heuristicID, int alpha, int beta){
        boolean isPlayer1Active= board.isPlayer1Active();
        //        System.out.println(isPlayer1Active+" "+alpha+" "+beta);
        if(depth==0)
            Heuristic.setHeuristicID(heuristicID);
        if(depth== maxDepth || board.getStonesInPit(13)>24 || board.getStonesInPit(6)>24){
            return Heuristic.getHeuristicVal(board);
        }
        if(isAllEmpty(isPlayer1Active)){
            return Heuristic.getHeuristicVal(board);
        }
//        board.setChildren(isPlayer1Active);
        int[] copyBoard=  new int[14];
        for(int i=0;i<14;i++)
            copyBoard[i]=board.getStonesInPit(i);
//        Board[] children= board.getChildren();
        int  max= Integer.MIN_VALUE, maxID=-1;
        int  min= Integer.MAX_VALUE,minID=-1;
        int offset=0;
        if(isPlayer1Active)offset=7;
        for(int i=0;i<6;i++){
            if(board.getStonesInPit(i+offset)!=0){
                board.choosePitForMove(i+1, isPlayer1Active);
                int val= minimax(board,depth+1,maxDepth,heuristicID,alpha, beta);
                for(int j=0;j<14;j++){
                    board.setStonesInPit(j,copyBoard[j]);
                    board.setPlayer1Active(isPlayer1Active);
                }
                board.setPlayer1Active(isPlayer1Active());
                if(!isPlayer1Active && val>max){
                    max=val;
                    maxID=i;
                    if(alpha<max)
                        alpha=max;

                }
                else if(isPlayer1Active && val<min){
                    min=val;
                    minID=i;
                    if(beta>min)
                        beta=min;
                }
                if(alpha>=beta){
//                    System.out.println("yasssssssss\n\n\n\n\n");
//                    board.printBoard();
                    if(isPlayer1Active) {
                        board.setNextId(minID+1);
                        return min;
                    }
                    board.setNextId(maxID+1);
                    return max;
                }

            }
        }
        if(!isPlayer1Active){
            board.setNextId(maxID+1);
            return max;
        }
        else{
            board.setNextId(minID+1);
            return min;
        }

    }


}