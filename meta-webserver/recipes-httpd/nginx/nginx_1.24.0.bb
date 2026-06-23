require nginx.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=175abb631c799f54573dc481454c8632"

SRC_URI:append = " \
                  file://CVE-2023-44487.patch \
                  file://CVE-2026-28755.patch \
                  file://CVE-2026-27651.patch \
                  file://CVE-2026-27654.patch \
                  file://CVE-2026-28753.patch \
                  file://CVE-2026-32647.patch \
                  file://CVE-2026-40701.patch \
                  file://CVE-2026-42934.patch \
                  file://CVE-2026-42945.patch \
                  file://CVE-2026-42946-01.patch \
                  file://CVE-2026-42946-02.patch \
                  file://CVE-2026-9256.patch \
                  file://CVE-2026-48142.patch \
"

SRC_URI[sha256sum] = "77a2541637b92a621e3ee76776c8b7b40cf6d707e69ba53a940283e30ff2f55d"

