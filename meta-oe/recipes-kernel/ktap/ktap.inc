# Released under the MIT license (see COPYING.MIT for the terms)

HOMEPAGE = "http://www.ktap.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE-GPL;md5=eb723b61539feef013de476e68b5c50a"

SRCREV = "c8401e07c89cdec9a5f673da4ddb12b7982b522b"
SRC_URI = "git://github.com/ktap/ktap.git"

S = "${WORKDIR}/git"

# Package config is abused as a general compile time configuration tool.
PACKAGECONFIG[ffi] = ""
