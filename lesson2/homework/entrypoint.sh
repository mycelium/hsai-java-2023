#!/bin/sh
set -e

CMD_PARAM=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
export ENV_PARAM=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)

exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$DEBUG_PORT Main.java $CMD_PARAM