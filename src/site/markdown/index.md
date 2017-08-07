<!---
 Copyright 2015 Benedikt Ritter

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
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
