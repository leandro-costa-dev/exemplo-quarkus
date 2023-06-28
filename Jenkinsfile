pipeline {
   agent any;
   tools {
      jdk 'JDK_17.0.7'
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
              echo 'Iniciando Analise do código...'
              bat '${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=pipeline-quarkus -Dsonar.host.url=http://localhost:9000 -Dsonar.login=9cf3dedcb948ba1f10bccfd24e6a1125396d53d9 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/entity/**,**/dto/**,MavenWrapperDownloader.java'
          }
      }
      stage('Sonar Quality Gate') {
          steps {
               echo 'Iniciando Analise de qualidade...'
               bat
          }
     }
   }
}