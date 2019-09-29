pipeline {
  agent any
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
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }

      }
    }
    stage('Push Image') {
      steps {
        script {
          docker.withRegistry('', registryCredential) {
            dockerImage.push()
          }
        }

      }
    }
  }
  environment {
    registry = 'sensileappz/sample-microservices'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
}