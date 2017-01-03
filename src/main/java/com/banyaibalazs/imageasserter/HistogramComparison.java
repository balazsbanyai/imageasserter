package com.banyaibalazs.imageasserter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;

import java.util.Collections;

import static org.opencv.imgproc.Imgproc.*;

class HistogramComparison implements SimilarityComparison {

    @Override
    public double compare(Mat im1, Mat im2) {
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

        Mat hist1 = new Mat();
        Imgproc.calcHist(Collections.singletonList(im1Hsv),
                channels,
                new Mat(),
                hist1,
                histSize,
                ranges,
                false);
        Core.normalize(hist1,
                hist1,
                0,
                1,
                Core.NORM_MINMAX,
                -1,
                new Mat());

        Mat hist2 = new Mat();
        Imgproc.calcHist(Collections.singletonList(im2Hsv),
                channels,
                new Mat(),
                hist2,
                histSize,
                ranges,
                false);
        Core.normalize(hist2,
                hist2,
                0,
                1,
                Core.NORM_MINMAX,
                -1,
                new Mat());

        return Imgproc.compareHist(hist1, hist2, CV_COMP_CORREL);
    }
}
