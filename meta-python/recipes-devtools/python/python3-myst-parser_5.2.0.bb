SUMMARY = "An extended commonmark compliant parser, with bridges to docutils/sphinx"
HOMEPAGE = "https://github.com/executablebooks/MyST-Parser"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a38a1697260a7ad7bf29f44b362db1fc"

SRC_URI[sha256sum] = "749cc02793f2be6b984b0b59b1fe33b6ed61d77644499b85db436d0d20117fa4"

inherit pypi python_flit_core

RDEPENDS:${PN} = "\
    python3-docutils \
    python3-jinja2 \
    python3-markdown-it-py \
    python3-mdit-py-plugins \
    python3-pyyaml \
    python3-sphinx \
    "

BBCLASSEXTEND = "native nativesdk"
