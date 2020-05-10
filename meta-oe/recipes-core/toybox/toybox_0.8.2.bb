SUMMARY = "Toybox combines common utilities together into a single executable."
HOMEPAGE = "http://www.landley.net/toybox/"
DEPENDS = "attr virtual/crypt"

LICENSE = "BSD-0-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78659a599b9325da368f2f1eb88f19c7"

inherit cml1 update-alternatives

SRC_URI = "http://www.landley.net/toybox/downloads/${BPN}-${PV}.tar.gz \
           "
SRC_URI[md5sum] = "f5cec2e37842ef552753b8b698c69690"
SRC_URI[sha256sum] = "9a2760fa442e9baf1be6064ab5ba8b90f2098e1d4bc33c788960b8d73f52fed5"

SECTION = "base"

TOYBOX_BIN = "generated/unstripped/toybox"

# Toybox is strict on what CC, CFLAGS and CROSS_COMPILE variables should contain.
# Fix CC, CFLAGS, CROSS_COMPILE to match expectations.
# CC = compiler name
# CFLAGS = only compiler flags
# CROSS_COMPILE = compiler prefix
export CFLAGS = "${@d.getVar("CC").replace(d.getVar("TARGET_PREFIX") + d.getVar("BUILD_CC").strip(), " ")}"

EXTRA_OEMAKE = 'CROSS_COMPILE="${TARGET_PREFIX}" CC=${BUILD_CC} HOSTCC="${BUILD_CC}" CPUS=${@oe.utils.cpu_count()}'

do_configure() {
    # allow user to define their own defconfig in bbappend, taken from kernel.bbclass
    if [ "${S}" != "${B}" ] && [ -f "${S}/.config" ] && [ ! -f "${B}/.config" ]; then
        mv "${S}/.config" "${B}/.config"
    fi

    # Copy defconfig to .config if .config does not exist. This allows
    # recipes to manage the .config themselves in do_configure_prepend().
    if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${B}/.config" ]; then
        cp "${WORKDIR}/defconfig" "${B}/.config"
    fi

    oe_runmake oldconfig || oe_runmake defconfig

    # Disable killall5 as it isn't managed by update-alternatives
    sed -e 's/CONFIG_KILLALL5=y/# CONFIG_KILLALL5 is not set/' -i .config

    # Disable swapon as it doesn't handle the '-a' argument used during boot
    sed -e 's/CONFIG_SWAPON=y/# CONFIG_SWAPON is not set/' -i .config
}

do_compile() {
    oe_runmake ${TOYBOX_BIN}

    # Create a list of links needed
    ${BUILD_CC} -I . scripts/install.c -o generated/instlist
    ./generated/instlist long | sed -e 's#^#/#' > toybox.links
}

do_install() {
    # Install manually instead of using 'make install'
    install -d ${D}${base_bindir}
    if grep -q "CONFIG_TOYBOX_SUID=y" ${B}/.config; then
        install -m 4755 ${B}/${TOYBOX_BIN} ${D}${base_bindir}/toybox
    else
        install -m 0755 ${B}/${TOYBOX_BIN} ${D}${base_bindir}/toybox
    fi

    install -d ${D}${sysconfdir}
    install -m 0644 ${B}/toybox.links ${D}${sysconfdir}
}

# If you've chosen to install toybox you probably want it to take precedence
# over busybox where possible but not over other packages
ALTERNATIVE_PRIORITY = "60"

python do_package_prepend () {
    # Read links from /etc/toybox.links and create appropriate
    # update-alternatives variables

    dvar = d.getVar('D')
    pn = d.getVar('PN')
    target = d.expand("${base_bindir}/toybox")

    f = open('%s/etc/toybox.links' % (dvar), 'r')
    for alt_link_name in f:
        alt_link_name = alt_link_name.strip()
        alt_name = os.path.basename(alt_link_name)
        d.appendVar('ALTERNATIVE_%s' % (pn), ' ' + alt_name)
        d.setVarFlag('ALTERNATIVE_LINK_NAME', alt_name, alt_link_name)
        d.setVarFlag('ALTERNATIVE_TARGET', alt_name, target)
    f.close()
}
