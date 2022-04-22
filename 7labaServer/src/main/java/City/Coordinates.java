package City;

 /**
 * Класс coordinates для City
 */

public class Coordinates {
    private int x; //Максимальное значение поля: 714
    private int y; //Значение поля должно быть больше -493


    public Coordinates(int x, int y) throws IllegalArgumentException {
        setX(x);
        setY(y);
    }
    public Coordinates() {
        super();
    }

    public void setY(int y) throws IllegalArgumentException {

        if(y <= -493){
            throw  new IllegalArgumentException("Y не может быть меньше -493!");
        }
        this.y = y;
    }

    public void setX(int x) throws IllegalArgumentException {
        if(x > 714){
            throw new IllegalArgumentException("X не может быть больше 714!");
        }
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString(){
        return x + " " + y;
    }
}


