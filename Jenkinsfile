pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh 'sh \'./mvnw -B -DskipTests clean package\''
      }
    }
  }
}