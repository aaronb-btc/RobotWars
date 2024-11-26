package com.btcag.robotwars.Models;

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

    public void takeDamage(int healthPoints) {
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

    public boolean isKnockedOut() {
        return this.health == 0;
    }

    public int getAttackRange() {
        return this.attackRange;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public int getHealth() {
        return this.health;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getMovementRate() {
        return this.movementRate;
    }

    public void moveRel(int xSteps, int ySteps) {
        this.x += xSteps;
        this.y += ySteps;
    }

    public char getDisplayChar() {
        return this.displayChar;
    }

    public String getName() {
        return this.name;
    }
}