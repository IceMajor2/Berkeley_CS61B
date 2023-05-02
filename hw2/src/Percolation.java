import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// TODO: Add any other necessary imports.

public class Percolation {

    public static void main(String[] args) {

    }

    private WeightedQuickUnionUF wquuf;
    private boolean[][] grid;
    private int side;
    private int openSites;

    public Percolation(int N) throws IllegalArgumentException {
        if(N <= 0) {
            throw new IllegalArgumentException();
        }
        this.side = N;
        this.grid = new boolean[side][side];
        this.openSites = 0;

        this.wquuf = new WeightedQuickUnionUF(side * side + 2); // added 2 is v-top and v-bottom
    }

    public void open(int row, int col) throws IndexOutOfBoundsException {
        if(isOpen(row, col)) {
            return;
        }
        this.grid[row][col] = true;
        this.openSites++;

        unionWithNeighbors(row, col);

        if(row == 0) {
            connectToVirtualTop(row, col);
        } else if (row == side - 1) {
            connectToVirtualBottom(row, col);
        }
    }

    private void unionWithNeighbors(int row, int col) {
        int[] openNeighborPos = getOpenNeighborsPos(row, col);
        int thisPos = getPosIndex(row, col);

        for(int neighPos : openNeighborPos) {
            wquuf.union(thisPos, neighPos);
        }
    }

    private void connectToVirtualTop(int row, int col) {
        int squarePos = getPosIndex(row, col);
        wquuf.union(0, squarePos);
    }

    private void connectToVirtualBottom(int row, int col) {
        int squarePos = getPosIndex(row, col);
        int vBottomPos = this.getVirtualBottomPos();
        wquuf.union(squarePos, vBottomPos);
    }

    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col];
    }

    public boolean isFull(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int squarePos = getPosIndex(row, col);
        return wquuf.connected(0, squarePos); // returns true if examined position is connected to v-top
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        int virtualBottomPos = getVirtualBottomPos();
        int virtualTopPos = 0;
        return wquuf.connected(virtualTopPos, virtualBottomPos);
    }

    private boolean validIndex(int row, int col) {
        if(row < 0 || col < 0 || row >= side || col >= side) {
            return false;
        }
        return true;
    }

    private int[] getOpenNeighborsPos(int row, int col) {
        Boolean up = null, down = null, left = null, right = null;
        try {
            up = this.grid[row - 1][col];
        } catch(ArrayIndexOutOfBoundsException e) {}
        try {
            down = this.grid[row + 1][col];
        } catch(ArrayIndexOutOfBoundsException e) {}
        try {
            left = this.grid[row][col - 1];
        } catch(ArrayIndexOutOfBoundsException e) {}
        try {
            right = this.grid[row][col + 1];
        } catch(ArrayIndexOutOfBoundsException e) {}

        int[] neighborPos = new int[4];
        int openNeighbors = 0;
        if(up != null && isOpen(row - 1, col)) {
            neighborPos[openNeighbors] = getPosIndex(row - 1, col);
            openNeighbors++;
        }
        if(down != null && isOpen(row + 1, col)) {
            neighborPos[openNeighbors] = getPosIndex(row + 1, col);
            openNeighbors++;
        }
        if(left != null && isOpen(row, col - 1)) {
            neighborPos[openNeighbors] = getPosIndex(row, col - 1);
            openNeighbors++;
        }
        if(right != null && isOpen(row, col + 1)) {
            neighborPos[openNeighbors] = getPosIndex(row, col + 1);
            openNeighbors++;
        }
        int[] returnArr = new int[openNeighbors];
        System.arraycopy(neighborPos, 0, returnArr, 0, openNeighbors);
        return returnArr;
    }

    private int getPosIndex(int row, int col) throws IndexOutOfBoundsException {
        if(!validIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return row * side + col + 1;
    }

    private int getVirtualBottomPos() {
        return side * side + 1;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
