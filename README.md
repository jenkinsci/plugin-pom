# Parent POM for Jenkins Plugins

[![GitHub release](https://img.shields.io/github/release/jenkinsci/plugin-pom.svg?label=changelog)](https://github.com/jenkinsci/plugin-pom/releases/latest)
[![GitHub license](https://img.shields.io/github/license/jenkinsci/plugin-pom)](https://github.com/jenkinsci/plugin-pom/blob/master/LICENSE)

## Introduction

The plugin parent POM is decoupled from the core Jenkins project, both from a Maven perspective and a repository perspective.
It provides a common build configuration for all Jenkins plugins.

## Requirements

Since version 6.2105.v0879858b_48d2, the plugin parent POM requires Jenkins 2.516 or newer and JDK 21 or newer.
Since version 5.0, the plugin parent POM requires Jenkins 2.479 or newer and JDK 17 or newer.
Since version 4.52, the plugin parent POM requires Jenkins 2.361 or newer and JDK 11 or newer.
Since version 4.40, the plugin parent POM supports Java 17.

## Usage

To use the plugin parent POM, change the parent POM of your plugin:

```xml
  <parent>
    <groupId>org.jenkins-ci.plugins</groupId>
    <artifactId>plugin</artifactId>
    <version>VERSION</version> <!-- See https://github.com/jenkinsci/plugin-pom/releases for available versions-->
    <relativePath />
  </parent>
```

Then override the needed properties, e.g.:

```xml
  <properties>
    <!--
    Take a look the developer documentation for the baseline version to use
    https://www.jenkins.io/doc/developer/plugin-development/choosing-jenkins-baseline/#currently-recommended-versions
    -->
    <jenkins.version>2.516.3</jenkins.version>
  </properties>
```

If you have a `jar:test-jar` execution, delete it and add the following to `<properties>`:

```xml
<no-test-jar>false</no-test-jar>
```

## Overridable properties

The following properties are overridable:

* `jenkins.version`: The Jenkins version required by the plugin. **Mandatory.** See [Requirements](#requirements) for more info. Being able to specify the `jenkins.version` simplifies testing the plugin with different core versions, which is important (among other reasons) for Plugin Compatibility Testing (PCT).
* `jenkins-test-harness.version`: The [JTH version](https://github.com/jenkinsci/jenkins-test-harness/releases) used to test plugin. Uses split test-harness (see [JENKINS-32478](https://issues.jenkins-ci.org/browse/JENKINS-32478)).
* `hpi-plugin.version`: The HPI Maven Plugin version used by the plugin. (Generally you should not set this to a version _lower_ than that specified in the plugin parent POM.)
* `stapler-maven-plugin.version`: The Stapler Maven plugin version required by the plugin.
* In order to make their versions the same as the used core version, `node.version` and `npm.version` properties are provided.

## Incrementals

You can configure your plugin to treat every commit as a release candidate.
See [Incrementals](https://github.com/jenkinsci/incrementals-tools) for details.

## Formatting

To opt in to code formatting of your Java sources and Maven POM with Spotless,
define the `spotless.check.skip` property to `false` and remove any existing
Spotless configuration from your POM.

To format existing code, run:

```bash
mvn spotless:apply
```

After formatting an existing repository, squash merge the PR and create a
[`.git-blame-ignore-revs`](https://docs.github.com/en/repositories/working-with-files/using-files/viewing-a-file#ignore-commits-in-the-blame-view)
file to hide the formatting commit from blame tools.

### Format as you build

You can set up `mvn spotless:apply` to run automatically (in `validate` phase) for projects which enabled spotless by adding the following to your `settings.xml`:

```xml
<settings>
  [...]
  <activeProfiles>
    [...]
    <activeProfile>may-spotless-apply</activeProfile>
  </activeProfiles>
</settings>
```

## Additional Checks

You can set the `ban-junit4-imports.skip` property to `false` if your tests are written with JUnit 5 (Jupiter) and you want to ensure that no JUnit 4 imports are used in your plugin.
This will prevent imports of JUnit 4 classes in your code to ensure newly added or improved tests do not reintroduce JUnit 4 (e.g., out of habit or from IDE import autocompletion).
Note that this will only address _imports_ of JUnit 4 in your code, and not uses from dependencies, or even use of fully qualified class names.

## Running Benchmarks

To run JMH benchmarks from JUnit tests, activate the `benchmark`
profile. For example:
```bash
mvn -P jmh-benchmark test
```
When the `jmh-benchmark` profile is enabled, no tests apart from JMH benchmarks will be run.
The names of the classes containing the benchmark runners should either begin with or
end with the word `Benchmark`. For example, `FooBenchmark` and `BenchmarkFoo` will
be detected when using `-Dbenchmark`, however, `FooBar` will be ignored.

See also: [documentation for JMH benchmarks](https://github.com/jenkinsci/jenkins-test-harness/blob/master/docs/jmh-benchmarks.adoc)

## Javadoc

Javadoc has been set to _quiet_ by default in 2.20+, which means it will only log errors and warnings.
If you really want it verbose, set `quiet` property to `false` for the plugin.

## Releasing

Tests are skipped during the `perform` phase of a release. This can be overridden by setting `release.skipTests` to false.

## Setup Wizard

By default, the setup wizard (Jenkins >= 2.0) is skipped when using `hpi:run`. If you want the wizard to be enabled just run:

```bash
mvn -Dhudson.Main.development=false hpi:run
```

## npm or yarn

If you want to add `npm` or `yarn` to your plugin, you can do so by creating a marker file:

For npm:

```bash
touch .mvn_exec_node
```

For yarn classic (1.x):

```bash
touch .mvn_exec_yarn
```

For yarn berry (>2.x):

```bash
touch .mvn_exec_yarn_corepack
```

You need to add corresponding properties to your `pom.xml` and set them to valid values:

For npm:
```xml
<properties>
    <node.version>set this to the latest node lts version</node.version>
    <npm.version>set this to the latest npm version</npm.version>
</properties>
```

For yarn berry:

```xml
<properties>
    <node.version>set this to the latest node lts version</node.version>
</properties>
```

In your `package.json` set the `packageManager` field:
```json
{
  "packageManager": "yarn@4.9.1"
}
```

For yarn classic:

```xml
<properties>
    <node.version>set this to the latest node lts version</node.version>
    <yarn.version>set this to the latest yarn version</yarn.version>
</properties>
```

### Configuring Jest to report results to Jenkins

Our [pipeline-library](https://github.com/jenkins-infra/pipeline-library) configures the Maven build to not fail the build when there is test failures so that Jenkins is able to report on the test failures itself.

This requires the tests to be configured to output a JUnit report in a supported location.

To do this you can install `jest-junit`:

```bash
npm install --dev jest-junit
```

```bash
yarn add --dev jest-junit
```

Then configure jest-junit by modifying the `package.json`:

```json
{
  "scripts": {
    "mvntest": "npm test",
    "test": "jest --ci --reporters=default --reporters=jest-junit"
  },
  "jest-junit": {
    "outputDirectory": "target/surefire-reports",
    "outputName": "jest-junit.xml"
  }
}

```

<!-- TODO when enough plugins have adopted this then remove this step -->
Then set the following properties in your `pom.xml` to configure the Maven build to let Jenkins report the results:

```xml
<properties>
  <maven.test.failure.ignore>false</maven.test.failure.ignore>
  <frontend.testFailureIgnore>${maven.test.failure.ignore}</frontend.testFailureIgnore>
</properties>
```

### Configuring eslint to report results to Jenkins

To configure the Maven build to report the eslint results to Jenkins you will need to setup the `eslint-formatter-checkstyle` formatter.

First install it:

```bash
npm install --dev eslint-formatter-checkstyle
```

```bash
yarn add --dev eslint-formatter-checkstyle
```

Then configure eslint, depending on your configuration it should look something like this:

```json
{
  "scripts": {
    "mvntest": "eslint src/main/js -f checkstyle -o target/eslint-warnings.xml --ext js"
  }
}
```

<!-- TODO when enough plugins have adopted this then remove this step -->
Then set the following properties in your `pom.xml` to configure the Maven build to let Jenkins report the results:

```xml
<properties>
  <maven.test.failure.ignore>false</maven.test.failure.ignore>
  <frontend.testFailureIgnore>${maven.test.failure.ignore}</frontend.testFailureIgnore>
</properties>
```


## Jenkins Core BOM

Since version 2.195, Jenkins provides a [Maven Bill Of Materials (BOM)](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Importing_Dependencies)
that centrally defines versions of various libraries used by Jenkins Core and should make it easier to update to newer Jenkins Core versions

For more information, see the [Dependency Management](https://jenkins.io/doc/developer/plugin-development/dependency-management/) section of the
[plugin development guide](https://jenkins.io/doc/developer/plugin-development/).
