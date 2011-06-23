DEPENDS += "dri2proto expat libdrm"

#not supported in oe-core base.bbclass
#FILESPATHPKG =. "mesa-${PV}:mesa:"
FILESPATH =. "${FILE_DIRNAME}/mesa:${FILE_DIRNAME}/mesa-${PV}:"

# most of our targets do not have DRI so will use mesa-xlib
DEFAULT_PREFERENCE = "-1"

MACHINE_DRI_MODULES ?= ""
#PACKAGE_ARCH = "${@['${MACHINE_ARCH}','${BASE_PACKAGE_ARCH}'][bb.data.getVar('MACHINE_DRI_MODULES',d,1) == '']}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECONF += "--with-driver=dri --disable-egl --disable-gallium -with-dri-drivers=swrast,${MACHINE_DRI_MODULES}"

python populate_packages_prepend() {
	import os.path

	dri_drivers_root = os.path.join(bb.data.getVar('libdir', d, 1), "dri")

	do_split_packages(d, dri_drivers_root, '^(.*)_dri\.so$', 'mesa-dri-driver-%s', 'Mesa %s DRI driver', extra_depends='')
}

PACKAGES_DYNAMIC = "mesa-dri-driver-*"

FILES_${PN}-dbg += "${libdir}/dri/.debug/*"

