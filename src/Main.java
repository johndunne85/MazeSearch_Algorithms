import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.*;
class Coordinate{
    public int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        Coordinate that = (Coordinate)o;
        if(x != that.x || y != that.y){
            return false;
        }
        return true;
    }

    public int hashCode(){
        return Objects.hash(x, y);
    }
}


public class Main {
    public static void main(String args[]) {

        List<List<Character>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList('_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'));
        matrix.add(Arrays.asList('|','S',' ','|',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ',' ',' ','-','-','|',' ','|',' ','-','-','-',' ',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|',' ',' ',' ','|','-','-',' ',' ',' ',' ',' ',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|','-','-','-','-','-','-',' ','|',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ',' ',' ','|',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ',' ',' ',' ','|',' ',' ','|',' ','|',' ',' ',' ',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|','-','-','-','-',' ','|',' ','-','|','-','|','-',' ','-','-','-','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ',' ',' ','|',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|',' ',' ','|','-','-','-',' ','|',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ',' ',' ','|',' ',' ','|',' ','|'));
        matrix.add(Arrays.asList('|',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ',' ',' ','|',' ',' ','|','X','|'));
        matrix.add(Arrays.asList('-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'));


        for(int i=0;i< matrix.size();i++){
            for(int j=0; j < matrix.get(0).size();j++){
                System.out.print(matrix.get(i).get(j));
            }
            System.out.println();
        }

        searchMaze(matrix, new Coordinate(1,2), new Coordinate(6,1));
        System.out.println();

        for(int i=0;i< matrix.size();i++){
            for(int j=0; j < matrix.get(0).size();j++){
                System.out.print(matrix.get(i).get(j));
            }
            System.out.println();
        }

    }
    public static List<Coordinate> searchMaze(List<List<Character>> maze, Coordinate s, Coordinate e){
        List<Coordinate> path = new ArrayList<>();
        if(!searchMazeHelper(maze, s, e, path)){
            return Collections.EMPTY_LIST;
        }
        return path;


    }
    private static boolean searchMazeHelper(List<List<Character>> maze, Coordinate cur, Coordinate e, List<Coordinate> path){
        if(!isFeasible(cur, maze)){
            return false;
        }
        path.add(cur);
        maze.get(cur.x).set(cur.y, 'o');
        if(cur.equals(e)){
            return true;
        }

        final int[][] SHIFT = {{0,1}, {1,0},{0,-1}, {-1,0}};
        for(int[] s : SHIFT){
            if(searchMazeHelper(maze, new Coordinate(cur.x + s[0], cur.y + s[1]), e, path)){
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private static boolean isFeasible(Coordinate cur, List<List<Character>> maze){
        return cur.x >= 0 && cur.x < maze.size() && cur.y >= 0
                && cur.y < maze.get(cur.x).size()
                && maze.get(cur.x).get(cur.y)== ' ';
    }


}
