pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timeout(time: 1, unit: 'HOURS')
    }
    agent {
        // TODO convert as in https://github.com/jenkinsci/archetypes/pull/44
        // (Archiving of build.log would be tricky; maybe just pass -Dinvoker.streamLogs instead.)
        docker {
            image 'maven:3.6.1-jdk-8'
            label 'docker'
        }
    }
    stages {
        stage('main') {
            steps {
                // TODO -s settings-azure.xml
                sh 'mvn -B --no-transfer-progress -Prun-plugin-pom-its clean verify'
            }
            post {
                failure {
                    catchError { // JENKINS-42478: in case the failure occurred prior to getting the node
                        archiveArtifacts artifacts: 'target/its/*/build.log', allowEmptyArchive: true
                    }
                }
            }
        }
    }
}
