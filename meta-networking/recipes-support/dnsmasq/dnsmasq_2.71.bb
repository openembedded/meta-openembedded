require dnsmasq.inc

SRC_URI += "\
    file://parallel-make.patch \
    file://lua.patch \
"

SRC_URI[dnsmasq-2.71.md5sum] = "15a68f7f6cc0119e843f67d2f79598f1"
SRC_URI[dnsmasq-2.71.sha256sum] = "7d8c64f66a396442e01b639df3ea6b4e02ba88cbe206c80be8de68b6841634c4"

