require nginx.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=79da1c70d587d3a199af9255ad393f99"

SRC_URI[sha256sum] = "f867314ada32e717b949930859656c48a72dc6657aec598db58af2f98a40e1c1"

inherit upstream-version-is-even

CVE_STATUS[CVE-2026-95101] = "fixed-version: Fixed since 1.30.6"
CVE_STATUS[CVE-2026-97629] = "fixed-version: Fixed since 1.30.6"

# Remove following entries when upgrading recipe to 1.32
CVE_STATUS[CVE-2026-42055] = "fixed-version: Fixed since 1.30.3"
CVE_STATUS[CVE-2026-48142] = "fixed-version: Fixed since 1.30.3"
CVE_STATUS[CVE-2026-90439] = "fixed-version: Fixed since 1.30.5"
CVE_STATUS[CVE-2026-113425] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-121810] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-123007] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-123633] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-123634] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-123640] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-125283] = "fixed-version: Fixed since 1.30.7"
CVE_STATUS[CVE-2026-132779] = "fixed-version: Fixed since 1.30.8"
