public class Game {
    private Board board;
    public Game(){
        board= new Board();
        board.setPlayer1Active(true);
    }

    public Board getBoard() {
        return board;
    }

    public boolean isPlayer1Active() {
        return board.isPlayer1Active();
    }
    public boolean isAllEmpty( boolean isPlayer1Active){
        if(board.isMySideEmpty(isPlayer1Active)){
            for(int i=1;i<=6;i++){
                board.setStonesInPitByPlayer(7, board.getStonesInPitByPlayer(7,!isPlayer1Active)+board.getStonesInPitByPlayer(i,!isPlayer1Active),!isPlayer1Active );
                board.setStonesInPitByPlayer(i,0,!isPlayer1Active);
            }
            return true;
        }
        return false;
    }
    public int minimax(Board board , boolean isPlayer1Active, int depth, int maxDepth,boolean isMax, int heuristicID, int alpha, int beta){
        if(depth==0)
            Heuristic.setHeuristicID(heuristicID);
        if(depth== maxDepth){
            return Heuristic.getHeuristicVal(board);
        }
        if(isAllEmpty(isPlayer1Active)){
            return Heuristic.getHeuristicVal(board);
        }
        board.setChildren(isPlayer1Active);
        Board[] children= board.getChildren();
        int  maxID=-1;
        int  minID=-1;
        int offset=0;
        if(isPlayer1Active())offset=7;
        for(int i=0;i<6;i++){
            if(board.getStonesInPit(i+offset)!=0){
                int val= minimax(children[i],!isPlayer1Active,depth+1,maxDepth,!isMax,heuristicID,alpha, beta);
                if(isMax && val>alpha){
                    alpha=val;
                    maxID=i;

                }
                else if(!isMax && val<beta){
                    beta=val;
                    minID=i;
                }
                if(alpha>beta){
//                    System.out.println("yasssssssss\n\n\n\n\n");
//                    board.printBoard();
                    if(!isMax) {
                        board.setNextId(minID+1);
                        return beta;
                    }
                    board.setNextId(maxID+1);
                    return alpha;
                }
            }
        }
        if(isMax){
            board.setNextId(maxID+1);
            return alpha;
        }
        else{
            board.setNextId(minID+1);
            return beta;
        }

    }


}
