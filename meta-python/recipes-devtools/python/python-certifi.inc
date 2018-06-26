SUMMARY = "Python package for providing Mozilla's CA Bundle."
DESCRIPTION = "This installable Python package contains a CA Bundle that you can reference in your \
Python code. This is useful for verifying HTTP requests, for example.  This is the same CA Bundle \
which ships with the Requests codebase, and is derived from Mozilla Firefox's canonical set."
HOMEPAGE = " http://certifi.io/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f77f61d14ee6feac4228d3ebd26cc1f1"

SRC_URI[sha256sum] = "13e698f54293db9f89122b0581843a782ad0934a4fe0172d2a980ba77fc61bb7"
SRC_URI[md5sum] = "f5d81d0048e84285611412b0dea0630a"

BBCLASSEXTEND = "native nativesdk"
