SUMMARY = "iODBC driver manager maintained by OpenLink Software."

DESCRIPTION = "This kit will provide you with everything you need to \
develop ODBC-compliant applications under Unix without having to pay \
royalties to other parties. \
"

HOMEPAGE = "http://www.iodbc.org/"

LICENSE = "LGPL-2.0-only | BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=8c0138a8701f0e1282f5c8b2c7d39114 \
                    file://LICENSE.BSD;md5=ff3a66a194e500df485da930da7f2c62 \
                    "

SRC_URI = "https://github.com/openlink/iODBC/archive/refs/tags/v3.52.15.tar.gz \
           "
S = "${WORKDIR}/iODBC-${PV}"
SRC_URI[sha256sum] = "f6b376b6dffb4807343d6d612ed527089f99869ed91bab0bbbb47fdea5ed6ace"

inherit autotools

EXTRA_OECONF += " --prefix=/usr/local \
		--with-iodbc-inidir=/etc \
		--enable-odbc3 \
		--enable-pthreads \
		--disable-libodbc \
		--disable-static \
		"
