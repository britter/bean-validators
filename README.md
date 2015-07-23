# bean-validators

Additional validator implementations for [javax.validation](http://beanvalidation.org/).

Validators will treat null and blank input strings as valid. If you want to make sure fields are not null/blank
you have to use `@NotNull` or `@NotBlank` in addition.

## ISBNs

```java
   @ISBN
   private String isbn;

   @ISBN(type = ISBNType.ISBN_10)
   private String isbn10;

   @ISBN(type = ISBNType.ISBN_13)
   private String isbn13;
```

## Numbers

```java
   @Numeric
   private String numeric;
```
