pipeline {
  agent any
  
  def registry = "sensileappz/sample-microservices"
  def registryCredential = ‘dockerhub’
  
  stages {
    stage('Compile') {
      steps {
        script {
          def mvnHome = tool 'Maven 3.6.2'
          if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dintegration-tests.skip=true clean package"
            // execute the unit testing and collect the reports
            junit '**//*target/surefire-reports/TEST-*.xml'
            archive 'target*//*.jar'
          } else {
            bat(/"${mvnHome}\bin\mvn" -Dintegration-tests.skip=true clean package/)
            junit '**//*target/surefire-reports/TEST-*.xml'
            archive 'target*//*.jar'
          }
        }

      }
    }
    stage('Build Image') {
      steps {
        script {
          docker.build(registry + ":$BUILD_NUMBER")
        }
      }
    }
  }
}
