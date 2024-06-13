package enums;

public enum Shoe {
    SPORT("Sport Shoes", 200),
    FORMAL("Formal Shoes", 300);

    private final String name;
    private final double price;

    Shoe(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
