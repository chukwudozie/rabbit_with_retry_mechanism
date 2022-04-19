package com.emeka.rabbit_consumer.entity;

public class Picture {
    private String name;
    private String type; // Either jpg, png or svg
    private String source;
    private  long size;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getSource() {return source;}

    public void setSource(String source) {this.source = source;}

    public long getSize() {return size;}

    public void setSize(long size) {this.size = size;}

    @Override
    public String toString() {
        return "Picture{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", size=" + size +
                '}';
    }
}
