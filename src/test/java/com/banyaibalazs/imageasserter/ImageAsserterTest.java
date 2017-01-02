package com.banyaibalazs.imageasserter;

import org.junit.Test;

import java.io.File;

import static com.banyaibalazs.imageasserter.ImageAsserter.assertSimilarity;
import static junit.framework.TestCase.fail;

/*
 * @author bbanyai, @date 12/29/16 10:32 PM
 */
public class ImageAsserterTest {

    @Test
    public void similarity_similarEmulatorImages_assertionSuccess() {
        assertSimilarity(getResourcePath("MobileAppEM.jpg"), getResourcePath("WebConsoleEM.jpg"));
    }

    @Test
    public void similarity_similarCameraImages_assertionSuccess() {
        assertSimilarity(getResourcePath("MobileAppCam.jpg"), getResourcePath("WebConsoleCam.jpg"));
    }

    @Test
    public void similarity_notSimilarCameraImages_assertionFails() {
        try {
            assertSimilarity(getResourcePath("MobileAppEM.jpg"), getResourcePath("WebConsoleCam.jpg"));
            fail("this method should have thrown assertionFailedError");
        } catch (AssertionError e) {

        }
    }

    private static String getResourcePath(String name){
        File file = new File(ImageAsserterTest.class.getClassLoader().getResource(name).getFile());
        return file.getAbsolutePath();
    }

}
