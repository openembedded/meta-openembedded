DESCRIPTION = "Tool Command Language ToolKit Extension"
HOMEPAGE = "http://tcl.sourceforge.net"
SECTION = "devel/tcltk"
LICENSE = "tcl"
LIC_FILES_CHKSUM = "file://license.terms;md5=c88f99decec11afa967ad33d314f87fe"
DEPENDS = "tcl virtual/libx11 libxt"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/tcl/tk${PV}-src.tar.gz \
    file://confsearch.diff;striplevel=2 \
    file://non-linux.diff;striplevel=2 \
    file://tklibrary.diff;striplevel=2 \
    file://tkprivate.diff;striplevel=2 \
    file://fix-xft.diff \
"
SRC_URI[md5sum] = "b883a1a3c489c17413fb602a94bf54e8"
SRC_URI[sha256sum] = "5c708b2b6f658916df59190b27750fa1ea2bc10992108e10f961c0700f058de6"

S = "${WORKDIR}/tk${PV}/unix"
LDFLAGS += "-Wl,-rpath,${libdir}/tcltk/8.6.0/lib"
inherit autotools

EXTRA_OECONF = "\
    --enable-threads \
    --with-x \
    --with-tcl=${STAGING_BINDIR_CROSS} \
    --libdir=${libdir} \
"

do_install_append() {
    ln -sf libtk8.6.so ${D}${libdir}/libtk8.6.so.0
    oe_libinstall -so libtk8.6 ${D}${libdir}
    ln -sf wish8.6 ${D}${bindir}/wish
	
    # Even after passing libdir=${libdir} at config, some incorrect dirs are still generated for the multilib build
    if [ "$libdir" != "/usr/lib" ]; then
        # Move files to correct library directory
        mv ${D}/usr/lib/tk8.6/* ${D}/${libdir}/tk8.6/
        # Remove unneeded/incorrect dir ('usr/lib/')
        rm -rf ${D}/usr/lib
    fi
}

PACKAGES =+ "${PN}-lib"

FILES_${PN}-lib = "${libdir}/libtk8.6.so*"
FILES_${PN} += "${libdir}/tk*"

# isn't getting picked up by shlibs code
RDEPENDS_${PN} += "tk-lib"
RDEPENDS_${PN}_class-native = ""

BINCONFIG_GLOB = "*Config.sh"
BBCLASSEXTEND = "native"
