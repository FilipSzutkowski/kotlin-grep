# kotlin-grep

Simple `grep`-like application for practising Kotlin.

Searches for a text query in a specified list of file paths. Prints out every line containing a match with the search
query.

## Usage

Run with "Program Arguments" where the first argument is the search query and all the remaining arguments are file paths
to search in.

Example:

```bash
common testdata/something.txt testdata/else.txt testdata/another.txt
```

> `common` is the string we are searching for

> File paths after that are which files the program will search in

### Example output

```bash
> Run with common testdata/something.txt testdata/else.txt testdata/another.txt

Searching for 'common' in:
   - testdata/something.txt
   - testdata/else.txt
   - testdata/another.txt

[testdata/something.txt]:
 Time since start: 26.662ms 
  - <Line 3> Common yes!

[testdata/else.txt]:
 Time since start: 26.667ms 
  - <Line 6> Common text

[testdata/another.txt]:
 Time since start: 26.764ms 
  - <Line 5> Common another!
  - <Line 7> More common!
```

**Note:** the test files are included in this repository.

## Implementations details and learning points

The main goal was to get my hands a bit dirty and try out some doing some basic I/O and concurrency.

This implementation utilises Kotlin's channels to open each file concurrently and find the lines containing the search
query.

Each file is read in a concurrent manner, but we wait for the result by consuming each channel, so that we print out the
result in a correct sequence. 