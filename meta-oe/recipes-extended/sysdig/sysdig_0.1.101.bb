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


SRC_URI = "https://github.com/draios/sysdig/archive/${PV}.tar.gz \
           file://01-donot-set-default-value-to-va_list.patch \
          "

SRC_URI[md5sum] = "5fe96a3a0fd98b2157a40cb29af41afc"
SRC_URI[sha256sum] = "6995e39be565514901b5cb587689ee2efbf8359293e4e597362382cccf0e9db6"

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
