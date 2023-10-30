/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jtabbedpane;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author mxtur
 */
public class ConfigurationsTypeAdapter extends TypeAdapter<Configurations> {

    @Override
    public void write(JsonWriter out, Configurations config) throws IOException {
        out.beginObject();
        out.name("title").value(config.title);
        out.name("devices");
        out.beginArray();
        for (ArrayList<Object> device : config.devices) {
            out.beginObject();
            out.name("name").value((String) device.get(0));
            out.name("critical").value((boolean) device.get(1));
            out.name("logs_on").value((boolean) device.get(2));
            out.endObject();
        }
        out.endArray();

        out.endObject();
    }

    @Override
    public Configurations read(JsonReader in) throws IOException {
        Configurations config = new Configurations();

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "title":
                    config.title = in.nextString();
                    break;
                case "devices":
                    in.beginArray();
                    List<ArrayList> devices = new ArrayList<>();
                    while (in.hasNext()) {
                        in.beginObject();
                        ArrayList<Object> innerList = new ArrayList<>();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "name":
                                    innerList.add(in.nextString());
                                    break;
                                case "critical":
                                    innerList.add(in.nextBoolean());
                                    break;
                                case "logs_on":
                                    innerList.add(in.nextBoolean());
                                    break;
                                default:
                                    break;
                            }
                        }
                        in.endObject();
                        devices.add(innerList);
                    }
                    in.endArray();
                    config.devices = devices;
                    break;
            }
        }
        in.endObject();
        config.condition = Color.YELLOW;
        return config;
    }

}
