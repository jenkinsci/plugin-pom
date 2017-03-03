properties([buildDiscarder(logRotator(numToKeepStr: '20'))])
timeout(time: 1, unit: 'HOURS') {
    node('docker') {
        checkout scm
        docker.image('maven:3.3.9-jdk-8').inside {
            sh 'mvn -B -Prun-plugin-pom-its clean verify'
        }
        try { // TODO cannot use Declarative due to JENKINS-42478
            archiveArtifacts artifacts: 'target/its/*/build.log', allowEmptyArchive: true            
        } catch (e) {
            throw e
        }
    }
}
