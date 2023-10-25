public class Location {
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getR() {
        return row;
    }

    public int getC() {
        return col;
    }

    public void incR(int x) {
        row += x;
    }

    public void incC(int x) {
        col += x;
    }

    public void set(int newR, int newC) {
        row = newR;
        col = newC;
    }

    public boolean equals(Location other) {
        return row == other.getR() && col == other.getC();
    }

    public boolean equals(int r, int c) {
        return row == r && col == c;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}

