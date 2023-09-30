#!/bin/bash

echo "CAREFUL, you must have your ssh key configured for the user fedora on distant host"
SERVER="51.255.168.95"
USER="fedora"
echo "Configuring server"
ssh "${USER}@${SERVER}" << 'EOL'
                        	cd /home/fedora/radnelac
                        	git checkout master
                        	eval `ssh-agent -s`
                          echo 'root' | ssh-add /home/fedora/.ssh/id_ed25519
                        	git pull
                        	mvn clean package
                        	sudo cp app/target/app-*.jar /opt/radenlac/app.jar
                        	echo 'restarting service'
                        	sudo chmod 777 /opt/radenlac/app.jar
                        	sudo systemctl restart radenlac
EOL