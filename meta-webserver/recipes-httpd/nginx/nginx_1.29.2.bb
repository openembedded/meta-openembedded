require nginx.inc

# 1.28.x branch is the current stable branch, the recommended default
# 1.29.x is the current mainline branches containing all new features
DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3dc49537b08b14c8b66ad247bb4c4593"

SRC_URI[sha256sum] = "5669e3c29d49bf7f6eb577275b86efe4504cf81af885c58a1ed7d2e7b8492437"

SRC_URI += "file://CVE-2026-1642.patch"
