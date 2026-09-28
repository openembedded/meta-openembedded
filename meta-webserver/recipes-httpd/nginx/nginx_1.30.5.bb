require nginx.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=79da1c70d587d3a199af9255ad393f99"

SRC_URI[sha256sum] = "6c20565aa2325cb82216ae804f4a4ff1875179014759a381c42ddc8e11c4906d"

inherit upstream-version-is-even

CVE_STATUS[CVE-2026-42055] = "fixed-version: Fixed since 1.30.3"
CVE_STATUS[CVE-2026-48142] = "fixed-version: Fixed since 1.30.3"
