package com.btcag.robotwars;

public  class Robot {
    private int x;
    private int y;
    private String name;
    private char displayChar;
    private int movementRate;
    private int attackRange;
    private int attackDamage;
    private int health;

    public Robot(int x, int y, String name, char displayChar, int movementRate, int attackRange, int attackDamage, int health) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.displayChar = displayChar;
        this.movementRate = movementRate;
        this.attackRange = attackRange;
        this.attackDamage = attackDamage;
        this.health = health;
    }

    void takeDamage(int healthPoints) {
        this.health -= healthPoints;
        if (this.health < 0) {
            this.health = 0;

        }
    }

    @Override
    public String toString() {
        return "Robot{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                ", displayChar=" + displayChar +
                ", movementRate=" + movementRate +
                ", attackRange=" + attackRange +
                ", attackDamage=" + attackDamage +
                ", health=" + health +
                '}';
    }

    boolean isKnockedOut() {
        return this.health == 0;
    }

    int getAttackRange() {
        return this.attackRange;
    }

    int getAttackDamage() {
        return this.attackDamage;
    }

    int getHealth() {
        return this.health;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getMovementRate() {
        return this.movementRate;
    }

    void moveRel(int xSteps, int ySteps) {
        this.x += xSteps;
        this.y += ySteps;
    }

    char getDisplayChar() {
        return this.displayChar;
    }

    String getName() {
        return this.name;
    }
}
