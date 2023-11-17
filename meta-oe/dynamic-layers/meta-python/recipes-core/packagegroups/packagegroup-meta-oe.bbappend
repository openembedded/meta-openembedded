RDEPENDS:packagegroup-meta-oe-devtools += "\
    python3-distutils-extra \
    rwmem \
    speedtest-cli \
    mongodb \
"

RDEPENDS:packagegroup-meta-oe-connectivity += "\
    lirc \
"

RDEPENDS:packagegroup-meta-oe-extended += "\
    lcdproc \
    mozjs-91 \
"
RDEPENDS:packagegroup-meta-oe-support += "\
    smem \
"
RDEPENDS:packagegroup-meta-oe-extended:remove:libc-musl = "lcdproc"

