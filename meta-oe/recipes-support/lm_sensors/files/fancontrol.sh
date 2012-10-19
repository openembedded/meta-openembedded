#! /bin/sh
### BEGIN INIT INFO
# Provides:          fancontrol
# Required-Start:    $local_fs
# Should-Start:
# Required-Stop:     $local_fs
# Should-Stop:
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: fancontrol initscript
# Description:       Starts and controls the fancontrol daemon
### END INIT INFO

PATH=/sbin:/usr/sbin:/bin:/usr/bin

DESC="fan control daemon"
NAME="fancontrol"
FANCONTROL=`which $NAME`

. /etc/init.d/functions || exit 1

# Exit if the package is not installed
[ -x "$FANCONTROL" ] || exit 0

case "$1" in
    start)
        echo -n "Starting $DESC: $NAME... "
        /sbin/start-stop-daemon -S -x $FANCONTROL -b -- $FANCONTROL_ARGS
        echo "done."
        ;;
    stop)
        echo -n "Stopping $DESC: $NAME... "
        /sbin/start-stop-daemon -K -x $FANCONTROL
        echo "done."
        ;;
    restart)
        echo "Restarting $DESC: $NAME... "
        $0 stop
        $0 start
        echo "done."
        ;;
    *)
        echo "Usage: $0 {start|stop|restart}"
        exit 1
        ;;
esac

exit 0
