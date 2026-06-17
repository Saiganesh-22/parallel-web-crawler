\\# Parallel Web Crawler


\\## Overview:-


Parallel Web Crawler is a Java-based application developed as part of the Udacity Advanced Java Programming Nanodegree Program.

The application crawls web pages starting from one or more URLs, extracts words and links, counts word occurrences across visited pages, and returns the most popular words found during crawling.

The project demonstrates advanced Java concepts including multithreading, concurrency, ForkJoinPool, dynamic proxies, dependency injection, profiling, JSON processing, file handling, and unit testing.



\\---


\\## Features:-


\\### Web Crawling:

\\\* Crawl one or more starting URLs

\\\* Follow links recursively

\\\* Limit crawling depth using configuration

\\\* Avoid revisiting already visited URLs

\\\* Ignore URLs using regular expression patterns



\\### Parallel Processing:

\\\* Process multiple web pages simultaneously

\\\* Use ForkJoinPool for concurrent execution

\\\* Improve crawling performance using multithreading

\\\* Thread-safe URL tracking and word counting



\\### Word Analysis:

\\\* Extract words from web pages

\\\* Count word occurrences

\\\* Return the most frequently used words

\\\* Sort results by popularity



\\### Configuration Management:

\\\* Load crawler settings from JSON file

\\\* Configure crawl depth

\\\* Configure timeout duration

\\\* Configure thread parallelism

\\\* Configure ignored URLs and ignored words



\\### Profiling Support:

\\\* Record execution times of annotated methods

\\\* Generate profiling reports

\\\* Use Dynamic Proxy implementation

\\### File Handling

\\\* Read configuration from JSON files

\\\* Write crawl results to JSON files

\\\* Write profiling data to files or standard output



\\---



\\## Technologies Used:-


\\### Programming Language:

\\\* Java 11


\\### Build Tool;

\\\* Maven


\\### Testing Framework:

\\\* JUnit 5


\\### Libraries:

\\\* Google Guice

\\\* Gson


\\### Concurrency:

\\\* ForkJoinPool

\\\* ConcurrentHashMap

\\\* Java Streams API


\\### Development Tools:

\\\* IntelliJ IDEA

\\\* Git

\\\* GitHub


\\---



\\## Project Structure:-


webcrawler:


├── src


│ ├── main

│ │ └── java

│ │


│ ├── com.udacity.webcrawler

│ │ ├── ParallelWebCrawler

│ │ ├── SequentialWebCrawler

│ │ ├── WebCrawler

│ │ ├── WebCrawlerModule

│ │ └── WordCounts

│ │


│ ├── parser

│ │ ├── PageParser

│ │ ├── PageParserFactory

│ │ └── PageParserImpl

│ │


│ ├── profiler

│ │ ├── Profiled

│ │ ├── Profiler

│ │ ├── ProfilerImpl

│ │ ├── ProfilingState

│ │ └── ProfilingMethodInterceptor

│ │


│ ├── json

│ │ ├── ConfigurationLoader

│ │ ├── CrawlResult

│ │ ├── CrawlResultWriter

│ │ └── CrawlerConfiguration

│ │


│ └── main

│ └── WebCrawlerMain

│


├── src/test

│ └── Unit Tests

│


└── pom.xml



\\---



\\## Workflow:-


\\### Step 1: Load Configuration:

The crawler reads configuration settings from a JSON file.


\\### Step 2: Start Crawling:

The crawler begins visiting the URLs specified in the startPages section.


\\### Step 3: Parse Web Pages:


Each page is parsed to extract:

\\\* Words

\\\* Links


\\### Step 4: Count Words:

The crawler combines word counts from all visited pages.


\\### Step 5: Visit Linked Pages:


The crawler recursively visits links until:

\\\* Maximum depth is reached

\\\* Timeout occurs


\\### Step 6: Generate Results


The crawler returns:

\\\* Most popular words

\\\* Number of URLs visited



\\### Step 7: Generate Profiling Data

Execution times of profiled methods are recorded.



\\---



\\## Sample Configuration:-


```json
{

 "startPages": \\\[

 "https://example.com"

 ],

 "ignoredUrls": \\\[],

 "ignoredWords": \\\[],

 "parallelism": 4,

 "maxDepth": 2,

 "timeoutSeconds": 10,

 "popularWordCount": 3,

 "profileOutputPath": "",

 "resultPath": ""

}



```
\\---



\\## Build Instructions:-


Clone the repository:


```bash

git clone https://github.com/Saiganesh-22/parallel-web-crawler.git

```


Navigate to the project:


```bash

cd webcrawler

```


Build the project:


```bash

mvn clean package

```


Run all tests:


```bash

mvn test

```


\\---



\\## Running the Application:-


Execute the crawler using:


```bash

java -cp target/udacity-webcrawler-1.0.jar com.udacity.webcrawler.main.WebCrawlerMain src/config.json

```



\\---



\\## Sample Output:-


```json

{

 "wordCounts": {

 "domains": 7,

 "example": 6,

 "domain": 6

},

 "urlsVisited": 2

}


```

\\### Output Explanation:


\\#### wordCounts

Shows the most frequently occurring words found during crawling.

Example:

\\\* domains → 7 times

\\\* example → 6 times

\\\* domain → 6 times


\\#### urlsVisited:

Shows the total number of unique URLs visited.

Example:

\\\* urlsVisited = 2

Meaning the crawler successfully visited two web pages.

\\---




\\## Unit Testing:-


The project includes unit tests for:

\\\* Configuration loading

\\\* Parallel crawler functionality

\\\* Sequential crawler functionality

\\\* Word counting

\\\* Page parsing

\\\* Crawl result writing

\\\* Dynamic proxy profiling

\\\* Profiler implementation


Framework Used:

\\\* JUnit 5



\\---



\\## Build Results:-


```text

Tests run: 38

Failures: 0

Errors: 0

Skipped: 0


BUILD SUCCESS

```


\\---



\\## Key Concepts Demonstrated:-


\\\* Object-Oriented Programming (OOP)

\\\* Multithreading

\\\* ForkJoin Framework

\\\* Concurrent Programming

\\\* Dynamic Proxies

\\\* Reflection API

\\\* Dependency Injection using Guice

\\\* JSON Serialization and Deserialization

\\\* Java Streams API

\\\* File Input and Output

\\\* Unit Testing

\\\* Maven Project Management

\\\* Git Version Control

\\\* GitHub Repository Management



\\---



\\## Project Outcome:-


The crawler successfully:

\\\* Crawls web pages

\\\* Processes pages in parallel

\\\* Counts word frequencies

\\\* Tracks visited URLs

\\\* Generates profiling information

\\\* Produces JSON-formatted output

\\\* Passes all 38 unit tests



\\---



\\## Reviewer Result:-


Project Status: PASSED

All rubric requirements were successfully completed.

Reviewer feedback highlighted:


\\\* Correct synchronization implementation


\\\* Proper Dynamic Proxy implementation


\\\* Effective use of Dependency Injection


\\\* Correct Stream API usage


\\\* Clean project architecture



\\---


\\## Author:-


\\### N. Sai Ganesh Yadav

B.Tech - Computer Science and Engineering

Java Developer | Full Stack Java Learner


GitHub:

https://github.com/Saiganesh-22

