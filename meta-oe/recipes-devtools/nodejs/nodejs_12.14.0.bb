DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT & BSD & Artistic-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=be4d5107c64dc3d7c57e3797e1a0674b"

DEPENDS = "openssl"
DEPENDS_append_class-target = " nodejs-native"

inherit pkgconfig ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-python2", "pythonnative", "", d)}

COMPATIBLE_MACHINE_armv4 = "(!.*armv4).*"
COMPATIBLE_MACHINE_armv5 = "(!.*armv5).*"
COMPATIBLE_MACHINE_mips64 = "(!.*mips64).*"

COMPATIBLE_HOST_riscv64 = "null"
COMPATIBLE_HOST_riscv32 = "null"

SRC_URI = "http://nodejs.org/dist/v${PV}/node-v${PV}.tar.xz \
           file://0001-Disable-running-gyp-files-for-bundled-deps.patch \
           file://0003-Install-both-binaries-and-use-libdir.patch \
           file://0004-v8-don-t-override-ARM-CFLAGS.patch \
           "
SRC_URI_append_class-target = " \
           file://0002-Using-native-binaries.patch \
           "

SRC_URI[md5sum] = "6762f5629f6f68fb9bdf83a741cba038"
SRC_URI[sha256sum] = "088a217ba2af641b8cc15be29f6e2956b8a33e6badb85596bbc2cdea9df9be71"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

def map_nodejs_arch(a, d):
    import re

    if   re.match('i.86$', a): return 'ia32'
    elif re.match('x86_64$', a): return 'x64'
    elif re.match('aarch64$', a): return 'arm64'
    elif re.match('(powerpc64|ppc64le)$', a): return 'ppc64'
    elif re.match('powerpc$', a): return 'ppc'
    return a

ARCHFLAGS_arm = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--with-arm-float-abi=hard', '--with-arm-float-abi=softfp', d)} \
                 ${@bb.utils.contains('TUNE_FEATURES', 'neon', '--with-arm-fpu=neon', \
                    bb.utils.contains('TUNE_FEATURES', 'vfpv3d16', '--with-arm-fpu=vfpv3-d16', \
                    bb.utils.contains('TUNE_FEATURES', 'vfpv3', '--with-arm-fpu=vfpv3', \
                    '--with-arm-fpu=vfp', d), d), d)}"
GYP_DEFINES_append_mipsel = " mips_arch_variant='r1' "
ARCHFLAGS ?= ""

PACKAGECONFIG ??= "ares icu libuv zlib"
PACKAGECONFIG[ares] = "--shared-cares,,c-ares"
PACKAGECONFIG[gyp] = ",,gyp-py2-native"
PACKAGECONFIG[icu] = "--with-intl=system-icu,--without-intl,icu"
PACKAGECONFIG[libuv] = "--shared-libuv,,libuv"
PACKAGECONFIG[nghttp2] = "--shared-nghttp2,,nghttp2"
PACKAGECONFIG[shared] = "--shared"
PACKAGECONFIG[zlib] = "--shared-zlib,,zlib"

# We don't want to cross-compile during target compile,
# and we need to use the right flags during host compile,
# too.
EXTRA_OEMAKE = "\
    CC.host='${CC}' \
    CFLAGS.host='${CPPFLAGS} ${CFLAGS}' \
    CXX.host='${CXX}' \
    CXXFLAGS.host='${CPPFLAGS} ${CXXFLAGS}' \
    LDFLAGS.host='${LDFLAGS}' \
    AR.host='${AR}' \
    \
    builddir_name=./ \
"

python do_unpack() {
    import shutil

    bb.build.exec_func('base_do_unpack', d)

    shutil.rmtree(d.getVar('S') + '/deps/openssl', True)
    if 'ares' in d.getVar('PACKAGECONFIG'):
        shutil.rmtree(d.getVar('S') + '/deps/cares', True)
    if 'gyp' in d.getVar('PACKAGECONFIG'):
        shutil.rmtree(d.getVar('S') + '/tools/gyp', True)
    if 'libuv' in d.getVar('PACKAGECONFIG'):
        shutil.rmtree(d.getVar('S') + '/deps/uv', True)
    if 'nghttp2' in d.getVar('PACKAGECONFIG'):
        shutil.rmtree(d.getVar('S') + '/deps/nghttp2', True)
    if 'zlib' in d.getVar('PACKAGECONFIG'):
        shutil.rmtree(d.getVar('S') + '/deps/zlib', True)
}

# Node is way too cool to use proper autotools, so we install two wrappers to forcefully inject proper arch cflags to workaround gypi
do_configure () {
    export LD="${CXX}"
    GYP_DEFINES="${GYP_DEFINES}" export GYP_DEFINES
    # $TARGET_ARCH settings don't match --dest-cpu settings
   ./configure --prefix=${prefix} --without-snapshot --shared-openssl \
               --dest-cpu="${@map_nodejs_arch(d.getVar('TARGET_ARCH'), d)}" \
               --dest-os=linux \
               --libdir=${D}${libdir} \
               ${ARCHFLAGS} \
               ${PACKAGECONFIG_CONFARGS}
}

do_compile () {
    export LD="${CXX}"
    oe_runmake BUILDTYPE=Release
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

do_install_append_class-native() {
    # use node from PATH instead of absolute path to sysroot
    # node-v0.10.25/tools/install.py is using:
    # shebang = os.path.join(node_prefix, 'bin/node')
    # update_shebang(link_path, shebang)
    # and node_prefix can be very long path to bindir in native sysroot and
    # when it exceeds 128 character shebang limit it's stripped to incorrect path
    # and npm fails to execute like in this case with 133 characters show in log.do_install:
    # updating shebang of /home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/work/x86_64-linux/nodejs-native/0.10.15-r0/image/home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/sysroots/x86_64-linux/usr/bin/npm to /home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/sysroots/x86_64-linux/usr/bin/node
    # /usr/bin/npm is symlink to /usr/lib/node_modules/npm/bin/npm-cli.js
    # use sed on npm-cli.js because otherwise symlink is replaced with normal file and
    # npm-cli.js continues to use old shebang
    sed "1s^.*^#\!/usr/bin/env node^g" -i ${D}${exec_prefix}/lib/node_modules/npm/bin/npm-cli.js

    # Install the native binaries to provide it within sysroot for the target compilation
    install -d ${D}${bindir}
    install -m 0755 ${S}/out/Release/torque ${D}${bindir}/torque
    install -m 0755 ${S}/out/Release/bytecode_builtins_list_generator ${D}${bindir}/bytecode_builtins_list_generator
    install -m 0755 ${S}/out/Release/gen-regexp-special-case ${D}${bindir}/gen-regexp-special-case
    install -m 0755 ${S}/out/Release/mkcodecache ${D}${bindir}/mkcodecache
    install -m 0755 ${S}/out/Release/node_mksnapshot ${D}${bindir}/node_mksnapshot
}

do_install_append_class-target() {
    sed "1s^.*^#\!${bindir}/env node^g" -i ${D}${exec_prefix}/lib/node_modules/npm/bin/npm-cli.js
}

PACKAGES =+ "${PN}-npm"
FILES_${PN}-npm = "${exec_prefix}/lib/node_modules ${bindir}/npm ${bindir}/npx"
RDEPENDS_${PN}-npm = "bash python-core python-shell python-datetime python-subprocess python-textutils \
    python-compiler python-misc python-multiprocessing"

PACKAGES =+ "${PN}-systemtap"
FILES_${PN}-systemtap = "${datadir}/systemtap"


BBCLASSEXTEND = "native"

python() {
    if 'meta-python2' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-python2 to be present.')
}
