package com.btcag.robotwars.Models;

import com.btcag.robotwars.Api.model.Align;
import com.btcag.robotwars.Enums.Direction;
import com.btcag.robotwars.Enums.ItemType;

import java.math.BigDecimal;
import java.util.Arrays;

public class Robot {
    private int x;
    private int y;
    private final com.btcag.robotwars.Api.model.Robot robot;
    private Align alignment = Align.N;
    private Item[] items = new Item[ItemType.values().length];

    public Robot(
            int x,
            int y,
            String id,
            String name,
            int health,
            int attackDamage,
            int attackRange,
            int movementRate
    ) {
        this.x = x;
        this.y = y;
        this.robot = new com.btcag.robotwars.Api.model.Robot();
        robot.setId(id);
        robot.setName(name);
        robot.setHealth(new BigDecimal(health));
        robot.setAttackDamage(new BigDecimal(attackDamage));
        robot.setAttackRange(new BigDecimal(attackRange));
        robot.setMovementRate(new BigDecimal(movementRate));
    }

    public Robot(
            int x,
            int y,
            com.btcag.robotwars.Api.model.Robot robot
    ) {
        this.x = x;
        this.y = y;
        this.robot = robot;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getId() {
        return robot.getId();
    }

    public String getName() {
        return robot.getName();
    }

    public int getHealth() {
        return robot.getHealth().intValue();
    }

    public int getAttackDamage() {
        return robot.getAttackDamage().intValue()
                + (getItem(ItemType.ATTACK_DAMAGE) == null
                ? 0
                : getItem(ItemType.ATTACK_DAMAGE).getIntensity());
    }

    public int getAttackRange() {
        return robot.getAttackRange().intValue()
                + (getItem(ItemType.ATTACK_RANGE) == null
                ? 0
                : getItem(ItemType.ATTACK_RANGE).getIntensity());
    }

    public int getMovementRate() {
        return robot.getMovementRate().intValue()
                + (getItem(ItemType.MOVEMENT_RATE) == null
                ? 0
                : getItem(ItemType.MOVEMENT_RATE).getIntensity());
    }

    public Align getAlignment() {
        return alignment;
    }

    public Item getItem(ItemType itemType) {
        return items[itemType.ordinal()];
    }

    public void setAlignment(Align alignment) {
        this.alignment = alignment;
    }

    public void setItem(Item item) {
        items[item.getItemType().ordinal()] = item;
    }

    public void decreaseItemDuration(ItemType itemType) {
        this.items[itemType.ordinal()].decreaseDuration();
    }

    public void deleteItem(ItemType itemType) {
        items[itemType.ordinal()] = null;
    }

    public void takeDamage(int healthPoints) {
        robot.setHealth(robot.getHealth().subtract(new BigDecimal(healthPoints)));
        if (getHealth() < 0) {
            robot.setHealth(BigDecimal.ZERO);
        }
    }

    public boolean isKnockedOut() {
        return getHealth() == 0;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "\n\tx=" + x +
                ",\n\ty=" + y +
                ",\n\trobot=\n" + robot +
                ",\n\talignment=" + alignment +
                ",\n\titems=" + Arrays.toString(items) +
                '}';
    }

    public void move(Direction direction, int steps) {
        this.alignment = Align.values()[direction.ordinal()];
        this.x += direction.x * steps;
        this.y += direction.y * steps;
    }
}