DESCRIPTION = "EFL based widget set for mobile devices"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=10a051c72424b80bc784a3903651b43b"
DEPENDS = "eet-native efreet evas ecore edje eet edbus ethumb poppler"
PV = "0.8.0+svnr${SRCPV}"
PR = "r2"
SRCREV = "${EFL_SRCREV}"

inherit efl gettext
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
  --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
  --disable-web \
"

do_compile_append() {
        sed -i -e s:${STAGING_DIR_TARGET}::g \
               -e s:/${TARGET_SYS}::g \
                  elementary.pc
}

PACKAGES += "${PN}-configs ${PN}-accessibility"

RDEPENDS_${PN} = "\
  ${PN}-themes \
  ${PN}-configs \
"
RSUGGESTS_${PN} = "${PN}-tests ${PN}-accessibility"

FILES_${PN} += "\
  ${libdir}/edje/modules/elm \
"

FILES_${PN}-themes = "\
  ${datadir}/elementary/themes \
"

FILES_${PN}-configs = "\
  ${datadir}/elementary/config \
"

FILES_${PN}-dbg += "\
  ${libdir}/elementary/modules/test_entry/*/.debug \
  ${libdir}/elementary/modules/test_map/*/.debug \
  ${libdir}/elementary/modules/access_output/*/.debug \
  ${libdir}/edje/modules/elm/*/.debug \
"

FILES_${PN}-tests = "\
  ${bindir}/elementary* \
  ${datadir}/elementary/images \
  ${datadir}/elementary/objects \
  ${datadir}/elementary/examples \
  ${datadir}/applications/* \
  ${datadir}/icons/* \
  ${libdir}/elementary/modules/test_entry/* \
  ${libdir}/elementary/modules/test_map/* \
"

FILES_${PN}-accessibility = "\
  ${libdir}/elementary/modules/access_output/* \
"

# Some upgrade path tweaking, as in evas
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN}-themes = "libelementary-ver-pre-svn-05-themes libelementary-ver-svn-06-themes libelementary-ver-pre-svn-06-themes"
RREPLACES_${PN}-configs = "libelementary-ver-pre-svn-05-configs libelementary-ver-svn-06-configs libelementary-ver-pre-svn-06-configs"
