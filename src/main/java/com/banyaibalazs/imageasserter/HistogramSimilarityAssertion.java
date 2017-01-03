package com.banyaibalazs.imageasserter;

public class HistogramSimilarityAssertion extends ImageSimilarityAssertion {

    private static final double DEFAULT_TRESHOLD = .7f;

    public HistogramSimilarityAssertion(double treshold) {
        super(treshold, new HistogramComparison());
    }

    public HistogramSimilarityAssertion() {
        this(DEFAULT_TRESHOLD);
    }
}
