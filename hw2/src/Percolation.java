import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// TODO: Add any other necessary imports.

public class Percolation {

    public static void main(String[] args) {

    }

    private WeightedQuickUnionUF wquuf;
    private int[][] grid;
    private int squareSide;
    private int openSites;

    public Percolation(int N) throws IllegalArgumentException {
        if(N <= 0) {
            throw new IllegalArgumentException();
        }
        this.squareSide = N;
        this.grid = new int[squareSide][squareSide];
        this.wquuf = new WeightedQuickUnionUF(squareSide * squareSide);
        this.openSites = 0;
    }

    public void open(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        this.grid[row][col] = 1;
        if(row == 0) {
            fill(row, col);
        }
        openSites++;

        unionWithNeighbor(row, col);
    }

    public void fill(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        this.grid[row][col] = 2;
    }

    private void unionWithNeighbor(int row, int col) {
        int thisPos = this.getPosIndex(row, col);
        int[][] neighbors = getNeighbors(row, col);
        for(int[] neighbor : neighbors) {
            if(!isOpen(neighbor[0], neighbor[1])) {
                continue;
            }
            if(isFull(neighbor[0], neighbor[1])) {
                fill(row, col);
            }
            int neighPos = this.getPosIndex(neighbor[0], neighbor[1]);
            wquuf.union(thisPos, neighPos);
        }
    }

    private int[][] getNeighbors(int row, int col) {
        int[] left = {row, col - 1};
        int[] right = {row, col + 1};
        int[] up = {row - 1, col};
        int[] down = {row + 1, col};

        int[][] temp = {left, right, up, down};

        int[][] temp2 = new int[4][2];

        int count = 0;
        for(int[] maybe : temp) {
            if(!validIndex(maybe[0], maybe[1])) {
                maybe = null;
                continue;
            }
            temp2[count] = maybe;
            count++;
        }

        int[][] neighbors = new int[count][2];
        System.arraycopy(temp2, 0, neighbors, 0, count);
        return neighbors;
    }

    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col] == 1 || this.grid[row][col] == 2;
    }

    public boolean isFull(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col] == 2;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        int[] lastRow = this.grid[squareSide - 1];
        int[] firstRow = this.grid[0];

        int lastColPointer = -1;
        int firstColPointer = -1;
        for(int square : lastRow) {
            lastColPointer++;
            int squarePos = this.getPosIndex(squareSide - 1, lastColPointer);
            if(!isFull(squareSide - 1, lastColPointer)) {
                continue;
            }
            for(int _square : firstRow) {
                firstColPointer++;
                int _squarePos = this.getPosIndex(0, firstColPointer);
                if(!isFull(0, firstColPointer)) {
                    continue;
                }
                if(wquuf.connected(squarePos, _squarePos)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validIndex(int row, int col) {
        if(row < 0 || col < 0 || row >= squareSide || col >= squareSide) {
            return false;
        }
        return true;
    }

    private int getPosIndex(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return row * squareSide + col;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
