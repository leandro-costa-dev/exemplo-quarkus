pipeline {
   agent any;
   tools {
      jdk 'JDK_11'
      maven 'MAVEN_3.8.8'
   }
   stages {
      stage('Build do Projeto') {
         steps {
              echo 'Iniciando Build...'
              bat ' ./mvnw clean package -DskipTests=true'
             }
         }
      stage('Testes Unitarios') {
          steps {
              echo 'Iniciando testes...'
              bat './mvnw verify'
          }
      }

      stage('Sonar Analise') {
           environment{
               scannerHome = tool 'sonar-scanner'
           }
           steps {
                withSonarQubeEnv('Sonar'){
                   echo 'Iniciando Analise do código...'
                   bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=pipeline-quarkus -Dsonar.host.url=http://192.168.100.10:9000 -Dsonar.login=9cf3dedcb948ba1f10bccfd24e6a1125396d53d9 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/entity/**,**/dto/**,MavenWrapperDownloader.java"
                }
           }
      }

      stage('Sonar QualityGate') {
            steps {
                sleep(5)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
      }

      stage('Deploy Docker') {
            steps {
                echo 'Realizando Deploy no Docker...'
                bat ' docker-compose build'
                bat ' docker-compose up -d'
            }
      }

      stage('Limpar Cache do Docker') {
            steps {
                echo 'Realizando Limpeza do Docker...'
                sleep(10)
                bat ' docker system prune -f'
                bat ' docker ps'
            }
      }
   }
   post{
       always{
           junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
       }
       unsuccessful {
            emailext attachLog: true, body: 'LOG:', subject: 'BUILD $BUILD_NUMBER exemplo-quarkus - Pipeline executada com Erro(s)!', to: 'costaleandro1987@gmail.com'

       }
       fixed {
            emailext attachLog: true, body: 'LOG:', subject: 'BUILD $BUILD_NUMBER exemplo-quarkus - Pipeline executada com Sucesso!', to: 'costaleandro1987@gmail.com'
       }
   }
}

