public class Heuristic {
    public static int heuristicID;
    public static void setHeuristicID(int id){
        heuristicID=id;
    }
    public static int heuristic1(Board board){
        return board.getStonesInPit(6)-board.getStonesInPit(13);
    }
    public static int getHeuristicVal(Board board){
        if(heuristicID==1)return heuristic1(board);
        return 0;
    }


}
