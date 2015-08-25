SUMMARY = "A New System Troubleshooting Tool Built for the Way You Work"
DESCRIPTION = "Sysdig is open source, system-level exploration: capture \
system state and activity from a running Linux instance, then save, \
filter and analyze."
HOMEPAGE = "http://www.sysdig.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit cmake pkgconfig

DEPENDS = "luajit zlib ncurses"
RDEPENDS_${PN} = "bash"

SRC_URI = "git://github.com/draios/sysdig.git;branch=master"
SRCREV = "b7394e29ced4f1a991af03c0381a5828abcbab7a"
PV = "0.1.102+git${SRCPV}"

S = "${WORKDIR}/git"

DIR_ETC="/etc"
EXTRA_OECMAKE = ' -DUSE_BUNDLED_LUAJIT="OFF" \
                  -DUSE_BUNDLED_ZLIB="OFF" \
                  -DBUILD_DRIVER="OFF" \
                  -DUSE_BUNDLED_NCURSES="OFF" \
                  -DDIR_ETC="${DIR_ETC}" \
                '

FILES_${PN} += " \
    ${DIR_ETC}/* \
    ${datadir}/zsh/* \ 
    ${prefix}/src/*  \
"
