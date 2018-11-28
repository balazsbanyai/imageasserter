package com.banyaibalazs.imageasserter;


import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageAsserterTest {

    @Test
    void similarity_similarEmulatorImages_assertionSuccess() {
        new ImageAsserter()
                .with(new HistogramSimilarityAssertion())
                .with(new FeatureSimilarityAssertion())
                .assertSimilarity(getResourcePath("MobileAppEmulatorCamera.jpg"), getResourcePath("WebConsoleEmulatorCamera.jpg"));
    }

    @Test
    void similarity_similarCameraImages_assertionSuccess() {

        new ImageAsserter()
                .with(new HistogramSimilarityAssertion())
                .with(new FeatureSimilarityAssertion())
                .assertSimilarity(getResourcePath("MobileAppRealCamera.jpg"), getResourcePath("WebConsoleRealCamera.jpg"));
    }

    @Test
    void similarity_cameraImageVsEmulatorImage_assertionFails() {
        Runnable action = () -> new ImageAsserter()
                .with(new HistogramSimilarityAssertion())
                .with(new FeatureSimilarityAssertion())
                .assertSimilarity(getResourcePath("MobileAppEmulatorCamera.jpg"), getResourcePath("WebConsoleRealCamera.jpg"));

        assertThrows(AssertionError.class, action::run);
    }

    @Test
    void similarity_emulatorImageVsBlankImage_assertionFails() {
        Runnable action = () -> new ImageAsserter()
                .with(new HistogramSimilarityAssertion())
                .with(new FeatureSimilarityAssertion())
                .assertSimilarity(getResourcePath("MobileAppBad.png"), getResourcePath("WebConsoleBad.png"));

        assertThrows(AssertionError.class, action::run);
    }

    private static String getResourcePath(String name){
        File file = new File(ImageAsserterTest.class.getClassLoader().getResource(name).getFile());
        return file.getAbsolutePath();
    }

}
