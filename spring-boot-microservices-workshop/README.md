#Create config directory

 cd $HOME 
 mkdir app-config-repo 
 cd app-config-repo 
 git init . 
 echo 'user.role=Dev' > application-dev.properties 
 echo 'user.role=Admin' > application-prod.properties 
 git add . 
 git commit -m 'Initial commit for application properties'
