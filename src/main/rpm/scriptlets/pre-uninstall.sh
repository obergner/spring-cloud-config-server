#!/bin/sh

if [ "$1" = 0 ] ; then
    # if this is uninstallation as opposed to upgrade, delete the service
    /sbin/service wm-config-server stop > /dev/null 2>&1
    /sbin/chkconfig --del wm-config-server
fi
exit 0
