/*
First create a 1d array with n*n length and keep adding values to it - nums

time complexity - O(n2)
space complexity - O(n2)
*/

class Solution {
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        int[] nums = new int[n * n];
        int index = 0;
        int row = n - 1; // number 1 is on row 5 as per example1
        int col = 0;
        int even = 0;
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();


        while(index < n * n){
            if(board[row][col] != -1 ){
                nums[index] = board[row][col] - 1; //if not -1 its a ladder/snake assign jump to that value
            } else {
                nums[index] = -1;
            }
            index++; // increment for while loop
            if(even % 2 == 0){ // to decide the direction of movement even -> right ; odd -> left
                col++;
                if(col == n){ // when we reach end of the row
                    row--; // increase row number to keep the game going
                    even++; // changing direction
                    col = n - 1; // set col number
                }
            } else {
                    col--;
                    if(col == -1){
                        row --;
                        even++;
                        col = 0;
                    }
                }
        }
        q.add(0); // add first index to queue
        //Start BFS
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n*n-1){
                    return moves;
                }
                for(int j = 1; j <= 6; j++){
                    int child = curr + j; // roll dice and add this current dice roll
                    if(child >= n * n){
                        continue;
                    }
                    if(nums[child] == -1){
                        q.add(child);
                        nums[child] = -2; // add value of the cell
                    } else{
                        if(nums[child] != -2){
                            q.add(nums[child]);
                            nums[child] = -2;
                        }
                    }
                }
            }
            moves++;
        }
        return -1;

    }
}