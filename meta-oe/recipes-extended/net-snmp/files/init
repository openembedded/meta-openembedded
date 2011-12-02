#! /bin/sh
# /etc/init.d/snmpd: start snmp daemon.

test -x /usr/sbin/snmpd || exit 0
test -x /usr/sbin/snmptrapd || exit 0

# Defaults
export MIBDIRS=/usr/share/snmp/mibs
SNMPDRUN=yes
SNMPDOPTS='-Lsd -Lf /dev/null -p /var/run/snmpd.pid'
TRAPDRUN=no
TRAPDOPTS='-Lsd -p /var/run/snmptrapd.pid'

# Cd to / before starting any daemons.
cd /

case "$1" in
  start)
    echo -n "Starting network management services:"
    if [ "$SNMPDRUN" = "yes" -a -f /etc/snmp/snmpd.conf ]; then
	start-stop-daemon -S -x /usr/sbin/snmpd \
	    -- $SNMPDOPTS
	echo -n " snmpd"
    fi
    if [ "$TRAPDRUN" = "yes" -a -f /etc/snmp/snmptrapd.conf ]; then
	start-stop-daemon -S -x /usr/sbin/snmptrapd \
	    -- $TRAPDOPTS
	echo -n " snmptrapd"
    fi
    echo "."
    ;;
  stop)
    echo -n "Stopping network management services:"
    start-stop-daemon -K -x /usr/sbin/snmpd
    echo -n " snmpd"
    start-stop-daemon -K -x /usr/sbin/snmptrapd
    echo -n " snmptrapd"
    echo "."
    ;;
  restart|reload|force-reload)
    echo -n "Restarting network management services:"
    start-stop-daemon -K -x /usr/sbin/snmpd
    start-stop-daemon -K -x /usr/sbin/snmptrapd
    # Allow the daemons time to exit completely.
    sleep 2
    if [ "$SNMPDRUN" = "yes" -a -f /etc/snmp/snmpd.conf ]; then
	start-stop-daemon -S -x /usr/sbin/snmpd -- $SNMPDOPTS
	echo -n " snmpd"
    fi
    if [ "$TRAPDRUN" = "yes" -a -f /etc/snmp/snmptrapd.conf ]; then
	# Allow snmpd time to start up.
	sleep 1
	start-stop-daemon -S -x /usr/sbin/snmptrapd -- $TRAPDOPTS
	echo -n " snmptrapd"
    fi
    echo "."
    ;;
  *)
    echo "Usage: /etc/init.d/snmpd {start|stop|restart|reload|force-reload}"
    exit 1
esac

exit 0
