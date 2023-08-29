#!/bin/bash
./mvnw clean -Pprod verify jib:dockerBuild
