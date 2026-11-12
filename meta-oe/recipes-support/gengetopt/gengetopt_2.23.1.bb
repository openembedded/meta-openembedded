SUMMARY = "skeleton main.c generator"
DESCRIPTION = "Gengetopt is a tool to write command line option parsing code for C programs."
SECTION = "utils"
HOMEPAGE = "https://www.gnu.org/software/gengetopt/gengetopt.html"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=ff95bfe019feaf92f524b73dd79e76eb"

SRC_URI = "${GNU_MIRROR}/gengetopt/${BPN}-${PV}.tar.xz \
           file://0001-gnulib-Do-not-expand-glibc-C23-qualifier-generic-macros.patch \
           "

SRC_URI[sha256sum] = "3b9def48422bd45f78af95936200b7f9287369a3db76c4907c42fe10f4922ab6"

# ftpmirror.gnu.org redirects to a random mirror, several of which serve a
# non-Apache index the auto-derived regex cannot parse. Pin the check to the
# canonical GNU FTP listing.
UPSTREAM_CHECK_URI = "https://ftp.gnu.org/gnu/gengetopt/"
UPSTREAM_CHECK_REGEX = "gengetopt-(?P<pver>\d+(\.\d+)+)\.tar"

inherit autotools texinfo

CXXFLAGS += "-std=c++14"

BBCLASSEXTEND = "native nativesdk"
