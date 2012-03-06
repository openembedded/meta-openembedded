require libspatialite.inc

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "450d1a0d9da1bd9f770b7db3f2509f69"
SRC_URI[sha256sum] = "4983d6584069fd5ff0cfcccccee1015088dab2db177c0dc7050ce8306b68f8e6"

SRC_URI =+ "file://fix-the-configure-script-and-freexl.patch"

