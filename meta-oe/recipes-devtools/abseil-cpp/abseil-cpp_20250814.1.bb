SUMMARY = "Abseil is a cpp library like STL"
DESCRIPTION = "Abseil provides pieces missing from the C++ standard. Contains \
additional useful libraries like algorithm, container, debugging, hash, memory, \
meta, numeric, strings, synchronization, time, types and utility."
HOMEPAGE = "https://abseil.io/"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=df52c6edb7adc22e533b2bacc3bd3915"

SRC_URI = "https://github.com/abseil/${BPN}/releases/download/${PV}/${BP}.tar.gz \
           file://0001-absl-always-use-asm-sgidefs.h.patch \
           file://0002-abseil-ppc-fixes.patch \
           "
SRC_URI[sha256sum] = "1692f77d1739bacf3f94337188b78583cf09bab7e420d2dc6c5605a4f86785a1"

inherit cmake

EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=ON \
                 -DBUILD_TESTING=OFF    \
                 -DABSL_ENABLE_INSTALL=ON \
                "
#  riscv32-yoe-linux-ld.lld: error: undefined reference: __atomic_store_8
#  >>> referenced by <recipe-sysroot>/usr/lib/libabsl_flags_internal.so.2505.0.0 (disallowed by --no-allow-shlib-undefined)
LDFLAGS:append:riscv32 = " -latomic"

SYSROOT_DIRS:append:class-nativesdk:mingw32 = " ${bindir}"

PACKAGES_DYNAMIC += "^libabsl-*"
PACKAGES_DYNAMIC:class-native = ""

PACKAGESPLITFUNCS =+ "split_dynamic_packages"

python split_dynamic_packages() {
    libdir = d.getVar('libdir')

    libpackages = do_split_packages(
        d,
        root=libdir,
        file_regex=r'^libabsl_(.*)\.so\..*$',
        output_pattern='libabsl-%s',
        description="abseil shared library %s",
        prepend=True,
        extra_depends='',
    )
    if libpackages:
        d.appendVar('RDEPENDS:' + d.getVar('PN'), ' ' + ' '.join(libpackages))
}

ALLOW_EMPTY:${PN} = "1"

BBCLASSEXTEND = "native nativesdk"
