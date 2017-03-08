Changelog
====

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
