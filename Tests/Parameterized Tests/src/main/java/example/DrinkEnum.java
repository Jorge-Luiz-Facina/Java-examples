package example;

public enum DrinkEnum {
    BEER("BEER", true),
    WINE("Wine", true),
    JUICE("Juice", false),
    CHAMPAGNE("Champagne", true),
    SODA("Soda", false);

    Boolean isAlcohol;

    String name;

    DrinkEnum(String name,Boolean isAlcohol){
        this.name = name;
        this.isAlcohol = isAlcohol;
    }
}
