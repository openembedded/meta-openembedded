SUMMARY = "High performance data logging and graphing system for time series data"
HOMEPAGE = "http://oss.oetiker.ch/rrdtool/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=44fee82a1d2ed0676cf35478283e0aa0"

DEPENDS = "libpng zlib cairo pango glib-2.0 libxml2"

PR = "r2"

SRC_URI = "http://oss.oetiker.ch/rrdtool/pub/rrdtool-${PV}.tar.gz \
           file://0001-rrdtool-eradicate-tcl-support.patch \
           file://remove_hardcoded_xml_include.patch \
"
SRC_URI[md5sum] = "ffe369d8921b4dfdeaaf43812100c38f"
SRC_URI[sha256sum] = "956aaf431c955ba88dd7d98920ade3a8c4bad04adb1f9431377950a813a7af11"

inherit autotools gettext pythonnative perlnative python-dir

EXTRA_AUTORECONF = "-I m4"

PACKAGECONFIG ??= "python perl"

PACKAGECONFIG[python] = "--enable-python=yes \
am_cv_python_pythondir=${STAGING_LIBDIR}/python${PYTHON_BASEVERSION}/site-packages \
am_cv_python_pyexecdir=${STAGING_LIBDIR}/python${PYTHON_BASEVERSION}/site-packages,\
--disable-python,python,"

PACKAGECONFIG[perl] = \
"--enable-perl=yes --with-perl-options='INSTALLDIRS="vendor"' \
ac_cv_path_PERL_CC='${CC}',  \
--disable-perl,perl,"

PACKAGECONFIG[dbi] = "--enable-libdbi,--disable-libdbi,libdbi"

EXTRA_OECONF = " \
    --enable-shared \
    --disable-libwrap \
    --program-prefix='' \
    rd_cv_ieee_works=yes \
    --disable-ruby \
    --disable-lua \
    --disable-rpath \
"

# don't use perl.real, this results in break issues with prebuilts since perl.real doesn't
# know where the PERL5LIB is...
# use wrapper perl instead
EXTRA_OEMAKE = "PERL=${STAGING_BINDIR_NATIVE}/perl-native/perl FULLPERL=${STAGING_BINDIR_NATIVE}/perl-native/perl"

export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

do_configure() {
	#fix the pkglib problem with newer automake
	#perl
	sed -i -e "s|-Wl,--rpath -Wl,\$rp||g" \
	    ${S}/bindings/perl-shared/Makefile.PL

	#python
	sed -i -e '/PYTHON_INCLUDES="-I${/c \
	PYTHON_INCLUDES="-I=/usr/include/python${PYTHON_BASEVERSION}"' \
	    ${S}/m4/acinclude.m4
	#remove the hardcoded $(libdir) rpath
	sed -i -e 's|--rpath=$(libdir)||g' ${S}/bindings/Makefile.am

	autotools_do_configure

	perl_version=`perl -v 2>/dev/null | \
	    sed -n 's/This is perl.*v[a-z ]*\([0-9]\.[0-9][0-9.]*\).*$/\1/p'`

	#modify python sitepkg
	#remove the dependency of perl-shared:Makefile
	#or perl-shared/Makefile will be regenerated
	#if any code touch bindings/Makefile after below perl bindings code
	sed -i -e "s:\$(PYTHON) setup.py install:\$(PYTHON) setup.py install \
	    --install-lib=${D}${PYTHON_SITEPACKAGES_DIR}:" \
	    -e "s:perl-shared/Makefile.PL Makefile:perl-shared/Makefile.PL:" \
	    ${S}/bindings/Makefile

	#redo the perl bindings
	(
	cd bindings/perl-shared;
	perl -I${STAGING_LIBDIR}/perl/$perl_version Makefile.PL INSTALLDIRS="vendor"
	    INSTALLPRIVLIB="abc";
	sed -i -e "s| ${libdir}/perl/| ${STAGING_LIBDIR}/perl/|g" Makefile;

	cd ../../bindings/perl-piped;
	perl -I${STAGING_LIBDIR}/perl/$perl_version Makefile.PL INSTALLDIRS="vendor";
	sed -i -e "s| ${libdir}/perl/| ${STAGING_LIBDIR}/perl/|g" Makefile;
	)

	#change the interpreter in file
	sed -i -e "s|^PERL = ${STAGING_BINDIR_NATIVE}/.*|PERL = /usr/bin/perl|g" \
	    ${S}/examples/Makefile
	sed -i -e "s|${STAGING_BINDIR_NATIVE}/perl-native/perl|/usr/bin/perl|g" \
	    ${S}/examples/*.pl
}

PACKAGES =+ "${PN}-perl ${PN}-python"

FILES_${PN}-doc += "${datadir}/examples"

DESCRIPTION_${PN}-perl = \
"The ${PN}-perl package includes RRDtool bindings for perl."
FILES_${PN}-perl = "${libdir}/perl/vendor_perl/*/*.pm ${datadir}/${PN}/examples \
    ${libdir}/perl/vendor_perl/*/auto/RRDs/RRDs.*"
RDEPENDS_${PN}-perl = "perl perl-module-lib perl-module-getopt-long perl-module-time-hires \
    perl-module-io-file perl-module-ipc-open2 perl-module-io-socket"

DESCRIPTION_${PN}-python = \
"The ${PN}-python package includes RRDtool bindings for python."
FILES_${PN}-python = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/*"
RDEPENDS_${PN}-python = "python"

FILES_${PN}-dbg += "${libdir}/perl/vendor_perl/*/auto/RRDs/.debug \
    ${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug"
