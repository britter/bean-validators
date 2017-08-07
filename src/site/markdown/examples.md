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
# Examples

All Validators will treat null and blank input strings as valid. If you want to make sure fields are not null/blank
you have to use `@NotNull` or `@NotBlank` in addition.

## @Empty

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

Various constraints which can be applied to instance of `java.lang.String`. All validators treat blank strings as valid.
If you need a non empty/non blank String, you annotations such as `@NotEmpty` or `@NotBlank` in addition. 

### @Alphabetic

Makes sure a String only contains letters. Can be configured to allow spaces as well.

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

### @AlphaNumeric

Makes sure a String only contains letters and digits. Can be configured to allow spaces as well.

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

### @ASCII

Makes sure a String ony contains ASCII printable characters. ASCII printable characters are all charaters in the range
32 thru 126.

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

### @Blank

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

### @ISBN

Makes sure a String represents an [International Standard Book Number (ISBN)](https://en.wikipedia.org/wiki/International_Standard_Book_Number).
There are two kinds of ISBNs: ISBN-10 and ISBN-13. The default settings will allow both. The
`com.github.britter.beanvalidators.strings.ISBNType` enum can be used to control this behavior.

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

### @JavaNumber

Makes sure a String contains a valid Java number. Note that this doesn't mean the given String can be parsed by the 
various `parse` and `valueOf` methods defined on `Integer` etc. It just means, that the String could be used to define
an number in Java code. For more information see [NumberUtils.isNumber(String)](http://commons.apache.org/proper/commons-lang/javadocs/api-release/index.html).

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

### @Numeric

Makes sure a String only contains digits.

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

Constraints validating all properties of `java.io.File` objects. There is an annotation for each property as well as one
for the negation of each property. All annotations can also be applied to Strings. The validators will construct a new
File instance using `new File(String)` and use that instance for validation.

### @Absolute

Makes sure a file represents an absolute path in the file system.

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

### @NotAbsolute

Makes sure a file does not represent an absolute path in the file system.

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

### @Directory

Makes sure a file represents a directory.

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

### @NotDirectory

Makes sure a file does not represent a directory.

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

### @Executable

Makes sure a file has executable permission in the file system.

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

### @NotExecutable

Makes sure a file does not have executable permission in the file system.

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

### @Existing

Makes sure a file exists.

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

### @NotExisting

Makes sure a file does not exist.

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

### @File

Makes sure a file represents a file in the file system.

```java
    /**
     * valid:
     *      file.isFile() == true
     *
     * invalid:
     *      file.isFile() == false
     */
    @com.github.britter.beanvalidators.file.IsFile
    private File file;
```

### @NotFile

Makes sure a file does not represent a file in the file system. Note that both `File.isFile()` and `File.isDirectory()`
can return false, for example for sockets, mounts or pipes.

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

### @Hidden

Makes sure a file is hidden. Note that the definition of a hidden files various between operating systems. For examples
in an UNIX-like OS, all files and directories starting with a dot are considered to be hidden, where as on Windows the
hidden attribute of the file has to be set to true.

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

### @NotHidden

Makes sure a file is not hidden.

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

### @Readable

Makes sure a file is readable.

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

### @NotReadable

Makes sure a file is not readable. There is probably no real use case for this annotation, but it has been added for completeness

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

### @Writable

Makes sure a file is writable.

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

### @NotWritable

Makes sure a file is not writeable.

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

Constraints dealing with networking.

### @Domain

Makes sure a string represents a valid domain. Note that the list of valid top level domains is changes frequently. Since
this validator uses `org.apache.commons.validator.routines.DomainValidator` under the hood, there may be domains which
are acutally valid reported as invalid, because the Apache Commons Validator TLP list is not up to date.

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

### @IP

Makes sure a String represents a valid [Internet Protocol (IP) address](https://en.wikipedia.org/wiki/IP_address). There
are two kinds of IP addresses: IPv4 and the newer IPv6. The default is to validate against both versions. The behavior
can be controlled by the `com.github.britter.beanvalidators.net.IPType` enum.

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

### @Port

Makes sure a String, int or Integer represents a valid [port](https://en.wikipedia.org/wiki/Port_(computer_networking))
number. A port number is a 16-bit unsigned integer, thus ranging from 0 to 65535. `@Port` can be applied to String, ints
and Integers. If `@Port` is applied to any other type, a ValidationException will be thrown.

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
