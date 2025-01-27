/*
 * Robot Wars API
 * Eine Api für das Spiel Robot Wars
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.btcag.robotwars.Api.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * Robot
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-01-23T09:27:40.603280371Z[GMT]")

public class Robot {
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("health")
  private BigDecimal health = null;

  @SerializedName("attackDamage")
  private BigDecimal attackDamage = null;

  @SerializedName("attackRange")
  private BigDecimal attackRange = null;

  @SerializedName("movementRate")
  private BigDecimal movementRate = null;

  public Robot id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Einzigartige Id des Roboters
   * @return id
  **/
  @Schema(example = "1234", required = true, description = "Einzigartige Id des Roboters")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Robot name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name des Roboters
   * @return name
  **/
  @Schema(example = "Wall e", required = true, description = "Name des Roboters")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Robot health(BigDecimal health) {
    this.health = health;
    return this;
  }

   /**
   * Leben des Roboters
   * @return health
  **/
  @Schema(example = "10", required = true, description = "Leben des Roboters")
  public BigDecimal getHealth() {
    return health;
  }

  public void setHealth(BigDecimal health) {
    this.health = health;
  }

  public Robot attackDamage(BigDecimal attackDamage) {
    this.attackDamage = attackDamage;
    return this;
  }

   /**
   * Schaden des Roboters
   * @return attackDamage
  **/
  @Schema(example = "10", required = true, description = "Schaden des Roboters")
  public BigDecimal getAttackDamage() {
    return attackDamage;
  }

  public void setAttackDamage(BigDecimal attackDamage) {
    this.attackDamage = attackDamage;
  }

  public Robot attackRange(BigDecimal attackRange) {
    this.attackRange = attackRange;
    return this;
  }

   /**
   * Angriffradius des Roboters
   * @return attackRange
  **/
  @Schema(example = "10", required = true, description = "Angriffradius des Roboters")
  public BigDecimal getAttackRange() {
    return attackRange;
  }

  public void setAttackRange(BigDecimal attackRange) {
    this.attackRange = attackRange;
  }

  public Robot movementRate(BigDecimal movementRate) {
    this.movementRate = movementRate;
    return this;
  }

   /**
   * Bewegungsradius des Roboters
   * @return movementRate
  **/
  @Schema(example = "10", required = true, description = "Bewegungsradius des Roboters")
  public BigDecimal getMovementRate() {
    return movementRate;
  }

  public void setMovementRate(BigDecimal movementRate) {
    this.movementRate = movementRate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Robot robot = (Robot) o;
    return Objects.equals(this.id, robot.id) &&
        Objects.equals(this.name, robot.name) &&
        Objects.equals(this.health, robot.health) &&
        Objects.equals(this.attackDamage, robot.attackDamage) &&
        Objects.equals(this.attackRange, robot.attackRange) &&
        Objects.equals(this.movementRate, robot.movementRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, health, attackDamage, attackRange, movementRate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Robot {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    health: ").append(toIndentedString(health)).append("\n");
    sb.append("    attackDamage: ").append(toIndentedString(attackDamage)).append("\n");
    sb.append("    attackRange: ").append(toIndentedString(attackRange)).append("\n");
    sb.append("    movementRate: ").append(toIndentedString(movementRate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
