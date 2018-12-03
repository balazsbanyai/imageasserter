package com.banyaibalazs.imageasserter;

import org.opencv.core.Mat;

/**
 * This class is marked deprecated, because it is scheduled to be removed from the public api.
 * */
@Deprecated
public interface SimilarityComparison {
    double compare(Mat im1, Mat im2);
}
