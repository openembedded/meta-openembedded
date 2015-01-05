require dnsmasq.inc

SRC_URI += "\
    file://parallel-make.patch \
    file://lua.patch \
"

SRC_URI[dnsmasq-2.72.md5sum] = "cf82f81cf09ad3d47612985012240483"
SRC_URI[dnsmasq-2.72.sha256sum] = "635f1b47417d17cf32e45cfcfd0213ac39fd09918479a25373ba9b2ce4adc05d"

