pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timeout(time: 1, unit: 'HOURS')
    }
    agent {
        docker {
            image 'maven:3.5.0-jdk-8'
            label 'docker'
        }
    }
    stages {
        stage('main') {
            steps {
                sh 'echo -rc$(git rev-list --first-parent --count HEAD).$(git rev-parse --short=12 HEAD); echo -rc$(git rev-list --first-parent --count $(git merge-base master HEAD)).$(git rev-list --first-parent --count ^master HEAD).$(git rev-parse --short=12 HEAD)' // TODO just checking
                sh 'mvn -B -Prun-plugin-pom-its clean verify'
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
