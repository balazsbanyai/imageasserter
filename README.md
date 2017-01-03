# ImageAsserter
This is a test utility which intended to help to assert whether 2 images are similar or not.

# health
[![Build Status](https://travis-ci.org/balazsbanyai/imageasserter.svg?branch=master)](https://travis-ci.org/balazsbanyai/imageasserter)
[![Code Coverage](https://img.shields.io/codecov/c/github/balazsbanyai/imageasserter/master.svg)](https://codecov.io/github/balazsbanyai/imageasserter?branch=master)


# usage
```java

...

new ImageAsserter()
    .with(new HistogramSimilarityAssertion()) // Default treshold
    .with(new FeatureSimilarityAssertion(.5f)) // Custom treshold
    .assertSimilarity("path_to_file1.jpg", "path_to_file2.jpg");

```

# maven

```groovy
compile 'com.banyaibalazs.imageasserter:imageasserter:2.0.0'
```

# license
Apache License Version 2.0

