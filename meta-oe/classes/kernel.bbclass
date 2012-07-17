inherit linux-kernel-base module_strip

PROVIDES += "virtual/kernel"
DEPENDS += "virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}depmod virtual/${TARGET_PREFIX}gcc${KERNEL_CCSUFFIX} update-modules"

# we include gcc above, we dont need virtual/libc
INHIBIT_DEFAULT_DEPS = "1"

KERNEL_IMAGETYPE ?= "zImage"
INITRAMFS_IMAGE ?= ""
INITRAMFS_TASK ?= ""

python __anonymous () {
    kerneltype = d.getVar('KERNEL_IMAGETYPE', True) or ''
    if kerneltype == 'uImage':
        depends = d.getVar("DEPENDS", True)
        depends = "%s u-boot-mkimage-native" % depends
        d.setVar("DEPENDS", depends)

    image = d.getVar('INITRAMFS_IMAGE', True)
    if image:
        d.setVar('INITRAMFS_TASK', '${INITRAMFS_IMAGE}:do_rootfs')

    machine_kernel_pr = d.getVar('MACHINE_KERNEL_PR', True)

    if machine_kernel_pr:
        d.setVar('PR', machine_kernel_pr)
}

inherit kernel-arch deploy

PACKAGES_DYNAMIC += "kernel-module-*"
PACKAGES_DYNAMIC += "kernel-image-*"
PACKAGES_DYNAMIC += "kernel-firmware-*"

export OS = "${TARGET_OS}"
export CROSS_COMPILE = "${TARGET_PREFIX}"

KERNEL_PRIORITY ?= "${@int(d.getVar('PV',1).split('-')[0].split('+')[0].split('.')[0]) * 10000 + \
                       int(d.getVar('PV',1).split('-')[0].split('+')[0].split('.')[1]) * 100 + \
                       int(d.getVar('PV',1).split('-')[0].split('+')[0].split('.')[-1])}"

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
KERNEL_LD = "${HOST_PREFIX}ld${KERNEL_LDSUFFIX} ${HOST_LD_KERNEL_ARCH}${TOOLCHAIN_OPTIONS}"

# Where built kernel lies in the kernel tree
KERNEL_OUTPUT ?= "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"
KERNEL_IMAGEDEST = "boot"

#
# configuration
#
export CMDLINE_CONSOLE = "console=${@d.getVar("KERNEL_CONSOLE",1) or "ttyS0"}"

KERNEL_VERSION = "${@get_kernelversion('${B}')}"

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

# Define where the kernel headers are installed on the target as well as where
# they are staged.
KERNEL_SRC_PATH = "/usr/src/kernel"

KERNEL_IMAGETYPE_FOR_MAKE = "${@(lambda s: s[:-3] if s[-3:] == ".gz" else s)(d.getVar('KERNEL_IMAGETYPE', True))}"

kernel_do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	oe_runmake include/linux/version.h CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	oe_runmake ${KERNEL_IMAGETYPE_FOR_MAKE} ${KERNEL_ALT_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	if test "${KERNEL_IMAGETYPE_FOR_MAKE}.gz" = "${KERNEL_IMAGETYPE}"; then
		gzip -9c < "${KERNEL_IMAGETYPE_FOR_MAKE}" > "${KERNEL_OUTPUT}"
	fi
}

do_compile_kernelmodules() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	if (grep -q -i -e '^CONFIG_MODULES=y$' .config); then
		oe_runmake ${PARALLEL_MAKE} modules CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	else
		bbnote "no modules to compile"
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
		rm -f "${D}/lib/modules/${KERNEL_VERSION}/modules.order"
		rm -f "${D}/lib/modules/${KERNEL_VERSION}/modules.builtin"
		rm "${D}/lib/modules/${KERNEL_VERSION}/build"
		rm "${D}/lib/modules/${KERNEL_VERSION}/source"
	else
		bbnote "no modules to install"
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
	install -d ${D}/etc/modules-load.d
	install -d ${D}/etc/modprobe.d

	#
	# Support for external module building - create a minimal copy of the
	# kernel source tree.
	#
	kerneldir=${D}${KERNEL_SRC_PATH}
	install -d $kerneldir

	#
	# Store the kernel version in sysroots for module-base.bbclass
	#

	echo "${KERNEL_VERSION}" > $kerneldir/kernel-abiversion

	#
	# Store kernel image name to allow use during image generation
	#

	echo "${KERNEL_IMAGE_BASE_NAME}" >$kerneldir/kernel-image-name

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
	if [ "${S}" != "${B}" ]; then
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
	oe_runmake -C $kerneldir CC="${KERNEL_CC}" LD="${KERNEL_LD}" clean
	make -C $kerneldir _mrproper_scripts
	find $kerneldir -path $kerneldir/lib -prune -o -path $kerneldir/tools -prune -o -path $kerneldir/scripts -prune -o -name "*.[csS]" -exec rm '{}' \;
	find $kerneldir/Documentation -name "*.txt" -exec rm '{}' \;

	# As of Linux kernel version 3.0.1, the clean target removes
	# arch/powerpc/lib/crtsavres.o which is present in
	# KBUILD_LDFLAGS_MODULE, making it required to build external modules.
	if [ ${ARCH} = "powerpc" ]; then
		cp arch/powerpc/lib/crtsavres.o $kerneldir/arch/powerpc/lib/crtsavres.o
	fi

	# Necessary for building modules like compat-wireless.
	cp include/generated/bounds.h $kerneldir/include/generated/bounds.h

	# Remove the following binaries which cause strip or arch QA errors
	# during do_package for cross-compiled platforms
	bin_files="arch/powerpc/boot/addnote arch/powerpc/boot/hack-coff \
	           arch/powerpc/boot/mktree scripts/kconfig/zconf.tab.o \
		   scripts/kconfig/conf.o scripts/kconfig/kxgettext.o"
	for entry in $bin_files; do
		rm -f $kerneldir/$entry
	done
}

sysroot_stage_all_append() {
	sysroot_stage_dir ${D}${KERNEL_SRC_PATH} ${SYSROOT_DESTDIR}${KERNEL_SRC_PATH}
}

kernel_do_configure() {
	# fixes extra + in /lib/modules/2.6.37+
	# $ scripts/setlocalversion . => +
	# $ make kernelversion => 2.6.37
	# $ make kernelrelease => 2.6.37+
	touch ${B}/.scmversion ${S}/.scmversion

	# Copy defconfig to .config if .config does not exist. This allows
	# recipes to manage the .config themselves in do_configure_prepend().
	if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${B}/.config" ]; then
		cp "${WORKDIR}/defconfig" "${B}/.config"
	fi
	yes '' | oe_runmake oldconfig

	if [ ! -z "${INITRAMFS_IMAGE}" ]; then
		for img in cpio.gz cpio.lzo cpio.lzma cpio.xz; do
		if [ -e "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}-${MACHINE}.$img" ]; then
			cp "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}-${MACHINE}.$img" initramfs.$img
		fi
		done
	fi
}

do_configure[depends] += "${INITRAMFS_TASK}"

do_savedefconfig() {
	oe_runmake savedefconfig
}
do_savedefconfig[nostamp] = "1"
addtask savedefconfig after do_configure

pkg_postinst_kernel-base () {
	cd /${KERNEL_IMAGEDEST}; update-alternatives --install /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ${KERNEL_PRIORITY} || true
}

pkg_postrm_kernel-base () {
	cd /${KERNEL_IMAGEDEST}; update-alternatives --remove ${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE}-${KERNEL_VERSION} || true
}

inherit cml1

EXPORT_FUNCTIONS do_compile do_install do_configure

# kernel-base becomes kernel-${KERNEL_VERSION}
# kernel-image becomes kernel-image-${KERNEL_VERISON}
PACKAGES = "kernel kernel-base kernel-vmlinux kernel-image kernel-dev"
FILES = ""
FILES_kernel-image = "/boot/${KERNEL_IMAGETYPE}*"
FILES_kernel-dev = "/boot/System.map* /boot/Module.symvers* /boot/config* ${KERNEL_SRC_PATH}"
FILES_kernel-vmlinux = "/boot/vmlinux*"
RDEPENDS_kernel = "kernel-base"
# Allow machines to override this dependency if kernel image files are 
# not wanted in images as standard
RDEPENDS_kernel-base ?= "kernel-image"
PKG_kernel-image = "kernel-image-${@legitimize_package_name('${KERNEL_VERSION}')}"
PKG_kernel-base = "kernel-${@legitimize_package_name('${KERNEL_VERSION}')}"
RPROVIDES_kernel-base += "kernel-${KERNEL_VERSION}"
ALLOW_EMPTY_kernel = "1"
ALLOW_EMPTY_kernel-base = "1"
ALLOW_EMPTY_kernel-image = "1"

pkg_postinst_kernel-image () {
if [ ! -e "$D/lib/modules/${KERNEL_VERSION}" ]; then
	mkdir -p $D/lib/modules/${KERNEL_VERSION}
fi
if [ -n "$D" ]; then
	${HOST_PREFIX}depmod -A -b $D -F ${STAGING_KERNEL_DIR}/System.map-${KERNEL_VERSION} ${KERNEL_VERSION}
else
	depmod -a ${KERNEL_VERSION}
fi
}

pkg_postinst_modules () {
if [ -z "$D" ]; then
	depmod -a ${KERNEL_VERSION}
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
		import tempfile, re, subprocess
		tempfile.tempdir = d.getVar("WORKDIR", True)
		tf = tempfile.mkstemp()
		tmpfile = tf[1]
		cmd = "PATH=\"%s\" %sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("PATH", True), d.getVar("HOST_PREFIX", True) or "", file, tmpfile)
		subprocess.call(cmd, shell=True)
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

		dvar = d.getVar('PKGD', True)
		if not dvar:
			bb.error("PKGD not defined")
			return

		kernelver = d.getVar('KERNEL_VERSION', True)
		kernelver_stripped = kernelver
		m = re.match('^(.*-hh.*)[\.\+].*$', kernelver)
		if m:
			kernelver_stripped = m.group(1)
		path = d.getVar("PATH", True)
		host_prefix = d.getVar("HOST_PREFIX", True) or ""

		cmd = "PATH=\"%s\" %sdepmod -n -a -r -b %s -F %s/boot/System.map-%s %s" % (path, host_prefix, dvar, dvar, kernelver, kernelver_stripped)
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
		file = file.replace(d.getVar('PKGD', True) or '', '', 1)
		# instead is prefixed with /lib/modules/${KERNEL_VERSION}
		file = file.replace("/lib/modules/%s/" % d.getVar('KERNEL_VERSION', True) or '', '', 1)

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

		dvar = d.getVar('PKGD', True)

		# If autoloading is requested, output /etc/modules-load.d/<name>.conf and append
		# appropriate modprobe commands to the postinst
		autoload = d.getVar('module_autoload_%s' % basename, True)
		if autoload:
			name = '%s/etc/modules-load.d/%s.conf' % (dvar, basename)
			f = open(name, 'w')
			for m in autoload.split():
				f.write('%s\n' % m)
			f.close()
			postinst = d.getVar('pkg_postinst_%s' % pkg, True)
			if not postinst:
				bb.fatal("pkg_postinst_%s not defined" % pkg)
			postinst += d.getVar('autoload_postinst_fragment', True) % autoload
			d.setVar('pkg_postinst_%s' % pkg, postinst)

		# Write out any modconf fragment
		modconf = d.getVar('module_conf_%s' % basename, True)
		if modconf:
			name = '%s/etc/modprobe.d/%s.conf' % (dvar, basename)
			f = open(name, 'w')
			f.write("%s\n" % modconf)
			f.close()

		files = d.getVar('FILES_%s' % pkg, True)
		files = "%s /etc/modules-load.d/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename)
		d.setVar('FILES_%s' % pkg, files)

		if vals.has_key("description"):
			old_desc = d.getVar('DESCRIPTION_' + pkg, True) or ""
			d.setVar('DESCRIPTION_' + pkg, old_desc + "; " + vals["description"])

		rdepends_str = d.getVar('RDEPENDS_' + pkg, True)
		if rdepends_str:
			rdepends = rdepends_str.split()
		else:
			rdepends = []
		rdepends.extend(get_dependencies(file, pattern, format))
		d.setVar('RDEPENDS_' + pkg, ' '.join(rdepends))

	module_deps = parse_depmod()
	module_regex = '^(.*)\.k?o$'
	module_pattern = 'kernel-module-%s'

	postinst = d.getVar('pkg_postinst_modules', True)
	postrm = d.getVar('pkg_postrm_modules', True)
	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.bin$', output_pattern='kernel-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.fw$', output_pattern='kernel-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.cis$', output_pattern='kernel-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='update-modules kernel-%s' % d.getVar("KERNEL_VERSION", True))

	# If modules-load.d and modprobe.d are empty at this point, remove them to
	# avoid warnings. removedirs only raises an OSError if an empty
	# directory cannot be removed.
	dvar = d.getVar('PKGD', True)
	for dir in ["%s/etc/modprobe.d" % (dvar), "%s/etc/modules-load.d" % (dvar), "%s/etc" % (dvar)]:
		if len(os.listdir(dir)) == 0:
			os.rmdir(dir)

	import re
	metapkg = "kernel-modules"
	d.setVar('ALLOW_EMPTY_' + metapkg, "1")
	d.setVar('FILES_' + metapkg, "")
	blacklist = [ 'kernel-dev', 'kernel-image', 'kernel-base', 'kernel-vmlinux' ]
	for l in module_deps.values():
		for i in l:
			pkg = module_pattern % legitimize_package_name(re.match(module_regex, os.path.basename(i)).group(1))
			blacklist.append(pkg)
	metapkg_rdepends = []
	packages = d.getVar('PACKAGES', True).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rdepends:
			metapkg_rdepends.append(pkg)
	d.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends))
	d.setVar('DESCRIPTION_' + metapkg, 'Kernel modules meta package')
	packages.append(metapkg)
	d.setVar('PACKAGES', ' '.join(packages))
}

# Support checking the kernel size since some kernels need to reside in partitions
# with a fixed length or there is a limit in transferring the kernel to memory
do_sizecheck() {
	if [ ! -z "${KERNEL_IMAGE_MAXSIZE}" ]; then
		size=`ls -l ${KERNEL_OUTPUT} | awk '{ print $5}'`
		if [ $size -ge ${KERNEL_IMAGE_MAXSIZE} ]; then
			rm ${KERNEL_OUTPUT}
			die "This kernel (size=$size > ${KERNEL_IMAGE_MAXSIZE}) is too big for your device. Please reduce the size of the kernel by making more of it modular."
		fi
	fi
}

addtask sizecheck before do_install after do_compile

KERNEL_IMAGE_BASE_NAME ?= "${KERNEL_IMAGETYPE}-${PV}-${PR}-${MACHINE}-${DATETIME}"
# Don't include the DATETIME variable in the sstate package signatures
KERNEL_IMAGE_BASE_NAME[vardepsexclude] = "DATETIME"
KERNEL_IMAGE_SYMLINK_NAME ?= "${KERNEL_IMAGETYPE}-${MACHINE}"

do_uboot_mkimage() {
	if test "x${KERNEL_IMAGETYPE}" = "xuImage" ; then 
		if test "x${KEEPUIMAGE}" = "x" ; then
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
	fi
}

addtask uboot_mkimage before do_install after do_compile

kernel_do_deploy() {
	install -m 0644 ${KERNEL_OUTPUT} ${DEPLOYDIR}/${KERNEL_IMAGE_BASE_NAME}.bin
	if (grep -q -i -e '^CONFIG_MODULES=y$' .config); then
		tar -cvzf ${DEPLOYDIR}/modules-${KERNEL_VERSION}-${PR}-${MACHINE}.tgz -C ${D} lib
	fi

	cd ${DEPLOYDIR}
	rm -f ${KERNEL_IMAGE_SYMLINK_NAME}.bin
	ln -sf ${KERNEL_IMAGE_BASE_NAME}.bin ${KERNEL_IMAGE_SYMLINK_NAME}.bin
	ln -sf ${KERNEL_IMAGE_BASE_NAME}.bin ${KERNEL_IMAGETYPE}

	cp ${COREBASE}/meta/files/deploydir_readme.txt ${DEPLOYDIR}/README_-_DO_NOT_DELETE_FILES_IN_THIS_DIRECTORY.txt
}
do_deploy[dirs] = "${DEPLOYDIR} ${B}"

addtask deploy before do_build after do_install

EXPORT_FUNCTIONS do_deploy

