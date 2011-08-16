DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6394136134ac02d730a7302418c94fb6"

DEPENDS = "openssl"

def nodejs_get_gcc_version(d):
    import subprocess,os,bb
    if os.path.exists(bb.data.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+bb.data.getVar('TARGET_PREFIX', d, 1)+'gcc'):
        return subprocess.Popen([bb.data.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+bb.data.getVar('TARGET_PREFIX', d, 1)+'gcc', '-v'], stderr=subprocess.PIPE).communicate()[1].splitlines()[-1].split()[2]

SRC_URI = " \
  http://nodejs.org/dist/node-v${PV}.tar.gz \
  file://libev-cross-cc_${PV}.patch \
"

SRC_URI[md5sum] = "9e9e791e125f6a601ebc663dc99c72a8"
SRC_URI[sha256sum] = "09b1100ca6828eedbe52418fbeb3352d71c0b1ff3344c44a5af3efb80c5b908c"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

do_configure () {
  sed -i -e 's:/usr/lib:${STAGING_LIBDIR}:g' wscript
  sed -i -e 's:/usr/local/lib:${STAGING_LIBDIR}:g' wscript
  ./configure --prefix=${prefix} --without-snapshot
}

do_compile () {
  make
}

do_install () {
  DESTDIR=${D} oe_runmake install
}

BBCLASSEXTEND = "native"
