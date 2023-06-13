pipeline {
   agent any;
   stages {
      stage('Testes Unitarios') {
          steps {
              echo 'Iniciando testes...'
              bat 'mvn test'
          }
      }
      stage('Build do Projeto') {
          steps {
              echo 'Iniciando Build...'
              bat ' mvn clean package -DskipTests=true'
          }
      }
   }
}
