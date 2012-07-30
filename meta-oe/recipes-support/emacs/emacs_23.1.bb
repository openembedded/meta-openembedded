require emacs.inc

SRC_URI = "${GNU_MIRROR}/emacs/emacs-${PV}.tar.gz;name=tarball \
           file://use-qemu.patch \
           file://nostdlib-unwind.patch \
           file://configure.in.lost.backslashes.patch \
           file://fix.dso.build.patch \
"
SRC_URI[tarball.md5sum] = "a620d4452769d04ad8864d662f34f8dd"
SRC_URI[tarball.sha256sum] = "a94cd46301f000d2d1dcd3fd7ef08ad952846d01ca5d4168b4ec3e38e857da47"
