SUMMARY = "Simple Go-based setuid+setgid+setgroups+exec"
HOMEPAGE = "https://github.com/tianon/gosu"
DESCRIPTION = "This is a simple tool grown out of the simple fact that su and sudo have very strange and often annoying TTY and signal-forwarding behavior."
LICENSE = "Apache-2.0 "
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

GO_IMPORT = "github.com/tianon/gosu"
SRC_URI = "git://${GO_IMPORT}.git;destsuffix=src/${GO_IMPORT};branch=master;protocol=https;tag=${PV} \
           git://github.com/moby/sys;name=user;destsuffix=src/github.com/moby/sys;branch=main;protocol=https \
           git://github.com/golang/sys;name=sys;destsuffix=src/golang.org/x/sys;branch=master;protocol=https \
"
SRCREV = "29b939aa5bd4dc588af765b8248f6e39150d2565"
#v0.4.1
SRCREV_user = "85a71bbe1faa36c552a960e6a5f3d0cfb632fbbe"
#v0.1.0
SRCREV_sys = "95e765b1cc43ac521bd4fd501e00774e34401449"

SRCREV_FORMAT .= "_user_sys"

S = "${UNPACKDIR}"
inherit go

CGO_ENABLED = "1"

do_compile:prepend() {
    # New Go versions has Go modules support enabled by default
    export GO111MODULE=off
}

RDEPENDS:${PN}-dev += "bash"
