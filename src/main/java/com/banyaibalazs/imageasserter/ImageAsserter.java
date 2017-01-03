package com.banyaibalazs.imageasserter;

import java.util.ArrayList;
import java.util.List;

public class ImageAsserter {

    static {
        nu.pattern.OpenCV.loadLibrary();
    }

    List<ImageSimilarityAssertion> assertions = new ArrayList<>();

    public ImageAsserter with(ImageSimilarityAssertion assertion) {
        assertions.add(assertion);
        return this;
    }

    public void assertSimilarity(String path1, String path2) {
        for (ImageSimilarityAssertion assertion : assertions) {
            assertion.assertSimilarity(path1, path2);
        }
    }


}
