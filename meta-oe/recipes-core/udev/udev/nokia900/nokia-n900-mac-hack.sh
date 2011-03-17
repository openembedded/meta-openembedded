#!/bin/sh

if [ $# -ne 2 ]; then
	echo "Usage: $0 iface newmac"
	echo "       newmac is only saved if /etc/<iface>MAC is not found"
	exit 1
fi

iface="$1"
newmac="$2"
macfile=/etc/"$iface"mac

# If no MAC is found, save the one given as argument
if [ ! -e $macfile ]; then
   /bin/echo "$newmac" > $macfile
# Otherwise load MAC from file
else
   newmac=`/bin/cat $macfile`
fi

# Configure interface
/sbin/ifconfig "$iface" down
/sbin/ifconfig "$iface" hw ether $newmac
