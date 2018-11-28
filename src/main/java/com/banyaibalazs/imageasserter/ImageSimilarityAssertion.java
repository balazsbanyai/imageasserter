package com.banyaibalazs.imageasserter;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;

import java.text.DecimalFormat;

import static org.opencv.imgproc.Imgproc.resize;

/**
 * Please use the {@link ImageSimilarityMatcher} Hamcrest matcher instead.
 * */
@Deprecated
public abstract class ImageSimilarityAssertion {

    private final double treshold;
    private final SimilarityComparison comparison;

    protected ImageSimilarityAssertion(double treshold, SimilarityComparison comparison) {
        this.treshold = treshold;
        this.comparison = comparison;
    }

    public void assertSimilarity(String path1, String path2) {
        Mat im1 = Highgui.imread(path1);
        Mat im2 = Highgui.imread(path2);

        int width = Math.max(im1.width(), im2.width());
        int height = Math.max(im1.height(), im1.height());

        resize(im1, im1, new Size(width, height));
        resize(im2, im2, new Size(width, height));

        double result = comparison.compare(im1, im2);

        if (result < treshold) {
            throw new AssertionError(
                    new StringBuilder()
                        .append("Image similarity ")
                        .append(new DecimalFormat("#.00").format(result))
                        .append(" was below the treshold ")
                        .append(new DecimalFormat("#.00").format(treshold))
                        .append(" (using ")
                        .append(comparison.getClass().getSimpleName())
                        .append(")")
            );
        }
    }



}
