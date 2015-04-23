/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Buser
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
public class Maze{
    private int dimension;
    private Node[][] map;
    private String mapString = "";
    private ArrayList<Node> snodes = new ArrayList<Node>();
    private Random myRandGen;
    public Maze(int x){
        if(x<4 || x>10){
             //return;
        }
        else{
            dimension = x;
            map = new Node[x][x];
            myRandGen = new java.util.Random(0); //seed is 0
            buildMaze(map,x);
            //printMaze(map);
            //solveMazeDFS(snodes);
            //printMaze(map);
            //shortestPath(snodes);
            /*for(int i=0;i<snodes.size();i++){
                snodes.get(i).visited = 0;
                snodes.get(i).timeF = -1;
                snodes.get(i).parent = null;
                snodes.get(i).tag="";
            }
            solveMazeBFS(snodes); 
            printMaze(map);
            shortestPath(snodes);
        }*/}
        
    }
    double myrandom() {
        return myRandGen.nextDouble(); //random in 0-1
    }
    private void shortestPath(){
        Node curr = null;
        for(int i=snodes.size()-1;i>=0;i--){
           if(snodes.get(i).end == true){
               curr = snodes.get(i);
               i=-1;
           } 
        }
        curr.tag = "#";
        while(curr.parent != null){
            curr = curr.parent;
            curr.tag = "#";
        }
        mapString = "";
     //   return mapString;
    }
        public String printShortestPath(){
           // this.
            this.shortestPath();
            String output ="+   ";
        for(int i = 1;i<dimension;i++){
            output = output + "+ - ";
        }
        output = output + "+ \n";
        
        for(int i = 0;i<dimension;i++){
            String sub = "+";
            for(int j = 0;j<dimension;j++){
                
                if(j==0){
                    if(map[i][j].tag==""){
                        output = output + "|   ";
                    }
                    else{
                        output = output + "| "+map[i][j].tag+ " ";
                    }
                }
                if(j+1<dimension && map[i][j].path.contains(map[i][j+1])){
                        if(map[i][j+1].tag==""){
                            output = output + "    ";
                        }
                        else{
                            output = output + "  "+map[i][j+1].tag+ " ";
                        }
                }
                else{
                        if(j==dimension-1){
                            output = output + "|\n";
                        }
                        else{
                            if(map[i][j+1].tag==""){
                                output = output + "|   ";
                            }
                            else{
                                output = output + "| "+map[i][j+1].tag+ " ";
                            }
                        }
                }
                if((i+1<dimension && map[i][j].path.contains(map[i+1][j]))||(i==dimension-1 && j==dimension-1)){
                        sub = sub + "   +";
                }
                else{
                        sub = sub + " - +";
                }
            }
            sub = sub + "\n";
            output = output + sub;
             
        }
        return output;
    }
    public void solveMazeBFS(){
        Queue<Node> q = new LinkedList<Node>();
        for(int i=0;i<snodes.size();i++){
           if(snodes.get(i).start == true){
               snodes.get(i).timeF = 0;
               q.offer(snodes.get(i));
               i=snodes.size();
           } 
        }
        while(!q.isEmpty()){
            Node u = q.poll();
            u.visited=2;
            for(int j=0;j<u.path.size();j++){
                Node v = u.path.get(j);
                if(v.visited==0){
                    v.visited = 1;
                    v.parent = u;
                    v.timeF = u.timeF+1;
                    if(v.timeF == 10){
                        v.timeF = 0;
                    }
                    //v.parent = u;
                    if(v.end==true){
                        return;
                    }
                    else{
                        q.offer(v);
                    }
                }
            }
        }
        mapString = "";
        mapString = this.printMaze();
    }
    public String printMaze(){
        String output ="+   ";
        for(int i = 1;i<dimension;i++){
            output = output + "+ - ";
        }
        output = output + "+ \n";
        
        for(int i = 0;i<dimension;i++){
            String sub = "+";
            for(int j = 0;j<dimension;j++){
                
                if(j==0){
                    if(map[i][j].timeF==-1){
                        output = output + "|   ";
                    }
                    else{
                        output = output + "| "+map[i][j].timeF+ " ";
                    }
                }
                if(j+1<dimension && map[i][j].path.contains(map[i][j+1])){
                        if(map[i][j+1].timeF==-1){
                            output = output + "    ";
                        }
                        else{
                            output = output + "  "+map[i][j+1].timeF+ " ";
                        }
                }
                else{
                        if(j==dimension-1){
                            output = output + "|\n";
                        }
                        else{
                            if(map[i][j+1].timeF==-1){
                                output = output + "|   ";
                            }
                            else{
                                output = output + "| "+map[i][j+1].timeF+ " ";
                            }
                        }
                }
                if((i+1<dimension && map[i][j].path.contains(map[i+1][j]))||(i==dimension-1 && j==dimension-1)){
                        sub = sub + "   +";
                }
                else{
                        sub = sub + " - +";
                }
            }
            sub = sub + "\n";
            output = output + sub;
            //output = output + sub;
        }
        //System.out.println(output);
        return output;
    }
    
    public void solveMazeDFS(){
        for(int i=0;i<snodes.size();i++){
            snodes.get(i).visited = 0;
                snodes.get(i).timeF = -1;
                snodes.get(i).parent = null;
        }
        int time = 0;
        for(int i=0;i<snodes.size();i++){
            if(snodes.get(i).visited==0){
                time = dfs_visit(snodes.get(i),time);
                if(time==-1){
                    mapString = "";
                    mapString = this.printMaze();
                    return;
                }
            }
        }
        
    }
    private int dfs_visit(Node curr,int time){
        curr.visited=1;
        curr.timeF=time;
        if(curr.end==true){
            return time=-1;
        }
        time++;
        if(time==10){
            time=0;
        }
        for(int i =0;i<curr.path.size();i++){
            if(curr.path.get(i).visited==0){
                curr.path.get(i).parent=curr;
                time = dfs_visit(curr.path.get(i),time);
                if(time == -1){
                    return time;
                }
            }
        }
        curr.visited=2;
        return time;
    }
    private void buildMaze(Node[][] map,int x){
        for(int i = 0; i<x;i++){
            for(int j = 0; j<x;j++){
                map[i][j] = new Node();
            }
        }
        map[0][0].start = true;
        map[x-1][x-1].end = true;
        buildAdjacency(map,x);
        randomizeGrid(map, x);
        for(int i = 0; i<x;i++){
            for(int j = 0; j<x;j++){
                snodes.add(map[i][j]);
            }
        }
    }
    private void buildAdjacency(Node maze[][], int x){
        for(int i = 0;i<x;i++){
            for(int j = 0;j<x;j++){
              if(i-1>=0){//above
                  maze[i][j].adjacency.add(maze[i-1][j]);
              }
              if(j-1>=0){//left
                  maze[i][j].adjacency.add(maze[i][j-1]);
              }
              if(j+1<x){//right
                  maze[i][j].adjacency.add(maze[i][j+1]);
              }
              if(i+1<x){//below
                  maze[i][j].adjacency.add(maze[i+1][j]);
              }
            }
        }
    }
    private void randomizeGrid(Node maze[][],int x){
        Stack s = new Stack();
        int total = x*x;
        Node start = maze[0][0];
        int visit =1;
        while(visit<total){
            ArrayList<Node> closed = new ArrayList<Node>();
            for(int i=0;i<start.adjacency.size();i++){
                if(start.adjacency.get(i).path.size()==0){
                    closed.add(start.adjacency.get(i));
                }
            }
            //int random;
            if(closed.size()>0){
                    int random = (int)(myrandom() * closed.size());
                    start.path.add(closed.get(random));
                    closed.get(random).path.add(start);
                    s.push(start);
                    start = closed.get(random);
                    visit++;
            }
            else{
                start = (Node) s.pop();
            }
        }
    }
    
    class Node{
      public int visited = 0,timeF =-1;//0=white //1=grey //2=black
      public ArrayList<Node> adjacency = new ArrayList<Node>();
      public ArrayList<Node> path = new ArrayList<Node>();
      public boolean start = false;
      public boolean end = false;
      public Node parent;
      public String tag = "";
      public Node(){}
      
    }
}
