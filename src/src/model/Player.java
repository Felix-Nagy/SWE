package src.model;

public class Player {
    private String name;
    private int points = 0;
    private Category category;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int add) {
        this.points += add;
    }

    public int getPoints() {
        return points;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", this.name, this.points);
    }
}
