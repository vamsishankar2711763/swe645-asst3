pipeline{
    
    environment {
        registry = "vsimhadr/studentsurvey645"
        registryCredential = 'dockerhub'
        def dateTag = new Date().format("yyyyMMdd-HHmmss")
	}
agent any
    tools{
        maven 'Maven 3.8.6'
    }
    stages{
     stage('Maven clean & Install'){
        steps{
            script{
                sh 'mvn clean'
                sh 'mvn install'
            }
        }
     }
     stage('Build Docker Image and Push to'){
        steps{
            script {
                  docker.withRegistry('',registryCredential) {
                        def image = docker.build('vsimhadr/studentsurvey645:'+ dateTag, '. --no-cache')
                        docker.withRegistry('',registryCredential) {
                            image.push()
                        }
                    }
            }
        }
     }
      stage('Deploying to single node in Rancher'){
        steps{
            script { 
                  sh 'kubectl set image deployment/deploy-1 container-0=vsimhadr/studentsurvey645:'+dateTag
            }
        }
     }
     }
 
  post {
	  always {
			sh 'docker logout'
		}
	}    
}
