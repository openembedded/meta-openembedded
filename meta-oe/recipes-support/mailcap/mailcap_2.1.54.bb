SUMMARY = "Helper application and MIME type associations for file types"
HOMEPAGE = "https://github.com/InfrastructureServices/mailcap"
DESCRIPTION = "The mailcap file is used by the metamail program. Metamail reads the \
mailcap file to determine how it should display non-text or multimedia \
material. Basically, mailcap associates a particular type of file \
with a particular program that a mail agent or other program can call \
in order to handle the file. Mailcap should be installed to allow \
certain programs to be able to handle non-text files. \
\
Also included in this package is the mime.types file which contains a \
list of MIME types and their filename extension associations, used  \
by several applications e.g. to determine MIME types for filenames."

SECTION = "System Environment/Base"

LICENSE = "MIT AND LicenseRef-PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=8dce08227d135cfda1f19d4c0c6689de"

SRC_URI = "git://github.com/InfrastructureServices/mailcap.git;protocol=https;branch=master;tag=r${@d.getVar('PV').replace('.', '-')}"
SRCREV = "9699055a1b4dfb90f7594ee2e8dda705fa56d3b8"

# Release tags are r2-1-54, which the git tag check cannot map to PV
UPSTREAM_VERSION_UNKNOWN = "1"

inherit update-alternatives

do_install() {
    oe_runmake install DESTDIR=${D} sysconfdir=${sysconfdir} mandir=${mandir}
}

ALTERNATIVE:${PN} = "mime.types"
ALTERNATIVE_LINK_NAME[mime.types] = "${sysconfdir}/mime.types"
