SUMMARY = "AbiWord is free word processing program similar to Microsoft(r) Word"
HOMEPAGE = "http://www.abiword.org"
SECTION = "x11/office"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecd3ac329fca77e2d0e412bec38e1c20"
DEPENDS     = "perl-native wv libglade libfribidi jpeg libpng \
               librsvg libwmf-native gtkmathview asio gtk+ evolution-data-server"
RDEPENDS_${PN}    = "glibc-gconv-ibm850 glibc-gconv-cp1252 \
               glibc-gconv-iso8859-15 glibc-gconv-iso8859-1"
RCONFLICTS_${PN} = "${PN}-embedded"

SRC_URI = "http://www.abisource.com/downloads/${BPN}/${PV}/source/${BP}.tar.gz \
           file://debian_patches_boost54.patch \
           file://autogen-common.sh \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=c5edcc3ccd864b19004d14e9c1c9a26a"

SRC_URI[md5sum] = "8d9c41cff3a8fbef8d0c835c65600e65"
SRC_URI[sha256sum] = "d17e318c00ff4eb353e0e7994b098b1d4f9ddd8712ac0261a0e38b89081fac01"

#want 3.x from 3.x.y for the installation directory
SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"

inherit autotools-brokensep pkgconfig

PACKAGECONFIG ??= "collab-backend-xmpp collab-backend-tcp"
PACKAGECONFIG[spell] = "--enable-spell,--disable-spell,enchant"
PACKAGECONFIG[collab-backend-xmpp] = "--enable-collab-backend-xmpp,--disable-collab-backend-xmpp,libgsf libxml2 loudmouth"
PACKAGECONFIG[collab-backend-tcp] = "--enable-collab-backend-tcp,--disable-collab-backend-tcp,libgsf libxml2"
PACKAGECONFIG[collab-backend-service] = "--enable-collab-backend-service,--disable-collab-backend-service,libgsf libxml2 libsoup-2.4 gnutls"
PACKAGECONFIG[collab-backend-telepathy] = "--enable-collab-backend-telepathy,--disable-collab-backend-telepathy,libgsf libxml2 telepathy-glib telepathy-mission-control"
PACKAGECONFIG[collab-backend-sugar] = "--enable-collab-backend-sugar,--disable-collab-backend-sugar,libgsf libxml2 dbus-glib"

EXTRA_OECONF = " --disable-static  \
                 --enable-plugins \
                 --enable-clipart \
                 --enable-templates \
                 --without-gnomevfs \
                 --with-gtk2 \
                 --with-libwmf-config=${STAGING_DIR} \
"

# AbiWord configure.ac does not play nicely with autoreconf
# so use the autogen.sh script that comes with AbiWord
do_configure() {
    install -m 0755 ${WORKDIR}/autogen-common.sh ${B}/autogen-common.sh
    ./autogen-common.sh
    autotools_do_configure
}

do_compile() {
    cd goffice-bits2
    make goffice-paths.h
    make libgoffice.la
    cd ${B}
    oe_runmake
}

PACKAGES += " ${PN}-clipart ${PN}-strings ${PN}-systemprofiles ${PN}-templates "

FILES_${PN} += " \
                ${libdir}/lib${PN}-*.so \
                ${datadir}/mime-info \
                ${datadir}/icons/* \
                ${datadir}/${PN}-${SHRT_VER}/glade \
                ${datadir}/${PN}-${SHRT_VER}/scripts \
                ${datadir}/${PN}-${SHRT_VER}/system.profile-en \
                ${datadir}/${PN}-${SHRT_VER}/system.profile-en_GB \
                ${datadir}/${PN}-${SHRT_VER}/templates/normal.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/normal.awt-en_GB \
                ${datadir}/${PN}-${SHRT_VER}/templates/Employee-Directory.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/Business-Report.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/Fax-Coversheet.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/Resume.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/Two-Columns.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/Memo.awt \
                ${datadir}/${PN}-${SHRT_VER}/templates/Press-Release.awt \
                ${datadir}/${PN}-${SHRT_VER}/certs \
                ${datadir}/${PN}-${SHRT_VER}/ui \
                ${datadir}/${PN}-${SHRT_VER}/xsl* \ 
                ${datadir}/${PN}-${SHRT_VER}/mime-info \
                ${datadir}/${PN}-${SHRT_VER}/Pr*.xml \
"

# don't steal /usr/lib/libabiword-3.0.so from ${PN}
# in this case it's needed in ${PN}
FILES_${PN}-dev = " \
                  ${includedir} \
                  ${libdir}/pkgconfig \
                  ${libdir}/${PN}*.la \
                  ${libdir}/lib${PN}*.la \
                  ${libdir}/${PN}-${SHRT_VER}/plugins/*.la \
"
FILES_${PN}-dbg += "${libdir}/${PN}-${SHRT_VER}/plugins/.debug"
FILES_${PN}-doc += "${datadir}/${PN}-*/readme*"

FILES_${PN}-strings        += "${datadir}/${PN}-${SHRT_VER}/strings"
FILES_${PN}-systemprofiles += "${datadir}/${PN}-${SHRT_VER}/system.profile*"
FILES_${PN}-clipart        += "${datadir}/${PN}-${SHRT_VER}/clipart"
FILES_${PN}-strings        += "${datadir}/${PN}-${SHRT_VER}/AbiWord/strings"
FILES_${PN}-systemprofiles += "${datadir}/${PN}-${SHRT_VER}/AbiWord/system.profile*"
FILES_${PN}-templates      += "${datadir}/${PN}-${SHRT_VER}/templates"

PACKAGES_DYNAMIC += "^${PN}-meta.* ^${PN}-plugin-.*"

python populate_packages_prepend () {
    abiword_libdir    = d.expand('${libdir}/${PN}-${SHRT_VER}/plugins')
    do_split_packages(d, abiword_libdir, '(.*)\.so$', 'abiword-plugin-%s', 'Abiword plugin for %s', extra_depends='')

    metapkg = "abiword-meta"
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")
    blacklist = [ 'abiword-plugins-dbg', 'abiword-plugins', 'abiword-plugins-doc', 'abiword-plugins-dev', 'abiword-plugins-locale' ]
    metapkg_rdepends = []
    packages = d.getVar('PACKAGES', 1).split()
    for pkg in packages[1:]:
        if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.count("-dev") and not pkg.count("-dbg") and not pkg.count("static") and not pkg.count("locale") and not pkg.count("abiword-doc"):
            print "Modifying ", pkg
            metapkg_rdepends.append(pkg)
    d.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends))
    d.setVar('DESCRIPTION_' + metapkg, 'abiword-plugin meta package')
    packages.append(metapkg)
    d.setVar('PACKAGES', ' '.join(packages))
}

FILES_${PN}-plugin-openxml += "${datadir}/${PN}-${SHRT_VER}/omml_xslt"
