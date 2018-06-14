require syslog-ng.inc

SRC_URI += " \
    file://fix-config-libnet.patch \
    file://fix-invalid-ownership.patch \
    file://syslog-ng.service-the-syslog-ng-service.patch \
    file://rename-PAGESIZE-variables-to-pagesize.patch \
"

SRC_URI[md5sum] = "da59a65cd5e293ec0fa3d3ecf4984af9"
SRC_URI[sha256sum] = "a2dabd28674e2b558e4fb92484ad111d77bf7150070aa28556827130b479f9ef"
