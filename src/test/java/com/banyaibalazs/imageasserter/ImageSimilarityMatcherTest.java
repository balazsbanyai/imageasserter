package com.banyaibalazs.imageasserter;


import org.junit.jupiter.api.Test;

import static com.banyaibalazs.imageasserter.ImageSimilarityMatcher.similarityTo;
import static com.banyaibalazs.imageasserter.MatchableImage.imageFromResource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageSimilarityMatcherTest {

    @Test
    void similarity_similarEmulatorImages_assertionSuccess() {
        assertThat(imageFromResource("MobileAppEmulatorCamera.jpg"), similarityTo(imageFromResource("WebConsoleEmulatorCamera.jpg"))
                    .inHistogram(greaterThan(.7))
                    .inFeatures(greaterThan(.6)));
    }

    @Test
    void similarity_similarCameraImages_assertionSuccess() {
        assertThat(imageFromResource("MobileAppRealCamera.jpg"), similarityTo(imageFromResource("WebConsoleRealCamera.jpg"))
                .inHistogram(greaterThan(.7))
                .inFeatures(greaterThan(.2)));
    }

    @Test
    void similarity_cameraImageVsEmulatorImage_assertionFails() {
        Runnable action = () ->
            assertThat(imageFromResource("MobileAppEmulatorCamera.jpg"), similarityTo(imageFromResource("WebConsoleRealCamera.jpg"))
                    .inHistogram(greaterThan(.7))
                    .inFeatures(greaterThan(.2)));

        assertThrows(AssertionError.class, action::run);
    }

    @Test
    void similarity_emulatorImageVsBlankImage_assertionFails() {
        Runnable action = () ->
                assertThat(imageFromResource("MobileAppBad.png"), similarityTo(imageFromResource("WebConsoleBad.png"))
                .inHistogram(greaterThan(.7))
                .inFeatures(greaterThan(.2)));

        assertThrows(AssertionError.class, action::run);
    }


}
