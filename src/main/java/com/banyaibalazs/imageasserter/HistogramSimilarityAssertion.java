package com.banyaibalazs.imageasserter;

/**
 * Please use the {@link ImageSimilarityMatcher} Hamcrest matcher instead.
 * */
@Deprecated
public class HistogramSimilarityAssertion extends ImageSimilarityAssertion {

    private static final double DEFAULT_TRESHOLD = .7f;

    public HistogramSimilarityAssertion(double treshold) {
        super(treshold, new HistogramComparison());
    }

    public HistogramSimilarityAssertion() {
        this(DEFAULT_TRESHOLD);
    }
}
