require openssl.inc

inherit pkgconfig native

SRC_URI[src.md5sum] = "ff8fb85610aef328315a9decbb2712e4"
SRC_URI[src.sha256sum] = "f731b36de3edaa361179ae6f449668b248a360e34e31e92902d976e9b9d604eb"
PR = "${INC_PR}.0"

DEFAULT_PREFERENCE = "-1"

export DIRS = "crypto ssl apps engines"

# This flag can contain target options (e.g -mfpu=neon for armv7-a systems)
export FULL_OPTIMIZATION = " "
export BUILD_OPTIMIZATION = " "

SRC_URI += "file://configure-targets.patch \
            file://shared-libs.patch \
            file://debian.patch \
	    file://libdeps-first.patch \
	   "

PARALLEL_MAKE = ""

CFLAG += " -Wa,--noexecstack "

NATIVE_INSTALL_WORKS = "1"
