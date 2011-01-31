require qt4-tools-native.inc

PR = "${INC_PR}.0"

# Find the g++.conf/linux.conf in the right directory.
FILESPATHPKG =. "qt-${PV}:"

EXTRA_OECONF += " -no-fast -silent -no-rpath"

TOBUILD := "src/tools/bootstrap ${TOBUILD}"

SRC_URI[md5sum] = "6f88d96507c84e9fea5bf3a71ebeb6d7"
SRC_URI[sha256sum] = "8cb5277c41f824cfc6dcee0e95e0bf23a9ad2c8d18d245105137481d092b124a"
