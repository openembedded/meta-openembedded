require ipsec-tools.inc

LIC_FILES_CHKSUM = "file://src/libipsec/pfkey.c;beginline=6;endline=31;md5=bc9b7ff40beff19fe6bc6aef26bd2b24"

SRC_URI += "file://ipsec-tools-install.patch"
SRC_URI[ipsec-tools-0.7.2.md5sum] = "72861f005746ee27984b2ee715ecc629"
SRC_URI[ipsec-tools-0.7.2.sha256sum] = "08722ff6c62de3e042fef337454f03622a79053108d6dcc686c9c854f9f9e031"
