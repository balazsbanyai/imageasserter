package com.banyaibalazs.imageasserter;

import org.hamcrest.Matcher;

/**
 * Matches images based on various properties, like histogram and features.
 * Each property that should be matched against, must be configured explicitly
 * using the related methods of this class.
 * */
public interface ImageSimilarityMatcher extends Matcher<MatchableImage> {

    /**
     * Factory method that creates an instance of {@link ImageSimilarityMatcher}
     * */
    static ImageSimilarityMatcher similarityTo(MatchableImage other) {
        return new ImageSimilarityMatcherImpl(other);
    }

    /**
     * Configures this matcher to compare the histograms of the images,
     * and match their similarity using the given matcher.
     * */
    ImageSimilarityMatcher inHistogram(Matcher<Double> matcher);

    /**
     * Configures this matcher to compare the features of the images,
     * and match their similarity using the given matcher.
     * */
    ImageSimilarityMatcher inFeatures(Matcher<Double> matcher);
}
