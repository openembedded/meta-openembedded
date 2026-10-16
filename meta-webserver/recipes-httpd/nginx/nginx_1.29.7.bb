require nginx.inc

SRC_URI:append = " \
    file://CVE-2026-40701.patch \
    file://CVE-2026-42934.patch \
    file://CVE-2026-42946_p1.patch \
    file://CVE-2026-42946_p2.patch \
    file://CVE-2026-42945.patch \
    file://CVE-2026-9256.patch \
    file://CVE-2026-42055.patch \
"

# 1.28.x branch is the current stable branch, the recommended default
# 1.29.x is the current mainline branches containing all new features
DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=79da1c70d587d3a199af9255ad393f99"

SRC_URI[sha256sum] = "673f8fb8c0961c44fbd9410d6161831453609b44063d3f2948253fc2b5692139"

