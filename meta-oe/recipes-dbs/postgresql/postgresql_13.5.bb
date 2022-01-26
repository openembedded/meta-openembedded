require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=255f15687738db8068fbe9b938c90217"

SRC_URI += "\
   file://not-check-libperl.patch \
   file://0001-Add-support-for-RISC-V.patch \
   file://0001-Improve-reproducibility.patch \
   file://0001-configure.in-bypass-autoconf-2.69-version-check.patch \
   file://remove_duplicate.patch \
"

SRC_URI[sha256sum] = "9b81067a55edbaabc418aacef457dd8477642827499560b00615a6ea6c13f6b3"
