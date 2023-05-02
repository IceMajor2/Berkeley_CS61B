import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// TODO: Add any other necessary imports.

public class Percolation {

    public static void main(String[] args) {

    }

    private WeightedQuickUnionUF wquuf;
    private int[][] grid;
    private int side;
    private int openSites;

    public Percolation(int N) throws IllegalArgumentException {
        if(N <= 0) {
            throw new IllegalArgumentException();
        }
        this.side = N;
        this.grid = new int[side][side];
        this.openSites = 0;

        this.wquuf = new WeightedQuickUnionUF(side * side + 2);
        this.createVirtualTop();
        this.createVirtualBottom();
    }

    public void open(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void createVirtualTop() {
        int[] firstRow = this.grid[0];

        int i = 0;
        for(int square : firstRow) {
            int squarePos = getPosIndex(0, i);
            wquuf.union(0, squarePos);
            i++;
        }
    }

    private void createVirtualBottom() {
        int[] lastRow = this.grid[side - 1];
        int virtualBottomPos = side * side + 1;

        int i = 0;
        for(int square : lastRow) {
            int squarePos = getPosIndex(0, i);
            wquuf.union(virtualBottomPos, squarePos);
            i++;
        }
    }

    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return isFull(row, col) || this.grid[row][col] == 1;
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
        int virtualBottomPos = side * side + 1;
        int virtualTopPos = 0;
        return wquuf.connected(virtualTopPos, virtualBottomPos);
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
        return row * side + col + 1;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
