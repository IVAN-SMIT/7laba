package City;

 /**
 * Класс Human, имеющий параметр height
 */

public class Human {
    private Float height; //Значение поля должно быть больше 0

    public Human(){  //хз почему, но эт важная штука оказалась
         super();
     }

    public float getHeight(){
        return height;
    }

    public  void setHeight(float height)throws IllegalArgumentException{
        if (height < 0){throw new IllegalArgumentException("Значение поля height должно быть больше 0");}
        this.height = height;
    }

    public Human(float height)throws IllegalArgumentException {
        setHeight(height);
    }

    @Override
    public String toString(){
        return height.toString() ;
    }

}
