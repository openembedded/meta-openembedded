DESCRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=90c3badc6055c699194c4a7cea583296"
HOMEPAGE = "http://www.lua.org/"

DEPENDS += "readline"
PR = "r10"
SRC_URI = "http://www.lua.org/ftp/lua-${PV}.tar.gz \
           file://bitwise_operators.patch \
           file://lua5.1.pc \
          "
S = "${WORKDIR}/lua-${PV}"

inherit pkgconfig binconfig

UCLIBC_PATCHES += "file://uclibc-pthread.patch"
SRC_URI_append_libc-uclibc = "${UCLIBC_PATCHES}"

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS}'"

do_configure_prepend() {                                                                                                                                     
	sed -i -e s:/usr/local:${prefix}:g src/luaconf.h
}

do_compile () {
	oe_runmake linux
}

do_install () {
	oe_runmake \
		'INSTALL_TOP=${D}${prefix}' \
		'INSTALL_BIN=${D}${bindir}' \
		'INSTALL_INC=${D}${includedir}/' \
		'INSTALL_MAN=${D}${mandir}/man1' \
		'INSTALL_SHARE=${D}${datadir}/lua' \
		install
	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${WORKDIR}/lua5.1.pc ${D}${libdir}/pkgconfig/lua5.1.pc
}
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "d0870f2de55d59c1c8419f36e8fac150"
SRC_URI[sha256sum] = "b038e225eaf2a5b57c9bcc35cd13aa8c6c8288ef493d52970c9545074098af3a"
