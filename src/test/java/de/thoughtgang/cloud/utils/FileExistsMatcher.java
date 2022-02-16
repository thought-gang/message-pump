package de.thoughtgang.cloud.utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;
import java.nio.file.Path;

public class FileExistsMatcher extends TypeSafeMatcher<String> {

    private Path directory;

    public FileExistsMatcher(String prefix) {

        this.directory = new File(prefix).toPath();

    }

    @Override
    protected boolean matchesSafely(String fileName) {
        File file = directory.resolve(fileName).toFile();
        return file.exists() && file.isFile();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format(" does not point to a file in the directory %s", this.directory.toString()));

    }

    public static Matcher<String>fileExistsInDir(String prefix) {
        return new FileExistsMatcher(prefix);
    }
}
