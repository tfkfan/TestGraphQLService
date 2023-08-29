#!/bin/bash
./mvnw clean -Pprod verify jib:dockerBuild && docker-compose -f src/main/docker/app.yml -p svc up -d
