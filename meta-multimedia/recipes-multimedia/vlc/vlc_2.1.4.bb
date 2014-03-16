require ${BPN}.inc

# work around build failure
EXTRA_OECONF += " --enable-libxml2=no"

SRC_URI += "file://0001-enable-subdir-objects.patch \
            file://0002-glibc-does-not-provide-strlcpy.patch \
"

SRC_URI[md5sum] = "7ed67d22f7425011078772bfc62ac222"
SRC_URI[sha256sum] = "3e566c7525478167e18cc53dc75d621e4af91eb40aabb6231e47db25d682d5d3"
