DESCRIPTION = "Qt Widget Extension for Technical Applications"
SECTION = "libs"

# LGPLv2.1 + some exceptions
LICENSE = "QWTv1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=dac2743472b0462ff3cfb4af42051c88"

SRC_URI = "${SOURCEFORGE_MIRROR}/qwt/qwt-${PV}.tar.bz2;name=qwt \
	file://qwt6.patch \
	"

S = "${WORKDIR}/qwt-${PV}"

do_configure_prepend() {
	sed -i -e 's:RELEASE_SUFFIX      = :RELEASE_SUFFIX      = ${QT_LIBINFIX}:' *.pri
	sed -i -e 's:qtAddLibrary(qwt:qtAddLibrary(qwt)${QT_LIBINFIX}:g' *.prf
	sed -e 's/# QWT_CONFIG     += QwtExamples/QWT_CONFIG     += QwtExamples/g' -i qwtconfig.pri
	sed -i -e 's:/usr/local/qwt-$$QWT_VERSION:${D}${prefix}:g' ${S}/*.pri
}

do_install() {
	oe_runmake -e install
	install -d ${D}${datadir}/doc/${PN}
	mv ${D}${prefix}/doc/* ${D}${datadir}/doc/${PN}/
	cd ${S}/examples
	install -d ${D}/${bindir}
	cd bin${QT_LIBINFIX}/
	for i in * ; do
		cp -pPR ${i} ${D}/${bindir}/${i}${QT_LIBINFIX}
	done
}

PACKAGES_prepend = "${PN}-examples "
FILES_${PN}-examples = "${bindir}/*"
