# bean-validators

[![Build Status](https://travis-ci.org/britter/bean-validators.svg?branch=master)](https://travis-ci.org/britter/bean-validators)
[![Coverage Status](https://coveralls.io/repos/britter/bean-validators/badge.svg)](https://coveralls.io/r/britter/bean-validators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](http://beanvalidation.org/).
Most validators use [Apache Commons Lang3](http://commons.apache.org/lang/) and
[Apache Commons Validator](http://commons.apache.org/validator/) functionality to implement various validation routines.

More information can be found on the [project website](https://britter.github.io/bean-validators).

## Usage

Add the following to your Maven dependency list:

```
<dependency>
  <groupId>com.github.britter</groupId>
  <artifactId>bean-validators</artifactId>
  <version>0.6.1</version>
</dependency>
```

## How to release

`mvn -Prelease release:prepare -Darguments=-Dgpg.keyname=<key to use>`

`mvn -Prelease release:perform -Darguments=-Dgpg.keyname=<key to use>`

## License

Code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Additional Resources

+ [javax.validation documentation](http://beanvalidation.org/)
+ [javax.validation reference implementation](http://hibernate.org/validator/)
+ [Apache Commons Lang3](http://commons.apache.org/lang/)
+ [Apache Commons Validator](http://commons.apache.org/validator/)
