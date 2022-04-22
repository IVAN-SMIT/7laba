package City;

    /**
    * Enum сlimate для класса City
    */

public enum Climate {
    MONSOON("MONSOON"),
    OCEANIC("OCEANIC"),
    MEDITERRANIAN("MEDITERRANIAN"),
    STEPPE("STEPPE");

    private String climate;

    Climate(String climate) {
        setClimate(climate);
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getClimate() {
        return climate;
    }

    public static Climate getEnumByName(String name) {
        for (Climate climate : values()) {
            if (climate.getClimate().equals(name)) {
                return climate;
            }
        }
        throw new IllegalArgumentException("Не удалось найти enum с именем: " + name);
    }
}