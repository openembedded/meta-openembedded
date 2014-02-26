require ${BPN}.inc

# work around build failure
EXTRA_OECONF += " --enable-libxml2=no"

SRC_URI += "file://0001-enable-subdir-objects.patch \
            file://0002-glibc-does-not-provide-strlcpy.patch \
"

SRC_URI[md5sum] = "fb6787ad749aadcfaeab3471939b3426"
SRC_URI[sha256sum] = "219b812be1bc2eba40faa86419d0aa2c479a7380af8af216e8bfa22eb6fc8ec4"
