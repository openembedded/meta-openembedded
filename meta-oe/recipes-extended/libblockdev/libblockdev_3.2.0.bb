DESCRIPTION = "libblockdev is a C library supporting GObject introspection for manipulation of \
block devices. It has a plugin-based architecture where each technology (like \
LVM, Btrfs, MD RAID, Swap,...) is implemented in a separate plugin, possibly \
with multiple implementations (e.g. using LVM CLI or the new LVM DBus API)."
HOMEPAGE = "http://rhinstaller.github.io/libblockdev/"
LICENSE = "LGPL-2.0-or-later"
SECTION = "devel/lib"

LIC_FILES_CHKSUM = "file://LICENSE;md5=c07cb499d259452f324bb90c3067d85c"

inherit autotools gobject-introspection pkgconfig lib_package

DEPENDS = "autoconf-archive-native glib-2.0 kmod udev libnvme"

SRC_URI = "git://github.com/storaged-project/libblockdev;branch=master;protocol=https \
           file://0001-fix-pythondir-for-multilib-when-cross-compiling.patch \
           "
SRCREV = "27881cb6e57eb0146c51d032ea72fbb3cd6bda7e"
S = "${WORKDIR}/git"

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}"

PACKAGECONFIG ??= "python3 lvm lvm-dbus dm parted fs escrow btrfs crypto mdraid mpath nvdimm tools smart smartmontools"
PACKAGECONFIG[python3] = "--with-python3, --without-python3,,python3"
PACKAGECONFIG[lvm] = "--with-lvm, --without-lvm, multipath-tools libyaml, lvm2"
PACKAGECONFIG[lvm-dbus] = "--with-lvm_dbus, --without-lvm_dbus, multipath-tools libyaml, lvm2"
PACKAGECONFIG[dm] = "--with-dm, --without-dm, multipath-tools, lvm2"
PACKAGECONFIG[parted] = "--with-part, --without-part, parted"
PACKAGECONFIG[fs] = "--with-fs, --without-fs, e2fsprogs util-linux"
PACKAGECONFIG[doc] = "--with-gtk-doc, --without-gtk-doc, gtk-doc-native"
PACKAGECONFIG[nvdimm] = "--with-nvdimm, --without-nvdimm, ndctl util-linux"
PACKAGECONFIG[escrow] = "--with-escrow, --without-escrow, nss volume-key"
PACKAGECONFIG[btrfs] = "--with-btrfs,--without-btrfs,libbytesize btrfs-tools"
PACKAGECONFIG[crypto] = "--with-crypto,--without-crypto,cryptsetup keyutils nss volume-key"
PACKAGECONFIG[mdraid] = "--with-mdraid,--without-mdraid,libbytesize"
PACKAGECONFIG[mpath] = "--with-mpath,--without-mpath, multipath-tools, lvm2"
PACKAGECONFIG[tools] = "--with-tools,--without-tools,libbytesize libdevmapper"
PACKAGECONFIG[smart] = "--with-smart,--without-smart,libatasmart"
PACKAGECONFIG[smartmontools] = "--with-smartmontools,--without-smartmontools,json-glib,smartmontools"

export GIR_EXTRA_LIBS_PATH="${B}/src/utils/.libs"
