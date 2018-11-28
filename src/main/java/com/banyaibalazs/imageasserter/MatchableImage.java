package com.banyaibalazs.imageasserter;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;

public abstract class MatchableImage {

    static {
        OpenCV.loadLibrary();
    }

    static MatchableImage imageFromResource(String resourcePath) {
        File file = new File(MatchableImage.class.getClassLoader().getResource(resourcePath).getFile());
        return imageFromFile(file);
    }

    static MatchableImage imageFromFile(File file) {
        return new MatchableImage() {
            @Override
            public Mat toMatrix() {
                return Highgui.imread(file.getAbsolutePath());
            }
        };
    }

    abstract Mat toMatrix();
}
