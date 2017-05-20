HOMEPAGE = "https://github.com/bb4242/sdnotify"
SUMMARY = "A pure Python implementation of systemd's service notification protocol (sd_notify)"

DESCRIPTION = "\
  sdnotify is a pure Python implementation of the systemd sd_notify protocol. \
  This protocol can be used to inform systemd about service start-up completion, \
  watchdog events, and other service status changes. \
  Thus, this package can be used to write system services in Python that play nicely with systemd. \
  "

SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cc572ccc4b18a4b7d13be5d01bc8213e"

SRC_URI[md5sum] = "a182c1ef47e1c6c965771aa3029da16f"
SRC_URI[sha256sum] = "e69220d4f6cbb02130f43f929350a80cf51033fde47dcb056fbda71e2dff2d5a"

do_install_append() {
	rm -r ${D}/${datadir}
}
