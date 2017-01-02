package com.banyaibalazs.imageasserter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

import static org.opencv.imgproc.Imgproc.*;

/*
 * @author bbanyai, @date 12/29/16 10:32 PM
 */
public class ImageAsserter {

    static final float DEFAULT_TRESHOLD = .85f;

    static {
        nu.pattern.OpenCV.loadLibrary();
    }

    public static void assertSimilarity(String path1, String path2) {
        assertSimilarity(path1, path2, DEFAULT_TRESHOLD);
    }

    public static void assertSimilarity(String path1, String path2, float treshold) {

        Mat im1 = Highgui.imread(path1);
        Mat im2 = Highgui.imread(path2);

        Mat im1Hsv = new Mat();
        Mat im2Hsv = new Mat();

        /// Convert to HSV
        cvtColor( im1, im1Hsv, COLOR_BGR2HSV );
        cvtColor( im2, im2Hsv, COLOR_BGR2HSV );

        /// Using 50 bins for hue and 60 for saturation
        int hueBins = 50;
        int saturationBins = 60;
        MatOfInt histSize = new MatOfInt( hueBins,  saturationBins);

        // hue varies from 0 to 179, saturation from 0 to 255
        MatOfFloat ranges =  new MatOfFloat( 0f, 180f, 0f, 256f );

        // we compute the histogram from the 0-th and 1-st channels
        MatOfInt channels = new MatOfInt(0, 1);

        Mat histRef = new Mat();
        Mat histSource = new Mat();

        ArrayList<Mat> histImages=new ArrayList<Mat>();
        histImages.add(im1Hsv);
        Imgproc.calcHist(histImages,
                channels,
                new Mat(),
                histRef,
                histSize,
                ranges,
                false);
        Core.normalize(histRef,
                histRef,
                0,
                1,
                Core.NORM_MINMAX,
                -1,
                new Mat());

        histImages=new ArrayList<Mat>();
        histImages.add(im2Hsv);
        Imgproc.calcHist(histImages,
                channels,
                new Mat(),
                histSource,
                histSize,
                ranges,
                false);
        Core.normalize(histSource,
                histSource,
                0,
                1,
                Core.NORM_MINMAX,
                -1,
                new Mat());


        double correlation = Imgproc.compareHist(histRef, histSource, CV_COMP_CORREL);

        if (correlation < treshold) {
            throw new AssertionError(String.format("Image similarity %1$,.2f was below the treshold %2$,.2f", correlation, treshold));
        }
    }

}
