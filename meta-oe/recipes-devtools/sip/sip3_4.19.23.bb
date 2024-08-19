SUMMARY = "SIP is a C++/Python Wrapper Generator"
HOMEPAGE = "https://riverbankcomputing.com/software/sip/"
SECTION = "devel"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE-GPL2;md5=e91355d8a6f8bd8f7c699d62863c7303"

SRC_URI = "https://www.riverbankcomputing.com/static/Downloads/sip/${PV}/sip-${PV}.tar.gz \
    file://added-the-py_ssize_t_clean-argument-to-the-module-directive.patch \
"

SRC_URI[md5sum] = "70adc0c9734e2d9dcd241d3f931dfc74"
SRC_URI[sha256sum] = "22ca9bcec5388114e40d4aafd7ccd0c4fe072297b628d0c5cdfa2f010c0bc7e7"

inherit python3-dir python3native

S = "${WORKDIR}/sip-${PV}"

DEPENDS = "python3 flex-native bison-native"

PACKAGES += "python3-sip3"

BBCLASSEXTEND = "native"

CONFIGURE_SYSROOT = "${STAGING_DIR_HOST}"
CONFIGURE_SYSROOT:class-native = "${STAGING_DIR_NATIVE}"

do_configure:prepend() {
    # Re-generate the lexical analyzer and parser
    # Required for the py_ssize_t_clean patch
    flex --outfile=sipgen/lexer.c sipgen/metasrc/lexer.l
    bison --yacc -Wcounterexamples --defines=sipgen/parser.h --output=sipgen/parser.c sipgen/metasrc/parser.y

    echo "py_platform = linux" > sip.cfg
    echo "py_inc_dir = ${STAGING_INCDIR}/python%(py_major).%(py_minor)${PYTHON_ABI}" >> sip.cfg
    echo "sip_bin_dir = ${D}/${bindir}" >> sip.cfg
    echo "sip_inc_dir = ${D}/${includedir}" >> sip.cfg
    echo "sip_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> sip.cfg
    echo "sip_sip_dir = ${D}/${datadir}/sip" >> sip.cfg
    ${PYTHON} configure.py --configuration sip.cfg --destdir /${D}${libdir}/${PYTHON_DIR}/site-packages/ --sip-module PyQt5.sip --sysroot ${CONFIGURE_SYSROOT} CC="${CC}" CXX="${CXX}" LINK="${CXX}" STRIP="" LINK_SHLIB="${CXX}" CFLAGS="${CFLAGS}" CXXFLAGS="${CXXFLAGS}" LFLAGS="${LDFLAGS}"
}

do_install() {
    oe_runmake install

    sed -i \
         -e "s@[^ ]*-fdebug-prefix-map=[^ ']*@@g" \
         -e "s@[^ ]*-fmacro-prefix-map=[^ ']*@@g" \
         -e "s@[^ ]*-ffile-prefix-map=[^ ']*@@g" \
         ${D}${libdir}/${PYTHON_DIR}/site-packages/sipconfig.py

    # Remove the destination directory
    sed -i -e "s@${D}/@@g" ${D}${libdir}/${PYTHON_DIR}/site-packages/sipconfig.py

    if [ -n "${STAGING_DIR_NATIVE}" ]; then
        sed -i -e "s@${STAGING_DIR_NATIVE}@@g" ${D}${libdir}/${PYTHON_DIR}/site-packages/sipconfig.py
    fi

    if [ -n "${STAGING_DIR_TARGET}" ]; then
       sed -i -e "s@${STAGING_DIR_TARGET}@@g" ${D}${libdir}/${PYTHON_DIR}/site-packages/sipconfig.py
    fi
}

FILES:python3-sip3 = "${libdir}/${PYTHON_DIR}/site-packages/"
FILES:${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/.debug"
