package com.banyaibalazs.imageasserter;

/**
 * Provides an assertion that is based on the similarity between the number of pixels for each tonal value.
 * This assertion does not check the alignment, only the sum of pixels.
 * */
public class HistogramSimilarityAssertion extends ImageSimilarityAssertion {

    private static final double DEFAULT_TRESHOLD = .7f;

    public HistogramSimilarityAssertion(double treshold) {
        super(treshold, new HistogramComparison());
    }

    public HistogramSimilarityAssertion() {
        this(DEFAULT_TRESHOLD);
    }
}
