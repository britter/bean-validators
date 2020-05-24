# bean-validators

[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fbritter%2Fbean-validators%2Fbadge&style=flat)](https://actions-badge.atrox.dev/britter/bean-validators/goto)
[![Coverage Status](https://coveralls.io/repos/britter/bean-validators/badge.svg)](https://coveralls.io/r/britter/bean-validators)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/dd0d4de9bb9e4160803b32b3ff0889c7)](https://www.codacy.com/app/britter/bean-validators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](http://beanvalidation.org/).
Most validators use [Apache Commons Lang3](http://commons.apache.org/lang/) and
[Apache Commons Validator](http://commons.apache.org/validator/) functionality to implement various validation routines.

More information can be found on the [project website](https://britter.github.io/bean-validators).

## How to release

- Set the version to be released in `build.gradle.kts` and commit and tag the change
- Sign an arbitrary file to force gpg-agent to store the password for the gpg key (see https://github.com/gradle/gradle/issues/11706)
- Run `./gradlew publishToSonatype`
- Login to Sonatype Nexus and close and release the staging repository
- Deploy the site for the new version `./gradlew gitPublishPush`
- Set the version to the next SNAPSHOT version in `build.gradle.kts` and commit and push the change

## License

Code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Additional Resources

+ [javax.validation documentation](http://beanvalidation.org/)
+ [javax.validation reference implementation](http://hibernate.org/validator/)
+ [Apache Commons Lang3](http://commons.apache.org/lang/)
+ [Apache Commons Validator](http://commons.apache.org/validator/)
