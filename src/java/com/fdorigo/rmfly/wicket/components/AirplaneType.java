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
    VINTAGE(1,"Vintage"),
    WARBIRD(2,"Warbird"),
    HOMEKIT(3,"Homenuilt, Kit"),
    HOMEPLANS(4,"Homenuilt, Plans"),
    LIGHTSPORT(5,"Light Sport"),
    CUSTOM(6,"Custom/Modern");
    
    
    private Integer value;
    private String name;
    AirplaneType(Integer value, String name)
    {
        this.value = value;
        this.name = name;
    }
    
    public String getByType()
    {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
