package City;

 /**
 * Enum Standard Of Living для City
 */

public enum StandardOfLiving {
    VERY_HIGH("VERY_HIGH"),
    HIGH("HIGH"),
    NIGHTMARE("NIGHTMARE");

    private String level;

    StandardOfLiving(String level) {
        setLevel(level);
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public static StandardOfLiving getEnumByName(String name) {
        for (StandardOfLiving living : values()) {
            if (living.getLevel().equals(name)) {
                return living;
            }
        }
        throw new IllegalArgumentException("Не удалось найти enum с именем: " + name);
    }
}