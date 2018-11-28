package com.banyaibalazs.imageasserter;

/**
 * Please use the {@link ImageSimilarityMatcher} Hamcrest matcher instead.
 * */
@Deprecated
public class FeatureSimilarityAssertion extends ImageSimilarityAssertion {

    private static final double DEFAULT_TRESHOLD = .3f;

    public FeatureSimilarityAssertion(double treshold) {
        super(treshold, new FeatureComparison());
    }

    public FeatureSimilarityAssertion() {
        this(DEFAULT_TRESHOLD);
    }

}
