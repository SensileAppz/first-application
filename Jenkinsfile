pipeline {
  agent any
  stages {
    stage('Code Checkout') {
      steps {
        git(url: 'git@github.com:SensileAppz/first-application.git', branch: 'master', changelog: true, credentialsId: 'GIT')
      }
    }
  }
}