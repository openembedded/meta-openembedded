inherit pkgconfig

require openssl.inc

# For target side versions of openssl enable support for OCF Linux driver
# if they are available.
DEPENDS += "ocf-linux"
CFLAG += "-DHAVE_CRYPTODEV -DUSE_CRYPTODEV_DIGESTS"

SRC_URI[src.md5sum] = "ff8fb85610aef328315a9decbb2712e4"
SRC_URI[src.sha256sum] = "f731b36de3edaa361179ae6f449668b248a360e34e31e92902d976e9b9d604eb"

PR = "${INC_PR}.2"

DEFAULT_PREFERENCE = "-1"

export DIRS = "crypto ssl apps engines"
export OE_LDFLAGS="${LDFLAGS}"

SRC_URI += "file://configure-targets.patch \
            file://shared-libs.patch \
            file://debian.patch \
            file://oe-ldflags.patch \
	    file://libdeps-first.patch \
	    file://engines-install-in-libdir-ssl.patch \
	   "

PARALLEL_MAKE = ""

PACKAGES += " \
	${PN}-engines \
	${PN}-engines-dbg \
	"

FILES_${PN}-engines = "${libdir}/ssl/engines/*.so"
FILES_${PN}-engines-dbg = "${libdir}/ssl/engines/.debug"
