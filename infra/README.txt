server IP : 51.255.168.95
server user : fedora

you must have an ssh key trusted by the server to be able to connect.

webserver is configured as a service on the server.

deploy.sh script trigger a deployment of last commit of origin/master branch on the server

git pull is made through ssh using the fedora_server key

if the fedora machine restarts, you will need to reconfigured the ssh agent in order to avoid ssh key password prompting
when deploying.

ssh-add ~/.ssh/id_ed25519

password of the key: root (should be changed and stored elsewhere but... since the fedora host can only be access by trusted ssh key,
we are pretty safe.