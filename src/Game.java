public class Game {
    private Board board;
    private boolean isPlayer1Active;
    public boolean autoPlay;
    public Game(boolean autoPlay){
        board= new Board();
        isPlayer1Active=true;
        this.autoPlay= autoPlay;
    }

    public Board getBoard() {
        return board;
    }

    public boolean choosePitForMove(int pitNo){

        if (pitNo < 1 || pitNo > 6) {
            System.out.println("Invalid Pit selected...\nSelect Again!");
            return false;
        }
        int offset;
        if(isPlayer1Active) {
            offset=6;
        }
        else offset=-1;
        int stones = board.getStonesInPit(pitNo + offset), stNo = stones;
        if (stones == 0) {
            System.out.println("Invalid choice.\n Select a non-empty pit again!");
            return false;
        }
        board.setStonesInPit(pitNo + offset, 0);
        for (int i = pitNo + offset+1; stNo > 0; i++) {
            if (i % 14 != 12-offset) {
                board.addAStone(i % 14);
                stNo--;
            }
        }
        if(7 - pitNo != stones) {
            isPlayer1Active = !isPlayer1Active;
            if((pitNo+offset+stones)%14>offset && (pitNo+offset+stones)%14<=offset+6 && board.getStonesInPit((pitNo+offset+stones)%14)==1 && board.getStonesInPit(12-((pitNo+offset+stones)%14))!=0 ){
                board.setStonesInPit((pitNo+offset+stones)%14,0);
                board.setStonesInPit(7+offset,1+board.getStonesInPit(12-((pitNo+offset+stones)%14)));
                board.setStonesInPit(12-((pitNo+offset+stones)%14),0);
            }
        }

//        else{
//            if (pitNo < 1 || pitNo > 6) {
//                System.out.println("Invalid Pit selected...\nSelect Again!");
//                return false;
//            }
//            int stones = board.getStonesInPit(pitNo-1 ), stNo = stones;
//            if (stones == 0) {
//                System.out.println("Invalid choice.\n Select a non-empty pit again!");
//                return false;
//            }
//            board.setStonesInPit(pitNo -1, 0);
//            for (int i = pitNo; stNo > 0; i++) {
//                if (i % 14 != 13) {
//                    board.addAStone(i % 14);
//                    stNo--;
//                }
//            }
//            if(7 - pitNo == stones)
//                isPlayer1Active = false;
//            else {
//                isPlayer1Active = true;
//                if((pitNo-1+stones)%14<6 && board.getStonesInPit((pitNo-1+stones)%14)==1 && board.getStonesInPit(12-((pitNo-1+stones)%14))!=0 ){
//                    board.setStonesInPit((pitNo-1+stones)%14,0);
//                    board.setStonesInPit(6,1+ board.getStonesInPit(12-((pitNo-1+stones)%14)));
//                    board.setStonesInPit(12-((pitNo-1+stones)%14),0);
//                }
//            }
//
//        }
        return true;
    }

}
