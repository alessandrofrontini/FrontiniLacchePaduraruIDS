package com.camerino.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class CliUtils {
    private CliUtils(){}

    public static File getResourceAsFile(String fileName) {
        return new File(Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName)
                .getFile()
        );
    }
}
