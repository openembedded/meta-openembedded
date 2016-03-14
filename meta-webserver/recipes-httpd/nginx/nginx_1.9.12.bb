require nginx.inc

# 1.9.x is the current mainline branches containing all new features
# 1.8.x branch is the current stable branch, the recommended default
DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0bb58ed0dfd4f5dbece3b52aba79f023"

SRC_URI[md5sum] = "0afe4a7e589a0de43b7b54aa055a4351"
SRC_URI[sha256sum] = "1af2eb956910ed4b11aaf525a81bc37e135907e7127948f9179f5410337da042"

DISABLE_STATIC = ""
