package helpers;

public class FullResult {

    private Number x;
    private Number y;
    private Number r;
    private int result;

    public FullResult (Number x, Number y, Number r, int result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }


    public Number getX ( ) {
        return x;
    }

    public Number getY ( ) {
        return y;
    }

    public Number getR ( ) {
        return r;
    }

    public int getResult ( ) {
        return result;
    }
}
