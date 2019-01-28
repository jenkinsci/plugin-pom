Changelog
=========

### 3.34

Release date: TBD

* Switched to SpotBugs from FindBugs, it may cause issues with configuration overrides on FindBugs

### 3.33

Release date: 2019-01-16

* [JENKINS-55562](https://issues.jenkins-ci.org/browse/JENKINS-55562) -
Maven HPI Plugin 3.2: Introduce a new `hpi.compatibleSinceVersion` property to support
[marking plugins as incompatible](https://wiki.jenkins.io/display/JENKINS/Marking+a+new+plugin+version+as+incompatible+with+older+versions) without the plugin configuration override
* [JENKINS-54949](https://issues.jenkins-ci.org/browse/JENKINS-54949) -
Maven HPI Plugin 3.2: Add support of adding the current pom.xml to the custom WAR in the `hpi:custom-war` mojo
* [PR #149](https://github.com/jenkinsci/plugin-pom/pull/149) -
Upgrade Maven Surefire and Failsafe plugins from 3.0.0-M1 to 3.0.0-M3
([SUREFIRE-1541](https://issues.apache.org/jira/browse/SUREFIRE-1541),
 [SUREFIRE-1588](https://issues.apache.org/jira/browse/SUREFIRE-1588),
 [SUREFIRE-1608](https://issues.apache.org/jira/browse/SUREFIRE-1608)
)
* [PR #147](https://github.com/jenkinsci/plugin-pom/pull/147) -
Fix warning about deprecated `contextPath` parameter when using hpi:run
* [PR #146](https://github.com/jenkinsci/plugin-pom/pull/146) -
Remove the JitPack support,
it is replaced by Incrementals
([JEP-305](https://github.com/jenkinsci/jep/blob/master/jep/305/README.adoc))

### 3.32

Release date: 2018-12-21

* [JENKINS-54631](https://issues.jenkins-ci.org/browse/JENKINS-54631) -
  Update the JaCoCo Maven Plugin to `0.8.2` to support running with `-Penable-jacoco` on JDK11
* [JENKINS-55098](https://issues.jenkins-ci.org/browse/JENKINS-55098) -
  Fix the [objenesis](http://objenesis.org/) version suggested in dependency management,
  now it is `3.0.1` as required by PowerMock

### 3.31

Release date: 2018-12-14

* [PR #142](https://github.com/jenkinsci/plugin-pom/pull/142) - `mvn hpi:run` was broken as of 3.29.

### 3.30

Release date: 2018-12-13

* [PR #137](https://github.com/jenkinsci/plugin-pom/pull/137),
  [PR #138](https://github.com/jenkinsci/plugin-pom/pull/138),
  [PR #140](https://github.com/jenkinsci/plugin-pom/pull/140) -
  Bulk update of Maven plugins
* [JENKINS-55098](https://issues.jenkins-ci.org/browse/JENKINS-55098) -
  Include Mockito and PowerMock to dependency management
  so that plugins can easily pick up versions with Java 11 support

### 3.29

Release date: 2018-12-05

* [JENKINS-20679](https://issues.jenkins-ci.org/browse/JENKINS-20679) -
Plugin POM now produces `Minimum-Java-Version` entry in the plugin manifest
  * `java.level` property value is used by default
  * `plugin.minimumJavaVersion` property can be used to override the default value, e.g. for Java 11 experimental releases
   ([JEP-211](https://github.com/jenkinsci/jep/tree/master/jep/211))
  * **WARNING:** The override should not be used to define higher versions than the Jenkins core requirement
   until [JENKINS-55048](https://issues.jenkins-ci.org/browse/JENKINS-55048) is released and widely adopted
* [JENKINS-20679](https://issues.jenkins-ci.org/browse/JENKINS-20679) -
Update to Maven HPI Plugin 3.0
([changelog](https://github.com/jenkinsci/maven-hpi-plugin#30-2018-12-05))
* [PR #136](https://github.com/jenkinsci/plugin-pom/pull/136) -
Update to extra-enforcer-rules to `1.1` to support JDK 11 bytecode checks
* [PR #132](https://github.com/jenkinsci/plugin-pom/pull/132) -
Prevent warning about [missing SLF4J providers](http://www.slf4j.org/codes.html#release) during the build

### 3.28

Release date: 2018-11-07

* Backed out Surefire update in 3.27 due to some regressions, but adding an alternate workaround for the same bug.

### 3.27

Release date: 2018-11-07

* Surefire update to enable tests to be run on some Java versions, such as the current updates for Debian/Ubuntu.

### 3.26

Release date: 2018-10-30

* Update `maven-hpi-plugin` with a couple of bug fixes.

### 3.25

Release date: 2018 Oct 05

* Updated `jenkins-test-harness` with further fixes to the usage of temporary directories introduced in 3.22.

### 3.24

Release date: 2018 Oct 02

* Making sure the temporary directory introduced in 3.22 exists.
* Updated `jenkins-test-harness`.

### 3.23

Release date: 2018 Sep 21

* Updated `jenkins-test-harness`.

### 3.22

Release date: 2018 Sep 14

* The temporary directory for Surefire tests (used for, among many other things, `$JENKINS_HOME` under test) now defaults to a location inside the `target` directory rather than a system default as before. The Maven property `surefireTempDir` can be used to override this location in case of trouble.

### 3.21

Release date: 2018 Sep 05

* `mvn incrementals:update` mishandled property expressions.

### 3.20

Release date: 2018 Aug 27

* [PR #120](https://github.com/jenkinsci/plugin-pom/pull/120) -
Fix Maven site generation which was broken due to the dependency conflict (`mvn site`)

### 3.19

Release date: 2018 Jul 20

* [JENKINS-51869](https://issues.jenkins-ci.org/browse/JENKINS-51869): revision numbers for Incrementals now reflect the drop of a `--first-parent` argument, so typical numbers will be a few times larger.
* Tweaking `completionGoals` configuration from 3.18.

### 3.18

Release date: 2018 Jul 16

* [PR #117](https://github.com/jenkinsci/plugin-pom/pull/117) -
`incrementals:reincrementalify` is now invoked automatically
in release completion goals
* [PR #116](https://github.com/jenkinsci/plugin-pom/pull/116) -
Repository now references up-to-date Incrementals documentation

### 3.17

Release date: 2018 Jun 30

* [PR #115](https://github.com/jenkinsci/plugin-pom/pull/115) -
Update Animal Sniffer Plugin from 1.16 to 1.17 to support
signature scanning with JDK 10
([JENKINS-52155](https://issues.jenkins-ci.org/browse/JENKINS-52155))

### 3.16

Release date: 2018 Jun 22

* [PR #114](https://github.com/jenkinsci/plugin-pom/pull/114) -
Update Animal Sniffer Plugin from 1.15 to 1.16
* [PR #114](https://github.com/jenkinsci/plugin-pom/pull/114) -
Make Animal Sniffer Version configurable via the
`animal.sniffer.version` property

### 3.15

Release date: 2018 Jun 12

* Pick up a fix to the access modifier checker to ignore synthetic code, relevant particularly in some generics constructs.

### 3.14

Release date: 2018 Jun 06

* Another forked execution problem affecting plugins which both create a test JAR and use node/yarn to process JavaScript.

### 3.13

Release date: 2018 Jun 01

* Update HPI plugin [from 2.3 to 2.6](https://github.com/jenkinsci/maven-hpi-plugin/blob/master/README.md#26-2018-jun-01).
* Simplify usage of the Javadoc plugin, running it only when in release mode.

### 3.12

Release date: 2018 May 16

* Analogously to the change in 3.11, use `test-jar-no-fork` rather than `test-jar`.

### 3.11

Release date: 2018 May 15

* Use `source:jar-no-fork` goal instead of `source:jar` to avoid a Maven bug.

### 3.10

Release date: 2018 May 11

* Preconfigure the `incrementals` Maven plugin, so you can run `incrementals:incrementalify` and more.
* For Incrementals mode, configure `flatten-maven-plugin` to keep the generated POM in the `target` directory.

### 3.9

Release date: 2018 Apr 27

* Support for JEP-305 “Incrementals”. [Guide](https://github.com/jenkinsci/incrementals-tools/blob/master/README.md)
* Skip FindBugs checks during `release:perform` to save time.
* Minimum supported Maven version updated to 3.3.1 (higher if using Incrementals).

### 3.8

Release date: 2018 Apr 10

* Update Jenkins Test Harness from 2.34 to 2.38
([Changelog](https://github.com/jenkinsci/jenkins-test-harness/#changelog))

### 3.7

Release date: 2018 Apr 03

* Access modifier checker plugin updated to 1.14 and synchronized with the annotation library; introduces `@Restricted(Beta.class)` which may be consumed in a downstream plugin by setting the POM property `useBeta` to `true`.

### 3.6

Release date: 2018 Mar 09

* Access modifier checker plugin updated, making checks more strict in some cases, such as `@Restricted(NoExternalUse.class)` on a type. You may pass `-Daccess-modifier-checker.failOnError=false` as a temporary workaround.
* Animal Sniffer plugin updated, fixing errors in certain cases, such as use of Java 9-enabled libraries.

### 3.5

Release date: 2018 Feb 19

* [PR #95](https://github.com/jenkinsci/plugin-pom/pull/95) - extend bytecode rule fix made in 3.2 to `task-reactor`, needed for testing against Jenkins 2.105 and later.

### 3.4

* [PR #94](https://github.com/jenkinsci/plugin-pom/pull/94) - update `jenkins-test-harness` and `maven-hpi-plugin`.

### 3.3

* [PR #84](https://github.com/jenkinsci/plugin-pom/pull/84) -
Make FindBugs effort and threshold options configurable.

### 3.2

* Ignore Java 8 bytecode coming from Remoting and Stapler, permitting plugins with `java.level=7` to still build (for example from `Jenkinsfile`) with, for example, `jenkins.version=2.89`.
* Run tests in alphabetical order.

### 3.1

* Force `java.level` to be specified in each POM. 3.0 had changed the default value to `8`, causing plugins which had only overridden `jenkins.version` (to something older to 2.60.x) to not be runnable on older Jenkins installations using Java 7.
* Fail early if using JDK 7 or earlier to build. (`java.level=7` is fine.)
* `jenkins-test-harness` updated to [2.32](https://github.com/jenkinsci/jenkins-test-harness/#232-2017-oct-28).

### 3.0

* [PR#83](https://github.com/jenkinsci/plugin-pom/pull/83) **DROP SUPPORT FOR JENKINS 1.x**
* [PR#87](https://github.com/jenkinsci/plugin-pom/pull/87) Configure the _maven-javadoc-plugin_ to link back to the Jenkins Javadoc

### 2.37

Release date: 2017, Oct 23

* `jenkins-test-harness` updates to [2.31](https://github.com/jenkinsci/jenkins-test-harness/#231-2017-oct-17), important for testing against core 2.86+
* `concurrency` property deprecated; use `forkCount` instead
* FindBugs Maven plugin update to 3.0.5

### 2.36

Release date: 2017, Sep 27

* `jenkins-test-harness` updated to [2.28](https://github.com/jenkinsci/jenkins-test-harness/#228-2017-sep-26)
* `maven-hpi-plugin` updated to [2.1](https://github.com/jenkinsci/maven-hpi-plugin#21-2017-sep-26)

### 2.35

Release date: 2017, Sep 20

* JENKINS-45245: allow IntelliJ IDEA to run `JenkinsRule`-based tests, broken since 2.30 due to a mismatch with Maven’s idea of the test classpath.

### 2.34

Release date: 2017, Sep 18

* [PR #74](https://github.com/jenkinsci/plugin-pom/pull/74) -
Annotate `Messages` classes generated by Localizer with `@Restricted(NoExternalUse.class)`
  * If the message is exposed intentionally, it is recommended to expose it via Java API class instead of a generated one.
* [PR #71](https://github.com/jenkinsci/plugin-pom/pull/71) -
Specify the default Max Memory for Maven Surefire Plugin (768MB).

### 2.33

Release date: 2017, Aug 07

* JENKINS-41631 amendment to work better on new cores.

### 2.32

Release date: 2017, Jun 29

* JENKINS-41631 amendment to support 1.585- core versions.

### 2.31

Release date: 2017, Jun 26

* JENKINS-41631: use `requireUpperBoundDeps` to prevent various common versioning problems, such as accidentally using incompatible plugin dependencies.
* JENKINS-44453: JenkinsRule should ensure that Jenkins reaches the COMPLETED milestone.

### 2.30

Release date: 2017, May 25

* Integrated `maven-hpi-plugin` 2.0:
  * The `war-for-test` artifact is no longer needed for functional tests, saving download bandwidth.
  * Bundled Jetty was updated, so JDK 8 is required at build time.
* Setup Wizard disabled by default when using `hpi:run`. To enable it use `-Dhudson.Main.development=false`.

### 2.29

Release date: 2017, May 19

* For plugins which use npm, load node and npm binaries from the Jenkins Artifactory rather than a third-party server; yarn-based plugins load just the node binary from Artifactory.

### 2.28

Release date: 2017, May 04

* Updated `access-modifier-checker` to fix a critical bug (lack of enforcement of any method calls).

### 2.27

Release date: 2017, May 02

* Updated `maven-hpi-plugin`, `jenkins-test-harness`, and more.
* Support for building JavaScript libraries with yarn.
* Ability to specify `jenkins-core.version` and `jenkins-war.version` separately so as to depend on timestamped snapshots of Jenkins core.

### 2.26

* Update `frontend-maven-plugin` from `1.3` to `1.4` ([PR#53](https://github.com/jenkinsci/plugin-pom/pull/53))
* Update all maven plugins to latest ([PR#54](https://github.com/jenkinsci/plugin-pom/pull/54))

### 2.25

Release date: 2017, Mar 22

* [JENKINS-42800](https://issues.jenkins-ci.org/browse/JENKINS-42800) -
Bump JaCoCo version from `0.7.2.201409121644` to `0.7.9` to be compatible with [Jenkins JaCoCo plugin](https://plugins.jenkins.io/jacoco) data format.

Compatibility notes:
* The change introduces the new reporting format in JaCoCo.
It may cause regressions in build flows which have the `enable-jacoco` profile enabled.

### 2.24

Release date: 2017, Mar 08

* Updated `frontend-maven-plugin` and `download-maven-plugin` for the benefit of plugins using npm.
* Removed broken attempt to activate `enable-jacoco` profile by default. Now disabled unless selected.

### 2.23

Release date: 2017, Feb 22

* Using [version 2.18](https://github.com/jenkinsci/jenkins-test-harness#218-2016-dec-20) of the test harness.

### 2.22

Release date: 2017, Feb 13

* Updated `maven-hpi-plugin` for better snapshot behavior.

### 2.21

Release date: 2017, Jan 19

* Default to `concurrency=1`. To run faster tests locally, use for example
```xml
<profile>
    <id>faster</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
        <concurrency>1C</concurrency>
    </properties>
</profile>
```

### 2.20

Release date: 2017, Jan 14

* Default to non-verbose javadoc generation logs.
([PR #41](https://github.com/jenkinsci/plugin-pom/pull/41))

### 2.19

Release date: 2016, Nov 10

* Fixed a critical regression in 2.18.

### 2.18

This release is **BROKEN**, use 2.19 instead.

Release date: 2016, Nov 08

* Introduced `no-test-jar` property.

This **Incompatible** for plugins declaring a `jar:test-jar` execution; you must use the new property instead.

### 2.17 and earlier

Changes not recorded.
