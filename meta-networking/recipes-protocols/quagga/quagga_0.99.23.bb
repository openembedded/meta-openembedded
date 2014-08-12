require quagga.inc

SRC_URI += "file://babel-close-the-stdout-stderr-as-in-other-daemons.patch \
"

SRC_URI[md5sum] = "d17145e62b6ea14f0f13bb63f59e5166"
SRC_URI[sha256sum] = "2c7798204f35dc7acea9f206647e8aa3957cae3b21733cdff413b506481a101c"

QUAGGASUBDIR = ""
