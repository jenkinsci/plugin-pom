# Parent POM for Jenkins Plugins

[![GitHub release](https://img.shields.io/github/release/jenkinsci/plugin-pom.svg?label=release)](https://github.com/jenkinsci/plugin-pom/releases/latest)

## Introduction

This new parent POM is decoupled from the core Jenkins project, both from the Maven and repository perspectives.

The main changes are:
* Reduced number of overridable properties. All references (e.g. dependencies and plugin versions) not
thought to be overridden are no longer based on properties. The main remaining overridable properties are:
  * `jenkins.version`: The Jenkins version required by the plugin. **Mandatory.**
  * `java.level`: The Java version to use to build the plugin. **Mandatory.** Should match the minimum Java version for the selected Jenkins version.
     See [Java Support](#java-support) for more info.
  * `jenkins-test-harness.version`: The [JTH version](https://github.com/jenkinsci/jenkins-test-harness/releases) used to test plugin.
  Uses split test-harness (see [JENKINS-32478](https://issues.jenkins-ci.org/browse/JENKINS-32478)).
  If the required Jenkins version is 1.580.1 or higher, JTH 2.1+ is recommended.
  * `hpi-plugin.version`: The HPI Maven Plugin version used by the plugin.
  (Generally you should not set this to a version _lower_ than that specified in the parent POM.)
  * `stapler-plugin.version`: The Stapler Maven plugin version required by the plugin.
  * `java.level.test`: The Java version to use to build the plugin tests.
  * In order to make their versions the same as the used core version, `slf4jVersion`, `node.version` and `npm.version`
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
    <version>3.43</version> <!-- or whatever the newest version available is -->
    <relativePath />
  </parent>
```
* Override the needed properties, e.g.:
```xml
  <properties>
    <jenkins.version>2.60.1</jenkins.version>
    <java.level>8</java.level>
  </properties>
```

If you had a `jar:test-jar` execution, delete it and add to `properties`:

```xml
<no-test-jar>false</no-test-jar>
```

## Java support

The plugin POM is designed for plugin builds with JDK 8 or above,
but target `java.level` for a plugin may differ from a JDK version used for the build.
Starting from Plugin POM `3.44`, support of Java 7 targets in Plugin POM is deprecated,
`java.level=8` and `jenkins.version>2.59` are expected to be used for most plugins.

### Using deprecated Java targets

If recent plugin POM versions are required for a plugin with older baselines,
a developer can use dependency version system properties to downgrade components incompatible with Java 7.

Example:

```xml
    <properties>
        <jenkins.version>2.32.3</jenkins.version>
        <java.level>7</java.level>
        <!-- Animal Sniffer Annotations 1.18 is not compatible with Java 7 anymore -->
        <animal.sniffer.version>1.17</animal.sniffer.version>
    </properties>
```

Such mode is no longer tested in the repository,
and the list of incompatible dependencies will expand without further notice in new releases.

## Incrementals

You can configure your plugin to treat every commit as a release candidate.
See [Incrementals](https://github.com/jenkinsci/incrementals-tools) for details.

## Running Benchmarks

To run JMH benchmarks from JUnit tests, you must run you must activate the `benchmark`
profile. For example:
```bash
mvn -Dbenchmark test
```
When the `benchmark` property is set, no tests apart from JMH benchmarks will be run.
The names of the classes containing the benchmark runners should either begin with or
end with the the word `Benchmark`. For example, `FooBenchmark` and `BenchmarkFoo` will
be detected when using `-Dbenchmark`, however, `FooBar` will be ignored.

See also: [documentation for JMH benchmarks](https://github.com/jenkinsci/jenkins-test-harness/blob/master/docs/jmh-benchmarks.adoc)

## Baselines

It is handy to be able to select different Jenkins baselines with a Maven profile.
To set this up, you must edit your `~/.m2/settings.xml` to include some new entries in the `<profiles>` section.
For example:

```xml
<profile>
    <id>jenkins-289</id>
    <properties>
        <jenkins.version>2.89.2</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-273</id>
    <properties>
        <jenkins.version>2.73.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-260</id>
    <properties>
        <jenkins.version>2.60.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-246</id>
    <properties>
        <jenkins.version>2.46.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-232</id>
    <properties>
        <jenkins.version>2.32.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-219</id>
    <properties>
        <jenkins.version>2.19.4</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-27</id>
    <properties>
        <jenkins.version>2.7.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-651</id>
    <properties>
        <jenkins.version>1.651.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-642</id>
    <properties>
        <jenkins.version>1.642.4</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-625</id>
    <properties>
        <jenkins.version>1.625.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-609</id>
    <properties>
        <jenkins.version>1.609.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-596</id>
    <properties>
        <jenkins.version>1.596.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-580</id>
    <properties>
        <jenkins.version>1.580.3</jenkins.version>
    </properties>
</profile>
<profile>
    <id>jenkins-565</id>
    <properties>
        <jenkins.version>1.565.3</jenkins.version>
        <jenkins-test-harness.version>1.565.3</jenkins-test-harness.version>
    </properties>
</profile>
```

Now for example if your plugin normally builds against 1.625.x, but you wish to test compatibility with 1.651.x,
there is no need to edit your POM. Just run:

    mvn -Pjenkins-651 clean test

or

    mvn -Pjenkins-651 hpi:run

## Setup Wizard

By default, the setup wizard (Jenkins >= 2.0) is skipped when using `hpi:run`. If you want the wizard to be enabled just run:

    mvn -Dhudson.Main.development=false hpi:run

## Jenkins Core BOM

Since version 2.195, Jenkins provides a [Maven Bill Of Materials (BOM)](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Importing_Dependencies)
that centrally defines versions of various libraries used by Jenkins Core.
The default behaviour of `plugin-pom` is to _not_ use the BOM, but when jenkins.version>=2.195 (and plugin-pom>=4.0) you can switch on Jenkins BOM support by setting the Maven property `use-jenkins-bom`.
For example:

`mvn -Djenkins.version=2.195 -Duse-jenkins-bom package`

This will import the BOM for Jenkins 2.195

For more information, see the [Dependency Management](https://jenkins.io/doc/developer/plugin-development/dependency-management/) section of the
[plugin development guide](https://jenkins.io/doc/developer/plugin-development/).
