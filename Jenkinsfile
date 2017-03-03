properties([buildDiscarder(logRotator(numToKeepStr: '20'))])
timeout(time: 1, unit: 'HOURS') {
    node('docker') {
        dir('plugin-pom') {
            checkout scm
        }
        dir('maven-hpi-plugin') {
            checkout([$class: 'GitSCM', userRemoteConfigs: [[url: 'https://github.com/jenkinsci/maven-hpi-plugin.git']], branches: [[name: '*/master']], extensions: [[$class: 'CleanBeforeCheckout']]])
        }
        docker.image('maven:3.3.9-jdk-8').inside {
            sh 'cd plugin-pom && sh integration-test.sh'
        }
        try {
            archiveArtifacts artifacts: 'maven-hpi-plugin/target/its/parent-2x-SNAPSHOT/build.log', allowEmptyArchive: true            
        } catch (e) {
            throw e
        }
    }
}
