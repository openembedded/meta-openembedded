require ${BPN}.inc

# work around build failure
EXTRA_OECONF += " --enable-libxml2=no"

SRC_URI += "file://0001-enable-subdir-objects.patch \
            file://0002-glibc-does-not-provide-strlcpy.patch \
            file://0003-use-am-path-libgcrypt.patch \
            file://0004-modules-gui-qt4-out-of-tree-build.patch \
            file://0005-libpostproc-header-check.patch \
            file://0006-make-opencv-configurable.patch \
            file://0007-use-vorbisidec.patch \
            file://0008-fix-luaL-checkint.patch \
            file://0009-fix-rdp-module.patch \
"

SRC_URI[md5sum] = "42273945758b521c408fabc7fd6d9946"
SRC_URI[sha256sum] = "543d9d7e378ec0fa1ee2e7f7f5acf8c456c7d0ecc32037171523197ef3cf1fcb"
