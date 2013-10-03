DESCRIPTION = "AutobahnPython provides a WebSocket (RFC6455, Hybi-10 to -17, Hixie-76) framework for creating WebSocket-based clients and servers. \
AutobahnPython also includes an implementation of WAMP (The WebSockets Application Messaging Protocol), a light-weight,  \
asynchronous RPC/PubSub over JSON/WebSocket protocol."
HOMEPAGE = "http://autobahn.ws/python"
SECTION = "console/network"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " file://autobahn/__init__.py;beginline=1;endline=17;md5=81964ceb3ab93bfb5e8cfbcd26f3af9c"

SRC_URI = "https://pypi.python.org/packages/source/a/autobahn/autobahn-${PV}.zip"
SRC_URI[md5sum] = "e2c80ba3e46428b32837537609d4d582"

S = "${WORKDIR}/autobahn-${PV}"

inherit setuptools

RDEPENDS_${PN} = "python python-pprint python-twisted-protocols python-netserver python-twisted-web"

