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
import com.btcag.robotwars.Api.model.Align;
import com.btcag.robotwars.Api.model.MovementType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * Move
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-01-23T09:27:40.603280371Z[GMT]")

public class Move {
  @SerializedName("id")
  private String id = null;

  @SerializedName("playerId")
  private String playerId = null;

  @SerializedName("movementType")
  private MovementType movementType = null;

  @SerializedName("align")
  private Align align = null;

  @SerializedName("mapIndex")
  private BigDecimal mapIndex = null;

  public Move id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Die Move ID für den angelegten Move
   * @return id
  **/
  @Schema(example = "1234", required = true, description = "Die Move ID für den angelegten Move")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Move playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

   /**
   * Der Spieler , der seinen Roboter bewegt oder Aktionen durchführt
   * @return playerId
  **/
  @Schema(example = "1234", required = true, description = "Der Spieler , der seinen Roboter bewegt oder Aktionen durchführt")
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public Move movementType(MovementType movementType) {
    this.movementType = movementType;
    return this;
  }

   /**
   * Get movementType
   * @return movementType
  **/
  @Schema(required = true, description = "")
  public MovementType getMovementType() {
    return movementType;
  }

  public void setMovementType(MovementType movementType) {
    this.movementType = movementType;
  }

  public Move align(Align align) {
    this.align = align;
    return this;
  }

   /**
   * Get align
   * @return align
  **/
  @Schema(required = true, description = "")
  public Align getAlign() {
    return align;
  }

  public void setAlign(Align align) {
    this.align = align;
  }

  public Move mapIndex(BigDecimal mapIndex) {
    this.mapIndex = mapIndex;
    return this;
  }

   /**
   * Get mapIndex
   * @return mapIndex
  **/
  @Schema(required = true, description = "")
  public BigDecimal getMapIndex() {
    return mapIndex;
  }

  public void setMapIndex(BigDecimal mapIndex) {
    this.mapIndex = mapIndex;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Move move = (Move) o;
    return Objects.equals(this.id, move.id) &&
        Objects.equals(this.playerId, move.playerId) &&
        Objects.equals(this.movementType, move.movementType) &&
        Objects.equals(this.align, move.align) &&
        Objects.equals(this.mapIndex, move.mapIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, playerId, movementType, align, mapIndex);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Move {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    movementType: ").append(toIndentedString(movementType)).append("\n");
    sb.append("    align: ").append(toIndentedString(align)).append("\n");
    sb.append("    mapIndex: ").append(toIndentedString(mapIndex)).append("\n");
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
