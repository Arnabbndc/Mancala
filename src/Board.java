import java.lang.reflect.Method;

public class Board {
    private int[] pit= {4,4,4,4,4,4,0,4,4,4,4,4,4,0};
    private Board[] children;

    private int nextId;
    private boolean isPlayer1Active;
    public int getStonesInPit(int id){
        return pit[id];
    }
    public void setStonesInPit(int id, int stones){
        pit[id]=stones;
    }
    public int getStonesInPitByPlayer(int id, boolean isPlayer1){
        if(!isPlayer1)
            return pit[id-1];
        else return pit[id+6];
    }
    public void setStonesInPitByPlayer(int id, int stones, boolean isPlayer1){
        if(!isPlayer1)
            pit[id-1]=stones;
        else pit[id+6]=stones;
    }
    public boolean isPlayer1Active() {
        return isPlayer1Active;
    }

    public void setPlayer1Active(boolean player1Active) {
        isPlayer1Active = player1Active;
    }

    public void addAStone(int id){
        pit[id]++;
    }
    public void setChildren(boolean isPlayer1Active) {
        children= new Board[6];
        for(int i=0;i<6;i++){
            children[i]= new Board();
            for(int j=0;j<14;j++)
            {
                children[i].setStonesInPit(j,this.pit[j]);
            }
            if(!isPlayer1Active && pit[i]!=0)children[i].choosePitForMove(i+1, isPlayer1Active);
            if(isPlayer1Active && pit[i+7]!=0)children[i].choosePitForMove(i+1, isPlayer1Active);
        }
    }
    boolean isMySideEmpty(boolean isPlayer1Active){
        int cnt=0, offset=0;
        if(isPlayer1Active){
            offset=7;
        }
        for(int i=0;i<6 && cnt==0;i++)
            cnt+=pit[i+offset];
        if(cnt!=0) return false;
        return true;
    }
    public Board[] getChildren() {
        return children;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public int getHeuristicVal(){
        return Heuristic.getHeuristicVal(this);
    }
    public void setHeuristicID(int id){
        Heuristic.setHeuristicID(id);
    }
    public void printBoard(){
        System.out.print("\t\t");
        for(int i=5;i>-1;i--){
            System.out.print(pit[i]+" | ");
        }
        System.out.println("\t\t");
        System.out.print("  "+pit[6]+"\t");
        for(int i=5;i>-1;i--){
            System.out.print("     ");
        }
        System.out.println(pit[13]);
        System.out.print("\t\t");
        for(int i=7;i<13;i++){
            System.out.print(pit[i]+" | ");
        }
        System.out.println("\t\t");
    }
    public boolean choosePitForMove(int pitNo, boolean isPlayer1Active){
//        System.out.println(pitNo+" Inside board");
        int offset;
        if(isPlayer1Active) {
            offset=6;
        }
        else offset=-1;
        int stones = getStonesInPit(pitNo + offset), stNo = stones;
        setStonesInPit(pitNo + offset, 0);
        for (int i = pitNo + offset+1; stNo > 0; i++) {
            if (i % 14 != 12-offset) {
                addAStone(i % 14);
                stNo--;
            }
        }
        if(7 - pitNo != stones) {
            this.isPlayer1Active = !isPlayer1Active;
            if((pitNo+offset+stones)%14>offset && (pitNo+offset+stones)%14<=offset+6 && getStonesInPit((pitNo+offset+stones)%14)==1 && getStonesInPit(12-((pitNo+offset+stones)%14))!=0 ){
                setStonesInPit((pitNo+offset+stones)%14,0);
                setStonesInPit(7+offset,1+getStonesInPit(7+offset)+getStonesInPit(12-((pitNo+offset+stones)%14)));
                setStonesInPit(12-((pitNo+offset+stones)%14),0);
            }
            else{
//                System.out.println("Another move gained....");
            }
        }

//        else{
//            if (pitNo < 1 || pitNo > 6) {
//                System.out.println("Invalid Pit selected...\nSelect Again!");
//                return false;
//            }
//            int stones = getStonesInPit(pitNo-1 ), stNo = stones;
//            if (stones == 0) {
//                System.out.println("Invalid choice.\n Select a non-empty pit again!");
//                return false;
//            }
//            setStonesInPit(pitNo -1, 0);
//            for (int i = pitNo; stNo > 0; i++) {
//                if (i % 14 != 13) {
//                    addAStone(i % 14);
//                    stNo--;
//                }
//            }
//            if(7 - pitNo == stones)
//                isPlayer1Active = false;
//            else {
//                isPlayer1Active = true;
//                if((pitNo-1+stones)%14<6 && getStonesInPit((pitNo-1+stones)%14)==1 && getStonesInPit(12-((pitNo-1+stones)%14))!=0 ){
//                    setStonesInPit((pitNo-1+stones)%14,0);
//                    setStonesInPit(6,1+ getStonesInPit(12-((pitNo-1+stones)%14)));
//                    setStonesInPit(12-((pitNo-1+stones)%14),0);
//                }
//            }
//
//        }
        return true;
    }

}
