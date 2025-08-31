package com.litmus7.inventoryFeed.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileMover {
	private static final Logger log = LogManager.getLogger(FileMover.class);

    public static void moveFile(Path source, Path targetDir) throws IOException {
        Files.createDirectories(targetDir); // ensure target folder exists
        Path target = targetDir.resolve(source.getFileName());

        log.info("Moving file {} -> {}", source.toAbsolutePath(), target.toAbsolutePath());

        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

        log.info("Successfully moved file {}", target.toAbsolutePath());
    }
}