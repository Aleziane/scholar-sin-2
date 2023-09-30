#!/bin/bash

sudo docker run --name postgres -e PGPASSWORD=root -e POSTGRES_PASSWORD=root -e POSTGRES_USER=radnelac  -p "5432:5432" -v "${PWD}"/db/mount:/var/lib/postgresql/data -d postgres