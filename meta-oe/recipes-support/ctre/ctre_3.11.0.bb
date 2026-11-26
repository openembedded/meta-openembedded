DESCRIPTION = "Fast compile-time regular expressions with support for matching/searching/capturing."
HOMEPAGE = "https://github.com/hanickadot/compile-time-regular-expressions"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2e982d844baa4df1c80de75470e0c5cb"

SRC_URI = "git://github.com/hanickadot/compile-time-regular-expressions.git;protocol=https;branch=main;tag=v${PV}"
SRCREV = "4ab18abfd88ccfc867734bf5d31b13285854577e"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"


inherit cmake

PACKAGECONFIG ??= ""
PACKAGECONFIG[module] = "-DCTRE_MODULE=ON,-DCTRE_MODULE=OFF"
PACKAGECONFIG[tests] = "-DCTRE_BUILD_TESTS=ON,-DCTRE_BUILD_TESTS=OFF"

EXTRA_OECMAKE:append = " \
-DCTRE_BUILD_PACKAGE=OFF \
-DCTRE_BUILD_PACKAGE_DEB=OFF \
-DCTRE_BUILD_PACKAGE_RPM=OFF \
"
