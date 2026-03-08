package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public int numIslands(char[][] grid) {

        int count = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == '1'){
                    count++;
                    removeIsland(grid, i, j);
                }

            }
        }

        return count;
    }

    public void removeIsland(char[][] grid, int i, int j){

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0';

        removeIsland(grid, i+1, j);
        removeIsland(grid, i-1, j);
        removeIsland(grid, i, j+1);
        removeIsland(grid, i, j-1);
    }
    public static void main(String[] args) {

        char[][] grid = {
                {'1','1','0','0'},
                {'1','0','0','1'},
                {'0','0','1','1'},
                {'0','0','0','0'}
        };

        Main obj = new Main();
        int result = obj.numIslands(grid);

        System.out.println("Number of Islands: " + result);
    }
}