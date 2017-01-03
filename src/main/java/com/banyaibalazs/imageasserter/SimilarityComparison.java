package com.banyaibalazs.imageasserter;

import org.opencv.core.Mat;

public interface SimilarityComparison {
    double compare(Mat im1, Mat im2);
}
