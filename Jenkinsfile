properties([
  buildDiscarder(logRotator(numToKeepStr: '10')),
  disableConcurrentBuilds(abortPrevious: true)
])

node('maven-11') {
  stage('Checkout') {
    infra.checkoutSCM()
  }

  stage('Build') {
    timeout(time: 1, unit: 'HOURS') {
      def mavenOptions = [
        '-Dset.changelist',
        'clean',
        'install',
      ]
      infra.runMaven(mavenOptions, 11)
      infra.prepareToPublishIncrementals()
    }
  }
}

infra.maybePublishIncrementals()
