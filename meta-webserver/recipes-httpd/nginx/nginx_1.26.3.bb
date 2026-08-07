require nginx.inc

# 1.24.x branch is the current stable branch, the recommended default
# 1.25.x is the current mainline branches containing all new features
DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a6547d7e5628787ee2a9c5a3480eb628"

SRC_URI:append = " file://CVE-2026-28755.patch"

SRC_URI[sha256sum] = "69ee2b237744036e61d24b836668aad3040dda461fe6f570f1787eab570c75aa"

