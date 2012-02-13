require mysql5_${PV}.inc
inherit native
PR ="r3"

SRC_URI = "http://downloads.mysql.com/archives/mysql-5.1/mysql-${PV}.tar.gz \
           file://fix-abi-check-gcc45.patch"

RDEPENDS_${PN} = ""
PACKAGES = ""
DEPENDS = "ncurses-native"
EXTRA_OEMAKE = ""
EXTRA_OECONF = " --with-embedded-server "

do_install() {
        oe_runmake 'DESTDIR=${D}' install
        mv -f ${D}${libdir}/mysql/* ${D}${libdir}
        rmdir ${D}${libdir}/mysql

        install -d ${D}${bindir}
        install -m 0755 sql/gen_lex_hash ${D}${bindir}/
}

NATIVE_INSTALL_WORKS = "1"

PSTAGING_DISABLED = "1"

SRC_URI[md5sum] = "32e7373c16271606007374396e6742ad"
SRC_URI[sha256sum] = "2b0737b84e7b42c9e54c9658d23bfaee1189cd5955f26b10bdb862761d0f0432"
