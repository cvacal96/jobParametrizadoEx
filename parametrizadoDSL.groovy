job('ejemplo-job-DSL') {
	description('Job DSL de ejemplo para el curso de Jenkins')
  scm { 
   	git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node -> 
    	node / gitConfigName('cvacal96')
      	node / gitConfigEmail('cvacal96@gmail.com')
    }
  }
  parameters {
  	stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Boolenao')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    cron('H/5 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('cvacal96@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
