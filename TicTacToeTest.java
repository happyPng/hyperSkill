	import java.util.Scanner;
	
public class TicTacToeTest {
    public static boolean checkTable(char table[][],boolean x,boolean o,boolean over) {
        for(int i=0;i<3;i++) {
        	for(int j=0;j<3;j++) {
        		if(j==0 && i==0) {
        			if((table[i][j]==table[i+1][j+1]&&table[i][j]==table[i+2][j+2])||(table[i][j]==table[i+1][j]&&table[i][j]==table[i+2][j])) {
        				if(table[i][j]=='X') {
        					x=true;
        				}else if(table[i][j]=='O') {
        					o=true;
        				}
        			}

            	}
        		if(j==0 &&i==2) {
        			if(table[i][j]==table[i-1][j+1]&&table[i][j]==table[i-2][j+2]) {
        				if(table[i][j]=='X') {
        					x=true;
        				}else if(table[i][j]=='O') {
        					o=true;
        				}
        			}
        			
        		} 
        		if(j==0) {
        			if(table[i][j]==table[i][j+1]&&table[i][j]==table[i][j+1]&&table[i][j]==table[i][j+2]) {
        				if(table[i][j]=='X') {
        					x=true;
        				}else if(table[i][j]=='O') {
        					o=true;
        				}
        			}
        			
        		}
        		if(i==0&&j==1) {
        			if(table[i][j]==table[i+1][j]&&table[i][j]==table[i+2][j]) {
        				if(table[i][j]=='X') {
        					x=true;
        				}else if(table[i][j]=='O') {
        					o=true;
        				}
        			}
        			
        		}
        		if(i==0&&j==2) {
        			if(table[i][j]==table[i+1][j]&&table[i][j]==table[i+2][j]) {
        				if(table[i][j]=='X') {
        					x=true;
        				}else if(table[i][j]=='O') {
        					o=true;
        				}
        			}
        			
        		}
        		
        	}
        	
        }
    	
        if(x) {
        	System.out.println("X wins");
        	return true;
        }else if(o) {
        	System.out.println("O wins");
        	return true;
        }else if(!x&&!o&&over) {
        	System.out.println("Draw");
        	return true;
        }else {
        	return false;
        }
	
	}
public static void printTable(char table[][]) {
	System.out.println("---------");	
		for(int i=0;i<3;i++) {
			System.out.print("|");
			for(int j=0;j<3;j++) {
				if(table[i][j]!='_') {
					System.out.print(" "+table[i][j]);					
				}else {
					System.out.print(" "+" ");					
				}
			}
			System.out.print(" |"+"\n");
			
		}
	System.out.println("---------");	
		
		
}

		public static void main(String[] args) {
			Scanner in=new Scanner(System.in);
	        char[][] table = new char[3][3];
            for(int i=0;i<3;i++) {
            	for(int j=0;j<3;j++) {
            		table[i][j]='_';
            	}
            }
            int white=9;
            boolean x=false;
            boolean o=false;
            printTable(table);
            String cordI,cordJ;
            char turn='X';
            while(!checkTable(table, x, o, white<=0)) {
            	try {
            		System.out.println("Enter the cords: ");
                    cordI=in.next();
                    cordJ=in.next();
                    if(Character.isLetter(cordI.charAt(0))||Character.isLetter(cordJ.charAt(0))){
                    	System.out.println("You should enter numbers!");
                    	continue;
                    }
                    
                    if(Integer.parseInt(cordJ)>3||Integer.parseInt(cordI)>3){
                    	System.out.println("Coordinates should be from 1 to 3!");
                    	continue;
                    }
                	
                    if(table[Integer.parseInt(cordI)-1][Integer.parseInt(cordJ)-1]!='_'){
                    	System.out.println("This cell is occupied! Choose another one!");
                    }else {
                    	table[Integer.parseInt(cordI)-1][Integer.parseInt(cordJ)-1]=turn;
                    	white--;
                    	printTable(table);
                        switch(turn) {
                        case 'X':turn = 'O'; break;
                        case 'O':turn = 'X'; break;
                        }
                    }
            	  }catch(Exception e) {
            		  System.out.println("Wrong input!");
            	  }
            }
		}
}
