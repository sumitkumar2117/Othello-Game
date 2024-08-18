import java.io.*;
import java.util.*;

public class Othello {
    int turn;
    int winner;
    int board[][];

    
    class Pair{
        int x;
        int y;
       public  Pair(int x, int y){
        this.x=x;
        this.y=y;
       }
       public int returningkey(){
        return this.x;
       }
       public int returningvalue(){
        return this.y;
       }
        
    }

    public Othello(String filename) throws Exception {
        
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        turn = sc.nextInt();
       
        board = new int[8][8];
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j){
                board[i][j] = sc.nextInt();
               
            }
        }
        winner = -1;
       
    }

    

    public ArrayList<Pair> possibleMoves(int playerSign,int[][] tmpboard){
        ArrayList<Pair> possible_moves= new ArrayList<Pair>();
        for(int X_Cor=0;X_Cor<8;X_Cor++){
            for(int Y_Cor=0;Y_Cor<8;Y_Cor++){
              
                if(isCorrectMove(playerSign, X_Cor, Y_Cor,tmpboard)){
                possible_moves.add(new Pair(X_Cor,Y_Cor));
               
                }
            }
          
        }
        return possible_moves;
    }

    public void switchingValues(int otherPlayerSign,int playerSign,int X_Cor,int Y_Cor,int changeInX,int changeInY,int[][] tmpboard){
        while( tmpboard[X_Cor][Y_Cor]==otherPlayerSign){
            tmpboard[X_Cor][Y_Cor]=playerSign;
            Y_Cor=Y_Cor+changeInY;
            X_Cor=X_Cor+changeInX;
        }

    }

    public boolean isFlipable(int otherPlayerSign,int X_Cor,int Y_Cor,int changeInX,int changeInY,int[][] tmpboard){
            
        if((X_Cor<0 || X_Cor>=8 || Y_Cor<0 || Y_Cor>=8))
            return false;
        if(tmpboard[X_Cor][Y_Cor]==otherPlayerSign){     
            while((Y_Cor>=0 && Y_Cor<8) && (X_Cor>=0 && X_Cor<8)){
                Y_Cor=Y_Cor+changeInY;
                X_Cor=X_Cor+changeInX;
                if(Y_Cor>=0 && Y_Cor<8 && X_Cor>=0 && X_Cor<8 &&  tmpboard[X_Cor][Y_Cor]==-1)
                return false;
                else if(Y_Cor>=0 && Y_Cor<8 && X_Cor>=0 && X_Cor<8 && tmpboard[X_Cor][Y_Cor]!=otherPlayerSign)
                return true;
               
            }
        }
        
        
        return false;
    }

    public boolean isCorrectMove(int playerSign,int X_Cor,int Y_Cor,int[][] tmpboard){
        int otherPlayerSign=0;
        if(playerSign==0)
        otherPlayerSign=1;
        if(tmpboard[X_Cor][Y_Cor]!=-1)
        return false;

        if(isFlipable(otherPlayerSign,X_Cor+1,Y_Cor+1,1,1,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor-1,Y_Cor-1,-1,-1,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor+1,Y_Cor,1,0,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor,Y_Cor+1,0,1,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor-1,Y_Cor+1,-1,1,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor+1,Y_Cor-1,1,-1,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor,Y_Cor-1,0,-1,tmpboard))
        return true;

        if(isFlipable(otherPlayerSign,X_Cor-1,Y_Cor,-1,0,tmpboard))
        return true;

        return false;
        
    }

    public void moving(int playerSign,int X_Cor,int Y_Cor,int[][] tmpboard){  //else if
        int otherPlayerSign=1;
        if(playerSign==1)
        otherPlayerSign=0;
        tmpboard[X_Cor][Y_Cor]=playerSign;

        if(isFlipable(otherPlayerSign,X_Cor+1,Y_Cor+1,1,1,tmpboard))
        switchingValues(otherPlayerSign, playerSign, X_Cor+1, Y_Cor+1, 1,1,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor-1,Y_Cor-1,-1,-1,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor-1,Y_Cor-1,-1,-1,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor+1,Y_Cor,1,0,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor+1,Y_Cor,1,0,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor,Y_Cor+1,0,1,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor,Y_Cor+1,0,1,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor-1,Y_Cor+1,-1,1,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor-1,Y_Cor+1,-1,1,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor+1,Y_Cor-1,1,-1,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor+1,Y_Cor-1,1,-1,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor,Y_Cor-1,0,-1,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor,Y_Cor-1,0,-1,tmpboard);

        if(isFlipable(otherPlayerSign,X_Cor-1,Y_Cor,-1,0,tmpboard))
        switchingValues(otherPlayerSign,playerSign,X_Cor-1,Y_Cor,-1,0,tmpboard);



    }
    public int boardScore1(int playerSign,int[][] tmpboard){

        int count_black=0,count_white=0;
        for(int i=0;i<8;i++){
        for(int j=0;j<8;j++){
         
            if(tmpboard[i][j]==0)
            count_black++;
            else if(tmpboard[i][j]==1)
            count_white++;
        }
     

    }
        int ans=count_black-count_white;
      
        if(playerSign==1)
        return ans*-1;
        return ans;
    }


    public int boardScore() {
         
        int count_black=0,count_white=0;
        for(int i=0;i<8;i++)
        for(int j=0;j<8;j++){
            if(board[i][j]==0)
            count_black++;
            else if(board[i][j]==1)
            count_white++;
        }
        int ans=count_black-count_white;
        
        if(turn==1)
        return ans*-1;
        return ans;
    }

   
    public int algo(int whoseturn,int[][] tmpboard,int k){
        int oppositeturn=1;
        if(whoseturn==1)
        oppositeturn=0;
        int bestmovecount=-23763;
        int ans=23763;
        ArrayList<Pair> possible_moves=possibleMoves(whoseturn,tmpboard);
        
        for(int i=0;i<possible_moves.size();i++){
            int X_Cor=possible_moves.get(i).returningkey();
            int Y_Cor=possible_moves.get(i).returningvalue();
            int[][] tmpboardnew=new int[8][8];
            for(int k1=0;k1<8;k1++)
                for(int j=0;j<8;j++)
                    tmpboardnew[k1][j]= tmpboard[k1][j];
                    moving(whoseturn,X_Cor,Y_Cor,tmpboardnew);
            int val=funcalgo(whoseturn,oppositeturn,1,tmpboardnew,k);
            if(val>bestmovecount){
                bestmovecount=val;
                ans=8*X_Cor+Y_Cor;
            }
            else if(val==bestmovecount)
            ans=Math.min(ans,8*X_Cor+Y_Cor);
                
        }
        return ans;
    }

    public int funcalgo(int originalturn,int currentturn,int dep,int[][] tmpboard,int k){
        if(dep==k )    
        return boardScore1(originalturn,tmpboard);
        int opponent=1;
        if(currentturn==1)
        opponent=0;
        ArrayList<Pair> possible_moves=possibleMoves(currentturn,tmpboard);
        if(possible_moves.size()==0)
        return funcalgo(originalturn, opponent, dep+1, tmpboard, k);
        else{
            int bestmovecount=-23763;
            if(originalturn!=currentturn)
            bestmovecount=23763;
            for(int i=0;i<possible_moves.size();i++){
                int[][] tmpboardnew1=new int[8][8];
                for(int k1=0;k1<8;k1++)
                    for(int j=0;j<8;j++)
                        tmpboardnew1[k1][j]= tmpboard[k1][j];
                int X_Cor=possible_moves.get(i).returningkey();
                int Y_Cor=possible_moves.get(i).returningvalue();
                moving(currentturn,X_Cor,Y_Cor,tmpboardnew1);
                    int val=funcalgo(originalturn, opponent, dep+1, tmpboardnew1, k);
                if(originalturn==currentturn){
                        if(val>bestmovecount)
                        bestmovecount=val;
                }
                else{
                        if(val<bestmovecount)
                        bestmovecount=val;
                }
            }
            return bestmovecount;
        }
        
    }

    public int bestMove(int k) {
        return algo(turn, board, k);
    }

    public boolean isGameEnd(int playerSign ,int[][] tmpboard){
        ArrayList<Pair> possible_moves= possibleMoves(playerSign, tmpboard);
        if(possible_moves.size()==0)
        return true;
        return false;
    }
    public boolean isBoardfull(){
        int count=0;
        for(int i=0;i<8;i++)
        for(int j=0;j<8;j++)
        if(board[i][j]==-1)
           count++;

        if(count==0)
        return true;
        return false;
    }

    public ArrayList<Integer> fullGame(int k) {

        int chi=turn;
        
        ArrayList<Integer> returningLlst = new ArrayList<Integer>();
        int playerSign=turn;
        while( !isGameEnd(0, board) || !isGameEnd(1, board)){
            int answer= bestMove(k);
          if(answer<=(8*7+7) && answer>=0){
            
            returningLlst.add(answer);
            int X_Cor=answer/8;
            int Y_Cor=answer%8;
            moving(playerSign,X_Cor,Y_Cor,board);
          }
            playerSign=1-playerSign;
            turn=1-turn;
        }
        turn=chi;
        int win=boardScore1(chi,board);
        if(turn==0)
        {
            if(win>0)
            winner=0;
            else if(win<0)
            winner=1;
            else 
            winner=-1;
        }
        else{
            if(win>0)
            winner=1;
            else if(win<0)
            winner=0;
            else 
            winner=-1;
        }
        
        return returningLlst ;
    }

    public int getWinner() {
        return winner;
    }

    public int getTurn() {
        return turn;
    }
    public int[][] getBoardCopy() {
        int copy[][] = new int[8][8];
        for(int i = 0; i < 8; ++i)
            System.arraycopy(board[i], 0, copy[i], 0, 8);
        return copy;
    }


}