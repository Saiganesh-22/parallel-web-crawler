package com.udacity.webcrawler.json;

import java.io.Writer;
import java.nio.file.Path;
import java.util.Objects;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Utility class to write a {@link CrawlResult} to file.
 */
public final class CrawlResultWriter {
  private final CrawlResult result;

  /**
   * Creates a new {@link CrawlResultWriter} that will write the given {@link CrawlResult}.
   */
  public CrawlResultWriter(CrawlResult result) {
    this.result = Objects.requireNonNull(result);
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Path}.
   *
   * <p>If a file already exists at the path, the existing file should not be deleted; new data
   * should be appended to it.
   *
   * @param path the file path where the crawl result data should be written.
   */
  public void write(Path path) {
    Objects.requireNonNull(path);

    try (Writer writer = Files.newBufferedWriter(
            path,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND)) {

      write(writer);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Writer}.
   *
   * @param writer the destination where the crawl result data should be written.
   */
  public void write(Writer writer) {
    Objects.requireNonNull(writer);

    Gson gson = new Gson();
    gson.toJson(result, writer);
  }
}
