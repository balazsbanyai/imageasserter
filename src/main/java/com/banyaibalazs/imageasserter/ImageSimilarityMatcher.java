package com.banyaibalazs.imageasserter;

import org.hamcrest.Matcher;

public interface ImageSimilarityMatcher extends Matcher<MatchableImage> {

    static ImageSimilarityMatcher similarityTo(MatchableImage other) {
        return new ImageSimilarityMatcherImpl(other);
    }

    ImageSimilarityMatcher inHistogram(Matcher<Double> matcher);

    ImageSimilarityMatcher inFeatures(Matcher<Double> matcher);
}
