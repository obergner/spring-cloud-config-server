#!/usr/bin/env bash

scriptdir="$(dirname "$0")"
builddir="${scriptdir}/target"
jar="${builddir}/spring-cloud-config-server-1.0.0-SNAPSHOT.jar"

java -Dspring.profiles.active=local -jar ${jar}

