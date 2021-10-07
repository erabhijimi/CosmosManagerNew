pipeline{
    agent any
    stages{
        stage("Startup"){
            steps{
                echo "Welcome Abhijit..."
            }
        }
        stage('Build service-discovery') {
            steps {
                sh 'mvn -f service-discovery/pom.xml clean install'
            }
        }
        stage('Build cloud-gateway-services') {
            steps {
                sh 'mvn -f cloud-gateway-services/pom.xml clean install'
            }
        }
        stage('Build login-service') {
            steps {
                sh 'mvn -f login-service/pom.xml clean install'
            }
        }
    }
}
