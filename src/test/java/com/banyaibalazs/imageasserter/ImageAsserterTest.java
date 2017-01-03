package com.banyaibalazs.imageasserter;

import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertFalse;

public class ImageAsserterTest {

    @Test
    public void similarity_similarEmulatorImages_assertionSuccess() {
        new ImageAsserter()
                .with(new HistogramSimilarityAssertion())
                .with(new FeatureSimilarityAssertion())
                .assertSimilarity(getResourcePath("MobileAppEM.jpg"), getResourcePath("WebConsoleEM.jpg"));
    }

    @Test
    public void similarity_similarCameraImages_assertionSuccess() {
        new ImageAsserter()
                .with(new HistogramSimilarityAssertion())
                .with(new FeatureSimilarityAssertion())
                .assertSimilarity(getResourcePath("MobileAppCam.jpg"), getResourcePath("WebConsoleCam.jpg"));
    }

    @Test
    public void similarity_cameraImageVsEmulatorImage_assertionFails() {
        boolean fail = false;
        try {
            new ImageAsserter()
                    .with(new HistogramSimilarityAssertion())
                    .with(new FeatureSimilarityAssertion())
                    .assertSimilarity(getResourcePath("MobileAppEM.jpg"), getResourcePath("WebConsoleCam.jpg"));
            fail = true;
        } catch (AssertionError e) {

        }
        assertFalse("this method should have thrown assertionFailedError", fail);
    }

    @Test
    public void similarity_emulatorImageVsBlankImage_assertionFails() {
        boolean fail = false;
        try {
            new ImageAsserter()
                    .with(new HistogramSimilarityAssertion())
                    .with(new FeatureSimilarityAssertion())
                    .assertSimilarity(getResourcePath("MobileAppBad.png"), getResourcePath("WebConsoleBad.png"));
            fail = true;
        } catch (AssertionError e) {

        }
        assertFalse("this method should have thrown assertionFailedError", fail);
    }

    private static String getResourcePath(String name){
        File file = new File(ImageAsserterTest.class.getClassLoader().getResource(name).getFile());
        return file.getAbsolutePath();
    }

}
