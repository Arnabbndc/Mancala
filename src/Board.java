import java.lang.reflect.Method;

public class Board {
    private int[] pit= {4,4,4,4,4,4,0,4,4,4,4,4,4,0};
    public int getStonesInPit(int id){
        return pit[id];
    }
    public void setStonesInPit(int id, int stones){
        pit[id]=stones;
    }
    public void addAStone(int id){
        pit[id]++;
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

}
