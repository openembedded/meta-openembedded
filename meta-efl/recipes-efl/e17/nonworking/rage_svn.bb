SUMMARY = "Rage is a media center application based on EFL"
AUTHOR = "Carsten 'The Rasterman' Haitzler"
HOMEPAGE = "http://www.rasterman.com"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=344895f253c32f38e182dcaf30fe8a35"
DEPENDS = "emotion evas ecore edje"
SECTION = "x11/multimedia"
PV = "0.3.0.042+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};protocol=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"
