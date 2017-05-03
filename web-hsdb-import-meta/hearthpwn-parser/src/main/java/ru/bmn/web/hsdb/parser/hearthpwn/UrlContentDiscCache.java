package ru.bmn.web.hsdb.parser.hearthpwn;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UrlContentDiscCache {
    private static final String PROPERTY_BASE_DIR = "url_content_disc_cache.base_dir";

    private final Path baseDir;
    private final Map<String, File> files = new HashMap<>();

    private boolean isInitialized = false;


    UrlContentDiscCache(String tag) {
        String baseDirProperty = System.getProperty(PROPERTY_BASE_DIR);
        if (baseDirProperty.isEmpty()) {
            throw new RuntimeException(
                String.format("Cache base dir expected (use %s property)", PROPERTY_BASE_DIR)
            );
        }
        this.baseDir = Paths.get(baseDirProperty, tag);
    }

    private void init()
        throws IOException
    {
        if (!this.isInitialized) {
            if (!this.baseDir.toFile().exists()) {
                Files.createDirectory(this.baseDir);
            }

            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.baseDir)) {
                for (Path path : dirStream) {
                    if (path.toFile().isFile()) {
                        String[] nameParts = path.toFile().getName().split("\\.", 2);
                        if (nameParts.length != 2) {
                            throw new RuntimeException(
                                String.format(
                                    "Corrupted cache file: %s",
                                    path.toString()
                                )
                            );
                        }
                        this.files.put(nameParts[0], path.toFile());
                    }
                }
            }
            finally {
                this.isInitialized = true;
            }
        }

    }

    private void put(URL url)
        throws IOException
    {
        File outputFile = Files.createFile(
            Paths.get(
                this.baseDir.toString(),
                this.urlToFileName(url).toString()
            )
        ).toFile();

        try (
            FileOutputStream output = new FileOutputStream(outputFile)
        ) {
            IOUtils.copy(
                new UrlResource(url).getInputStream(),
                output
            );
        }

        this.files.put(this.urlToKey(url), outputFile);
    }

    private String urlToKey(URL url) {
        return DigestUtils.md5DigestAsHex(url.toString().getBytes());
    }

    private Path urlToFileName(URL url) {
        return Paths.get(
            url.getHost().replaceAll("\\W", "_"),
            String.format(
                "%s.%s",
                    this.urlToKey(url),
                    url.getPath().replaceAll("\\W", "_")
            )
        );
    }

    public byte[] get(URL url)
        throws IOException
    {
        this.init();

        byte[] result = null;

        File file = this.files.get(this.urlToKey(url));
        if (file == null) {
            this.put(url);
        }
        else {
            try {
                result = Files.readAllBytes(file.toPath());
            }
            catch (IOException e) {
                return null;
            }
        }
        return result;
    }

    public boolean delete(URL url) {
        File file = this.files.remove(this.urlToKey(url));
        if (file != null) {
            try {
                Files.delete(file.toPath());
            }
            catch (IOException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getText(URL url)
        throws IOException
    {
        return new String(this.get(url), Charset.defaultCharset());
    }
}