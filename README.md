# bean-validators

[![Build Status](https://travis-ci.org/britter/bean-validators.svg?branch=master)](https://travis-ci.org/britter/bean-validators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](http://beanvalidation.org/).
Most validators use [Apache Commons Lang3](http://commons.apache.org/lang/) and 
[Apache Commons Validator](http://commons.apache.org/validator/) functionality to implement various validation routines.

Validators will treat null and blank input strings as valid. If you want to make sure fields are not null/blank
you have to use `@NotNull` or `@NotBlank` in addition.

## Alphabetic

```java
    /**
     * valid:
     *      abcd
     *
     * invalid:
     *      1234
     *      abcd1234
     *      ab cd1234
     *      ab cd
     */
    @Alphabetic
    private String alphabetic;

    /**
     * valid:
     *      abcd
     *      ab cd
     *
     * invalid:
     *      1234
     *      abcd1234
     *      ab cd1234
     */
    @Alphabetic(allowSpaces = true)
    private String alphabetic;
```

## AlphaNumeric

```java
    /**
     * valid:
     *      abcd
     *      abcd1234
     *      1234
     *
     * invalid:
     *      abcd?
     *      ab cd
     *      ab cd 1234
     *      ab cd 1234?
     */
    @AlphaNumeric
    private String alphaNum;

    /**
     * valid:
     *      abcd
     *      abcd1234
     *      ab cd
     *      ab cd 1234
     *
     * invalid:
     *      abcd?
     *      ab cd 1234?
     */
    @AlphaNumeric(allowSpaces = true)
    private String alphaNum;
```

## Blank

The opposite of `org.hibernate.validator.constraints.NotBlank`.

```java
    /**
     * valid:
     *      null
     *      ""
     *      "  "
     *
     * invalid:
     *      abcd
     */
    @Blank
    private String blank;
```

## Empty

The `@Empty` annotation is the opposite of `org.hibernate.validator.constraints.NotEmpty` and works on String, 
Collections, Maps and arrays. If any other type is annotated with `@Empty` a `javax.validation.ValidationException` will
be thrown. 

```java
    /**
     * valid:
     *      null
     *      ""
     *
     * invalid:
     *      "  "
     *      abcd
     */
    @Empty
    private String empty;

    /**
     * valid:
     *      null
     *      empty.isEmpty() == true
     *
     * invalid:
     *      empty.isEmpty() == false
     */
    @Empty
    private Collection<T> empty;

    /**
     * valid:
     *      null
     *      empty.isEmpty() == true
     *
     * invalid:
     *      empty.isEmpty() == false
     */
    @Empty
    private Map<K, V> empty;

    /**
     * valid:
     *      null
     *      empty.length == 0
     *
     * invalid:
     *      empty.length != 0
     */
    @Empty
    private T[] empty;
```

## ISBNs

```java
    /**
     * valid:
     *      3551551677
     *      978-3-55155-167-2
     *
     * invalid:
     *      35515516770
     *      978-3-551551672
     *      abc
     */
    @ISBN
    private String isbn;

    /**
     * valid:
     *      3551551677
     *
     * invalid:
     *      35515516770
     *      978-3-55155-167-2
     *      978-3-551551672
     *      abc
     */
    @ISBN(type = ISBNType.ISBN_10)
    private String isbn10;

    /**
     * valid:
     *      978-3-55155-167-2
     *
     * invalid:
     *      3551551677
     *      35515516770
     *      978-3-551551672
     *      abc
     */
    @ISBN(type = ISBNType.ISBN_13)
    private String isbn13;
```

## Numbers

```java
    /**
     * valid:
     *      1234
     *
     * invalid:
     *      abc
     *      -1234
     *      0.1234
     *      0x1234
     *      0e1234
     */
    @Numeric
    private String numeric;
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

