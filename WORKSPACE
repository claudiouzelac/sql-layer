workspace(name = "sql_layer")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# proto_library, cc_proto_library, and java_proto_library rules implicitly
# depend on @com_google_protobuf for protoc and proto runtimes.
# This statement defines the @com_google_protobuf repo.
http_archive(
    name = "com_google_protobuf",
    sha256 = "cef7f1b5a7c5fba672bec2a319246e8feba471f04dcebfe362d55930ee7c1c30",
    strip_prefix = "protobuf-3.5.0",
    urls = ["https://github.com/google/protobuf/archive/v3.5.0.zip"],
)

# java_lite_proto_library rules implicitly depend on @com_google_protobuf_javalite//:javalite_toolchain,
# which is the JavaLite proto runtime (base classes and common utilities).
http_archive(
    name = "com_google_protobuf_javalite",
    sha256 = "d8a2fed3708781196f92e1e7e7e713cf66804bd2944894401057214aff4f468e",
    strip_prefix = "protobuf-5e8916e881c573c5d83980197a6f783c132d4276",
    urls = ["https://github.com/google/protobuf/archive/5e8916e881c573c5d83980197a6f783c132d4276.zip"],
)

maven_server(
    name = "default",
    url = "http://central.maven.org/maven2/",
)

GUICE_VERS = "4.2.2"

maven_jar(
    name = "guice_library",
    artifact = "com.google.inject:guice:" + GUICE_VERS,
    sha1 = "6dacbe18e5eaa7f6c9c36db33b42e7985e94ce77",
)

maven_jar(
    name = "guice_assistedinject",
    artifact = "com.google.inject.extensions:guice-assistedinject:" + GUICE_VERS,
    sha1 = "c33fb10080d58446f752b4fcfff8a5fabb80a449",
)
maven_jar(
    name = "guice_servlet",
    artifact = "com.google.inject.extensions:guice-servlet:" + GUICE_VERS,
    sha1 = "0d0054bdd812224078357a9b11409e43d182a046",
)

maven_jar(
    name = "javax_inject",
    artifact = "javax.inject:javax.inject:1",
    sha1 = "6975da39a7040257bd51d21a231b76c915872d38",
)

maven_jar(
    name = "servlet_api_3_1",
    artifact = "org.apache.tomcat:tomcat-servlet-api:8.5.23",
    sha1 = "021a212688ec94fe77aff74ab34cc74f6f940e60",
)

GUAVA_VERSION = "27.0.1-jre"
GUAVA_BIN_SHA1 = "bd41a290787b5301e63929676d792c507bbc00ae"
GUAVA_DOC_URL = "https://google.github.io/guava/releases/" + GUAVA_VERSION + "/api/docs/"

maven_jar(
    name = "guava",
    artifact = "com.google.guava:guava:" + GUAVA_VERSION,
    sha1 = GUAVA_BIN_SHA1,
)

SLF4J_VERS = "1.7.7"

maven_jar(
    name = "log_api",
    artifact = "org.slf4j:slf4j-api:" + SLF4J_VERS,
    sha1 = "2b8019b6249bb05d81d3a3094e468753e2b21311",
)

maven_jar(
    name = "log_ext",
    artifact = "org.slf4j:slf4j-ext:" + SLF4J_VERS,
    sha1 = "09a8f58c784c37525d2624062414358acf296717",
)

maven_jar(
    name = "impl_log4j",
    artifact = "org.slf4j:slf4j-log4j12:" + SLF4J_VERS,
    sha1 = "58f588119ffd1702c77ccab6acb54bfb41bed8bd",
)

maven_jar(
    name = "jcl_over_slf4j",
    artifact = "org.slf4j:jcl-over-slf4j:" + SLF4J_VERS,
    sha1 = "56003dcd0a31deea6391b9e2ef2f2dc90b205a92",
)

maven_jar(
    name = "log4j",
    artifact = "log4j:log4j:1.2.17",
    sha1 = "5af35056b4d257e4b64b9e8069c0746e8b08629f",
)

maven_jar(
    name = "jgrapht_jdk15",
    artifact = "org.jgrapht:jgrapht-jdk1.5:0.7.3",
)

JACKSON_VERSION = "2.9.8"

maven_jar(
    name = "jackson_core",
    artifact = "com.fasterxml.jackson.core:jackson-core:" + JACKSON_VERSION,
)

maven_jar(
    name = "jackson_databind",
    artifact = "com.fasterxml.jackson.core:jackson-databind:" + JACKSON_VERSION,
)

maven_jar(
    name = "protobuf",
    artifact = "com.google.protobuf:protobuf-java:3.6.1",
    sha1 = "0d06d46ecfd92ec6d0f3b423b4cd81cb38d8b924",
)