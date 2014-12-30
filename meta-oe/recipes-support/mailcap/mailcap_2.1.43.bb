SUMMARY = "Helper application and MIME type associations for file types"
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

SRC_URI = "https://git.fedorahosted.org/cgit/${PN}.git/snapshot/r2-1-43.tar.gz"
SRC_URI[md5sum] = "8fd185ffae710301de3bc297877a404b"
SRC_URI[sha256sum] = "a630892b5fe59eb9e3bef30c597de1c2b7f35243dea4b0c995fd482aa20c2d6b"
LICENSE = "PD & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=100fcfb84512ccc03ffc7d89ac391305"
S = "${WORKDIR}/r2-1-43"
do_install() {
    oe_runmake install DESTDIR=${D} sysconfdir=${sysconfdir} mandir=${mandir}
}
