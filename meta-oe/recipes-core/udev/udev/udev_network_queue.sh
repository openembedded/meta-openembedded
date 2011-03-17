#! /bin/sh
#
# Copyright Matthias Hentges <devel@hentges.net> (c) 2006
# License: GPL (see http://www.gnu.org/licenses/gpl.txt for a copy of the license)
#
# Filename: udev_network_queue.sh
# Date: 03-May-06

do_start() {
	if test -e /dev/udev_network_queue
	then
		echo "Activating queued NICs..."
		for NIC in `cat /dev/udev_network_queue`
		do	
			export INTERFACE="$NIC" ; export ACTION=add
			/etc/udev/scripts/network.sh
		done
		echo ""
	else
		echo "No NICs queued"
	fi
}

do_stop() {
	/bin/true
}

case "$1" in
start)		do_start;;
stop)		do_stop;;
restart)	do_stop
		do_start;;
*)	echo "Usage: `basename $0` [ start | stop | restart ]"
	exit 0;;
esac
