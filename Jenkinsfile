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
      stage('Imagem Docker') {
          steps {
              echo 'Gerando imagem docker...'
              bat 'docker build -f src/main/docker/Dockerfile.jvm -t quarkus/exemplo-quarkus-jvm .'
          }
      }
   }
}