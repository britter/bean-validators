package com.github.britter.beanvalidators.file;

import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

abstract class BaseFileTest {

    static final String NON_EXISTENT_PATH = "/does/not/exist";

    static final File NON_EXISTENT_FILE = new File(NON_EXISTENT_PATH);

    @TempDir
    public Path tmpDir;

    File file() {
        return file("temp");
    }

    File file(String name) {
        try {
            return Files.createFile(tmpDir.resolve(name)).toFile();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    File dir() {
        return dir("tempDir");
    }

    File dir(String name) {
        try {
            return Files.createDirectory(tmpDir.resolve(name)).toFile();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
