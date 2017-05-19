SUMMARY = "Python package for providing Mozilla's CA Bundle."
DESCRIPTION = "This installable Python package contains a CA Bundle that you can reference in your \
Python code. This is useful for verifying HTTP requests, for example.  This is the same CA Bundle \
which ships with the Requests codebase, and is derived from Mozilla Firefox's canonical set."
HOMEPAGE = " http://certifi.io/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f77f61d14ee6feac4228d3ebd26cc1f1"

SRC_URI[md5sum] = "db40445044feda1437ce3ccd5fc28a57"
SRC_URI[sha256sum] = "f7527ebf7461582ce95f7a9e03dd141ce810d40590834f4ec20cddd54234c10a"

BBCLASSEXTEND = "native nativesdk"
