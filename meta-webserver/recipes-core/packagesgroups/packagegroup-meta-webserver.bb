SUMMARY = "Meta-webserver packagegroups"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = ' \
    packagegroup-meta-webserver \
    packagegroup-meta-webserver-http \
    packagegroup-meta-webserver-php \
    packagegroup-meta-webserver-support \
    packagegroup-meta-webserver-webadmin \
'

RDEPENDS_packagegroup-meta-webserver = "\
    packagegroup-meta-webserver-http \
    packagegroup-meta-webserver-php \
    packagegroup-meta-webserver-support \
    packagegroup-meta-webserver-webadmin \
"

RDEPENDS_packagegroup-meta-webserver-http = "\
    nginx monkey cherokee hiawatha nostromo apache-websocket \
    apache2 sthttpd \
    "

RDEPENDS_packagegroup-meta-webserver-php = "\
    phpmyadmin xdebug \
    "

RDEPENDS_packagegroup-meta-webserver-support = "\
    spawn-fcgi fcgi \
    "

RDEPENDS_packagegroup-meta-webserver-webadmin = "\
    netdata webmin \
    "
EXCLUDE_FROM_WORLD = "1"
