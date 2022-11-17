# Parent POM for Jenkins Plugins

[![GitHub release](https://img.shields.io/github/release/jenkinsci/plugin-pom.svg?label=changelog)](https://github.com/jenkinsci/plugin-pom/releases/latest)
[![GitHub license](https://img.shields.io/github/license/jenkinsci/plugin-pom)](https://github.com/jenkinsci/plugin-pom/blob/master/LICENSE)

## Introduction

This new parent POM is decoupled from the core Jenkins project, both from the Maven and repository perspectives.

Since version 4.0 the plugin pom supports Jenkins 2.200 and higher and a select few older LTS lines ([full list](https://repo.jenkins-ci.org/releases/org/jenkins-ci/main/jenkins-bom/))
 
The main changes are:
* Reduced number of overridable properties. All references (e.g. dependencies and plugin versions) not
thought to be overridden are no longer based on properties. The main remaining overridable properties are:
  * `jenkins.version`: The Jenkins version required by the plugin. **Mandatory.**
     See [Java Support](#java-support) for more info.
  * `jenkins-test-harness.version`: The [JTH version](https://github.com/jenkinsci/jenkins-test-harness/releases) used to test plugin.
  Uses split test-harness (see [JENKINS-32478](https://issues.jenkins-ci.org/browse/JENKINS-32478)).
  * `hpi-plugin.version`: The HPI Maven Plugin version used by the plugin.
  (Generally you should not set this to a version _lower_ than that specified in the parent POM.)
  * `stapler-plugin.version`: The Stapler Maven plugin version required by the plugin.
  * In order to make their versions the same as the used core version, `node.version` and `npm.version`
  properties are provided.
* Tests are skipped during the `perform` phase of a release (can be overridden by setting `release.skipTests` to false).
* Javadoc has been set to _quiet_ by default in 2.20+, which means it will only log errors and warnings. 
  If you really want it verbose, set `quiet` property to `false` for the plugin.
* General clean up.

Being able to specify the `jenkins.version` simplifies testing the plugin with different core versions, which is
important, among others, for the Plugin Compatibility Testing.

## Usage

In order to use the new POM:
* Change the parent POM of your plugin:
```xml
  <parent>
    <groupId>org.jenkins-ci.plugins</groupId>
    <artifactId>plugin</artifactId>
    <version>VERSION</version> <!-- See https://github.com/jenkinsci/plugin-pom/releases for available versions-->
    <relativePath />
  </parent>
```
* Override the needed properties, e.g.:
```xml
  <properties>
    <!--
    Take a look the developer documentation for the baseline version to use
    https://www.jenkins.io/doc/developer/plugin-development/choosing-jenkins-baseline/#currently-recommended-versions
    -->
    <jenkins.version>2.332.4</jenkins.version>
  </properties>
```

If you had a `jar:test-jar` execution, delete it and add to `properties`:

```xml
<no-test-jar>false</no-test-jar>
```

## Java support

The plugin POM is designed for plugin builds with JDK 8 or above.  
Starting from Plugin POM `4.40`, support of Java 17 was added.

## Incrementals

You can configure your plugin to treat every commit as a release candidate.
See [Incrementals](https://github.com/jenkinsci/incrementals-tools) for details.

## Running Benchmarks

To run JMH benchmarks from JUnit tests, you must run you must activate the `benchmark`
profile. For example:
```bash
mvn -P jmh-benchmark test
```
When the `jmh-benchmark` profile is enabled, no tests apart from JMH benchmarks will be run.
The names of the classes containing the benchmark runners should either begin with or
end with the word `Benchmark`. For example, `FooBenchmark` and `BenchmarkFoo` will
be detected when using `-Dbenchmark`, however, `FooBar` will be ignored.

See also: [documentation for JMH benchmarks](https://github.com/jenkinsci/jenkins-test-harness/blob/master/docs/jmh-benchmarks.adoc)

## Setup Wizard

By default, the setup wizard (Jenkins >= 2.0) is skipped when using `hpi:run`. If you want the wizard to be enabled just run:

    mvn -Dhudson.Main.development=false hpi:run

## Jenkins Core BOM

Since version 2.195, Jenkins provides a [Maven Bill Of Materials (BOM)](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Importing_Dependencies)
that centrally defines versions of various libraries used by Jenkins Core and should make it easier to update to newer Jenkins Core versions

For more information, see the [Dependency Management](https://jenkins.io/doc/developer/plugin-development/dependency-management/) section of the
[plugin development guide](https://jenkins.io/doc/developer/plugin-development/).
