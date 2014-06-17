require ${BPN}.inc

SRC_URI += "file://0001-Use-proc-self-exe-for-swig-swiglib-on-non-Win32-plat.patch \
            file://0001-configure-use-pkg-config-for-pcre-detection.patch \
           "


SRC_URI[md5sum] = "c3fb0b2d710cc82ed0154b91e43085a4"
SRC_URI[sha256sum] = "65e13f22a60cecd7279c59882ff8ebe1ffe34078e85c602821a541817a4317f7"

