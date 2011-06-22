inherit linux-kernel-base module_strip

PROVIDES += "virtual/kernel"
DEPENDS += "virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}depmod-${@get_kernelmajorversion('${PV}')} virtual/${TARGET_PREFIX}gcc${KERNEL_CCSUFFIX} update-modules"

# we include gcc above, we dont need virtual/libc
INHIBIT_DEFAULT_DEPS = "1"

KERNEL_IMAGETYPE ?= "zImage"

python __anonymous () {
    kerneltype = bb.data.getVar('KERNEL_IMAGETYPE', d, 1) or ''
    if kerneltype == 'uImage':
    	depends = bb.data.getVar("DEPENDS", d, 1)
    	depends = "%s u-boot-mkimage-native" % depends
    	bb.data.setVar("DEPENDS", depends, d)

    image = bb.data.getVar('INITRAMFS_IMAGE', d, True)
    if image != '' and image is not None:
        bb.data.setVar('INITRAMFS_TASK', '${INITRAMFS_IMAGE}:do_rootfs', d)

    machine_kernel_pr = bb.data.getVar('MACHINE_KERNEL_PR', d, True)

    if machine_kernel_pr:
       bb.data.setVar('PR', machine_kernel_pr, d)
}

INITRAMFS_IMAGE ?= ""
INITRAMFS_TASK ?= ""

inherit kernel-arch deploy

PACKAGES_DYNAMIC += "kernel-module-*"
PACKAGES_DYNAMIC += "kernel-image-*"
PACKAGES_DYNAMIC += "kernel-firmware-*"

export OS = "${TARGET_OS}"
export CROSS_COMPILE = "${TARGET_PREFIX}"

KERNEL_PRIORITY = "${@bb.data.getVar('PV',d,1).split('-')[0].split('.')[-1]}"

KERNEL_RELEASE ?= "${KERNEL_VERSION}"

KERNEL_CCSUFFIX ?= ""
KERNEL_LDSUFFIX ?= ""

# Set TARGET_??_KERNEL_ARCH in the machine .conf to set architecture
# specific options necessary for building the kernel and modules.
#FIXME: should be this: TARGET_CC_KERNEL_ARCH ?= "${TARGET_CC_ARCH}"
TARGET_CC_KERNEL_ARCH ?= ""
HOST_CC_KERNEL_ARCH ?= "${TARGET_CC_KERNEL_ARCH}"
TARGET_LD_KERNEL_ARCH ?= ""
HOST_LD_KERNEL_ARCH ?= "${TARGET_LD_KERNEL_ARCH}"

KERNEL_CC = "${CCACHE}${HOST_PREFIX}gcc${KERNEL_CCSUFFIX} ${HOST_CC_KERNEL_ARCH}${TOOLCHAIN_OPTIONS}"
KERNEL_LD = "${LD}${KERNEL_LDSUFFIX} ${HOST_LD_KERNEL_ARCH}${TOOLCHAIN_OPTIONS}"

# Where built kernel lies in the kernel tree
KERNEL_OUTPUT ?= "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"
KERNEL_IMAGEDEST = "boot"

#
# configuration
#
export CMDLINE_CONSOLE = "console=${@bb.data.getVar("KERNEL_CONSOLE",d,1) or "ttyS0"}"

KERNEL_VERSION = "${@get_kernelversion('${B}')}"
KERNEL_MAJOR_VERSION = "${@get_kernelmajorversion('${KERNEL_VERSION}')}"

KERNEL_LOCALVERSION ?= ""

# kernels are generally machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"

# U-Boot support
UBOOT_ENTRYPOINT ?= "20008000"
UBOOT_LOADADDRESS ?= "${UBOOT_ENTRYPOINT}"

# For the kernel, we don't want the '-e MAKEFLAGS=' in EXTRA_OEMAKE.
# We don't want to override kernel Makefile variables from the environment
EXTRA_OEMAKE = ""

KERNEL_ALT_IMAGETYPE ??= ""

kernel_do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	oe_runmake include/linux/version.h CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	if [ "${KERNEL_MAJOR_VERSION}" != "2.6" ]; then
		oe_runmake dep CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	fi
	oe_runmake ${KERNEL_IMAGETYPE} ${KERNEL_ALT_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}"
}

do_compile_kernelmodules() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	if (grep -q -i -e '^CONFIG_MODULES=y$' .config); then
		oe_runmake modules  CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	else
		oenote "no modules to compile"
	fi
}
addtask compile_kernelmodules after do_compile before do_install

kernel_do_install() {
	#
	# First install the modules
	#
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	if (grep -q -i -e '^CONFIG_MODULES=y$' .config); then
		oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" modules_install
	else
		oenote "no modules to install"
	fi

	#
	# Install various kernel output (zImage, map file, config, module support files)
	#	
	install -d ${D}/${KERNEL_IMAGEDEST}
	install -d ${D}/boot
	install -m 0644 ${KERNEL_OUTPUT} ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
	install -m 0644 System.map ${D}/boot/System.map-${KERNEL_VERSION}
	install -m 0644 .config ${D}/boot/config-${KERNEL_VERSION}
	install -m 0644 vmlinux ${D}/boot/vmlinux-${KERNEL_VERSION}
	[ -e Module.symvers ] && install -m 0644 Module.symvers ${D}/boot/Module.symvers-${KERNEL_VERSION}
	install -d ${D}/etc/modutils
	if [ "${KERNEL_MAJOR_VERSION}" = "2.6" ]; then
		install -d ${D}/etc/modprobe.d
	fi

	#
	# Support for external module building - create a minimal copy of the
	# kernel source tree.
	#
	kerneldir=${STAGING_KERNEL_DIR}
	install -d $kerneldir

	#
	# Store the kernel version in sysroots for module-base.bbclass
	#

	echo "${KERNEL_VERSION}" > $kerneldir/kernel-abiversion

	#
	# Copy the entire source tree. In case an external build directory is
	# used, copy the build directory over first, then copy over the source
	# dir. This ensures the original Makefiles are used and not the
	# redirecting Makefiles in the build directory.
	#
	# work and sysroots can be on different partitions, so we can't rely on
	# hardlinking, unfortunately.
	#
	cp -fR * $kerneldir
	cp .config $kerneldir
	if [ ! "${S}" == "${B}" ]; then
		cp -fR ${S}/* $kerneldir
	fi
	install -m 0644 ${KERNEL_OUTPUT} $kerneldir/${KERNEL_IMAGETYPE}
	install -m 0644 System.map $kerneldir/System.map-${KERNEL_VERSION}

	#
	# Clean and remove files not needed for building modules.
	# Some distributions go through a lot more trouble to strip out
	# unecessary headers, for now, we just prune the obvious bits.
	#
	# We don't want to leave host-arch binaries in /sysroots, so
	# we clean the scripts dir while leaving the generated config
	# and include files.
	#
	find $kerneldir -name "*.o" -delete
	#oe_runmake -C $kerneldir CC="${KERNEL_CC}" LD="${KERNEL_LD}" clean
	make -C $kerneldir _mrproper_scripts
	find $kerneldir -path $kerneldir/scripts -prune -o -name "*.[csS]" -exec rm '{}' \;
	find $kerneldir/Documentation -name "*.txt" -exec rm '{}' \;

	# Remove the following binaries which cause strip errors
	# during do_package for cross-compiled platforms
	bin_files="arch/powerpc/boot/addnote arch/powerpc/boot/hack-coff \
	           arch/powerpc/boot/mktree"
	for entry in $bin_files; do
	        rm -f $kerneldir/$entry
	done
}

sysroot_stage_all_append() {
	sysroot_stage_dir ${D}/kernel ${SYSROOT_DESTDIR}/kernel
}

kernel_do_configure() {
	# Copy defconfig to .config if .config does not exist. This allows
	# recipes to manage the .config themselves in do_configure_prepend().
	if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${S}/.config" ]; then
		cp "${WORKDIR}/defconfig" "${S}/.config"
	fi

	yes '' | oe_runmake oldconfig

	if [ ! -z "${INITRAMFS_IMAGE}" ]; then
		for img in cpio.gz cpio.lzo cpio.lzma; do
		if [ -e "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}-${MACHINE}.$img" ]; then
			cp "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}-${MACHINE}.$img" initramfs.$img
		fi
		done
	fi
}

kernel_do_configure[depends] += "${INITRAMFS_TASK}"

do_menuconfig() {
        export DISPLAY='${DISPLAY}'
        export DBUS_SESSION_BUS_ADDRESS='${DBUS_SESSION_BUS_ADDRESS}'
        export XAUTHORITY='${XAUTHORITY}'
	export TERMWINDOWTITLE="${PN} Kernel Configuration"
	export SHELLCMDS="make menuconfig"
	${TERMCMDRUN}
	if [ $? -ne 0 ]; then
		echo "Fatal: '${TERMCMD}' not found. Check TERMCMD variable."
		exit 1
	fi
}
do_menuconfig[nostamp] = "1"
addtask menuconfig after do_patch

pkg_postinst_kernel () {
	cd /${KERNEL_IMAGEDEST}; update-alternatives --install /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ${KERNEL_PRIORITY} || true
}

pkg_postrm_kernel () {
	cd /${KERNEL_IMAGEDEST}; update-alternatives --remove ${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE}-${KERNEL_VERSION} || true
}

inherit cml1

EXPORT_FUNCTIONS do_compile do_install do_configure

# kernel-base becomes kernel-${KERNEL_VERSION}
# kernel-image becomes kernel-image-${KERNEL_VERISON}
PACKAGES = "kernel kernel-base kernel-image kernel-dev kernel-vmlinux kernel-misc"
FILES = ""
FILES_kernel-image = "/boot/${KERNEL_IMAGETYPE}*"
FILES_kernel-dev = "/boot/System.map* /boot/Module.symvers* /boot/config*"
FILES_kernel-vmlinux = "/boot/vmlinux*"
# misc is a package to contain files we need in staging
FILES_kernel-misc = "/kernel/include/config /kernel/scripts /kernel/drivers/crypto /kernel/drivers/media"
RDEPENDS_kernel = "kernel-base"
# Allow machines to override this dependency if kernel image files are 
# not wanted in images as standard
RDEPENDS_kernel-base ?= "kernel-image"
PKG_kernel-image = "kernel-image-${@legitimize_package_name('${KERNEL_VERSION}')}"
PKG_kernel-base = "kernel-${@legitimize_package_name('${KERNEL_VERSION}')}"
ALLOW_EMPTY_kernel = "1"
ALLOW_EMPTY_kernel-base = "1"
ALLOW_EMPTY_kernel-image = "1"

# Userspace workarounds for kernel modules issues
# This is shame, fix the kernel instead!
DEPENDS_kernel-module-dtl1-cs = "bluez-dtl1-workaround"
RDEPENDS_kernel-module-dtl1-cs = "bluez-dtl1-workaround"

pkg_postinst_kernel-image () {
if [ ! -e "$D/lib/modules/${KERNEL_VERSION}" ]; then
	mkdir -p $D/lib/modules/${KERNEL_VERSION}
fi
if [ -n "$D" ]; then
	${HOST_PREFIX}depmod-${KERNEL_MAJOR_VERSION} -A -b $D -F ${STAGING_KERNEL_DIR}/System.map-${KERNEL_VERSION} ${KERNEL_VERSION}
else
	depmod -a
fi
}

pkg_postinst_modules () {
if [ -n "$D" ]; then
	${HOST_PREFIX}depmod-${KERNEL_MAJOR_VERSION} -A -b $D -F ${STAGING_KERNEL_DIR}/System.map-${KERNEL_VERSION} ${KERNEL_VERSION}
else
	depmod -a
	update-modules || true
fi
}

pkg_postrm_modules () {
update-modules || true
}

autoload_postinst_fragment() {
if [ x"$D" = "x" ]; then
	modprobe %s || true
fi
}

# autoload defaults (alphabetically sorted)
module_autoload_hidp = "hidp"
module_autoload_ipv6 = "ipv6"
module_autoload_ipsec = "ipsec"
module_autoload_ircomm-tty = "ircomm-tty"
module_autoload_rfcomm = "rfcomm"
module_autoload_sa1100-rtc = "sa1100-rtc"
# sa1100-rtc was renamed in 2.6.23 onwards
module_autoload_rtc-sa1100 = "rtc-sa1100"

# alias defaults (alphabetically sorted)
module_conf_af_packet = "alias net-pf-17 af_packet"
module_conf_bluez = "alias net-pf-31 bluez"
module_conf_bnep = "alias bt-proto-4 bnep"
module_conf_hci_uart = "alias tty-ldisc-15 hci_uart"
module_conf_l2cap = "alias bt-proto-0 l2cap"
module_conf_sco = "alias bt-proto-2 sco"
module_conf_rfcomm = "alias bt-proto-3 rfcomm"

python populate_packages_prepend () {
	def extract_modinfo(file):
		import tempfile, re
		tempfile.tempdir = bb.data.getVar("WORKDIR", d, 1)
		tf = tempfile.mkstemp()
		tmpfile = tf[1]
		cmd = "PATH=\"%s\" %sobjcopy -j .modinfo -O binary %s %s" % (bb.data.getVar("PATH", d, 1), bb.data.getVar("HOST_PREFIX", d, 1) or "", file, tmpfile)
		os.system(cmd)
		f = open(tmpfile)
		l = f.read().split("\000")
		f.close()
		os.close(tf[0])
		os.unlink(tmpfile)
		exp = re.compile("([^=]+)=(.*)")
		vals = {}
		for i in l:
			m = exp.match(i)
			if not m:
				continue
			vals[m.group(1)] = m.group(2)
		return vals
	
	def parse_depmod():
		import re

		dvar = bb.data.getVar('PKGD', d, 1)
		if not dvar:
			bb.error("PKGD not defined")
			return

		kernelver = bb.data.getVar('KERNEL_VERSION', d, 1)
		kernelver_stripped = kernelver
		m = re.match('^(.*-hh.*)[\.\+].*$', kernelver)
		if m:
			kernelver_stripped = m.group(1)
		path = bb.data.getVar("PATH", d, 1)
		host_prefix = bb.data.getVar("HOST_PREFIX", d, 1) or ""
		major_version = bb.data.getVar('KERNEL_MAJOR_VERSION', d, 1)

		cmd = "PATH=\"%s\" %sdepmod-%s -n -a -r -b %s -F %s/boot/System.map-%s %s" % (path, host_prefix, major_version, dvar, dvar, kernelver, kernelver_stripped)
		f = os.popen(cmd, 'r')

		deps = {}
		pattern0 = "^(.*\.k?o):..*$"
		pattern1 = "^(.*\.k?o):\s*(.*\.k?o)\s*$"
		pattern2 = "^(.*\.k?o):\s*(.*\.k?o)\s*\\\$"
		pattern3 = "^\t(.*\.k?o)\s*\\\$"
		pattern4 = "^\t(.*\.k?o)\s*$"

		line = f.readline()
		while line:
			if not re.match(pattern0, line):
				line = f.readline()
				continue
			m1 = re.match(pattern1, line)
			if m1:
				deps[m1.group(1)] = m1.group(2).split()
			else:
				m2 = re.match(pattern2, line)
				if m2:
					deps[m2.group(1)] = m2.group(2).split()
					line = f.readline()
					m3 = re.match(pattern3, line)
					while m3:
						deps[m2.group(1)].extend(m3.group(1).split())
						line = f.readline()
						m3 = re.match(pattern3, line)
					m4 = re.match(pattern4, line)
					deps[m2.group(1)].extend(m4.group(1).split())
			line = f.readline()
		f.close()
		return deps
	
	def get_dependencies(file, pattern, format):
                # file no longer includes PKGD
		file = file.replace(bb.data.getVar('PKGD', d, 1) or '', '', 1)
                # instead is prefixed with /lib/modules/${KERNEL_VERSION}
                file = file.replace("/lib/modules/%s/" % bb.data.getVar('KERNEL_VERSION', d, 1) or '', '', 1)

		if module_deps.has_key(file):
			import re
			dependencies = []
			for i in module_deps[file]:
				m = re.match(pattern, os.path.basename(i))
				if not m:
					continue
				on = legitimize_package_name(m.group(1))
				dependency_pkg = format % on
				dependencies.append(dependency_pkg)
			return dependencies
		return []

	def frob_metadata(file, pkg, pattern, format, basename):
		import re
		vals = extract_modinfo(file)

		dvar = bb.data.getVar('PKGD', d, 1)

		# If autoloading is requested, output /etc/modutils/<name> and append
		# appropriate modprobe commands to the postinst
		autoload = bb.data.getVar('module_autoload_%s' % basename, d, 1)
		if autoload:
			name = '%s/etc/modutils/%s' % (dvar, basename)
			f = open(name, 'w')
			for m in autoload.split():
				f.write('%s\n' % m)
			f.close()
			postinst = bb.data.getVar('pkg_postinst_%s' % pkg, d, 1)
			if not postinst:
				bb.fatal("pkg_postinst_%s not defined" % pkg)
			postinst += bb.data.getVar('autoload_postinst_fragment', d, 1) % autoload
			bb.data.setVar('pkg_postinst_%s' % pkg, postinst, d)

		# Write out any modconf fragment
		modconf = bb.data.getVar('module_conf_%s' % basename, d, 1)
		if modconf:
			if bb.data.getVar("KERNEL_MAJOR_VERSION", d, 1) == "2.6":
				name = '%s/etc/modprobe.d/%s.conf' % (dvar, basename)
			else:
				name = '%s/etc/modutils/%s.conf' % (dvar, basename)
			f = open(name, 'w')
			f.write("%s\n" % modconf)
			f.close()

		files = bb.data.getVar('FILES_%s' % pkg, d, 1)
		files = "%s /etc/modutils/%s /etc/modutils/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename, basename)
		bb.data.setVar('FILES_%s' % pkg, files, d)

		if vals.has_key("description"):
			old_desc = bb.data.getVar('DESCRIPTION_' + pkg, d, 1) or ""
			bb.data.setVar('DESCRIPTION_' + pkg, old_desc + "; " + vals["description"], d)

		rdepends_str = bb.data.getVar('RDEPENDS_' + pkg, d, 1)
		if rdepends_str:
			rdepends = rdepends_str.split()
		else:
			rdepends = []
		rdepends.extend(get_dependencies(file, pattern, format))
		bb.data.setVar('RDEPENDS_' + pkg, ' '.join(rdepends), d)

	module_deps = parse_depmod()
	module_regex = '^(.*)\.k?o$'
	module_pattern = 'kernel-module-%s'

	postinst = bb.data.getVar('pkg_postinst_modules', d, 1)
	postrm = bb.data.getVar('pkg_postrm_modules', d, 1)
	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.bin$', output_pattern='kernel-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.fw$', output_pattern='kernel-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='update-modules kernel-%s' % bb.data.getVar("KERNEL_VERSION", d, 1))

	import re
	metapkg = "kernel-modules"
	bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
	bb.data.setVar('FILES_' + metapkg, "", d)
	blacklist = [ 'kernel-dev', 'kernel-image', 'kernel-base', 'kernel-vmlinux', 'perf', 'kernel-misc' ]
	for l in module_deps.values():
		for i in l:
			pkg = module_pattern % legitimize_package_name(re.match(module_regex, os.path.basename(i)).group(1))
			blacklist.append(pkg)
	metapkg_rdepends = []
	packages = bb.data.getVar('PACKAGES', d, 1).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rdepends:
			metapkg_rdepends.append(pkg)
	bb.data.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends), d)
	bb.data.setVar('DESCRIPTION_' + metapkg, 'Kernel modules meta package', d)
	packages.append(metapkg)
	bb.data.setVar('PACKAGES', ' '.join(packages), d)
}

# Support checking the kernel size since some kernels need to reside in partitions
# with a fixed length or there is a limit in transferring the kernel to memory
do_sizecheck() {
	if [ ! -z "${KERNEL_IMAGE_MAXSIZE}" ]; then
        	size=`ls -l arch/${ARCH}/boot/${KERNEL_IMAGETYPE} | awk '{ print $5}'`
        	if [ $size -ge ${KERNEL_IMAGE_MAXSIZE} ]; then
                	rm arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
                	die  "This kernel (size=$size > ${KERNEL_IMAGE_MAXSIZE}) is too big for your device. Please reduce the size of the kernel by making more of it modular."
        	fi
    	fi
}

addtask sizecheck before do_install after do_compile

do_uboot_mkimage() {
    if test "x${KERNEL_IMAGETYPE}" = "xuImage" ; then 
        ENTRYPOINT=${UBOOT_ENTRYPOINT}
        if test -n "${UBOOT_ENTRYSYMBOL}"; then
            ENTRYPOINT=`${HOST_PREFIX}nm ${S}/vmlinux | \
                   awk '$3=="${UBOOT_ENTRYSYMBOL}" {print $1}'`
        fi
        if test -e arch/${ARCH}/boot/compressed/vmlinux ; then
            ${OBJCOPY} -O binary -R .note -R .comment -S arch/${ARCH}/boot/compressed/vmlinux linux.bin
            uboot-mkimage -A ${UBOOT_ARCH} -O linux -T kernel -C none -a ${UBOOT_LOADADDRESS} -e $ENTRYPOINT -n "${DISTRO_NAME}/${PV}/${MACHINE}" -d linux.bin arch/${ARCH}/boot/uImage
            rm -f linux.bin
        else
            ${OBJCOPY} -O binary -R .note -R .comment -S vmlinux linux.bin
            rm -f linux.bin.gz
            gzip -9 linux.bin
            uboot-mkimage -A ${UBOOT_ARCH} -O linux -T kernel -C gzip -a ${UBOOT_LOADADDRESS} -e $ENTRYPOINT -n "${DISTRO_NAME}/${PV}/${MACHINE}" -d linux.bin.gz arch/${ARCH}/boot/uImage
            rm -f linux.bin.gz
        fi
    fi
}

addtask uboot_mkimage before do_install after do_compile

KERNEL_IMAGE_BASE_NAME ?= "${KERNEL_IMAGETYPE}-${PV}-${PR}-${MACHINE}-${DATETIME}"
# Don't include the DATETIME variable in the sstate package signatures
KERNEL_IMAGE_BASE_NAME[vardepsexclude] = "DATETIME"
KERNEL_IMAGE_SYMLINK_NAME ?= "${KERNEL_IMAGETYPE}-${MACHINE}"

kernel_do_deploy() {
	install -m 0644 arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${DEPLOYDIR}/${KERNEL_IMAGE_BASE_NAME}.bin
	if (grep -q -i -e '^CONFIG_MODULES=y$' .config); then
		tar -cvzf ${DEPLOYDIR}/modules-${KERNEL_VERSION}-${PR}-${MACHINE}.tgz -C ${D} lib
	fi

	cd ${DEPLOYDIR}
	rm -f ${KERNEL_IMAGE_SYMLINK_NAME}.bin
	ln -sf ${KERNEL_IMAGE_BASE_NAME}.bin ${KERNEL_IMAGE_SYMLINK_NAME}.bin
}
do_deploy[dirs] = "${DEPLOYDIR} ${B}"

addtask deploy before do_package after do_install

EXPORT_FUNCTIONS do_deploy

# perf must be enabled in individual kernel recipes
PACKAGES =+ "perf"
FILES_perf = "${bindir}/* \
              ${libexecdir}"
