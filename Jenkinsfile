pipeline {
  agent any
  stages {
    stage('Build with unit testing') {
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
  }
}