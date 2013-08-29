require mariadb_${PV}.inc
inherit native

PROVIDES += "mysql5-native"

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

