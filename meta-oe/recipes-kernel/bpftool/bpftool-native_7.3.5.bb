SUMMARY = "Inspect and manipulate eBPF programs and maps"
DESCRIPTION = "bpftool is a kernel tool for inspection and simple manipulation \
of eBPF programs and maps."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
UPSTREAM_CHECK_URI = "https://www.kernel.org/"

DEPENDS = "binutils-native elfutils-native"

inherit native bash-completion

SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v${@d.getVar('PV').split('.')[0]}.x/linux-${PV}.tar.xz"
SRC_URI[sha256sum] = "8f293f83d54f0b7b9be56863ebc5e4ea79d73e530b4ae55c572be97ef39f0990"

S = "${UNPACKDIR}/linux-${PV}"

DEBUG_PREFIX_MAP_EXTRA:toolchain-gcc = ""

EXTRA_OEMAKE = "\
    V=1 \
    -C ${S}/tools/bpf/bpftool \
    O=${B} \
    CROSS=${TARGET_PREFIX} \
    CC="${CC} ${DEBUG_PREFIX_MAP} -ffile-prefix-map=${STAGING_KERNEL_DIR}=${KERNEL_SRC_PATH} ${CFLAGS}" \
    HOSTCC="${BUILD_CC} ${BUILD_CFLAGS}" \
    LD="${LD}" \
    AR=${AR} \
    ARCH=${ARCH} \
    bash_compdir=${prefix}/share/bash-completion \
"

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake DESTDIR=${D} install
}

FILES:${PN} += "${exec_prefix}/sbin/*"
