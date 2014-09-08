SUMMARY = "Toybox combines common utilities together into a single executable."
HOMEPAGE = "http://www.landley.net/toybox/"

SRC_URI = "http://www.landley.net/toybox/downloads/${BPN}-${PV}.tar.bz2 \
    file://0001-Match-paths-with-busybox.patch"
SRC_URI[md5sum] = "a8f1022175689defec51cf5a71c41326"
SRC_URI[sha256sum] = "e6dc9052826a3bdae1923e3301c9e3542e890af5ed66534052943f8412255bff"

LICENSE = "BSD-0-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=57cc240a6204b2ea8c68450d42f9d33f"

SECTION = "base"

do_configure() {
    oe_runmake defconfig

    # Disable killall5 as it isn't managed by update-alternatives
    sed -e 's/CONFIG_KILLALL5=y/# CONFIG_KILLALL5 is not set/' -i .config
}

do_compile() {
    oe_runmake toybox_unstripped

    # Create a list of links needed
    oe_runmake generated/instlist
    ./generated/instlist long | sed -e 's#^#/#' > toybox.links
}

do_install() {
    # Install manually instead of using 'make install'
    install -d ${D}${base_bindir}
    if grep -q "CONFIG_TOYBOX_SUID=y" ${B}/.config; then
        install -m 4755 ${B}/toybox_unstripped ${D}${base_bindir}/toybox
    else
        install -m 0755 ${B}/toybox_unstripped ${D}${base_bindir}/toybox
    fi

    install -d ${D}${sysconfdir}
    install -m 0644 ${B}/toybox.links ${D}${sysconfdir}
}

inherit update-alternatives

# If you've chosen to install toybox you probably want it to take precedence
# over busybox where possible but not over other packages
ALTERNATIVE_PRIORITY = "60"

python do_package_prepend () {
    # Read links from /etc/toybox.links and create appropriate
    # update-alternatives variables

    dvar = d.getVar('D', True)
    pn = d.getVar('PN', True)
    target = "/bin/toybox"

    f = open('%s/etc/toybox.links' % (dvar), 'r')
    for alt_link_name in f:
        alt_link_name = alt_link_name.strip()
        alt_name = os.path.basename(alt_link_name)
        d.appendVar('ALTERNATIVE_%s' % (pn), ' ' + alt_name)
        d.setVarFlag('ALTERNATIVE_LINK_NAME', alt_name, alt_link_name)
        d.setVarFlag('ALTERNATIVE_TARGET', alt_name, target)
    f.close()
}
