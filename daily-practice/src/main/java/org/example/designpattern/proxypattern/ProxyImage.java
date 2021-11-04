package org.example.designpattern.proxypattern;

public class ProxyImage implements Image {
    @Override
    public void disPlay() {
        if (null == realImage) {
            realImage = new RealImage(fileName);
        }
        realImage.disPlay();
    }


    private String fileName;
    private RealImage realImage;
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
}
