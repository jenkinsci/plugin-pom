properties([buildDiscarder(logRotator(numToKeepStr: '20'))])
node('maven-11') {
    checkout scm
    timeout(time: 1, unit: 'HOURS') {
        // TODO Azure mirror
        ansiColor('xterm') {
            withEnv(['MAVEN_OPTS=-Djansi.force=true']) {
                sh 'mvn -B -Dstyle.color=always -ntp clean verify'
            }
        }
    }
}
