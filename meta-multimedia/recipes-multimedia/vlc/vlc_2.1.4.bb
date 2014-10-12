require ${BPN}.inc

# work around build failure
EXTRA_OECONF += " --enable-libxml2=no"

SRC_URI += "file://0001-enable-subdir-objects.patch \
            file://0002-glibc-does-not-provide-strlcpy.patch \
            file://0003-use-am-path-libgcrypt.patch \
            file://0004-modules-gui-qt4-out-of-tree-build.patch \
            file://0005-libpostproc-header-check.patch \
            file://0006-make-opencv-configurable.patch \
"

SRC_URI[md5sum] = "7ed67d22f7425011078772bfc62ac222"
SRC_URI[sha256sum] = "3e566c7525478167e18cc53dc75d621e4af91eb40aabb6231e47db25d682d5d3"
