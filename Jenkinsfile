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
    stage('Destroy') {
      parallel {
        stage('Destroy') {
          steps {
            script {
              sh "docker rmi $registry:$BUILD_NUMBER"
            }

          }
        }
        stage('Versioning') {
          steps {
            git(url: 'git@github.com:SensileAppz/jenkins.git', branch: 'master', credentialsId: 'GIT')
            sh '''echo "$BUILD_NUMBER" > first-application-version
            git config --global user.email "srikar2642@gmail.com"
            git config --global user.name "Janardhan Korada"
            git add first-application-version
            git commit -m \'Added New Version Number\''''
            withCredentials([sshUserPrivateKey(credentialsId: 'GIT', keyFileVariable: 'SSH_KEY')]) {
              sh("git push origin master:master")
            }
          }
        }
      }
    }
  }
  environment {
    registry = 'sensileappz/first-application'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
}
