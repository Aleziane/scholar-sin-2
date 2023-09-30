#!/bin/bash

sudo docker exec -it "$(sudo docker ps -aqf "name=postgres" )" psql  -U radnelac -W radnelac