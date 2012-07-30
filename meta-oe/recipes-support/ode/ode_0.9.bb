DESCRIPTION = "ODE is an Open Source Physics Engine."
SECTION = "libs"
HOMEPAGE = "http://www.ode.org"
LICENSE = "LGPLv2.1 & BSD"
LIC_FILES_CHKSUM = "file://LICENSE-BSD.TXT;md5=c74e6304a772117e059458fb9763a928 \
                    file://LICENSE.TXT;md5=771782cb6245c7fbbe74bc0ec059beff"


SRC_URI = "${SOURCEFORGE_MIRROR}/opende/ode-src-${PV}.zip \
           file://install.patch"

SRC_URI[md5sum] = "4c03759b76a0649a6d5108c8e172e1e4"
SRC_URI[sha256sum] = "460d0851b743b1f144ef2a8259004d6774504c95d08e9357a96a296111496feb"

inherit autotools binconfig

EXTRA_OECONF = "--disable-demos --enable-soname"

do_configure_append() {
    echo "#define dInfinity DBL_MAX" >>include/ode/config.h
}

FILES_${PN} = "${libdir}/lib*${SOLIBS}"

