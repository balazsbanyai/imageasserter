package com.banyaibalazs.imageasserter;

/**
 * Provides an assertion that is based on the similarity between "good" edges and corners of images.
 * This matcher is not orientation-aware.
 * */
public class FeatureSimilarityAssertion extends ImageSimilarityAssertion {

    private static final double DEFAULT_TRESHOLD = .3f;

    public FeatureSimilarityAssertion(double treshold) {
        super(treshold, new FeatureComparison());
    }

    public FeatureSimilarityAssertion() {
        this(DEFAULT_TRESHOLD);
    }

}
