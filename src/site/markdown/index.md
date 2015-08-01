# bean-validators

[![Build Status](https://travis-ci.org/britter/bean-validators.svg?branch=master)](https://travis-ci.org/britter/bean-validators)
[![Coverage Status](https://coveralls.io/repos/britter/bean-validators/badge.svg)](https://coveralls.io/r/britter/bean-validators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](http://beanvalidation.org/).

There are annotations available for various basic Java types such as:

- Strings
    - @Blank
    - @Alphabetic
- Files
    - @Readable
    - @Writable
    - @Directory
- Networking
    - @Domain
    - @IP
    - @Port
- Misc
    - @Empty

Most validators use [Apache Commons Lang3](http://commons.apache.org/lang/) and 
[Apache Commons Validator](http://commons.apache.org/validator/) functionality to implement various validation routines.

## Documentation

Sample usage can be found on the [Examples](examples.html) page. Further more the latest [JavaDoc](apidocs/index.html) can be browsed.
