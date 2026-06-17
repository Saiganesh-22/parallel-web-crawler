package com.udacity.webcrawler;
import com.udacity.webcrawler.json.CrawlResult;
import com.udacity.webcrawler.parser.PageParser;
import com.udacity.webcrawler.parser.PageParserFactory;
import javax.inject.Inject;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinPool;
/**
 * A concrete implementation of {@link WebCrawler} that runs multiple threads on a
 * {@link ForkJoinPool} to fetch and process multiple web pages in parallel.
 */
final class ParallelWebCrawler implements WebCrawler {
  private final Clock clock;
  private final Duration timeout;
  private final List<Pattern> ignoredUrls;
  private final int popularWordCount;
  private final ForkJoinPool pool;
  private final PageParserFactory parserFactory;
  private final int maxDepth;

  @Inject
  ParallelWebCrawler(
          Clock clock,
          PageParserFactory parserFactory,
          @Timeout Duration timeout,
          @PopularWordCount int popularWordCount,
          @TargetParallelism int threadCount,
          @MaxDepth int maxDepth,
          @IgnoredUrls List<Pattern> ignoredUrls) {


    this.clock = clock;
    this.parserFactory = parserFactory;
    this.maxDepth = maxDepth;
    this.timeout = timeout;
    this.ignoredUrls = ignoredUrls;
    this.popularWordCount = popularWordCount;
    this.pool = new ForkJoinPool(threadCount);
  }

  public CrawlResult crawl(List<String> startingUrls) {

    if (startingUrls == null) {
      throw new IllegalArgumentException();
    }

    Instant deadline = clock.instant().plus(timeout);

    Map<String, Integer> counts = new ConcurrentHashMap<>();

    Set<String> visitedUrls = ConcurrentHashMap.newKeySet();

    if (startingUrls.isEmpty() || maxDepth == 0) {
      return new CrawlResult.Builder()
              .setWordCounts(new ConcurrentHashMap<>())
              .setUrlsVisited(0)
              .build();
    }




    List<ForkJoinTask<?>> tasks = new ArrayList<>();

    for (String url : startingUrls) {

      ForkJoinTask<?> task = ForkJoinTask.adapt(() ->
              crawlInternal(
                      url,
                      deadline,
                      maxDepth,
                      counts,
                      visitedUrls));

      task.fork();
      tasks.add(task);
    }

    for (ForkJoinTask<?> task : tasks) {
      task.join();
    }
    return new CrawlResult.Builder()
            .setWordCounts(
                    WordCounts.sort(counts, popularWordCount))
            .setUrlsVisited(visitedUrls.size())
            .build();
  }

  private void crawlInternal(
          String url,
          Instant deadline,
          int maxDepth,
          Map<String, Integer> counts,
          Set<String> visitedUrls) {
    if (maxDepth == 0 || clock.instant().isAfter(deadline)) {
      return;
    }

    if (ignoredUrls.stream().anyMatch(pattern -> pattern.matcher(url).matches())) {
      return;
    }

    if (!visitedUrls.add(url)) {
      return;
    }
    PageParser.Result result = parserFactory.get(url).parse();
    for (Map.Entry<String, Integer> e : result.getWordCounts().entrySet()) {
      counts.merge(e.getKey(), e.getValue(), Integer::sum);
    }
    List<ForkJoinTask<?>> subtasks = new ArrayList<>();

    for (String link : result.getLinks()) {

      ForkJoinTask<?> subtask = ForkJoinTask.adapt(() ->
              crawlInternal(
                      link,
                      deadline,
                      maxDepth - 1,
                      counts,
                      visitedUrls));
      subtask.fork();
      subtasks.add(subtask);
    }
    for (ForkJoinTask<?> task : subtasks) {
      task.join();
    }
  }
  @Override
  public int getMaxParallelism() {
    return pool.getParallelism();
  }
}






