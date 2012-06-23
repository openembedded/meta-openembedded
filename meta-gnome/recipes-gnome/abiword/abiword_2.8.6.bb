DESCRIPTION = "AbiWord is free word processing program similar to Microsoft(r) Word"
HOMEPAGE = "http://www.abiword.org"
SECTION = "x11/office"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecd3ac329fca77e2d0e412bec38e1c20"
DEPENDS     = "perl-native wv libgsf libglade libfribidi jpeg libpng libxml2"
RDEPENDS_${PN}    = "glibc-gconv-ibm850 glibc-gconv-cp1252 \
               glibc-gconv-iso8859-15 glibc-gconv-iso8859-1"

SRC_URI = "http://www.abiword.org/downloads/abiword/${PV}/source/abiword-${PV}.tar.gz \
           file://fix.double.install.patch \
"
SRC_URI[md5sum] = "bbc9c124f8072875129bd67092f0fa0b"
SRC_URI[sha256sum] = "db34eeb5457fb7572fc76ec2a73cdb4f7a67307e7468b6c4bde820b58c598b3f"

#want 2.x from 2.x.y for the installation directory
SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"

FILES_${PN} += " \
		${datadir}/icons/* \
		${datadir}/abiword-${SHRT_VER}/glade \
		${datadir}/abiword-${SHRT_VER}/scripts \
		${datadir}/abiword-${SHRT_VER}/system.profile-en \
		${datadir}/abiword-${SHRT_VER}/system.profile-en_GB \
		${datadir}/abiword-${SHRT_VER}/templates/normal.awt \
		${datadir}/abiword-${SHRT_VER}/templates/normal.awt-en_GB \
		${datadir}/abiword-${SHRT_VER}/templates/Employee-Directory.awt \
		${datadir}/abiword-${SHRT_VER}/templates/Business-Report.awt \
		${datadir}/abiword-${SHRT_VER}/templates/Fax-Coversheet.awt \
		${datadir}/abiword-${SHRT_VER}/templates/Resume.awt \
		${datadir}/abiword-${SHRT_VER}/templates/Two-Columns.awt \
		${datadir}/abiword-${SHRT_VER}/templates/Memo.awt \
		${datadir}/abiword-${SHRT_VER}/templates/Press-Release.awt "

inherit autotools pkgconfig

PARALLEL_MAKE = ""

EXTRA_OECONF = " --disable-pspell \
                 --disable-spellcheck \
		 --disable-printing \
		 --disable-exports \
		 --with-sys-wv"

# AbiWord configure.ac does not play nicely with autoreconf
# so use the autogen.sh script that comes with AbiWord
#
do_configure() {
        cd ${S}
        export NOCONFIGURE="no"; ./autogen.sh
        oe_runconf
}


do_install_append() {
        install -d ${D}${datadir}/pixmaps/
        mv ${D}${datadir}/icons/* ${D}${datadir}/pixmaps/
        rmdir ${D}${datadir}/icons
}

PACKAGES += " abiword-clipart abiword-icons abiword-strings abiword-systemprofiles abiword-templates "

FILES_abiword-clipart 		+= "${datadir}/abiword-${SHRT_VER}/clipart"
FILES_abiword-icons 		+= "${datadir}/abiword-${SHRT_VER}/icons"
FILES_abiword-strings 		+= "${datadir}/abiword-${SHRT_VER}/AbiWord/strings"
FILES_abiword-systemprofiles 	+= "${datadir}/abiword-${SHRT_VER}/AbiWord/system.profile*"
FILES_abiword-templates 	+= "${datadir}/abiword-${SHRT_VER}/templates"

DEPENDS += " librsvg loudmouth libwmf-native gtkmathview asio"
RCONFLICTS_${PN} = "abiword-embedded"

PR = "r4"

SRC_URI = "http://www.abisource.com/downloads/abiword/${PV}/source/abiword-${PV}.tar.gz;name=abiword \
           file://autogen-common.sh \
           file://nodolt.patch \
           file://fix.no.undefined.param.patch \
           file://abiword.fix.glib-2.32.patch"

LIC_FILES_CHKSUM = "file://COPYING;md5=ecd3ac329fca77e2d0e412bec38e1c20"

SRC_URI[abiword.md5sum] = "f883b0a7f26229a9c66fd6a1a94381aa"
SRC_URI[abiword.sha256sum] = "d99089a63a6cfc1a6a4a026be9278028d47d224088d24b1853acb67e95683a15"


EXTRA_OECONF = " --disable-static  \
                 --enable-plugins \
                 --without-gnomevfs \
                 --enable-collab-backend-xmpp \
                 --enable-collab-backend-tcp \
                 --enable-collab-backend-service \
                 --with-libwmf-config=${STAGING_DIR} \
"

do_configure() {
    install -m 0755 ${WORKDIR}/autogen-common.sh ${S}/autogen-common.sh
    cd ${S}
    ./autogen-common.sh
    autotools_do_configure
}

FILES_${PN} 			+= "${libdir}/libabiword-*.so ${datadir}/mime-info ${datadir}/abiword-${SHRT_VER}/certs ${datadir}/abiword-${SHRT_VER}/ui ${datadir}/abiword-${SHRT_VER}/xsl* ${datadir}/abiword-${SHRT_VER}/mime-info ${datadir}/abiword-${SHRT_VER}/Pr*.xml"
FILES_abiword-strings           += "${datadir}/abiword-${SHRT_VER}/strings"
FILES_abiword-systemprofiles    += "${datadir}/abiword-${SHRT_VER}/system.profile*"

PACKAGES_DYNAMIC = "abiword-meta abiword-plugin-*"

python populate_packages_prepend () {
	abiword_libdir    = bb.data.expand('${libdir}/abiword-2.8/plugins', d)
	do_split_packages(d, abiword_libdir, '(.*)\.so$', 'abiword-plugin-%s', 'Abiword plugin for %s', extra_depends='')

	metapkg = "abiword-meta"
	bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
	bb.data.setVar('FILES_' + metapkg, "", d)
	blacklist = [ 'abiword-plugins-dbg', 'abiword-plugins', 'abiword-plugins-doc', 'abiword-plugins-dev', 'abiword-plugins-locale' ]
	metapkg_rdepends = []
	packages = bb.data.getVar('PACKAGES', d, 1).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.count("-dev") and not pkg.count("-dbg") and not pkg.count("static") and not pkg.count("locale") and not pkg.count("abiword-doc"):
			print "Modifying ", pkg
			metapkg_rdepends.append(pkg)
	bb.data.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends), d)
	bb.data.setVar('DESCRIPTION_' + metapkg, 'abiword-plugin meta package', d)
	packages.append(metapkg)
	bb.data.setVar('PACKAGES', ' '.join(packages), d)
}

# don't steal /usr/lib/libabiword-2.8.so from ${PN}
# in this case it's needed in ${PN}
FILES_${PN}-dev = "${includedir} ${libdir}/pkgconfig ${libdir}/abiword*.la ${libdir}/abiword-${SHRT_VER}/plugins/*.la ${libdir}/libabiword*.la"
FILES_${PN}-dbg += "${libdir}/abiword-${SHRT_VER}/plugins/.debug"
FILES_${PN}-doc += "${datadir}/abiword-*/readme*"
