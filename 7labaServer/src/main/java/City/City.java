package City;

import auxiliary.Messager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * City - главный класс в коллекции, имеет все необходимые сеттеры и геттеры
 */


public class City {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime localDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0
    private Long population; //Значение поля должно быть больше 0, Поле не может быть null
    private Long metersAboveSeaLevel;
    private long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private Climate climate; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле не может быть null
    private Human governor; //Поле не может быть null


    public City(){super();}
    Messager city = new Messager();
    boolean flag = true;

    public City(Long id, String name, Coordinates coordinates,
                String localDate,
                int area,
                Long population, Long metersAboveSeaLevel,
                long carCode, Climate climate,
                StandardOfLiving standardOfLiving,Human governor) throws Exception {

        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(localDate);
        setArea(area);
        setPopulation(population);
        setMetersAboveSeaLevel(metersAboveSeaLevel);
        setCarCode(carCode);
        setClimate(climate);
        setStandardOfLiving(standardOfLiving);
        setGovernor(governor);
    }

    public City(Long id, String name, Coordinates coordinates,
                String localDate,
                int area,
                Long population, Long metersAboveSeaLevel,
                long carCode, Climate climate,
                StandardOfLiving standardOfLiving) throws Exception {

        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(localDate);
        setArea(area);
        setPopulation(population);
        setMetersAboveSeaLevel(metersAboveSeaLevel);
        setCarCode(carCode);
        setClimate(climate);
        setStandardOfLiving(standardOfLiving);
    }

    /*-------------------------------------------------------------------*/

    public Long getId(){
        return  id;
    }
    public String getName(){
        return name;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public  String getLocalDate(){return localDate.toString();}
    public int getArea(){return area;}
    public Long getPopulation(){return population;}
    public Long getMetersAboveSeaLevel(){return  metersAboveSeaLevel;}
    public long getCarCode(){return carCode;}
    public Climate getClimate(){return climate;}
    public StandardOfLiving getStandardOfLiving(){return standardOfLiving;}
    public Human getGovernor(){return governor;}


    /*--------------------------------------------------------------------*/

    public  void setId(long id) throws Exception {
        if(id <= 0) {
            city.println("id не может быть меньше 0",flag);
            throw new IllegalArgumentException("id не может быть меньше 0");}
        //this.id = (long) random()*1000;\
        this.id = id;
    }
    public void setName(String name) throws Exception {
        if(name == null){
            city.println("Name не может быть null!",flag);
            throw new IllegalArgumentException("Name не может быть null!");}
        if (name.equals("")){
            city.println("Name не может быть пустой строкой",flag);
            throw new IllegalArgumentException("Name не может быть пустой строкой");}
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws Exception {
        if (coordinates == null) {
            city.println("coordinates не может быть null!",flag);
            throw new IllegalArgumentException("coordinates не может быть null!");}
        this.coordinates = coordinates;
    }

    public void setCreationDate(String localDate) throws Exception {
        if(localDate == null) {
            city.println("Дата создания не может быть null",flag);
            throw new IllegalArgumentException("Дата создания не может быть null");}
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        formatter = formatter.withLocale(Locale.CANADA); //на случай если месяцы будут после дней [России не было, пришлось канаду( ]
        this.localDate = LocalDateTime.parse(localDate);        //Значение этого поля должно генерироваться автоматически
    }

    public  void setArea(int area) throws Exception {
        if(area <= 0) {
            city.println("area не может быть меньше 0",flag);
            throw new IllegalArgumentException("area не может быть меньше 0");}
        this.area = area;
    }

    public void setPopulation(Long population) throws Exception {
        if(population == null | population <=0){
            city.println("population не может быть null или меньше нуля!",flag);
            throw new IllegalArgumentException("population не может быть null или меньше нуля!");
        }
        this.population=population;
    }

    public void setMetersAboveSeaLevel(Long metersAboveSeaLevel){
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }
    public void setCarCode(long carCode) throws Exception {
        if(carCode > 1000){
            city.println("Максимальное значение carCode: 1000",flag);
            throw new IllegalArgumentException("Максимальное значение carCode: 1000");}
        if(carCode <= 0){
            city.println("Значение carCode должно быть больше 0",flag);
            throw new IllegalArgumentException("Значение carCode должно быть больше 0");}
        this.carCode = carCode;
    }
    public void setClimate(Climate climate) throws Exception {
        if (climate == null) {
            city.println("climate не может быть null!",flag);
            throw new IllegalArgumentException("climate не может быть null!");}
        this.climate = climate;
    }
    public void setStandardOfLiving(StandardOfLiving standardOfLiving) throws Exception {
        if(standardOfLiving == null){
            city.println("standardOfLiving не может быть null!",flag);
            throw new IllegalArgumentException("standardOfLiving не может быть null!");}
        this.standardOfLiving = standardOfLiving;
    }
    public void setGovernor(Human governor) throws Exception {
        if(governor == null){city.println("null",flag);}
        this.governor = governor;
    }

    /*--------------------------------------------------------------------*/

    @Override
    public  String toString(){
        return "id:" + id + " | name: " + name + " | coordinates:" + coordinates.toString() + " | date:" +

                localDate.toString() +
                " | area:" + area + " | population:" +
                population + " | meters above sea level:" + metersAboveSeaLevel + " | car code:" +carCode+ " | climate:"
                + climate.toString()+ " | standard of living:" +standardOfLiving.toString()+ " | Human{height:"+governor.toString() + "}";
    }

}
