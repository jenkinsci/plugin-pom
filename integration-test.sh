#!/bin/sh
set -e
mvn -B clean install
version=`mvn help:evaluate -Dexpression=project.version | perl -n -e 'print $1 if /^([0-9.]+-SNAPSHOT)$/'`
cd ../maven-hpi-plugin
rm -rf src/it/parent-2x-SNAPSHOT
cp -rv src/it/parent-2x src/it/parent-2x-SNAPSHOT
perl -0777 -p -i -e "s{        <version>[0-9.]+</version>}{<version>${version}</version>}" src/it/parent-2x-SNAPSHOT/pom.xml
mvn -B clean verify -Prun-its -Dinvoker.test=parent-2x-SNAPSHOT
# and check maven-hpi-plugin/target/its/parent-2x-SNAPSHOT/build.log
