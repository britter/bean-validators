# bean-validators

[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fbritter%2Fbean-validators%2Fbadge%3Fref%3Dmain&style=flat)](https://actions-badge.atrox.dev/britter/bean-validators/goto?ref=main)
[![Coverage Status](https://coveralls.io/repos/britter/bean-validators/badge.svg)](https://coveralls.io/r/britter/bean-validators)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/dd0d4de9bb9e4160803b32b3ff0889c7)](https://www.codacy.com/app/britter/bean-validators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/)
[![License](https://img.shields.io/:license-apache-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](https://beanvalidation.org/).
Most validators use [Apache Commons Lang3](https://commons.apache.org/lang/) and
[Apache Commons Validator](https://commons.apache.org/validator/) functionality to implement various validation routines.

More information can be found on the [project website](https://britter.github.io/bean-validators).

## How to release

- Set the version to be released in `build.gradle.kts` and commit and tag the change
- Sign an arbitrary file to force gpg-agent to store the password for the gpg key (see https://github.com/gradle/gradle/issues/11706)
- Run `./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository`
- Deploy the site for the new version `./gradlew gitPublishPush`
- Set the version to the next SNAPSHOT version in `build.gradle.kts` and commit and push the change

## License

Code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Additional Resources

+ [javax.validation documentation](https://beanvalidation.org/)
+ [javax.validation reference implementation](https://hibernate.org/validator/)
+ [Apache Commons Lang3](https://commons.apache.org/lang/)
+ [Apache Commons Validator](https://commons.apache.org/validator/)
