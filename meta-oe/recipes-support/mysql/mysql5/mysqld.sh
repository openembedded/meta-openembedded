# MySQL init script

. /etc/default/rcS

case "$1" in
	start)
		/usr/bin/mysqld_safe &
		;;
	stop)
		if test -f /var/lib/mysql/mysqld.pid ; then
			PID=`cat /var/lib/mysql/mysqld.pid`
			kill $PID
		fi
		;;
	restart)
		$0 stop
		$0 start
		;;
	*)
		echo "Usage: /etc/init.d/mysqld {start|stop|restart}"
		;;
esac

exit 0
