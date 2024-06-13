package enums;

public enum Electronic {

    Radio("Radio",500),
    Television("Television",1500);


    private final String name;
    private final double price;

    Electronic(String name, double price) {
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
