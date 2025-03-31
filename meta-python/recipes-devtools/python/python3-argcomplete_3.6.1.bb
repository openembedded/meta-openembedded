SUMMARY = "Argcomplete provides easy, extensible command line tab completion of arguments for your Python script."
HOMEPAGE = "https://github.com/kislyuk/argcomplete"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI[sha256sum] = "927531c2fbaa004979f18c2316f6ffadcfc5cc2de15ae2624dfe65deaf60e14f"

PYPI_PACKAGE = "argcomplete"

inherit pypi python_hatchling

DEPENDS += "python3-hatch-vcs-native"

RDEPENDS:${PN} += "\
    python3-core \
    python3-io \
"

BBCLASSEXTEND = "native nativesdk"

