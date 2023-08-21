require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=c31f662bb2bfb3b4187fe9a53e0ffe7c"

SRC_URI += "\
   file://not-check-libperl.patch \
   file://0001-Add-support-for-RISC-V.patch \
   file://0001-Improve-reproducibility.patch \
"

SRC_URI[sha256sum] = "c5f1fff7a0f93e1ec3746417b0594290ece617b4995ed95b8d527af0ba0e38f3"
