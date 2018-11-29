package com.banyaibalazs.imageasserter;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;

/**
 * Represents an image that can be used for matching.
 * */
public abstract class MatchableImage {

    static {
        OpenCV.loadLibrary();
    }

    /**
     * Creates an instance of {@link MatchableImage} from a resource file that is on the classpath.
     * */
    public static MatchableImage imageFromResource(String resourcePath) {
        File file = new File(MatchableImage.class.getClassLoader().getResource(resourcePath).getFile());
        return imageFromFile(file);
    }

    /**
     * Creates an instance of {@link MatchableImage} from a file.
     * */
    public static MatchableImage imageFromFile(File file) {
        return new MatchableImage() {
            @Override
            public Mat toMatrix() {
                return Highgui.imread(file.getAbsolutePath());
            }
        };
    }

    abstract Mat toMatrix();
}
