properties([
  buildDiscarder(logRotator(numToKeepStr: '10')),
  disableConcurrentBuilds(abortPrevious: true)
])

node('maven-8') {
  stage('Checkout') {
    infra.checkoutSCM()
  }

  stage('Build') {
    timeout(time: 1, unit: 'HOURS') {
      ansiColor('xterm') {
        def mavenOptions = [
          '-Dset.changelist',
          '-Dstyle.color=always',
          'clean',
          'verify',
        ]
        infra.runMaven(mavenOptions, 8)
        infra.prepareToPublishIncrementals()
      }
    }
  }
}

infra.maybePublishIncrementals()
