inherit pkgconfig

require openssl.inc
SRC_URI[src.md5sum] = "e3873edfffc783624cfbdb65e2249cbd"
SRC_URI[src.sha256sum] = "18a9bd1fc02b8ef90dded34fafaa9089baaafef278a19fc4e89c2ab0dcf70f63"

PR = "${INC_PR}.1"

DEFAULT_PREFERENCE = "-1"

export DIRS = "crypto ssl apps engines"
export OE_LDFLAGS="${LDFLAGS}"

SRC_URI += "file://configure-targets.patch \
            file://shared-libs.patch \
            file://debian.patch \
            file://oe-ldflags.patch \
	    file://libdeps-first.patch \
	    file://engines-install-in-libdir-ssl.patch \
	    file://openssl-fix-ssl3_get_key_exchange-double-free.patch \
	   "

PARALLEL_MAKE = ""

PACKAGES += " \
	${PN}-engines \
	${PN}-engines-dbg \
	"

FILES_${PN}-engines = "${libdir}/ssl/engines/*.so"
FILES_${PN}-engines-dbg = "${libdir}/ssl/engines/.debug"
