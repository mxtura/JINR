/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jtabbedpane;

import com.google.gson.annotations.Expose;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mxtur
 */
public class Configurations
        implements Cloneable {
    public String title;
    public List<ArrayList> devices = new ArrayList<>();
    
    @Expose(serialize = false) 
    public Color condition = Color.YELLOW;

    public Configurations(){
        this.title = "notitle   ";
    };
    
    public Configurations(String title) {
        this.title = title;
    }

    public void Add(String address) {
        ArrayList<Object> addressesArr = new ArrayList();
        addressesArr.add(address);
        addressesArr.add(true);
        this.devices.add(addressesArr);
    }

    public void DeleteAd(int indAd) {
        this.devices.remove(indAd);
    }

    public void ChangeNameAd(int ind, String nm) {
        ((ArrayList<String>) this.devices.get(ind)).set(0, nm);
    }

    public Configurations clone() {
        try {
            Configurations copy = (Configurations) super.clone();
            copy.devices = new ArrayList<>();
            for (ArrayList<?> innerList : this.devices) {
                copy.devices.add(new ArrayList(innerList));
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Configurations that = (Configurations) o;
        return (this.title.equals(that.title) && this.devices.equals(that.devices));
    }

    public int hashCode() {
        return Objects.hash(new Object[] { this.title, this.devices });
    }
}