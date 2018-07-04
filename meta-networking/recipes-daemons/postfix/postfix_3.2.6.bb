require postfix.inc

SRC_URI += "file://0001-Check-for-glibc-before-setting-CANT_USE_SEND_RECV_MS.patch \
            file://0001-makedefs-Use-native-compiler-to-build-makedefs.test.patch \
            file://postfix-install.patch \
            file://icu-config.patch \
           "
SRC_URI[md5sum] = "d10f1fb551be86f6e48c2908dd8a12ff"
SRC_URI[sha256sum] = "1df036380bae7c356bfa8989a87de58033b6aaa09b3bafcfa36c3694dd449039"

UPSTREAM_CHECK_REGEX = "postfix\-(?P<pver>3\.2(\.\d+)+).tar.gz"
