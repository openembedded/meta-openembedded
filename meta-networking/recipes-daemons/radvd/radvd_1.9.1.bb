PR = "r1"

require radvd.inc

SRC_URI[md5sum] = "e807ad7e9a76d46b6133df391385cd31"
SRC_URI[sha256sum] = "54eb5704a2b710ba946fa30d2bca811fa23b1b3bfab322c38cb0c2a9619aa933"

SRC_URI += "\
    file://change-scanner-dependency.patch"
