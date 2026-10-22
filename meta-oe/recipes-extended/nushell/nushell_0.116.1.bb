SUMMARY = "Nushell is a modern shell for structured data"
HOMEPAGE = "https://crates.io/crates/nu"
DESCRIPTION = "Nushell is a modern shell that works with structured data rather than text. \
               Nushell takes cues from a lot of familiar territory: traditional shells like \
               bash, object based shells like PowerShell, gradually typed languages like \
               TypeScript, functional programming, systems programming, and more."

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=ea22f3cfd911b3519505220cfc0ce542 \
"

DEPENDS = "libgit2 sqlite3"

SRC_URI = "crate://crates.io/nu/${PV};name=nu"
SRC_URI[nu.sha256sum] = "62e21f2d6a14430ae2701a4e6f11be42d40a11204c204bef32a8b9879f79e767"

S = "${CARGO_VENDORING_DIRECTORY}/nu-${PV}"

inherit cargo cargo-update-recipe-crates pkgconfig

require ${BPN}-crates.inc

BBCLASSEXTEND = "native"
