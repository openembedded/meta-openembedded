require nginx.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=3dc49537b08b14c8b66ad247bb4c4593"

SRC_URI[sha256sum] = "c6b5c6b086c0df9d3ca3ff5e084c1d0ef909e6038279c71c1c3e985f576ff76a"

SRC_URI += "file://CVE-2025-53859.patch"
