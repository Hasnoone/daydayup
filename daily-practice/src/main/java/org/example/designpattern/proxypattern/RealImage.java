package org.example.designpattern.proxypattern;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void disPlay() {
        System.out.println("display file :" + this.fileName);
    }


    public void loadFromDisk(String fileName) {
        System.out.println("Loading from disk :" + fileName);
    }

}
