require ${BPN}.inc

SRC_URI += "file://0001-Use-proc-self-exe-for-swig-swiglib-on-non-Win32-plat.patch \
            file://0001-configure-use-pkg-config-for-pcre-detection.patch \
           "

SRC_URI[md5sum] = "62f9b0d010cef36a13a010dc530d0d41"
SRC_URI[sha256sum] = "a2669657cabcedc371f63c0457407a183e0b6b2ef4e7e303c1ec9a3964cc7813"
