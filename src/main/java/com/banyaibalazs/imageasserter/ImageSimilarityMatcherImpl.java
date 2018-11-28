package com.banyaibalazs.imageasserter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.opencv.imgproc.Imgproc.resize;

class ImageSimilarityMatcherImpl extends TypeSafeMatcher<MatchableImage> implements ImageSimilarityMatcher {

    private MatchableImage actual;

    private final List<Matcher<? super MatchableImage>> matchers = new ArrayList<>();
    private final List<Matcher<? super MatchableImage>> failed = new ArrayList<>();

    ImageSimilarityMatcherImpl(MatchableImage actual) {
        this.actual = actual;
    }

    @Override
    protected boolean matchesSafely(MatchableImage item) {
        return matchers.stream()
                .filter(matcher -> !matcher.matches(item))
                .peek(failed::add)
                .collect(Collectors.toList())
                .isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        failed.forEach(description::appendDescriptionOf);
    }

    @Override
    protected void describeMismatchSafely(MatchableImage item, Description mismatchDescription) {
        failed.forEach(matcher -> matcher.describeMismatch(item, mismatchDescription));
    }

    @Override
    public ImageSimilarityMatcher inHistogram(Matcher<Double> matcher) {
        matchers.add(new HistogramMatcher(matcher));
        return this;
    }

    @Override
    public ImageSimilarityMatcher inFeatures(Matcher<Double> matcher) {
        matchers.add(new FeatureMatcher(matcher));
        return this;
    }

    private class HistogramMatcher extends TypeSafeMatcher<MatchableImage> {

        private Matcher<Double> matcher;

        HistogramMatcher(Matcher<Double> matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean matchesSafely(MatchableImage item) {
            return matcher.matches(calculateSimilarity(item));
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("histogram similarity of the images is expected to be ").appendDescriptionOf(matcher);
        }

        @Override
        protected void describeMismatchSafely(MatchableImage item, Description mismatchDescription) {
            matcher.describeMismatch(calculateSimilarity(item), mismatchDescription);
        }

        private double calculateSimilarity(MatchableImage item) {
            return new HistogramComparison().compare(actual.toMatrix(), item.toMatrix());
        }
    }

    private class FeatureMatcher extends TypeSafeMatcher<MatchableImage> {

        private Matcher<Double> matcher;

        FeatureMatcher(Matcher<Double> matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean matchesSafely(MatchableImage item) {
            return matcher.matches(calculateSimilarity(item));
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("feature similarity of the images is expected to be ").appendDescriptionOf(matcher);
        }

        @Override
        protected void describeMismatchSafely(MatchableImage item, Description mismatchDescription) {
            matcher.describeMismatch(calculateSimilarity(item), mismatchDescription);
        }

        private double calculateSimilarity(MatchableImage item) {
            Mat im1 = actual.toMatrix();
            Mat im2 = item.toMatrix();
            int width = Math.max(im1.width(), im2.width());
            int height = Math.max(im1.height(), im1.height());
            resize(im1, im1, new Size(width, height));
            resize(im2, im2, new Size(width, height));
            return new FeatureComparison().compare(im1, im2);
        }
    }
}
