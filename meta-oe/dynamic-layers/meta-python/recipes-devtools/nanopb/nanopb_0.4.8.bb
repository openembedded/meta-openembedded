DESCRIPTION="Protocol Buffers with small code size"
LICENSE="Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9db4b73a55a3994384112efcdb37c01f"

DEPENDS = "protobuf-native"

SRC_URI = "git://github.com/nanopb/nanopb.git;branch=master;protocol=https"
SRCREV = "6cfe48d6f1593f8fa5c0f90437f5e6522587745e"

S = "${WORKDIR}/git"

inherit cmake python3-dir

EXTRA_OECMAKE += "-Dnanopb_PYTHON_INSTDIR_OVERRIDE=${PYTHON_SITEPACKAGES_DIR}"

RDEPENDS:${PN} += "${PYTHON_PN}-protobuf"

# Maintain compatability with old header locations for packages
# which haven't yet migrated to `nanopb/pb*.h`
do_install:append() {
  for hdr in ${D}${includedir}/nanopb/*; do
	ln -sv nanopb/$(basename "$hdr") ${D}${includedir}/
  done
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}"

BBCLASSEXTEND = "native nativesdk"

