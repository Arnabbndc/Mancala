public class Heuristic {
    public static int heuristicID;
    public static void setHeuristicID(int id){
        heuristicID=id;
    }
    public static int heuristic1(Board board){
        return (board.getStonesInPit(6)-board.getStonesInPit(13));
    }
    public static int heuristic2(Board board){
        int cnt=0;
        for(int i=1;i<7;i++)
            cnt+=board.getStonesInPitByPlayer(i,false)-board.getStonesInPitByPlayer(i,true);
        return  4*heuristic1(board)+cnt;
    }
    public static int getHeuristicVal(Board board){
        if(heuristicID==1)return heuristic1(board);
        return 0;
    }


}
