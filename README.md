# bean-validators

[![Build Status](https://travis-ci.org/britter/bean-validators.svg?branch=master)](https://travis-ci.org/britter/bean-validators)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](http://beanvalidation.org/).

Validators will treat null and blank input strings as valid. If you want to make sure fields are not null/blank
you have to use `@NotNull` or `@NotBlank` in addition.

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
