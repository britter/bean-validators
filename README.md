# bean-validators

[![Build Status](https://travis-ci.org/britter/bean-validators.svg?branch=master)](https://travis-ci.org/britter/bean-validators)
[![Coverage Status](https://coveralls.io/repos/britter/bean-validators/badge.svg)](https://coveralls.io/r/britter/bean-validators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.britter/bean-validators/)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Additional validator implementations for [javax.validation](http://beanvalidation.org/).
Most validators use [Apache Commons Lang3](http://commons.apache.org/lang/) and 
[Apache Commons Validator](http://commons.apache.org/validator/) functionality to implement various validation routines.

Validators will treat null and blank input strings as valid. If you want to make sure fields are not null/blank
you have to use `@NotNull` or `@NotBlank` in addition.

## General

### Empty

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
     *      "abcd"
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

## Strings

### Alphabetic

```java
    /**
     * valid:
     *      "abcd"
     *
     * invalid:
     *      "1234"
     *      "abcd1234"
     *      "ab cd1234"
     *      "ab cd"
     */
    @Alphabetic
    private String alphabetic;

    /**
     * valid:
     *      "abcd"
     *      "ab cd"
     *
     * invalid:
     *      "1234"
     *      "abcd1234"
     *      "ab cd1234"
     */
    @Alphabetic(allowSpaces = true)
    private String alphabetic;
```

### AlphaNumeric

```java
    /**
     * valid:
     *      "abcd"
     *      "abcd1234"
     *      "1234"
     *
     * invalid:
     *      "abcd?"
     *      "ab cd"
     *      "ab cd 1234"
     *      "ab cd 1234?"
     */
    @AlphaNumeric
    private String alphaNum;

    /**
     * valid:
     *      "abcd"
     *      "abcd1234"
     *      "ab cd"
     *      "ab cd 1234"
     *
     * invalid:
     *      "abcd?"
     *      "ab cd 1234?"
     */
    @AlphaNumeric(allowSpaces = true)
    private String alphaNum;
```

### ASCII

```java
    /**
     * valid:
     *      "abcd"
     *      "abcd1234"
     *      "ab cd"
     *
     * invalid:
     *      "äöü"
     */
    @ASCII
    private String ascii;
```

### Blank

The opposite of `org.hibernate.validator.constraints.NotBlank`.

```java
    /**
     * valid:
     *      null
     *      ""
     *      "  "
     *
     * invalid:
     *      "abcd"
     */
    @Blank
    private String blank;
```

### ISBNs

```java
    /**
     * valid:
     *      "3551551677"
     *      "978-3-55155-167-2"
     *
     * invalid:
     *      "35515516770"
     *      "978-3-551551672"
     *      "abc"
     */
    @ISBN
    private String isbn;

    /**
     * valid:
     *      "3551551677"
     *
     * invalid:
     *      "35515516770"
     *      "978-3-55155-167-2"
     *      "978-3-551551672"
     *      "abc"
     */
    @ISBN(type = ISBNType.ISBN_10)
    private String isbn10;

    /**
     * valid:
     *      "978-3-55155-167-2"
     *
     * invalid:
     *      "3551551677"
     *      "35515516770"
     *      "978-3-551551672"
     *      "abc"
     */
    @ISBN(type = ISBNType.ISBN_13)
    private String isbn13;
```

### Java number

Makes sure a string contains a valid Java number.

```java
    /**
     * valid:
     *      "1234"
     *      "-1234"
     *      "1234L"
     *      "0x1A"
     *      "0X1A"
     *      "017"
     *      "0.17"
     *
     * invalid:
     *      "abcd"
     *      "0x1G"
     *      "018"
     *      "0,17"
     */
    @JavaNumber
    private String javaNum;
```

### Numbers

```java
    /**
     * valid:
     *      "1234"
     *
     * invalid:
     *      "abc"
     *      "-1234"
     *      "0.1234"
     *      "0x1234"
     *      "0e1234"
     */
    @Numeric
    private String numeric;
```

## Files

### Absolute

```java
    /**
     * valid:
     *      file.isAbsolute() == true
     *
     * invalid:
     *      dir.isAbsolute() == false
     */
    @Absolute
    private File file;
```

### Not Absolute

```java
    /**
     * valid:
     *      file.isAbsolute() == false
     *
     * invalid:
     *      dir.isAbsolute() == true
     */
    @NotAbsolute
    private File file;
```

### Directory

```java
    /**
     * valid:
     *      dir.isDirectory() == true
     *
     * invalid:
     *      dir.isDirectory() == false
     */
    @Directory
    private File dir;
```

### Not Directory

```java
    /**
     * valid:
     *      dir.isDirectory() == false
     *
     * invalid:
     *      dir.isDirectory() == true
     */
    @NotDirectory
    private File dir;
```

### Existing

```java
    /**
     * valid:
     *      file.exists() == true
     *
     * invalid:
     *      file.exists() == false
     */
    @Existing
    private File file;
```

### Not Existing

```java
    /**
     * valid:
     *      file.exists() == false
     *
     * invalid:
     *      file.exists() == true
     */
    @NotExisting
    private File file;
```

### File

```java
    /**
     * valid:
     *      file.isFile() == true
     *
     * invalid:
     *      file.isFile() == false
     */
    @com.github.britter.beanvalidators.file.File
    private File file;
```

### Not File

```java
    /**
     * valid:
     *      file.isFile() == false
     *
     * invalid:
     *      file.isFile() == true
     */
    @NotFile
    private File file;
```

### Hidden

```java
    /**
     * valid:
     *      file.isHidden() == true
     *
     * invalid:
     *      file.isHidden() == false
     */
    @Hidden
    private File file;
```

### Not Hidden

```java
    /**
     * valid:
     *      file.isHidden() == false
     *
     * invalid:
     *      file.isHidden() == true
     */
    @NotHidden
    private File file;
```

### Executable

```java
    /**
     * valid:
     *      file.canExecute() == true
     *
     * invalid:
     *      file.canExecute() == false
     */
    @Executable
    private File file;
```

### Not Executable

```java
    /**
     * valid:
     *      file.canExecute() == false
     *
     * invalid:
     *      file.canExecute() == true
     */
    @NotExecutable
    private File file;
```

### Readable

```java
    /**
     * valid:
     *      file.canRead() == true
     *
     * invalid:
     *      file.canRead() == false
     */
    @Readable
    private File file;
```

### Not Readable

```java
    /**
     * valid:
     *      file.canRead() == false
     *
     * invalid:
     *      file.canRead() == true
     */
    @NotReadable
    private File file;
```

### Writable

```java
    /**
     * valid:
     *      file.canWrite() == true
     *
     * invalid:
     *      file.canWrite() == false
     */
    @Writable
    private File file;
```

### Not Writable

```java
    /**
     * valid:
     *      file.canWrite() == false
     *
     * invalid:
     *      file.canWrite() == true
     */
    @NotWritable
    private File file;
```

## Net

### Domain

```java
    /**
     * valid:
     *      "www.example.com"
     *
     * invalid:
     *      "http://www.example.com"
     *      "abcd"
     */
    @Domain
    private String domain;
```

### IP

```java
    /**
     * valid:
     *      "192.168.0.1"
     *      "fe80::8a1f:a1ff:fe11:9326"
     *
     * invalid:
     *      "abcd"
     *      "999.168.0.1"
     *      "ge80::8a1f:a1ff:fe11:9326"
     */
    @IP
    private String ip;

    /**
     * valid:
     *      "192.168.0.1"
     *
     * invalid:
     *      "abcd"
     *      "fe80::8a1f:a1ff:fe11:9326"
     *      "999.168.0.1"
     *      "ge80::8a1f:a1ff:fe11:9326"
     */
    @IP(type = IPType.IP_V4)
    private String ipv4;

    /**
     * valid:
     *      "fe80::8a1f:a1ff:fe11:9326"
     *
     * invalid:
     *      "abcd"
     *      "192.168.0.1"
     *      "999.168.0.1"
     *      "ge80::8a1f:a1ff:fe11:9326"
     */
    @IP(type = IPType.IP_V6)
    private String ipv6;
```

### Port

`@Port` can be applied to String, ints and Integers. If `@Port` is applied to any other type, a ValidationException will
be thrown.

```java
    /**
     * valid:
     *      "8080"
     *
     * invalid:
     *      "-8080"
     *      "65537"
     *      "abcd"
     */
    @Port
    private String portString;

    /**
     * valid:
     *      8080
     *
     * invalid:
     *      -8080
     *      65537
     */
    @Port
    private int portInt;

    /**
     * valid:
     *      Integer.valueOf(8080)
     *
     * invalid:
     *      Integer.valueOf(-8080)
     *      Integer.valueOf(65537)
     */
    @Port
    private Integer portInteger;
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

