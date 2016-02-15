package com.company;

public class Main {

    public static void main(String[] args) {
        new Solver(new int[]{0,0}, new int[]{8,8});
    }

    public static class Solver{

        int lowest_backtrack;
        int[] board;
        int[] board_size;
        int[][] moves = {{2,1},{2,-1},{1,2},{1,-2},{-1,2},{-1,-2},{-2,1},{-2,-1}};

        public Solver(int[] start_pos, int[] board_size){
            this.board_size = board_size;
            board = new int[board_size[0]*board_size[1]];
            lowest_backtrack = board.length;
            if (solve(1,start_pos)){
                printSolution();
            }else{
                System.out.println("No solutions found");
            }
        }

        private boolean solve(int turn, int[] pos){
            int linear_pos = pos[0]+pos[1]*board_size[0];
            if (board[linear_pos] == 0 ){
                board[linear_pos] = turn;
                if (turn == board.length){
                    return true;
                }else{
                    for (int[] move:moves){
                        int[] next_move = {pos[0]+move[0],pos[1]+move[1]};
                        if (0<=next_move[0]&&next_move[0]<board_size[0]&&0<=next_move[1]&&next_move[1]<board_size[1]){
                            if(solve(turn+1,next_move)){
                                return true;
                            }
                        }
                    }
                    if (lowest_backtrack > turn) {
                        lowest_backtrack = turn;
                        System.out.println("Failed.. backtracking. @ turn " + turn);
                    }
                    board[linear_pos] = 0;
                }
            }
            return false;
        }

        private void printSolution(){
            System.out.println("Solution found! \n-----------------------");
            for (int i = 0; i<board.length;i++){
                System.out.print(board[i]+ "\t");
                if (i%board_size[0]==board_size[0]-1){
                    System.out.print("\n");
                }
            }
        }
    }
}