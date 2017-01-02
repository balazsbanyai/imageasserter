# ImageAsserter
This is a test utility which intended to help to assert whether 2 images are similar or not.

# health
[![Build Status](https://travis-ci.org/balazsbanyai/imageasserter.svg?branch=master)](https://travis-ci.org/balazsbanyai/imageasserter)

# usage
```java
import static com.banyaibalazs.imageasserter.ImageAsserter.assertSimilarity;

...

assertSimilarity("path_to_file1.jpg", "path_to_file2.jpg") // Default treshold
assertSimilarity("path_to_file1.jpg", "path_to_file2.jpg", 0.9) // Custom treshold

```

# maven
The artifact is not yet published to jcenter nor mavenCentral, so an additional repo has to be added to your project:

```groovy
compile 'com.banyaibalazs.imageasserter:imageasserter:1.0.0'
```

# license
Apache License Version 2.0

