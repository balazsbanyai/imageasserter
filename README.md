# ImageAsserter
This is a test utility which intended to help to assert whether 2 images are similar or not.

# health
[![Build Status](https://travis-ci.org/balazsbanyai/imageasserter.svg?branch=master)](https://travis-ci.org/balazsbanyai/imageasserter)

# usage
```java

...

new ImageAsserter()
    .with(new HistogramSimilarityAssertion()) // Default treshold
    .with(new FeatureSimilarityAssertion(.5f)) // Custom treshold
    .assertSimilarity(getResourcePath("path_to_file1.jpg"), getResourcePath("path_to_file2.jpg"));

```

# maven

```groovy
compile 'com.banyaibalazs.imageasserter:imageasserter:2.0.0'
```

# license
Apache License Version 2.0

