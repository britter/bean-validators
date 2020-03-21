package com.github.britter.beanvalidators.file;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;

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
            File file = Files.createFile(tmpDir.resolve(name)).toFile();
            if (name.startsWith(".")) {
                makeHidden(file);
            }
            return file;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    File dir() {
        return dir("tempDir");
    }

    File dir(String name) {
        try {
            File dir = Files.createDirectory(tmpDir.resolve(name)).toFile();
            if (name.startsWith(".")) {
                makeHidden(dir);
            }
            return dir;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void makeHidden(File file) throws IOException {
        if (SystemUtils.IS_OS_WINDOWS) {
            DosFileAttributeView attributes = Files.getFileAttributeView(file.toPath(), DosFileAttributeView.class);
            attributes.setHidden(true);
        }
    }
}
