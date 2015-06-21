#!/bin/sh

/usr/sbin/groupadd -r wm-config-server &>/dev/null || :
# SUSE version had -o here, but in Fedora -o isn't allowed without -u
/usr/sbin/useradd -g wm-config-server -s /bin/false -r -c "Spring Cloud config server" \
	-d "/usr/share/wm-config-server" wm-config-server &>/dev/null || :
