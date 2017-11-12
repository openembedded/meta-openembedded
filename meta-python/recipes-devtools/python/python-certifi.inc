SUMMARY = "Python package for providing Mozilla's CA Bundle."
DESCRIPTION = "This installable Python package contains a CA Bundle that you can reference in your \
Python code. This is useful for verifying HTTP requests, for example.  This is the same CA Bundle \
which ships with the Requests codebase, and is derived from Mozilla Firefox's canonical set."
HOMEPAGE = " http://certifi.io/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f77f61d14ee6feac4228d3ebd26cc1f1"

SRC_URI[md5sum] = "c15ac46ed1fe4b607ff3405928f9a992"
SRC_URI[sha256sum] = "5ec74291ca1136b40f0379e1128ff80e866597e4e2c1e755739a913bbc3613c0"

BBCLASSEXTEND = "native nativesdk"
