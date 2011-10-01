DESCRIPTION = "${PN} bindings"
LICENSE = "BSD"
AUTHOR = "Gustavo Sverzut Barbieri <barbieri@gmail.com>"
# NOTE: Due to a bug in distutils, even if we don't use pyrex but cython,
# we need to build pyrex otherwise cython doesn't get called to build
# the extension modules.
DEPENDS = "python-cython-native python-pyrex-native python-numeric eina"
RDEPENDS_${PN} += "python-lang"
PV = "0.7.3+svnr${SRCPV}"
INC_PR = "r2"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS

inherit e-base autotools pkgconfig distutils-base

SRC_URI = "${E_SVN}/trunk/BINDINGS/python;module=${SRCNAME};proto=http"
S = "${WORKDIR}/${SRCNAME}"


do_configure_prepend() {
	# prefix CYTHON_.*_INCLUDEDIR with path to STAGING_DIR_HOST also use $PKG_CONFIG instead of pkg-config directly
        sed -i "s#\`\$PKG_CONFIG --variable=includedir \"python-evas#${STAGING_DIR_HOST}\`\$PKG_CONFIG --variable=includedir \"python-evas#g" ${S}/configure.ac
}

do_install_append() {
	if [ -e examples ]; then
		for i in `find examples -name "*.edc"`; do
			cd ${S}/`dirname $i`
			echo "Generating .edj file for $i..."
			edje_cc `basename $i`
			echo "Removing sources in this directory..."
			rm -f *.edc *.png *.ttf *.jpeg
		done
		cd ${S}
		install -d ${D}${datadir}/${PN}/
		cp -a examples ${D}${datadir}/${PN}/
		find ${D}${datadir}/${PN}/examples -name ".svn" | xargs rm -rf
	fi
}

FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/*.egg/*/*/.debug"

PACKAGES += "${PN}-examples"
FILES_${PN}-examples = "${datadir}/${PN}/examples"
