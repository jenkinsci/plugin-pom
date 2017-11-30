# plugin-pom
Parent POM for Jenkins Plugins

## Introduction

This new parent POM is decoupled from the core Jenkins project, both from the Maven and repository perspectives.

The main changes are:
* Reduced number of overridable properties. All references (e.g. dependencies and plugin versions) not
thought to be overridden are no longer based on properties. The main remaining overridable properties are:
  * `jenkins.version`: The Jenkins version required by the plugin. **Mandatory.**
  * `java.level`: The Java version to use to build the plugin. **Mandatory.** Should match the minimum Java version for the selected Jenkins version.
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
    <version>2.29</version> <!-- or whatever the newest version available is -->
    <relativePath />
  </parent>
```
* Override the needed properties, e.g.:
```xml
  <properties>
    <jenkins.version>1.609.1</jenkins.version>
    <java.level>7</java.level>
  </properties>
```

If you had a `jar:test-jar` execution, delete it and add to `properties`:

```xml
<no-test-jar>false</no-test-jar>
```

## Baselines

It is handy to be able to select different Jenkins baselines with a Maven profile.
To set this up, you must edit your `~/.m2/settings.xml` to include some new entries in the `<profiles>` section.
For example:

```xml
<profile>
    <id>jenkins-289</id>
    <properties>
        <jenkins.version>2.89</jenkins.version>
        <java.level>8</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-273</id>
    <properties>
        <jenkins.version>2.73.3</jenkins.version>
        <java.level>8</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-260</id>
    <properties>
        <jenkins.version>2.60.3</jenkins.version>
        <java.level>8</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-246</id>
    <properties>
        <jenkins.version>2.46.3</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-232</id>
    <properties>
        <jenkins.version>2.32.3</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-219</id>
    <properties>
        <jenkins.version>2.19.4</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-27</id>
    <properties>
        <jenkins.version>2.7.3</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-651</id>
    <properties>
        <jenkins.version>1.651.3</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-642</id>
    <properties>
        <jenkins.version>1.642.4</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-625</id>
    <properties>
        <jenkins.version>1.625.3</jenkins.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-609</id>
    <properties>
        <jenkins.version>1.609.3</jenkins.version>
        <java.level>6</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-596</id>
    <properties>
        <jenkins.version>1.596.3</jenkins.version>
        <java.level>6</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-580</id>
    <properties>
        <jenkins.version>1.580.3</jenkins.version>
        <java.level>6</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-565</id>
    <properties>
        <jenkins.version>1.565.3</jenkins.version>
        <jenkins-test-harness.version>1.565.3</jenkins-test-harness.version>
        <java.level>6</java.level>
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


## For maintainers

You can run

    mvn -Prun-plugin-pom-its clean verify

Remember to update the [changelog](CHANGELOG.md).
