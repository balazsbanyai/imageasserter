package com.banyaibalazs.imageasserter;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

class FeatureComparison implements SimilarityComparison {

    @Override
    public double compare(Mat im1, Mat im2) {
        int COLOR = Imgproc.COLOR_BGR2GRAY;
        Imgproc.cvtColor(im1, im1, COLOR);
        Imgproc.cvtColor(im2, im2, COLOR);
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.PYRAMID_FAST);

        int descriptor = DescriptorExtractor.ORB;
        DescriptorExtractor extractor = DescriptorExtractor.create(descriptor);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        MatOfKeyPoint im1Keypoints = new MatOfKeyPoint();
        MatOfKeyPoint im2Keypoints = new MatOfKeyPoint();
        Mat im1Descriptors = new Mat();
        Mat im2Descriptors = new Mat();
        MatOfDMatch matches = new MatOfDMatch();
        detector.detect(im1, im1Keypoints);
        detector.detect(im2, im2Keypoints);

        extractor.compute(im1, im1Keypoints, im1Descriptors);
        extractor.compute(im2, im2Keypoints, im2Descriptors);

        if (im1Descriptors.rows() > 0 && im2Descriptors.rows() > 0) {
            matcher.match(im1Descriptors, im2Descriptors, matches);

            List<DMatch> matchesList = matches.toList();
            List<DMatch> matchesFiltered = new ArrayList<>();
            float mindDist = 20;
            for (int i = 0; i < matchesList.size(); i++) {
                if (matchesList.get(i).distance <= mindDist) {
                    matchesFiltered.add(matches.toList().get(i));
                }
            }

            int countOfKeypointsCouldHaveBeenFound = Math.min(im1Keypoints.rows(), im2Keypoints.rows());
            int countOfFoundMatches = matchesFiltered.size();

            float result = (float) countOfFoundMatches / countOfKeypointsCouldHaveBeenFound;
            return (float) Math.sqrt(result);
        } else {
            return 0f;
        }
    }
}
