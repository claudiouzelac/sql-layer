package(default_visibility = ["//visibility:public"])

java_library(
    name = "fdb-sql-layer-core-util",
    srcs = [
        "AkibanAppender.java",
        "ArgumentValidation.java",
        "BitSets.java",
        "BloomFilter.java",
        "ByteSource.java",
        "DagChecker.java",
        "Debug.java",
        "Enumerated.java",
        "EnumeratingIterator.java",
        # "Exceptions.java",
        "FilteringIterator.java",
        "Flywheel.java",
        "GCMonitor.java",
        "JsonUtils.java",
        # "ListUtils.java",
        "LoggingStream.java",
        "MultipleCauseException.java",
        "OsUtils.java",
        "Recycler.java",
        "SparseArray.java",
        # "Strings.java",
        "WrappingByteSource.java"
    ],
    deps = [
        "@jgrapht_jdk15//jar",
        "@guava//jar",
        "@jackson_core//jar",
        "@jackson_databind//jar",
        "@log_api//jar",
    ]
)