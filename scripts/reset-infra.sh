#!/bin/bash

sudo docker kill postgres
yes y | docker container prune
rm -rf ./db/mount

./start-dev-infra.sh
