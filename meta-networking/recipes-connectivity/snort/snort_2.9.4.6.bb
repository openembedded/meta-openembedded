DESCRIPTION = "snort - a free lightweight network intrusion detection system for UNIX and Windows."
HOMEPAGE = "http://www.snort.org/"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=78fa8ef966b48fbf9095e13cc92377c5"

DEPENDS = "libpcap libpcre daq libdnet"


SRC_URI = " ${GENTOO_MIRROR}/${BP}.tar.gz;name=tarball \
            file://snort.init \
            file://disable-inaddr-none.patch \
            file://disable-dap-address-space-id.patch \
            file://0001-libpcap-search-sysroot-for-headers.patch \
"

SRC_URI[tarball.md5sum] = "4111df01a4f21bd1d328a18b76d625bd"
SRC_URI[tarball.sha256sum] = "cfaa5390b1840aaaa68a6c05a7077dd92cb916e6186a014baa451d43cdb0b3bc"

inherit autotools  gettext  update-rc.d

INITSCRIPT_NAME = "snort"
INITSCRIPT_PARAMS = "defaults"

EXTRA_OECONF = " \
	--enable-gre \
	--enable-linux-smp-stats \
	--enable-reload \
	--enable-reload-error-restart \
	--enable-targetbased \
	--disable-static-daq \
	"

do_install_append() {
    install -d ${D}/${sysconfdir}/snort/rules
    install -d ${D}/${sysconfdir}/snort/preproc_rules
    install -d ${D}${sysconfdir}/init.d
    for i in map config conf dtd; do
        cp ${S}/etc/*.$i ${D}/${sysconfdir}/snort/
    done
    cp ${S}/preproc_rules/*.rules ${D}/${sysconfdir}/snort/preproc_rules/
    install -m 755 ${WORKDIR}/snort.init ${D}/${sysconfdir}/init.d/snort
    mkdir -p ${D}/${localstatedir}/log/snort
    install -d ${D}/var/log/snort
}

FILES_${PN} += " \
	${libdir}/snort_dynamicengine/*.so.* \
	${libdir}/snort_dynamicpreprocessor/*.so.* \
	${libdir}/snort_dynamicrules/*.so.* \
	"
FILES_${PN}-dbg += " \
	${libdir}/snort_dynamicengine/.debug \
	${libdir}/snort_dynamicpreprocessor/.debug \
	${libdir}/snort_dynamicrules/.debug \
	"
FILES_${PN}-staticdev += " \
	${libdir}/snort_dynamicengine/*.a \
	${libdir}/snort_dynamicpreprocessor/*.a \
	${libdir}/snort_dynamicrules/*.a \
	${libdir}/snort/dynamic_preproc/*.a \
	${libdir}/snort/dynamic_output/*.a \
	"
FILES_${PN}-dev += " \
	${libdir}/snort_dynamicengine/*.la \
	${libdir}/snort_dynamicpreprocessor/*.la \
	${libdir}/snort_dynamicrules/*.la \
	${libdir}/snort_dynamicengine/*.so \
	${libdir}/snort_dynamicpreprocessor/*.so \
	${libdir}/snort_dynamicrules/*.so \
	${prefix}/src/snort_dynamicsrc \
	"

RRECOMMENDS_${PN} += "barnyard2"
