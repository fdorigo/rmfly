/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket.components;

/**
 *
 * @author fdorigo
 */
public enum AirplaneType {
    VINTAGE(1,"Vintage", "cat_vintage"),
    WARBIRD(2,"Warbird", "cat_warbird"),
    HOMEKIT(3,"Homebuilt, Kit", "cat_home_kit"),
    HOMEPLANS(4,"Homebuilt, Plans", "cat_home_plan"),
    LIGHTSPORT(5,"Light Sport", "cat_light_sport"),
    CUSTOM(6,"Custom/Modern", "cat_modern");
    
    
    private final Integer value;
    private final String name;
    private final String id;
    
    AirplaneType(final Integer value, final String name, String id)
    {
        this.value = value;
        this.name = name;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public String getWicketId() {
        return this.id;
    }
    
    public static AirplaneType fromString(String text) {
    if (text != null) {
      for (AirplaneType b : AirplaneType.values()) {
        if (text.equalsIgnoreCase(b.name)) {
          return b;
        }
      }
    }
    return null;
  }
    
}
